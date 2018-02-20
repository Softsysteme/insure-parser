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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import tools.Uri2PackageNameConverter;

public class DomParser {
    private List<Object> speicher;
    private static transient Object prototype;
    final static Logger logger = LoggerFactory.getLogger(DomParser.class);
    private int i = 0;

    private NameSpaceResolver resolver;
    InsureParserCacheManager cm = InsureParserCacheManager.INSTANCE;

    // constructor
    public DomParser() {
        speicher = new ArrayList<Object>();
    }

    public void parseXml(String filePath) {
        i++;
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
            document = builder.parse(copyFile(filePath, i));
        } catch (SAXException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Normalize the XML Structure; important !!
        document.getDocumentElement().normalize();
        resolver = new NameSpaceResolver(document, false);

        // Here comes the root node
        Element root = document.getDocumentElement();
        // Get the root element
        NodeList nListRoot = document.getElementsByTagName("core");
        // Get all Enumerations
        NodeList nListEnum = document.getElementsByTagName("enumerations");
        // Get all Prototypes
        NodeList nListPrototypes = document.getElementsByTagName("prototypes");
        // Get all functions
        NodeList nListFunctions = document.getElementsByTagName("functions");
        try {
            System.out.println(i);
            parseEnums(nListEnum);
            parseFunction(nListFunctions);
            parsePrototypes(nListPrototypes);
        } catch (IllegalArgumentException | DOMException e) {
        }

    }

