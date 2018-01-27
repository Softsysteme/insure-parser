package parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import caches.InsureParserCacheManager;
import insure.infoservice.feldsteuerung.EingabeelementLiterals;
import insure.infoservice.feldsteuerung.EingabeelementeigenschaftLiterals;
import insure.infoservice.feldsteuerung.SteuerelementLiterals;
import insure.infoservice.feldsteuerung.SteuerelementeigenschaftLiterals;

public class DomParser {
    private List<Object> speicher;
    InsureParserCacheManager cm = InsureParserCacheManager.INSTANCE;

    public DomParser() {
        speicher = new ArrayList<Object>();
    }

    public void parseXml(String[] filePaths) {
        // Get Document Builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Build Document
        Document document = null;
        try {
            document = builder.parse(mergeFiles(resolvePaths(filePaths)));
        } catch (SAXException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Normalize the XML Structure; important !!
        document.getDocumentElement().normalize();

        // Here comes the root node
        Element root = document.getDocumentElement();

        // Get all Repositories
        NodeList nList = document.getElementsByTagName("repositories");
        visitChildNodes(nList);

    }

    // This function is called recursively
    private void visitChildNodes(NodeList nList) {
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                // Check all attributes
                if (node.getLocalName().contentEquals("enumerations")) {
                    if (node.hasAttributes()) {
                        // get attributes names and values
                        NamedNodeMap nodeMap = node.getAttributes();
                        if (nodeMap.getNamedItem("type") != null) {
                            Node nodeType = nodeMap.getNamedItem("type");
                            if (nodeType.getNodeValue().contentEquals("Eingabelement")) {
                                String nodeValue = nodeMap.getNamedItem("modelElementId").getNodeValue();
                                cm.putInCache(nodeValue, EingabeelementLiterals.valueOf(nodeValue));
                                speicher.add(EingabeelementLiterals.valueOf(nodeValue));
                            }

                            if (nodeType.getNodeValue().contentEquals("Steuerelement")) {
                                String nodeValue = nodeMap.getNamedItem("modelElementId").getNodeValue();
                                cm.putInCache(nodeValue, SteuerelementLiterals.valueOf(nodeValue));
                                speicher.add(SteuerelementLiterals.valueOf(nodeValue));
                            }

                            if (nodeType.getNodeValue().contentEquals("Steuerelementeigenschaft")) {
                                String nodeValue = nodeMap.getNamedItem("modelElementId").getNodeValue();
                                cm.putInCache(nodeValue, SteuerelementeigenschaftLiterals.valueOf(nodeValue));
                                speicher.add(SteuerelementeigenschaftLiterals.valueOf(nodeValue));
                            }

                            if (nodeType.getNodeValue().contentEquals("Eingabeelementeigenschaft")) {
                                String nodeValue = nodeMap.getNamedItem("modelElementId").getNodeValue();
                                cm.putInCache(nodeValue, EingabeelementeigenschaftLiterals.valueOf(nodeValue));
                                speicher.add(EingabeelementeigenschaftLiterals.valueOf(nodeValue));

                            }
                            if (node.hasChildNodes()) {
                                // We got more childs; Let's visit them as well
                                visitChildNodes(node.getChildNodes());

                            }
                        }
                    }
                }

                if (node.getLocalName().contentEquals("prototypes")) {
                    if (node.hasAttributes()) {

                    }

                }
            }
        }
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
