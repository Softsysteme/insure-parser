package parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.helpers.DefaultValidationEventHandler;
import javax.xml.stream.EventFilter;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.osgi.framework.wiring.BundleWiring;
import org.xml.sax.SAXException;

import insure.core.impl.Repository;
import insure.core.impl.RootRepository;
import insure.infoservice.feldsteuerung.impl.Eingabeelement;
import insure.infoservice.feldsteuerung.impl.Eingabeelementeigenschaft;
import insure.infoservice.feldsteuerung.impl.Feldelementeigenschaften;
import insure.infoservice.feldsteuerung.impl.Feldsteuerung;
import insure.infoservice.feldsteuerung.impl.FeldsteuerungIdentifier;
import insure.infoservice.feldsteuerung.impl.StandardFeldelementeigenschaften;
import insure.infoservice.feldsteuerung.impl.Steuerelement;
import insure.infoservice.feldsteuerung.impl.Steuerelementeigenschaft;
import insure.infoservice.feldsteuerung.impl.TemplateFeldelementeigenschaften;

/**
 * Klasse zum Parsen XML-Dateien (durch Kombination von JAXB mit StAX). Es wird nicht das StartElement geparst, aber alle darunter angeordneten Elemente.
 */
public class XmlParser {

    // Liste mit deserialisierte Objekten
    private static List<Object> objects;

    public XmlParser() {
        // Liste muss dynamisch wachsen
        objects = new LinkedList<Object>();
    }

    /**
     * Hilfsmethode zum Parsen grosser XML-Dateien (durch Kombination von JAXB mit StAX). Es k�nnen alle Klassen geparst werden, die die XmlRootElement-Annotation haben.
     *
     * @param xsdDatei
     *            Schema-XSD-Datei (Metamodel datei in XSD-Format)
     * @param xmlDatei
     *            XML-Datei (Instances- Model in xmi format)
     * @param encoding
     *            Character-Encoding, z.B. UTF-8
     * @param packageName
     *            Package wo alle zu lesende Klassen zu finden sind
     * @param eoverab
     *            Callback-Objekt fuer die Verarbeitung der einzelnen Elemente
     * @return Anzahl der gefundenen Elemente
     */
    public static int parseXml(String xsdDatei, String xmlDatei, String encoding, String packagebundleName, String packageName,
            objectVerarbeitung overab) throws Exception {
        if (xsdDatei != null && xsdDatei.trim().length() > 0) {
            try (Reader xml = new InputStreamReader(new FileInputStream(xmlDatei), encoding)) {
                // validate(xsdDatei, xml);
            }
        }
        try (Reader xml = new InputStreamReader(new FileInputStream(xmlDatei), encoding)) {
            return parseXml(xml, packagebundleName, packageName, overab);
        }
    }

    /**
     * Hilfsmethode zum Parsen grosser XML-Dateien (durch Kombination von JAXB mit StAX). Es koennen alle im angegebenen Package vorhandenen Klassen geparst werden, welche eine
     * XmlRootElement-Annotation haben.
     *
     * @param xml
     *            Reader zur XML-Datei
     * @param packageName
     *            Package mit den zu lesenden Java-Klassen
     * @param elemVerarb
     *            Callback-Objekt fuer die Verarbeitung der einzelnen Elemente
     * @return Anzahl der gefundenen Elemente
     */
    public static int parseXml(Reader xml, String packagebundleName, String packageName, objectVerarbeitung overab)
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
        Bundle packagebundle = Platform.getBundle(packagebundleName);
        BundleWiring bundleWiring = packagebundle.adapt(BundleWiring.class);
        XMLEventReader staxFiltRd = staxFactory.createFilteredReader(staxReader, startElementFilter);
        // Ueberspringe StartElement:
        // staxFiltRd.nextEvent();
        // staxFiltRd.nextEvent();
        // staxFiltRd.nextEvent();
        // staxFiltRd.nextEvent();
        // staxFiltRd.nextEvent();
        // staxFiltRd.nextEvent();
        // staxFiltRd.nextEvent();
        // staxFiltRd.nextEvent();
        // staxFiltRd.nextEvent();
        // staxFiltRd.nextEvent();
        // staxFiltRd.nextEvent();
        // staxFiltRd.nextEvent();
        // staxFiltRd.nextEvent();
        // staxFiltRd.nextEvent();
        // staxFiltRd.nextEvent();
        // staxFiltRd.nextEvent();
        // staxFiltRd.nextEvent();
        // staxFiltRd.nextEvent();
        // staxFiltRd.nextEvent();

