package JAXBandStAX;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.EventFilter;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import JAXBandStAX.objectVerarbeitung;

/**
 * Klasse zum Parsen XML-Dateien (durch Kombination von JAXB mit StAX). Es wird
 * nicht das StartElement geparst, aber alle darunter angeordneten Elemente.
 */
public class XmlToJavaParserMitStaxUndJaxb {

	/**
	 * Hilfsmethode zum Parsen grosser XML-Dateien (durch Kombination von JAXB mit
	 * StAX). Es k�nnen alle Klassen geparst werden, die die
	 * XmlRootElement-Annotation haben.
	 *
	 * @param xsdDatei
	 *            Schema-XSD-Datei
	 * @param xmlDatei
	 *            XML-Datei
	 * @param encoding
	 *            Character-Encoding, z.B. UTF-8
	 * @param packageName
	 *            Package mit den zu lesenden Java-Klassen
	 * @param eoverab
	 *            Callback-Objekt fuer die Verarbeitung der einzelnen Elemente
	 * @return Anzahl der gefundenen Elemente
	 */
	public static int parseXml(String xsdDatei, String xmlDatei, String encoding, String packageName,
			objectVerarbeitung overab) throws Exception {
		if (xsdDatei != null && xsdDatei.trim().length() > 0) {
			try (Reader xml = new InputStreamReader(new FileInputStream(xmlDatei), encoding)) {
				//validate(xsdDatei, xml);
			}
		}
		try (Reader xml = new InputStreamReader(new FileInputStream(xmlDatei), encoding)) {
			return parseXml(xml, packageName, overab);
		}
	}

	/**
	 * Hilfsmethode zum Parsen grosser XML-Dateien (durch Kombination von JAXB mit
	 * StAX). Es koennen alle im angegebenen Package vorhandenen Klassen geparst
	 * werden, welche eine XmlRootElement-Annotation haben.
	 * 
	 * @param xml
	 *            Reader zur XML-Datei
	 * @param packageName
	 *            Package mit den zu lesenden Java-Klassen
	 * @param elemVerarb
	 *            Callback-Objekt fuer die Verarbeitung der einzelnen Elemente
	 * @return Anzahl der gefundenen Elemente
	 */
	public static int parseXml(Reader xml, String packageName, objectVerarbeitung overab)
			throws XMLStreamException, JAXBException {
		// StAX:
		EventFilter startElementFilter = new EventFilter() {
			@Override
			public boolean accept(XMLEvent event) {
				return event.isStartElement();
			}
		};
		int anzahlElem = 0;
		XMLInputFactory staxFactory = XMLInputFactory.newInstance();
		XMLEventReader staxReader = staxFactory.createXMLEventReader(xml);
		XMLEventReader staxFiltRd = staxFactory.createFilteredReader(staxReader, startElementFilter);
		// Ueberspringe StartElement:
		staxFiltRd.nextEvent();
		// JAXB:
		JAXBContext jaxbContext = JAXBContext.newInstance(packageName);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		// Parsing:
		while (staxFiltRd.peek() != null) {
			Object element = unmarshaller.unmarshal(staxReader);
			overab.verarbeite(element);
			anzahlElem++;
		}
		return anzahlElem;
	}

	/**
	 * Hilfsmethode zum Parsen grosser XML-Dateien (durch Kombination von JAXB mit
	 * StAX).
	 * Es kann nur die eine angegebene Klasse geparst werden (eine
	 * XmlRootElement-Annotation wird nicht benoetigt).<br>
	 * <b>Achtung:</b> Zum XML-Reader wird normalerweise kein close() aufgerufen,
	 * ausser siehe Bug: {@link "http://bugs.sun.com/view_bug.do?bug_id=6539065"}
	 *
	 * @param xml
	 *            Reader zur XML-Datei
	 * @param elementClass
	 *            Die zu parsende Java-Klasse
	 * @param elemVerarb
	 *            Callback-Objekt fuer die Verarbeitung der einzelnen Elemente
	 * @return Anzahl der gefundenen Elemente
	 */
	public static long parseXml(Reader xml, Class<?> elementClass, objectVerarbeitung overab)
			throws XMLStreamException, JAXBException {
		// StAX:
		EventFilter startElementFilter = new EventFilter() {
			@Override
			public boolean accept(XMLEvent event) {
				return event.isStartElement();
			}
		};
		long anzahlElem = 0;
		XMLInputFactory staxFactory = XMLInputFactory.newInstance();
		XMLEventReader staxReader = staxFactory.createXMLEventReader(xml);
		XMLEventReader staxFiltRd = staxFactory.createFilteredReader(staxReader, startElementFilter);
		// Ueberspringe StartElement:
		staxFiltRd.nextEvent();
		// JAXB:
		JAXBContext jaxbContext = JAXBContext.newInstance(elementClass);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		// Parsing:
		while (staxFiltRd.peek() != null) {
			Object element = unmarshaller.unmarshal(staxReader, elementClass);
			if (element instanceof JAXBElement && ((JAXBElement<?>) element).getValue() != null) {
				element = ((JAXBElement<?>) element).getValue();
			}
			overab.verarbeite(element);
			anzahlElem++;
		}
		return anzahlElem;
	}

	/**
	 * Validierung des XML-Readers gegen ein XSD-Schema. xsdSchema XSD-Schema
	 * @param xml
	 *            XML-Reader
	 */
	public static void validate(String xsdSchema, Reader xml) throws SAXException, IOException {

		final String w3cXmlSchemaNsUri = "http://www.w3.org/2001/XMLSchema";
		SchemaFactory schemaFactory = SchemaFactory.newInstance(w3cXmlSchemaNsUri);
		Schema schema = schemaFactory.newSchema(new File(xsdSchema));
		Validator validator = schema.newValidator();
		validator.validate(new StreamSource(xml));
	}
}