    // parse Enums
    public void parseEnums(NodeList nList) {
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
                            String nodeName = nodeType.getNodeValue();
                            List<String> list = splitString(nodeName);
                            String nodeValue = nodeMap.getNamedItem("modelElementId").getNodeValue();
                            String literalsName = nodeMap.getNamedItem("name").getNodeValue();
                            if (nodeName.contains("Identifier")) {
                                saveIdentifier(convertToPackageName(resolver.getNamespaceURI((list.get(0)))), list.get(1), nodeMap.getNamedItem("key").getNodeValue(), nodeValue);
                            } else {
                                saveEnum(convertToPackageName(resolver.getNamespaceURI((list.get(0)))), list.get(1), literalsName, null, nodeValue);
                            }
                        }
                    }

                }
            }
        }
    }

    // parse Prototypes
    public void parsePrototypes(NodeList nList) {
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            String nodeValue = node.getAttributes().getNamedItem("modelElementId").getNodeValue();
            // works only with prototypes of type Feldsteuerung
            if (node.getNodeName().contentEquals("prototypes") && node.getAttributes().getNamedItem("xsi:type").getNodeValue().contains("feldsteuerung")) {
                visitnode(node, prototype);
                speicher.add(prototype);
                cm.putInCache(nodeValue, prototype);
            }
        }
    }

    // Functions parser
    public void parseFunction(NodeList nList) {
        Object function = null;
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            String nodeValue = node.getAttributes().getNamedItem("modelElementId").getNodeValue();
            // work only with prototypes of type Feldsteuerung
            if (node.getNodeName().contentEquals("functions") && node.getAttributes().getNamedItem("xsi:type").getNodeValue().contains("feldsteuerung")) {
                function = visitnode(node);
                speicher.add(function);
                cm.putInCache(nodeValue, function);
            }
        }
    }

    public Object visitnode(Node node) {
        Class<?> clazz = null;
        List<Field> fields = null;
        List<Field> listFields = new ArrayList<Field>();
        List<Field> mapFields = new ArrayList<Field>();
        List<Field> otherFields = new ArrayList<Field>();
        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        Object object = null;
        if (nodeMap.getNamedItem("xsi:type") != null) {
            Node nodeType = nodeMap.getNamedItem("xsi:type");
            String functionName = nodeType.getNodeValue();
            List<String> list = splitString(functionName);
            try {
                clazz = Class.forName(buildFullyQualifiedName(convertToPackageName(resolver.getNamespaceURI((list.get(0)))), list.get(1)));
                Constructor<?> constructor = clazz.getDeclaredConstructor();
                object = constructor.newInstance();
                fields = getAllDeclaredFields(clazz);

            } catch (ClassNotFoundException | InstantiationException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                // TODO Auto-generated catch block
            }
            Iterator<Field> iter = fields.iterator();
            while (iter.hasNext()) {

                Field field = (iter.next());
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
                try {
                    setObjectFields(clazz, object, nodeMap);
                } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | DOMException e) {
                    // TODO Auto-generated catch block
                }

            }

        }

        if (node.hasChildNodes()) {
            for (Field field : mapFields) {
                Map<Object, Object> map = new HashMap<Object, Object>();
                // only Element nodes getChildNodesWithName
                for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                    Node item = node.getChildNodes().item(i);
                    if (item.getNodeType() == 1) {
                        if (field.getName().contentEquals(item.getNodeName())) {
                            ParameterizedType pt = (ParameterizedType) field.getGenericType();
                            Type[] mapTypes = pt.getActualTypeArguments();
                            Class<?>[] mapClasses = new Class<?>[mapTypes.length];
                            for (int k = 0; k < mapClasses.length; k++) {
                                try {
                                    mapClasses[k] = Class.forName(mapTypes[k].getTypeName());
                                } catch (ClassNotFoundException e) {
                                    // TODO Auto-generated catch block
                                }
                            }
                            Method put = null;
                            try {
                                put = HashMap.class.getDeclaredMethod("put", Object.class, Object.class);
                            } catch (NoSuchMethodException | SecurityException e1) {
                                // TODO Auto-generated catch block
                            }
                            try {
                                Object[] obj = buildAndSetMap(mapClasses, item.getChildNodes(), item, field);
                                put.invoke(map, obj[0], obj[1]);
                            } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | DOMException e) {
                                // TODO Auto-generated catch block
                            }
                            Method methode = null;
                            try {
                                methode = findMethod(clazz, "set" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1, field.getName().length()),
                                    field.getType());
                            } catch (NoSuchMethodException e) {
                                // TODO Auto-generated catch block
                            }
                            try {
                                methode.invoke(object, map);
                            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                                // TODO Auto-generated catch block
                            }
                        }
                    }

                }
            }

            for (Field field : otherFields) {
                for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                    Node item = node.getChildNodes().item(i);
                    if (item.getNodeType() == 1) {
                        if (field.getName().contentEquals(item.getNodeName())) {
                            try {
                                setRefObject(clazz, field, object, item);
                            } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | DOMException e) {
                                // TODO Auto-generated catch block
                            }
                        }
                    }

                }
            }
        }
        return object;
    }

    public void visitnode(Node node, Object object) {
        Class<?> clazz = null;
        List<Field> fields = null;
        List<Field> listFields = new ArrayList<Field>();
        List<Field> mapFields = new ArrayList<Field>();
        List<Field> otherFields = new ArrayList<Field>();
        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        if (nodeMap.getNamedItem("xsi:type") != null) {
            Node nodeType = nodeMap.getNamedItem("xsi:type");
            String functionName = nodeType.getNodeValue();
            List<String> list = splitString(functionName);
            try {
                clazz = Class.forName(buildFullyQualifiedName(convertToPackageName(resolver.getNamespaceURI((list.get(0)))), list.get(1)));
                Constructor<?> constructor = clazz.getDeclaredConstructor();
                object = constructor.newInstance();
                fields = getAllDeclaredFields(clazz);

            } catch (ClassNotFoundException | InstantiationException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                // TODO Auto-generated catch block
            }
            Iterator<Field> iter = fields.iterator();
            while (iter.hasNext()) {

                Field field = (iter.next());
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
                try {
                    setObjectFields(clazz, object, nodeMap);
                } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | DOMException e) {
                    // TODO Auto-generated catch block
                }

            }

        }

        if (node.hasChildNodes()) {
            for (Field field : mapFields) {
                Map<Object, Object> map = new HashMap<Object, Object>();
                // only Element nodes getChildNodesWithName
                for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                    Node item = node.getChildNodes().item(i);
                    if (item.getNodeType() == 1) {
                        if (field.getName().contentEquals(item.getNodeName())) {
                            ParameterizedType pt = (ParameterizedType) field.getGenericType();
                            Type[] mapTypes = pt.getActualTypeArguments();
                            Class<?>[] mapClasses = new Class<?>[mapTypes.length];
                            for (int k = 0; k < mapClasses.length; k++) {
                                try {
                                    mapClasses[k] = Class.forName(mapTypes[k].getTypeName());
                                } catch (ClassNotFoundException e) {
                                    // TODO Auto-generated catch block
                                }
                            }
                            Method put = null;
                            try {
                                put = HashMap.class.getDeclaredMethod("put", Object.class, Object.class);
                            } catch (NoSuchMethodException | SecurityException e1) {
                                // TODO Auto-generated catch block
                            }
                            try {
                                Object[] obj = buildAndSetMap(mapClasses, item.getChildNodes(), item, field);
                                put.invoke(map, obj[0], obj[1]);
                            } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | DOMException e) {
                                // TODO Auto-generated catch block
                            }
                            Method methode = null;
                            try {
                                methode = findMethod(clazz, "set" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1, field.getName().length()),
                                    field.getType());
                            } catch (NoSuchMethodException e) {
                                // TODO Auto-generated catch block
                            }
                            try {
                                methode.invoke(object, map);
                            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                                // TODO Auto-generated catch block
                            }
                        }
                    }

                }
            }

            for (Field field : otherFields) {
                for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                    Node item = node.getChildNodes().item(i);
                    if (item.getNodeType() == 1) {
                        if (field.getName().contentEquals(item.getNodeName())) {
                            try {
                                setRefObject(clazz, field, object, item);
                            } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | DOMException e) {
                                // TODO Auto-generated catch block
                            }
                        }
                    }

                }
            }
        }
    }

    // set the object fiels according to the corresponding attributes contained in @attrList
    public void setObjectFields(Class<?> clazz, Object obj, NamedNodeMap attrList)
            throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, DOMException {
        for (int i = 0; i < attrList.getLength(); i++) {
            Node item = attrList.item(i);
            String nodeN = item.getNodeName();
            Type type = (attributCorrespondsToField(item, getAllDeclaredFields(clazz)));
            if (type != null) {
                Class<?> cl = null;

                try {
                    cl = Class.forName(type.getTypeName());
                } catch (ClassNotFoundException e) {

                }

                Method methode = findMethod(clazz, "set" + Character.toUpperCase(nodeN.charAt(0)) + nodeN.substring(1, nodeN.length()), cl);
                if (cl.isPrimitive()) {
                    methode.invoke(obj, item.getNodeValue());
                }

                else {
                    methode.invoke(obj, cm.retrieveFromCache(item.getNodeValue()));
                }
            }
        }
    }

    // set a reference Object to the current object
    public void setRefObject(Class<?> clazz, Field field, Object obj, Node node)
            throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, DOMException {
        Class<?> type = field.getType();
        Method methode = findMethod(clazz, "set" + Character.toUpperCase(node.getNodeName().charAt(0)) + node.getNodeName().substring(1, node.getNodeName().length()), type);
        if (node.hasChildNodes()) {
            Object object = visitnode(node);
            methode.invoke(obj, object);
        }

        else {

            if (type.isPrimitive()) {
                methode.invoke(obj, node.getNodeValue());
            }

            else {
                methode.invoke(obj, cm.retrieveFromCache(node.getAttributes().getNamedItem("href").getNodeValue()));
            }
        }
    }

    // construct and set the Map to the Object
    public Object[] buildAndSetMap(Class<?>[] clazz, NodeList children, Node node, Field field)
            throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, DOMException {
        Object[] result = new Object[2];
        Object key = null;
        Object value = null;
        NamedNodeMap attList = node.getAttributes();
        if (attList != null) {
            Node vnamedItem = attList.getNamedItem("value");
            if (vnamedItem != null) {
                value = cm.retrieveFromCache(vnamedItem.getNodeValue());
            }

            Node knamedItem = attList.getNamedItem("key");
            if (knamedItem != null) {
                key = cm.retrieveFromCache(knamedItem.getNodeValue());
            }
        }
        for (int i = 0; i < children.getLength(); i++) {
            Node item = children.item(i);

            if (item.getNodeType() == 1) {
                NamedNodeMap attributes = item.getAttributes();
                if (item.getNodeName().contentEquals("key")) {
                    Node att = attributes.getNamedItem("href");
                    if (att != null) {
                        key = cm.retrieveFromCache(att.getNodeValue());

                    } else {
                        key = cm.retrieveFromCache(attributes.getNamedItem("xsi:type").getNodeValue());
                    }
                }

                if (item.getNodeName().contentEquals("value")) {
                    Node att = attributes.getNamedItem("href");
                    if (att != null) {
                        value = cm.retrieveFromCache(att.getNodeValue());
                    } else {
                        value = cm.retrieveFromCache(attributes.item(0).getNodeValue());
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

    // returns true when the node contains a child wihth the given name
    public boolean containsChild(Node node, String nodeName) {
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            if (node.getChildNodes().item(i).getNodeName().contentEquals(nodeName))
                return true;
        }
        return false;
    }

    // return a list containing the childnodes with the given name
    public List<Node> getChildNodesWithName(Node parent, String nodeName) {
        List<Node> result = new ArrayList<Node>();
        for (int i = 0; i < parent.getChildNodes().getLength(); i++) {
            Node item = parent.getChildNodes().item(i);
            if (item.getNodeName().contentEquals(nodeName) && (item.getNodeType() == 1))
                result.add(i, item);
        }
        return result;
    }

    // search for a method declared in a class and return it
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

    // return a list containing both elements prefix and name space
    public List<String> splitString(String s) {
        List<String> result = new ArrayList<String>();
        result.add(0, s.substring(0, s.indexOf(':')));
        result.add(1, s.substring(s.indexOf(':') + 1, s.length()));
        return result;
    }

    public String buildFullyQualifiedName(String packageName, String className) {
        return packageName + "." + className;

    }

    // constructs and save an identifier in the cache and in the Memory
    public void saveIdentifier(String packageName, String localName, String literalsKey, String nodeValue) {
        if (packageName.contains("entity.tickets")) {
            packageName = packageName.replace(".entity", "");
        }
        @SuppressWarnings("unused")
        IEnumeration identifier = null;
        String name = buildFullyQualifiedName(packageName, localName);
        Class<?> cls = null;
        try {
            cls = Class.forName(name);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Method method1 = null;
        try {
            method1 = cls.getMethod("fromKey", int.class);
        } catch (NoSuchMethodException | SecurityException e) {
            // TODO Auto-generated catch block

        }
        try {
            identifier = (IEnumeration) method1.invoke(cls, Integer.parseInt(literalsKey));
            System.out.println(identifier + ":" + Integer.parseInt(literalsKey));
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            // TODO Auto-generated catch block

        }

        if (identifier != null) {
            cm.putInCache(nodeValue, identifier);
            speicher.add(identifier);
        }

    }

    // constructs and save the simple enum in the cache and in the Memory
    @SuppressWarnings("unchecked")
    public void saveEnum(String packageName, String localName, String literalsName, String literalsKey, String nodeValue) {
        if (packageName.contains("entity.tickets")) {
            packageName = packageName.replace(".entity", "");
        }
        IEnumeration enumeration = null;
        String name = buildFullyQualifiedName(packageName, localName);
        name += "Literals";
        @SuppressWarnings("rawtypes")
        Class cls = null;
        try {
            cls = Class.forName(name);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
        }
        Method method1 = null;
        Method method2 = null;
        try {
            method1 = cls.getMethod("getInstance");
            method2 = cls.getMethod("valueOf", String.class);
        } catch (NoSuchMethodException | SecurityException e) {
            // TODO Auto-generated catch block
        }

        Object obj = null;
        try {
            obj = method1.invoke(cls);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            // TODO Auto-generated catch block
        }
        try {
            enumeration = (IEnumeration) method2.invoke(obj, literalsName);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            // TODO Auto-generated catch block
        }
        if (enumeration != null) {
            cm.putInCache(nodeValue, enumeration);
            speicher.add(enumeration);
        }

    }

    // convert the namespace to a valid package name
    public String convertToPackageName(String xmlNamespace) {
        NameConverter nameConverter = new Uri2PackageNameConverter();
        return nameConverter.toPackageName(xmlNamespace);
    }

    // suppress the unwanted expressions after the attribute href leaving only the ID
    public File correctHref(File inputXML, String outputPath) {

        BufferedReader br = null;
        String newString = "";
        StringBuilder strTotale = new StringBuilder();
        try {

            FileReader reader = new FileReader(inputXML);
            br = new BufferedReader(reader);
            while ((newString = br.readLine()) != null) {
                if (newString.contains("href=")) {
                    newString = newString.replace(newString.substring((newString.indexOf("=") + 2), newString.indexOf("#") + 1), "");
                }
                strTotale.append(newString + '\n');
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
        } // calls it
        finally {
            try {
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
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
                }
        }

        return outputFile;
    }

    // copy the given file to the classpath
    public File copyFile(String xmlPath, int i) {
        File xmlFile = null;
        InputStream is = this.getClass().getResourceAsStream(xmlPath);
        byte[] buffer = null;
        try {
            buffer = new byte[is.available()];
        } catch (IOException e1) {
            // TODO Auto-generated catch block
        }
        try {
            is.read(buffer);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
        }
        ;
        try {
            xmlFile = new File("src/main/resources/copiedFile" + i);
            @SuppressWarnings("resource")
            OutputStream outStream = new FileOutputStream(xmlFile);
            outStream.write(buffer);
            xmlFile = correctHref(xmlFile, "/src/main/resources/model/modified" + i);
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }

        return xmlFile;
    }

    // returns a list containing all the fields declared in the type hierrachy
    public List<Field> getAllDeclaredFields(Class<?> clazz) {
        List<Field> flds = new ArrayList<Field>();
        while (clazz.getSuperclass() != null) {
            for (Field f : clazz.getDeclaredFields()) {
                flds.add(f);
            }
            clazz = clazz.getSuperclass();
        }
        return flds;
    }

    // return the Type of the field corresponding to a node attribute
    public Type attributCorrespondsToField(Node node, List<Field> fields) {
        Iterator<Field> iter = fields.iterator();
        while (iter.hasNext()) {
            Field next = iter.next();
            if (next.getName().contentEquals(node.getNodeName())) {
                return next.getType();
            }
        }
        return null;

    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Map getFieldNamesAndValues(final Object valueObj) throws IllegalArgumentException,
            IllegalAccessException {

        logger.info("Begin - getFieldNamesAndValues");
        @SuppressWarnings("rawtypes")
        Class c1 = valueObj.getClass();
        logger.info("Class name got is: " + c1.getName());

        Map fieldMap = new HashMap();
        Field[] valueObjFields = c1.getDeclaredFields();

        // compare values now
        for (int i = 0; i < valueObjFields.length; i++) {

            String fieldName = valueObjFields[i].getName();

            logger.info("Getting Field Values for Field: " + valueObjFields[i].getName());
            valueObjFields[i].setAccessible(true);

            Object newObj = valueObjFields[i].get(valueObj);

            logger.info("Value of field  " + fieldName + "  value: " + newObj);
            fieldMap.put(fieldName, newObj);

        }
        logger.info("End - getFieldNamesAndValues");
        return fieldMap;
    }

    // prints all the objects contained in the memory for a good visibillity
    public void printMemoryContentInDetails() {
        for (int i = 0; i < speicher.size(); i++) {
            Object object = speicher.get(i);
            try {
                System.out.println(getFieldNamesAndValues(object));
                System.out.println("---------------------------------------------");
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
            }
        }
    }

}
