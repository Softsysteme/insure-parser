package parser;

import java.io.File;
import java.io.IOException;
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

import org.xml.sax.SAXException;

import adapters.SuperRoot;
import insure.core.IRepository;
import insure.core.IRootRepository;
import insure.core.impl.RootRepository;

/**
 * Klasse zum Parsen XML-Dateien (durch Kombination von JAXB mit StAX). Es wird nicht das StartElement geparst, aber alle darunter angeordneten Elemente.
 */
public class XmlParser {

    // Liste mit deserialisierte Objekten
    private static List<RootRepository> objects;

    public static List<RootRepository> getObjects() {
        return objects;
    }

    public static void setObjects(List<RootRepository> objects) {
        XmlParser.objects = objects;
    }

    public XmlParser() {
        // Liste muss dynamisch wachsen
        objects = new LinkedList<RootRepository>();
    }

    /**
     * Hilfsmethode zum Parsen grosser XML-Dateien (durch Kombination von JAXB mit StAX). Es kann nur die eine angegebene Klasse geparst werden (eine XmlRootElement-Annotation wird nicht benoetigt).
     * <br>
     * <b>Achtung:</b> Zum XML-Reader wird normalerweise kein close() aufgerufen.
     *
     * @param readrs[
     *            Reader zur XML-Datei
     * @param elementClass
     *            Die zu parsende Java-Klasse
     * @param elemVerarb
     *            Callback-Objekt fuer die Verarbeitung der einzelnen Elemente
     * @return Anzahl der gefundenen Elemente
     */
    public long parseXml(Reader reader, Class<?> elementClasses, objectVerarbeitung overab)
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
        XMLEventReader staxReader = staxFactory.createXMLEventReader(reader);
        XMLEventReader staxFiltRd = staxFactory.createFilteredReader(staxReader, startElementFilter);
        JAXBContext jaxbContext = JAXBContext.newInstance(elementClasses);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        // unmarshaller.setProperty(IDResolver.class.getName(), new IDResolverExtension());
        unmarshaller.setEventHandler(new DefaultValidationEventHandler());
        // staxFiltRd.nextEvent();
        // Parsing:
        Object element = unmarshaller.unmarshal(staxReader, elementClasses);

        if (element instanceof JAXBElement && ((JAXBElement<?>) element).getValue() != null) {
            element = ((JAXBElement<?>) element).getValue();
        }

        overab.verarbeite((SuperRoot) element);
        for (IRootRepository root : ((SuperRoot) element).getRootRepository()) {
            System.out.println(root.toString());
            for (IRepository repo : root.getRepositories()) {
                System.out.println(repo.toString());
                System.out.println(repo.getEnumerations().toString());
                System.out.println(repo.getPrototypes().toString());
                for (IRepository r : repo.getRepositories()) {
                    System.out.println(r.toString());
                    System.out.println(r.getEnumerations().toString());
                    System.out.println(r.getPrototypes().toString());

                    for (IRepository re : r.getRepositories()) {
                        System.out.println(re.toString());
                        System.out.println(re.getEnumerations().toString());
                        System.out.println(re.getPrototypes().toString());

                        for (IRepository y : re.getRepositories()) {
                            System.out.println(y.toString());
                            System.out.println(y.getEnumerations().toString());
                            System.out.println(y.getPrototypes().toString());
                        }
                    }
                }
            }

            objects.add((RootRepository) root);
        }
        return anzahlElem++;

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
