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
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import insure.infoservice.feldsteuerung.Feldsteuerung;
import tools.NameSpaceResolver;
import tools.Uri2PackageNameConverter;

public class DomParser {
    private List<Object> speicher;

    private NameSpaceResolver resolver;
    InsureParserCacheManager cm = InsureParserCacheManager.INSTANCE;

    public DomParser() {
        speicher = new ArrayList<Object>();
    }

    public void parseXml(String filePath) {
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
            document = builder.parse(resolvePaths(filePath));
        } catch (SAXException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Normalize the XML Structure; important !!
        document.getDocumentElement().normalize();
        resolver = new NameSpaceResolver(document, true);

        // Here comes the root node
        Element root = document.getDocumentElement();

        // Get all Enumerations
        NodeList nListEnum = document.getElementsByTagName("enumerations");
        // Get all Prototypes
        NodeList nListPrototypes = document.getElementsByTagName("prototypes");
        try {
            visitChildNodes(nListEnum);
            visitChildNodes(nListPrototypes);
            // visitChildNodes(nListPrototypes);
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | DOMException e) {
        }

    }

    // This function is called recursively
    @SuppressWarnings("rawtypes")
    private void visitChildNodes(NodeList nList) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, DOMException {
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                // Check all attributes
                if (node.getNodeName().contentEquals("enumerations")) {
                    if (node.hasAttributes()) {
                        // get attributes names and values
                        NamedNodeMap nodeMap = node.getAttributes();
                        if (nodeMap.getNamedItem("xsi:type") != null) {
                            Node nodeType = nodeMap.getNamedItem("xsi:type");
                            String literalName = nodeType.getNodeValue();
                            List<String> list = splitString(literalName);
                            String nodeValue = nodeMap.getNamedItem("modelElementId").getNodeValue();
                            String literalsName = nodeMap.getNamedItem("name").getNodeValue();
                            saveEnum(convertToPackageName(resolver.getNamespaceURI((list.get(0)))), list.get(1), literalsName, nodeValue);
                        }
                    }
                    if (node.hasChildNodes()) {
                        // We got more childs; Let's visit them as well
                        visitChildNodes(node.getChildNodes());

                    }

                }
                // work only with prototypes of type Feldsteuerung
                if (node.getNodeName().contentEquals("prototypes") && node.getAttributes().getNamedItem("xsi:type").getNodeValue().contains("Feldsteuerung")) {
                    Class<?> clazz = null;
                    List<Field> fields = null;
                    List<Field> listFields = new ArrayList<Field>();
                    List<Field> mapFields = new ArrayList<Field>();
                    List<Field> otherFields = new ArrayList<Field>();
                    Object prototype = null;
                    String nodeValue = null;
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
                            fields = getallDeclaredFields(clazz);

                        } catch (ClassNotFoundException | InstantiationException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        Iterator iter = fields.iterator();
                        while (iter.hasNext()) {

                            Field field = (Field) (iter.next());
                            Class<?> type = field.getType();
                            if (List.class.isAssignableFrom(type) || Map.class.isAssignableFrom(type)) {
                                if (List.class.isAssignableFrom(type)) {
                                    listFields.add(field);

                                }

                                if (Map.class.isAssignableFrom(type)) {
                                    mapFields.add(field);
                                }
                            }

                            else {
                                otherFields.add(field);
                            }
                        }
                        if (node.hasAttributes()) {
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
                                    if (field.getName().contentEquals(item.getNodeName())) {
                                        ParameterizedType pt = (ParameterizedType) field.getGenericType();
                                        Type[] mapTypes = pt.getActualTypeArguments();
                                        Class<?>[] mapClasses = new Class<?>[mapTypes.length];
                                        for (int k = 0; k < mapClasses.length; k++) {
                                            mapClasses[k] = mapTypes[k].getClass();
                                        }
                                        map = new HashMap<Object, Object>();
                                        Method put = Map.class.getDeclaredMethod("put", Object.class, Object.class);
                                        Object[] obj = buildObjectWithAttributes(mapClasses, item.getChildNodes(), item, field);
                                        System.out.println(obj[0] + "," + obj[1]);
                                        Method methode = findMethod(clazz, "set" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1, field.getName().length()),
                                            field.getType());
                                        methode.invoke(prototype, map);
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
                                    if (field.getName().contentEquals(item.getNodeName())) {
                                        System.out.println("tttttttttttttttttttttt");
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
                    speicher.add(prototype);
                    if (!(prototype instanceof Feldsteuerung))
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
                    continue;
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
            type = clazz.getDeclaredField(node.getNodeName()).getType();
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Method methode = findMethod(clazz, "set" + Character.toUpperCase(node.getNodeName().charAt(0)) + node.getNodeName().substring(1, node.getNodeName().length() + 1), type);
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
        NamedNodeMap attList = node.getAttributes();
        if (attList != null) {
            if (attList.getNamedItem("value") != null) {
                value = cm.retrieveFromCache(attList.getNamedItem("value").getNodeValue());

            }

            if (attList.getNamedItem("key") != null) {
                key = cm.retrieveFromCache(attList.getNamedItem("key").getNodeValue());
            }
        }
        for (int i = 0; i < children.getLength(); i++) {
            Node item = children.item(i);
            if (item.getNodeType() == 1) {
                if (item.getNodeName().contentEquals("key")) {
                    Node att = item.getAttributes().getNamedItem("href");
                    if (att != null) {
                        key = cm.retrieveFromCache(att.getNodeValue());
                    }
                }

                if (item.getNodeName().contentEquals("value")) {
                    Node att = item.getAttributes().getNamedItem("href");
                    if (att != null) {
                        value = cm.retrieveFromCache(att.getNodeValue());
                    }
                }
            }

        }
        result[0] = key;
        result[1] = value;

        return result;
    }

    public List<Object> getSpeicher() {
        return speicher;
    }

    public void setSpeicher(List<Object> speicher) {
        this.speicher = speicher;
    }

    public boolean containsChild(Node node, String nodeName) {
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            if (node.getChildNodes().item(i).getNodeName().contentEquals(nodeName))
                return true;
        }
        return false;
    }

    public List<Node> getChildNodesWithName(Node parent, String nodeName) {
        List<Node> result = new ArrayList<Node>();
        for (int i = 0; i < parent.getChildNodes().getLength(); i++) {
            Node item = parent.getChildNodes().item(i);
            if (item.getNodeName().contentEquals(nodeName) && (item.getNodeType() == 1))
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
        result.add(1, s.substring(s.indexOf(':') + 1, s.length()));
        return result;
    }

    public String buildFullyQualifiedName(String packageName, String className) {
        return packageName + "." + className;

    }

    @SuppressWarnings("unchecked")
    public void saveEnum(String packageName, String localName, String literalsName, String nodeValue) {
        if (packageName.contains("entity.tickets")) {
            packageName = packageName.replace(".entity", "");
        }
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
            method1 = cls.getMethod("getInstance");
            method2 = cls.getMethod("valueOf", String.class);
        } catch (NoSuchMethodException | SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {

            Object obj = method1.invoke(cls);
            enumeration = (IEnumeration) method2.invoke(obj, literalsName);
            if (enumeration != null) {
                cm.putInCache(nodeValue, enumeration);
                speicher.add(enumeration);
            }

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
        NameConverter nameConverter = new Uri2PackageNameConverter();
        return nameConverter.toPackageName(xmlNamespace);
    }

    public File supressHref(File inputXML, String outputPath) {

        BufferedReader br = null;
        String newString = "";
        StringBuilder strTotale = new StringBuilder();
        try {

            FileReader reader = new FileReader(inputXML);
            // String search = "href";
            br = new BufferedReader(reader);
            while ((newString = br.readLine()) != null) {
                if (newString.contains("href=")) {
                    newString = newString.replace(newString.substring((newString.indexOf("=") + 2), newString.indexOf("#") + 1), "");
                    // newString = newString.replaceAll(search, "");
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

    public File resolvePaths(String xmlPath) {
        File xmlFile = null;
        InputStream is = this.getClass().getResourceAsStream(xmlPath);
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
            xmlFile = new File("src/main/resources/targetFile");
            @SuppressWarnings("resource")
            OutputStream outStream = new FileOutputStream(xmlFile);
            outStream.write(buffer);
            xmlFile = supressHref(xmlFile, "/src/main/resources/model/modified");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return xmlFile;
    }

    public List<Field> getallDeclaredFields(Class<?> clazz) {
        List<Field> flds = new ArrayList<Field>();
        while (clazz.getSuperclass() != null) {
            for (Field f : clazz.getDeclaredFields()) {
                flds.add(f);
            }
            clazz = clazz.getSuperclass();
        }
        return flds;
    }

}
