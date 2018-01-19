package parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
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

import insure.RootRepository;
import insure.SuperRoot;

/**
 * Klasse zum Parsen XML-Dateien (durch Kombination von JAXB mit StAX). Es wird nicht das StartElement geparst, aber alle darunter angeordneten Elemente.
 */
public class XmlParser {

    // Liste mit deserialisierte Objekten
    private static List<RootRepository> objects;

    public List<RootRepository> getObjects() {
        return objects;
    }

    public static void setObjects(List<RootRepository> objects) {
        XmlParser.objects = objects;
    }

    public XmlParser() {
        // Liste muss dynamisch wachsen
        objects = new LinkedList<RootRepository>();
    }

    public long parseXml(String[] xmlBundleNames, String[] filePaths, Class<?> elementClasses, objectVerarbeitung overab)
            throws XMLStreamException, JAXBException {
        // StAX:
        Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(mergeFiles(resolvePaths(filePaths))), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
        unmarshaller.setEventHandler(new DefaultValidationEventHandler());
        // staxFiltRd.nextEvent();
        // Parsing:
        Object element = unmarshaller.unmarshal(staxReader, elementClasses);

        if (element instanceof JAXBElement && ((JAXBElement<?>) element).getValue() != null) {
            element = ((JAXBElement<?>) element).getValue();
        }

        overab.verarbeite(element);
        for (RootRepository root : ((SuperRoot) element).getRootRepository()) {
            System.out.println(root.getRepositories());
            objects.add(root);
        }
        return anzahlElem++;

    }

    public File supressXsi(File inputXML, String outputPath) {

        BufferedReader br = null;
        String newString = "";
        StringBuilder strTotale = new StringBuilder();
        try {

            FileReader reader = new FileReader(inputXML);
            String search = "xsi:type";
            String search2 = "<steuerelementeigenschaften key=";
            String search3 = "<eingabeelementeigenschaften key=";

            br = new BufferedReader(reader);
            while ((newString = br.readLine()) != null) {
                newString = newString.replaceAll(search, "type");
                if (newString.contains("href=")) {
                    newString = newString.replace(newString.substring((newString.indexOf("=") + 2), newString.indexOf("#") + 1), "");
                }
                if (newString.contains(search2)) {
                    String key = newString.substring((newString.indexOf("=") + 1), newString.indexOf(">"));
                    newString = newString.replace(newString.substring((newString.indexOf("=") - 4), newString.length()), ">\n\t<key ref=" + key + "/>");
                }
                if (newString.contains(search3)) {
                    String key = newString.substring((newString.indexOf("=") + 1), newString.indexOf(">"));
                    newString = newString.replace(newString.substring((newString.indexOf("=") - 4), newString.length()), ">\n\t<key ref=" + key + "/>");
                }

                strTotale.append(newString + '\n');
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // calls it
        finally {
            try {
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        File outputFile = new File(outputPath);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write(strTotale.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (writer != null)
                try {
                    writer.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }

        return outputFile;
    }

    public File mergeFiles(File[] xmlFiles) {
        File outputFile = new File("/src/main/resources/model/merged-file.xml");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write("<?xml version=" + "\"1.0\" " + "encoding=" + "\"UTF-8\"" + "?>" + '\n');
            writer.write(("<SuperRoot>" + '\n'));
            for (int i = 0; i < xmlFiles.length; i++) {
                writer.write(createBuilder(xmlFiles[i], false).toString());
            }
            writer.write(("</SuperRoot>" + '\n'));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (writer != null)
                try {
                    writer.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }

        BufferedReader br = null;
        String newString = "";
        StringBuilder strTotale = new StringBuilder();
        try {

            FileReader reader = new FileReader(outputFile);
            br = new BufferedReader(reader);
            while ((newString = br.readLine()) != null) {
                strTotale.append(newString + '\n');
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // calls it
        finally {
            System.out.println(strTotale.toString());
            try {
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return outputFile;

    }

    public StringBuilder createBuilder(File xmlFile, boolean removeXmlTag) {
        BufferedReader br = null;
        String newString = "";
        StringBuilder strTotale = new StringBuilder();
        try {

            FileReader reader = new FileReader(xmlFile);
            br = new BufferedReader(reader);
            while ((newString = br.readLine()) != null) {
                if (newString.contains("?xml") && !removeXmlTag) {
                    strTotale.append("" + '\n');
                } else {
                    strTotale.append(newString + '\n');
                }
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // calls it
        finally {
            try {
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return strTotale;

    }

    /**
     * 
     * InputStream is = this.getClass().getResourceAsStream("/test.xml");
     * 
     * @param xmlPaths
     *            byte[] buffer = new byte[initialStream.available()]; initialStream.read(buffer);
     * 
     *            File targetFile = new File("src/main/resources/targetFile.tmp"); OutputStream outStream = new FileOutputStream(targetFile); outStream.write(buffer);
     */

    public File[] resolvePaths(String[] xmlPaths) {
        File[] xmlFiles = new File[xmlPaths.length];
        for (int i = 0; i < xmlPaths.length; i++) {
            InputStream is = this.getClass().getResourceAsStream(xmlPaths[i]);
            byte[] buffer = null;
            try {
                buffer = new byte[is.available()];
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                is.read(buffer);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            ;
            try {
                xmlFiles[i] = new File("src/main/resources/targetFile" + i);
                @SuppressWarnings("resource")
                OutputStream outStream = new FileOutputStream(xmlFiles[i]);
                outStream.write(buffer);
                xmlFiles[i] = supressXsi(xmlFiles[i], "/src/main/resources/model/modified" + i);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return xmlFiles;
    }

}