        // JAXB:
        JAXBContext jaxbContext = JAXBContext.newInstance(RootRepository.class, Repository.class, Eingabeelement.class, Steuerelement.class, Eingabeelementeigenschaft.class,
            Steuerelementeigenschaft.class, StandardFeldelementeigenschaften.class, TemplateFeldelementeigenschaften.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setEventHandler(new DefaultValidationEventHandler());
        // Parsing:Repository.
        // unmarshaller.setEventHandler(
        // new ValidationEventHandler() {
        // public boolean handleEvent(ValidationEvent event) {
        // throw new RuntimeException(event.getMessage(),
        // event.getLinkedException());
        // }
        // });
        while (staxFiltRd.peek() != null) {

            Object element = unmarshaller.unmarshal(staxReader);
            if (element instanceof Repository) {

                overab.verarbeite((Repository) element);
                System.out.println(((Repository) element).getEnumerations().toString());
                objects.add((Repository) element);
                System.out.println(anzahlElem++);
            }
            if (element instanceof RootRepository) {

                overab.verarbeite((RootRepository) element);
                System.out.println(((RootRepository) element).getRepositories().toString());
                objects.add((RootRepository) element);
                System.out.println(anzahlElem++);
            }
            if (element instanceof Steuerelement) {

                overab.verarbeite((Steuerelement) element);
                objects.add((Steuerelement) element);
                System.out.println(anzahlElem++);
            }
            if (element instanceof Eingabeelement) {

                overab.verarbeite((Eingabeelement) element);
                objects.add((Eingabeelement) element);
                System.out.println(anzahlElem++);
            }

            if (element instanceof Eingabeelementeigenschaft) {

                overab.verarbeite((Eingabeelementeigenschaft) element);
                objects.add((Eingabeelementeigenschaft) element);
                System.out.println(anzahlElem++);
            }
            if (element instanceof Steuerelementeigenschaft) {

                overab.verarbeite((Steuerelementeigenschaft) element);
                objects.add((Steuerelementeigenschaft) element);
                System.out.println(anzahlElem++);
            }
            if (element instanceof Feldsteuerung) {

                overab.verarbeite((Feldsteuerung) element);
                objects.add((Feldsteuerung) element);
                System.out.println(anzahlElem++);
            }
            if (element instanceof Feldelementeigenschaften) {

                overab.verarbeite((Feldelementeigenschaften) element);
                objects.add((Feldelementeigenschaften) element);
                System.out.println(anzahlElem++);
            }
            if (element instanceof TemplateFeldelementeigenschaften) {

                overab.verarbeite((TemplateFeldelementeigenschaften) element);
                objects.add((TemplateFeldelementeigenschaften) element);
                System.out.println(anzahlElem++);
            }

            if (element instanceof StandardFeldelementeigenschaften) {

                overab.verarbeite((StandardFeldelementeigenschaften) element);
                objects.add((StandardFeldelementeigenschaften) element);
                System.out.println(anzahlElem++);
            }
            if (element instanceof FeldsteuerungIdentifier) {

                overab.verarbeite((FeldsteuerungIdentifier) element);
                objects.add((FeldsteuerungIdentifier) element);
                System.out.println(anzahlElem++);
            }

            // System.out.println(element.getEnumerations().toString());

            // staxFiltRd.nextEvent();
        }
        return anzahlElem;
    }

    /**
     * Hilfsmethode zum Parsen grosser XML-Dateien (durch Kombination von JAXB mit StAX). Es kann nur die eine angegebene Klasse geparst werden (eine XmlRootElement-Annotation wird nicht benoetigt).
     * <br>
     * <b>Achtung:</b> Zum XML-Reader wird normalerweise kein close() aufgerufen.
     *
     * @param xml
     *            Reader zur XML-Datei
     * @param elementClass
     *            Die zu parsende Java-Klasse
     * @param elemVerarb
     *            Callback-Objekt fuer die Verarbeitung der einzelnen Elemente
     * @return Anzahl der gefundenen Elemente
     */
    public long parseXml(Reader xml, Class<?>[] elementClasses, objectVerarbeitung overab)
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
        JAXBContext jaxbContext = JAXBContext.newInstance(elementClasses, null);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        // try {
        // unmarshaller.setAdapter(new RepositoryAdapter());
        // } catch (Exception e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // Parsing:
        while (staxFiltRd.peek() != null) {
            for (int i = 0; i < elementClasses.length; i++) {

                Object element = unmarshaller.unmarshal(staxReader, elementClasses[i]);
                if (element instanceof JAXBElement && ((JAXBElement<?>) element).getValue() != null) {
                    element = ((JAXBElement<?>) element).getValue();
                }
                overab.verarbeite(element);
                anzahlElem++;
            }
        }
        return anzahlElem;
    }

    /**
     * Validierung des XML-Readers gegen ein XSD-Schema. xsdSchema XSD-Schema
     *
     * @param xml
     *            XML-Reader
     */
    public void validate(String xsdSchema, Reader xml) throws SAXException, IOException {

        final String w3cXmlSchemaNsUri = "http://www.w3.org/2001/XMLSchema";
        SchemaFactory schemaFactory = SchemaFactory.newInstance(w3cXmlSchemaNsUri);
        Schema schema = schemaFactory.newSchema(new File(xsdSchema));
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(xml));
    }
}
