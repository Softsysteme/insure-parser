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
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.xml.bind.api.impl.NameConverter;

import caches.InsureParserCacheManager;
import insure.core.IEnumeration;
import tools.NameSpaceResolver;

public class DomParser {
    private List<Object> speicher;
    private NameSpaceResolver resolver;
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
        resolver = new NameSpaceResolver(document, false);

        // Here comes the root node
        Element root = document.getDocumentElement();

        // Get all Repositories
        NodeList nList = document.getElementsByTagName("repositories");
        try {
            visitChildNodes(nList);
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DOMException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // This function is called recursively
    @SuppressWarnings("rawtypes")
    private void visitChildNodes(NodeList nList) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, DOMException {
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                // Check all attributes
                if (node.getLocalName().contentEquals("enumerations")) {
                    if (node.hasAttributes()) {
                        // get attributes names and values
                        NamedNodeMap nodeMap = node.getAttributes();
                        if (nodeMap.getNamedItem("xsi:type") != null) {
                            Node nodeType = nodeMap.getNamedItem("xsi:type");
                            String literalName = nodeType.getNodeValue();
                            List<String> list = splitString(literalName);
                            String nodeValue = nodeMap.getNamedItem("modelElementId").getNodeValue();
                            saveEnum(convertToPackageName(resolver.getNamespaceURI((list.get(0)))), list.get(1), nodeValue);
                            if (node.hasChildNodes()) {
                                // We got more childs; Let's visit them as well
                                visitChildNodes(node.getChildNodes());

                            }
                        }
                    }
                }
                // work only with prototypes of type Feldsteuerung
                if (node.getLocalName().contentEquals("prototypes") && node.getFirstChild().getNodeValue().contains("Feldsteuerung")) {
                    Class<?> clazz = null;
                    Field[] fields = null;
                    List<Field> listFields = new ArrayList<Field>();
                    List<Field> mapFields = new ArrayList<Field>();
                    List<Field> otherFields = new ArrayList<Field>();
                    Object prototype = null;
                    String nodeValue = null;
                    if (node.hasAttributes()) {
                        // get attributes names and values
                        NamedNodeMap nodeMap = node.getAttributes();
                        if (nodeMap.getNamedItem("xsi:type") != null) {
                            Node nodeType = nodeMap.getNamedItem("xsi:type");
                            String literalName = nodeType.getNodeValue();
                            List<String> list = splitString(literalName);
                            nodeValue = nodeMap.getNamedItem("modelElementId").getNodeValue();
                            try {
                                clazz = Class.forName(buildFullyQualifiedName(convertToPackageName(resolver.getNamespaceURI((list.get(0)))), list.get(1)));
                                Constructor constructor = clazz.getDeclaredConstructor();
                                prototype = constructor.newInstance();
                                fields = clazz.getDeclaredFields();
                            } catch (ClassNotFoundException | InstantiationException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                            for (int i = 0; i < fields.length; i++) {
                                if (List.class.isAssignableFrom(fields[i].getType()) || Map.class.isAssignableFrom(fields[i].getType())) {
                                    if (List.class.isAssignableFrom(fields[i].getType())) {
                                        listFields.add(fields[i]);

                                    }

                                    if (Map.class.isAssignableFrom(fields[i].getType())) {
                                        mapFields.add(fields[i]);
                                    }
                                }

                                else {
                                    otherFields.add(fields[i]);
                                }
                            }
                            buildObjectWithAttributes(clazz, prototype, nodeMap);

                        }

                    }

                    if (node.hasChildNodes()) {
                        for (Field field : mapFields) {
                            Object map = null;
                            // only Element nodes getChildNodesWithName
                            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                                Node item = node.getChildNodes().item(i);
                                if (item.getNodeType() == 1) {
                                    if (field.getName().contentEquals(item.getLocalName())) {
                                        ParameterizedType pt = (ParameterizedType) field.getGenericType();
                                        Class<?>[] mapClasses = (Class<?>[]) pt.getActualTypeArguments();
                                        try {
                                            map = field.getType().newInstance();
                                            Method put = HashMap.class.getDeclaredMethod("put", mapClasses[0], mapClasses[1]);
                                            Object[] obj = buildObjectWithAttributes(mapClasses, item.getChildNodes(), item, field);
                                            put.invoke(map, obj[0], obj[1]);
                                            Method methode = findMethod(clazz, "set" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1, field.getName().length() + 1),
                                                field.getType());
                                            methode.invoke(prototype, map);
                                        } catch (InstantiationException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    }
                                }

                            }
                        }

                        // visitPropertyChildNode(clazz, item);
                        for (Field field : otherFields) {
                            Object element = null;
                            // only Element nodes getChildNodesWithName
                            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                                Node item = node.getChildNodes().item(i);
                                if (item.getNodeType() == 1) {
                                    if (field.getName().contentEquals(item.getLocalName())) {
                                        ParameterizedType pt = (ParameterizedType) field.getGenericType();
                                        Class<?> elementClazz = (Class<?>) pt.getActualTypeArguments()[0];
                                        try {
                                            element = field.getType().newInstance();
                                            buildObjectWithAttributes(elementClazz, element, item);

                                        } catch (InstantiationException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    }
                                }

                            }
                        }
                    }

                    cm.putInCache(nodeValue, prototype);
                }
            }
        }
    }

    // private Object visitPropertyChildNode(Class<?> clazz, Node item)
    // throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, DOMException {
    // Object list = null;
    // // List Elements
    // if (!((containsChild(item, "key")) && (containsChild(item, "value")))) {
    // Field ListField = clazz.getDeclaredField(item.getLocalName());
    // ParameterizedType ListType = (ParameterizedType) ListField.getGenericType();
    // Class<?> ListClass = (Class<?>) ListType.getActualTypeArguments()[0];
    // list = ListField.getType().newInstance();
    // Method add = List.class.getDeclaredMethod("add", ListClass);
    //
    // //add.invoke(list, buildObjectWithAttributes(ListClass, item.getAttributes(), item));
    // }
    //
    // return list;
    //
    // }

    // create a new Object and set the attributes of this object
    public void buildObjectWithAttributes(Class<?> clazz, Object obj, NamedNodeMap attrList)
            throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, DOMException {
        for (int i = 0; i < attrList.getLength(); i++) {
            String nodeN = attrList.item(i).getNodeName();
            if (!nodeN.contentEquals("xsi:type")) {
                Class<?> type = null;
                try {
                    type = clazz.getDeclaredField(nodeN).getType();
                } catch (NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Method methode = findMethod(clazz, "set" + Character.toUpperCase(nodeN.charAt(0)) + nodeN.substring(1, nodeN.length() + 1), type);
                if (type.isPrimitive()) {
                    methode.invoke(obj, attrList.item(i).getNodeValue());
                }

                else {
                    methode.invoke(obj, cm.retrieveFromCache(attrList.item(i).getNodeValue()));
                }
            }
        }

    }

    // create a new Object and set the attributes of this object
    public void buildObjectWithAttributes(Class<?> clazz, Object obj, Node node)
            throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, DOMException {

        Class<?> type = null;
        try {
            type = clazz.getDeclaredField(node.getLocalName()).getType();
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Method methode = findMethod(clazz, "set" + Character.toUpperCase(node.getLocalName().charAt(0)) + node.getLocalName().substring(1, node.getLocalName().length() + 1), type);
        if (type.isPrimitive()) {
            methode.invoke(obj, node.getNodeValue());
        }

        else {
            methode.invoke(obj, cm.retrieveFromCache(node.getNodeValue()));
        }
    }

    public Object[] buildObjectWithAttributes(Class<?>[] clazz, NodeList children, Node node, Field field)
            throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, DOMException {
        Object[] result = new Object[2];
        Object key = null;
        Object value = null;
        for (int i = 0; i < children.getLength(); i++) {

            if (!containsChild(children.item(i), "xsi:type")) {
                if (containsChild(children.item(i), "key")) {
                    key = cm.retrieveFromCache(children.item(i).getNodeValue());
                }

                if (containsChild(children.item(i), "value")) {
                    value = cm.retrieveFromCache(children.item(i).getNodeValue());
                }
            } else {
                if (children.item(i).hasAttributes()) {
                    NamedNodeMap att = children.item(i).getAttributes();
                    if (att.getNamedItem("key") != null) {
                        key = cm.retrieveFromCache(att.getNamedItem("key").getNodeValue());
                    }
                    if (att.getNamedItem("value") != null) {
                        value = cm.retrieveFromCache(att.getNamedItem("value").getNodeValue());
                    }
                }

                if (children.item(i).getNodeType() == 1) {
                    NamedNodeMap att = children.item(i).getAttributes();
                    if (children.item(i).getNodeName().contentEquals("key")) {
                        key = cm.retrieveFromCache(att.getNamedItem("key").getNodeValue());
                    }
                    if (children.item(i).getNodeName().contentEquals("value")) {
                        value = cm.retrieveFromCache(att.getNamedItem("value").getNodeValue());
                    }
                }
            }

        }
        result[0] = key;
        result[1] = value;

        return result;
    }

    public boolean containsChild(Node node, String nodeName) {
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            if (node.getChildNodes().item(i).getLocalName().contentEquals(nodeName))
                return true;
        }
        return false;
    }

    public List<Node> getChildNodesWithName(Node parent, String nodeName) {
        List<Node> result = new ArrayList<Node>();
        for (int i = 0; i < parent.getChildNodes().getLength(); i++) {
            Node item = parent.getChildNodes().item(i);
            if (item.getLocalName().contentEquals(nodeName) && (item.getNodeType() == 1))
                result.add(i, item);
        }
        return result;
    }

    public Method findMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {

        // First try the trivial approach. This works usually, but not always.
        try {
            return clazz.getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException ex) {
        }

        // Then loop through all available methods, checking them one by one.
        for (Method method : clazz.getMethods()) {

            String name = method.getName();
            if (!methodName.equals(name)) { // The method must have right name.
                continue;
            }

            Class<?>[] acceptedParameterTypes = method.getParameterTypes();
            if (acceptedParameterTypes.length != parameterTypes.length) { // Must have right number of parameters.
                continue;
            }

            boolean match = true;
            for (int i = 0; i < acceptedParameterTypes.length; i++) { // All parameters must be right type.
                if (null != parameterTypes[i] && !acceptedParameterTypes[i].isAssignableFrom(parameterTypes[i])) {
                    match = false;
                    break;
                }
                if (null == parameterTypes[i] && acceptedParameterTypes[i].isPrimitive()) { // Accept null except for primitive fields.
                    match = false;
                    break;
                }
            }

            if (match) {
                return method;
            }

        }

        // None of our trials was successful!
        throw new NoSuchMethodException();
    }

    public List<String> splitString(String s) {
        List<String> result = new ArrayList<String>();
        result.add(0, s.substring(0, s.indexOf(':')));
        result.add(1, s.substring(s.indexOf(':') + 1, s.length() + 1));
        return result;

    }

    public String buildFullyQualifiedName(String packageName, String className) {

        return packageName + "." + className;

    }

    @SuppressWarnings("unchecked")
    public void saveEnum(String packageName, String localName, String nodeValue) {
        IEnumeration enumeration;
        String name = buildFullyQualifiedName(packageName, localName);
        name += "Literals";
        @SuppressWarnings("rawtypes")
        Class cls = null;
        try {
            cls = Class.forName(name);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Method method1 = null;
        Method method2 = null;
        try {
            method1 = cls.getMethod("getInstance", new Class[0]);
            method2 = cls.getMethod("valueOf", new Class[0]);
        } catch (NoSuchMethodException | SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {

            method1.invoke(cls, new Object[0]);
            enumeration = (IEnumeration) method2.invoke(cls, nodeValue);
            cm.putInCache(nodeValue, enumeration);
            speicher.add(enumeration);

        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String convertToPackageName(String xmlNamespace) {
        NameConverter nameConverter = new NameConverter.Standard();
        return nameConverter.toPackageName(xmlNamespace);
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
