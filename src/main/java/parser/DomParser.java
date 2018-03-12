package parser;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import caches.InsureParserCacheManager;
import insure.core.IEnumeration;
import tools.NameSpaceResolver;

/**
 * 
 * @author mpouma
 *
 */
public class DomParser {
    private Map<String, Object> speicher;
    private String filePath;
    private static transient Object prototype;
    private List<String> referencedXml;
    final static Logger logger = LoggerFactory.getLogger(DomParser.class);

    private NameSpaceResolver resolver;
    InsureParserCacheManager cm = InsureParserCacheManager.INSTANCE;

    // constructor
    public DomParser(String filePath) {
        this.filePath = filePath;
        speicher = new HashMap<String, Object>();
        referencedXml = new ArrayList<String>();
        this.findReferencedFiles(copyFile(filePath));
    }

    public void parseFileAndReferencedFiles() {
        // all referenced xml data are first parsed
        Iterator<String> iter = referencedXml.iterator();
        while (iter.hasNext()) {
            String next = iter.next();
            parseXml(next);
        }

        parseXml(filePath);
    }

    /**
     * 
     * @param filePath
     *
     */
    public void parseXml(String filePath) {
        // Get Document Builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e1) {
            // TODO Auto-generated catch block
        }
        // Build Document
        Document insureDocument = null;
        try {
            insureDocument = builder.parse(correctHref(copyFile(filePath)));
        } catch (SAXException | IOException e) {
            // TODO Auto-generated catch block
        }

        // Normalize the XML Structure; important !!
        insureDocument.getDocumentElement().normalize();
        resolver = new NameSpaceResolver(insureDocument, false);
        // Get all Enumerations
        NodeList nListEnum = insureDocument.getElementsByTagName("enumerations");
        // Get all Prototypes
        NodeList nListPrototypes = insureDocument.getElementsByTagName("prototypes");
        // Get all functions
        NodeList nListFunctions = insureDocument.getElementsByTagName("functions");
        try {
            parseEnums(nListEnum);
            parsePrototypes(nListPrototypes);
            parseFunction(nListFunctions);
        } catch (IllegalArgumentException | DOMException e) {
        }

    }

    /**
     * 
     * @param nList
     *            Enums parser
     */
    @SuppressWarnings("unchecked")
    public void parseEnums(NodeList nList) {
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                // Check all attributes
                if (node.hasAttributes()) {
                    // get attributes names and values
                    NamedNodeMap nodeMap = node.getAttributes();
                    if (nodeMap.getNamedItem("xsi:type") != null) {
                        Node nodeType = nodeMap.getNamedItem("xsi:type");
                        String nodeName = nodeType.getNodeValue();

                        List<String> list = splitString(nodeName);
                        String att_ID_Value = nodeMap.getNamedItem("modelElementId").getNodeValue();
                        String att_Name_Value = nodeMap.getNamedItem("name").getNodeValue();
                        String att_Key_Value = nodeMap.getNamedItem("key") != null ? nodeMap.getNamedItem("key").getNodeValue() : null;
                        saveEnum((List<Object>) cm.retrieveFromCache(resolver.getNamespaceURI(list.get(0))), list.get(1), att_Name_Value, att_Key_Value, att_ID_Value);
                    }
                }

            }
        }

    }

    /**
     * 
     * @param nList
     *            Prototypes parser
     */
    public void parsePrototypes(NodeList nList) {
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            String att_ID_Value = node.getAttributes().getNamedItem("modelElementId").getNodeValue();
            prototype = visitnode(node, prototype);
            speicher.put(att_ID_Value, prototype);
            cm.putInCache(att_ID_Value, prototype);
        }
    }

    /**
     * Functions parser
     * 
     * @param nList
     */
    public void parseFunction(NodeList nList) {
        Object function = null;
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            String att_ID_Value = node.getAttributes().getNamedItem("modelElementId").getNodeValue();
            function = visitnode(node, new Object());
            speicher.put(att_ID_Value, function);
            cm.putInCache(att_ID_Value, function);
        }
    }

    /**
     * constructs and save the simple enum in the cache and in the Memory
     * 
     * @param packageName
     * @param localName
     * @param literalsName
     * @param literalsKey
     * @param nodeValue
     *            there is a bug to fix: the NoSuchMethodError is thrown because of the absence of the method toKey() by the identifier interfaces this cause an initialization error. as solution, the
     *            method tokey() should be add to all identifier interfaces
     */
    @SuppressWarnings("unchecked")
    public void saveEnum(List<Object> list, String localName, String literalsName, String literalsKey, String IdValue) {
        IEnumeration enumeration = null;
        String name = buildFullyQualifiedName(list, localName);
        name += "Literals";
        @SuppressWarnings("rawtypes")
        Class cls = null;
        try {
            cls = Class.forName(name);
        } catch (NoSuchMethodError e) {
            if (literalsKey != null) {
                try {
                    useFromKeyMethodForSaving(list, localName, literalsKey, IdValue);
                } catch (ClassNotFoundException e1) {

                }
            }

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
        } catch (NoClassDefFoundError e2) {
        }

        Method method1 = null;
        Method method2 = null;

        if (cls != null) {
            try {
                method1 = cls.getMethod("getInstance");
                method2 = cls.getMethod("valueOf", String.class);
            } catch (NoSuchMethodException | SecurityException e1) {
            }

            Object obj = null;
            try {
                obj = method1.invoke(cls);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
                // TODO Auto-generated catch block
            }
            try {
                enumeration = (IEnumeration) method2.invoke(obj, literalsName);
                if (enumeration == null) {
                    if (literalsKey != null) {
                        try {
                            Method method3 = cls.getMethod("fromKey", int.class);
                            enumeration = (IEnumeration) method3.invoke(obj, Integer.parseInt(literalsKey));
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            System.out.println(e.toString());
                        }
                    }

                }
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
                // TODO Auto-generated catch block
            }
            if (enumeration != null) {
                cm.putInCache(IdValue, enumeration);
                speicher.put(IdValue, enumeration);
            }

        }
    }

    /**
     * 
     * @param packageName
     * @param localName
     * @param literalsKey
     * @param nodeValue
     * @throws ClassNotFoundException
     *             Note: this method should be useless when the NoSuchMethodError bug is fixed constructs and save an Enum that have not null key attribute in the cache and in the Memory
     */
    public void useFromKeyMethodForSaving(List<Object> list, String localName, String literalsKey, String IdValue) throws ClassNotFoundException {
        @SuppressWarnings("unused")
        Object objectToSave = null;
        String name = buildFullyQualifiedName(list, localName);
        Class<?> cls = null;
        try {
            cls = Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw e;
        }
        Method method1 = null;
        try {
            method1 = cls.getMethod("fromKey", int.class);
        } catch (NoSuchMethodException | SecurityException e) {
            // TODO Auto-generated catch block

        }
        try {
            objectToSave = method1.invoke(cls, Integer.parseInt(literalsKey));
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            // TODO Auto-generated catch block

        }

        if (objectToSave != null) {
            cm.putInCache(IdValue, objectToSave);
            speicher.put(IdValue, objectToSave);
        }

    }

    /**
     * 
     * @param parentClass
     * @param nodeClass
     * @param node
     * @return for nodes that doesn't have type attribute (often happens by List Element) we refer to the parent to discover the child type
     */
    public Object visitNodeWithParent(Class<?> nodeClass, Node node) {
        // List<Class<?>> fields = new ArrayList<Class<?>>();
        // Reflections reflections = new Reflections(nodeClass.getPackage().getName());
        // Set<?> subTypes = reflections.getSubTypesOf(nodeClass);
        // Iterator<?> it = subTypes.iterator();
        // while (it.hasNext()) {
        // // System.out.println(it.next());
        // }

        // try to find a concrete class implementing the interface (waiting for better solution)
        if (nodeClass.isInterface()) {
            String concreteClassName = nodeClass.getPackage().getName() + "." + nodeClass.getSimpleName().substring(1, nodeClass.getSimpleName().length());
            try {
                nodeClass = Class.forName(concreteClassName);
            } catch (ClassNotFoundException e) {
                System.out.println(concreteClassName + " not found");
            }
        } else {
            try {
                nodeClass = Class.forName(nodeClass.getName());
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
            }
        }

        Object result = null;
        try {
            result = nodeClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e3) {
            // TODO Auto-generated catch block
        }

        List<Field> listFields = new ArrayList<Field>();
        List<Field> mapFields = new ArrayList<Field>();
        List<Field> otherFields = new ArrayList<Field>();
        List<Field> childFields = getAllDeclaredFields(nodeClass);
        Iterator<Field> iter = childFields.iterator();
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
            } else {
                otherFields.add(field);
            }
        }
        if (node.hasAttributes()) {
            try {
                setObjectFields(nodeClass, result, node.getAttributes());
            } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | DOMException e) {
                // TODO Auto-generated catch block
            }

        }

        if (node.hasChildNodes()) {
            for (Field field : mapFields) {
                Map<Object, Object> map = new HashMap<Object, Object>();
                // only Element nodes getChildNodesWithName
                for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                    Node item = node.getChildNodes().item(i);
                    if (item.hasChildNodes()) {
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
                                Object[] obj = buildMapElement(mapClasses, item.getChildNodes(), item, field);
                                put.invoke(map, obj[0], obj[1]);
                            } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | DOMException e) {
                                // TODO Auto-generated catch block
                            }
                            Method methode = null;
                            try {
                                methode = findMethod(nodeClass, "set" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1, field.getName().length()),
                                    field.getType());
                            } catch (NoSuchMethodException e) {
                                // TODO Auto-generated catch block
                            }
                            try {
                                methode.invoke(result, map);
                            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                                // TODO Auto-generated catch block
                            }
                        }
                    }

                }
            }

            for (Field field : listFields) {
                List<Object> lst = new ArrayList<Object>();
                // only Element nodes getChildNodesWithName
                for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                    Node item = node.getChildNodes().item(i);
                    if (item.hasChildNodes()) {
                        if (field.getName().contentEquals(item.getNodeName())) {
                            ParameterizedType pt = (ParameterizedType) field.getGenericType();
                            Type listType = pt.getActualTypeArguments()[0];
                            Class<?> listClass = null;
                            try {
                                listClass = Class.forName(listType.getTypeName());
                            } catch (ClassNotFoundException e2) {
                                // TODO Auto-generated catch blockF
                            }
                            Method add = null;
                            try {
                                add = ArrayList.class.getDeclaredMethod("add", Object.class);
                            } catch (NoSuchMethodException | SecurityException e1) {
                                // TODO Auto-generated catch block
                            }
                            try {
                                Object obj = buildListElement(nodeClass, listClass, item.getChildNodes(), item, field);
                                add.invoke(lst, obj);
                            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | DOMException e) {
                                // TODO Auto-generated catch block
                            }
                        }
                    }
                }
                Method methode = null;
                try {
                    methode = findMethod(nodeClass, "set" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1, field.getName().length()),
                        field.getType());

                } catch (NoSuchMethodException e) {
                    // TODO Auto-generated catch block
                }
                if (methode != null) {
                    try {
                        methode.invoke(result, lst);
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        // TODO Auto-generated catch block
                    }

                }
            }
            for (Field field : otherFields) {
                for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                    Node item = node.getChildNodes().item(i);
                    if (item.hasChildNodes() || item.getNodeType() == 1) {
                        if (field.getName().contentEquals(item.getNodeName())) {
                            try {
                                setRefObject(nodeClass, field, result, item);
                            } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | DOMException e) {
                            }
                        }
                    }
                }
            }

        }
        cm.putInCache(node.getAttributes().getNamedItem("modelElementId").getNodeValue(), result);
        return result;

    }

    /**
     * 
     * @param node
     * @param object
     * @return
     */
    public Object visitnode(Node node, Object object) {
        Class<?> clazz = null;
        List<Field> fields = null;
        List<Field> listFields = new ArrayList<Field>();
        List<Field> mapFields = new ArrayList<Field>();
        List<Field> otherFields = new ArrayList<Field>();
        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        if (nodeMap.getNamedItem("href") != null) {
            return nodeMap.getNamedItem("href").getNodeValue();
        } else {

            if (nodeMap.getNamedItem("xsi:type") != null) {
                Node nodeType = nodeMap.getNamedItem("xsi:type");
                String prototypeName = nodeType.getNodeValue();
                List<String> list = splitString(prototypeName);
                try {
                    clazz = Class.forName(buildFullyQualifiedName(cm.retrieveFromCache(resolver.getNamespaceURI((list.get(0)))), list.get(1)));
                    if (clazz.isInterface()) {
                        try {
                            clazz = Class.forName(clazz.getName().replace("I", ""));
                        } catch (ClassNotFoundException e) {
                            // TODO Auto-generated catch block
                        }
                    }
                    Constructor<?> constructor = clazz.getDeclaredConstructor();

                    object = constructor.newInstance();
                    fields = getAllDeclaredFields(clazz);

                } catch (InstantiationException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException e) {

                } catch (ClassNotFoundException e) {
                    Map<String, Object> propertyRefs = new HashMap<String, Object>();
                    Map<String, String> propertyFields = new HashMap<String, String>();
                    if (node.hasChildNodes()) {
                        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                            Node it = node.getChildNodes().item(i);
                            if (it.hasChildNodes()) {
                                propertyRefs.put(it.getAttributes().getNamedItem("modelElementId").getNodeValue(), visitnode(it, new Object()));
                            }

                        }
                    }
                    propertyFields.put("modelElementId",
                        node.getAttributes().getNamedItem("modelElementId") != null ? node.getAttributes().getNamedItem("modelElementId").getNodeValue() : null);
                    propertyFields.put("name", node.getAttributes().getNamedItem("name") != null ? node.getAttributes().getNamedItem("name").getNodeValue() : null);
                    propertyFields.put("beschreibung",
                        node.getAttributes().getNamedItem("beschreibung") != null ? node.getAttributes().getNamedItem("beschreibung").getNodeValue() : null);
                    return new Object() {
                        public Map<String, Object> containments = new HashMap<String, Object>();
                        private Map<String, String> propertyFields = new HashMap<String, String>();;

                        private Object init(Map<String, String> prop, Map<String, Object> propRf) {
                            containments = propRf;
                            propertyFields = prop;
                            return this;
                        }

                        @SuppressWarnings("unused")
                        public String getName() {
                            return propertyFields.get("name");
                        }

                        @SuppressWarnings("unused")
                        public void setName(String value) {
                            propertyFields.remove("name");
                            propertyFields.put("name", value);

                        }

                        @SuppressWarnings("unused")
                        public String getBeschreibung() {
                            return propertyFields.get("beschreibung");
                        }

                        @SuppressWarnings("unused")
                        public void setBeschreibung(String value) {
                            propertyFields.remove("beschreibung");
                            propertyFields.put("beschreibung", value);

                        }

                        @SuppressWarnings("unused")
                        public String getModelElementId() {
                            return propertyFields.get("modelElementId");
                        }

                        @SuppressWarnings("unused")
                        public void setModelElementId(String value) {
                            propertyFields.remove("modelElementId");
                            propertyFields.put("modelElementId", value);

                        }

                        @SuppressWarnings("unused")
                        public Map<String, Object> getContainments() {
                            return this.containments;
                        }

                        @SuppressWarnings("unused")
                        public void setContainments(Map<String, Object> containments) {
                            this.containments = containments;
                        }
                    }.init(propertyFields, propertyRefs);

                } catch (InvocationTargetException e) {
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

                        else {
                            mapFields.add(field);
                        }
                    } else {
                        otherFields.add(field);
                    }

                }
                if (node.hasAttributes()) {
                    if (node.getAttributes().getNamedItem("href") != null) {
                        return cm.retrieveFromCache(node.getAttributes().getNamedItem("href").getNodeValue());
                    }

                    else {
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
                            if (item.hasChildNodes()) {
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
                                        Object[] obj = buildMapElement(mapClasses, item.getChildNodes(), item, field);
                                        put.invoke(map, obj[0], obj[1]);
                                    } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | DOMException e) {
                                        // TODO Auto-generated catch block
                                    }
                                }
                            }
                            Method methode = null;
                            try {
                                methode = findMethod(clazz, "set" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1, field.getName().length()),
                                    field.getType());
                            } catch (NoSuchMethodException e) {
                                // TODO Auto-generated catch block
                            }
                            if (methode != null) {
                                try {
                                    methode.invoke(object, map);
                                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                                    // TODO Auto-generated catch block
                                }
                            }
                        }

                    }
                }

                for (Field field : listFields) {
                    List<Object> lst = new ArrayList<Object>();
                    // only Element nodes getChildNodesWithName
                    for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                        Node item = node.getChildNodes().item(i);
                        if (item.hasChildNodes()) {
                            if (field.getName().contentEquals(item.getNodeName())) {
                                ParameterizedType pt = (ParameterizedType) field.getGenericType();
                                Type listType = pt.getActualTypeArguments()[0];
                                Class<?> listClass = null;
                                try {
                                    listClass = Class.forName(listType.getTypeName());
                                } catch (ClassNotFoundException e2) {
                                    // TODO Auto-generated catch blockF
                                }
                                Method add = null;
                                try {
                                    add = ArrayList.class.getDeclaredMethod("add", Object.class);
                                } catch (NoSuchMethodException | SecurityException e1) {
                                    // TODO Auto-generated catch block
                                }
                                try {
                                    Object obj = buildListElement(clazz, listClass, item.getChildNodes(), item, field);
                                    add.invoke(lst, obj);
                                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | DOMException e) {
                                    // TODO Auto-generated catch block
                                }
                            }
                        }
                    }
                    Method methode = null;
                    try {
                        methode = findMethod(clazz, "set" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1, field.getName().length()),
                            field.getType());

                    } catch (NoSuchMethodException e) {
                        // TODO Auto-generated catch block

                    }

                    if (methode != null) {
                        try {
                            methode.invoke(object, lst);
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                            // TODO Auto-generated catch block
                        }
                    }

                }
                for (Field field : otherFields) {
                    for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                        Node item = node.getChildNodes().item(i);
                        if (item.hasChildNodes() || item.getNodeType() == 1) {
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
            } else {
                if (node.getParentNode().getAttributes().getNamedItem("xsi:type") != null) {
                    List<String> list = splitString(node.getParentNode().getAttributes().getNamedItem("xsi:type").getNodeValue());
                    try {
                        clazz = Class.forName(buildFullyQualifiedName(cm.retrieveFromCache(resolver.getNamespaceURI((list.get(0)))), list.get(1)));
                        if (clazz.isInterface()) {
                            try {
                                clazz = Class.forName(clazz.getName().replace("I", ""));
                            } catch (ClassNotFoundException e) {
                                // TODO Auto-generated catch block
                            }
                        }
                        for (Field f : this.getAllDeclaredFields(clazz)) {

                            if (f.getName().contentEquals(node.getNodeName())) {
                                f.setAccessible(true);
                                clazz = f.getType();
                            }

                        }
                        object = visitNodeWithParent(clazz, node);
                    } catch (ClassNotFoundException e) {

                    }
                } else {
                    Object obj = cm.retrieveFromCache(node.getParentNode().getAttributes().getNamedItem("modelElementId").getNodeValue());
                    if (obj != null) {
                        List<Field> fieldList = this.getAllDeclaredFields(object.getClass());
                        Iterator<Field> iter = fieldList.iterator();
                        while (iter.hasNext()) {
                            Field next = iter.next();
                            if (next.getName().contentEquals(node.getNodeName())) {
                                visitNodeWithParent(next.getType(), node);
                            }
                        }
                    }
                }
            }
        }

        return object;

    }

    /**
     * 
     * @param parentClass
     * @param listClass
     * @param childNodes
     * @param item
     * @param field
     * @return constructs and returns an List element
     */
    private Object buildListElement(Class<?> parentClass, Class<?> listClass, NodeList childNodes, Node item, Field field) {
        Object result = null;
        if (childNodes != null) {
            if (item.hasAttributes()) {
                if (item.getAttributes().getNamedItem("href") != null) {
                    return cm.retrieveFromCache(item.getAttributes().getNamedItem("href").getNodeValue());
                }
                if (item.getAttributes().getNamedItem("xsi:type") != null) {
                    result = visitnode(item, new Object());
                } else {
                    result = visitNodeWithParent(listClass, item);
                }
            }

        }
        return result;
    }

    /**
     * 
     * @param clazz
     * @param obj
     * @param attrList
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws DOMException
     *             set the object's fiels according to the corresponding attributes contained in @attrList
     */
    public void setObjectFields(Class<?> clazz, Object obj, NamedNodeMap attrList)
            throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, DOMException {
        for (int i = 0; i < attrList.getLength(); i++) {
            Node item = attrList.item(i);
            String nodeN = item.getNodeName();
            Type type = (attributCorrespondsToField(item, getAllDeclaredFields(clazz)));
            if (type != null) {
                Class<?> cl = null;
                if (type.getTypeName().contains("java.lang") || type.getTypeName().contains("java.util") || type.getTypeName().contains("double")) {
                    Method methode = findMethod(clazz, "set" + Character.toUpperCase(nodeN.charAt(0)) + nodeN.substring(1, nodeN.length()), type.getClass());
                    methode.invoke(obj, item.getNodeValue());
                } else {
                    try {
                        cl = Class.forName(type.getTypeName());

                    } catch (ClassNotFoundException e) {
                    }

                    Method methode = findMethod(clazz, "set" + Character.toUpperCase(nodeN.charAt(0)) + nodeN.substring(1, nodeN.length()), cl);
                    Object object = cm.retrieveFromCache(item.getNodeValue());
                    if (object != null) {
                        methode.invoke(obj, object);
                    }

                }

            }
        }
    }

    /**
     * resolve and set a reference object to the current object
     * 
     * @param clazz
     * @param field
     * @param obj
     * @param node
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws DOMException
     */
    public void setRefObject(Class<?> clazz, Field field, Object obj, Node node)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, DOMException {
        Class<?> type = field.getType();
        Method methode = findMethod(clazz, "set" + Character.toUpperCase(node.getNodeName().charAt(0)) + node.getNodeName().substring(1, node.getNodeName().length()), type);
        if (node.getNodeType() == 1 || node.hasChildNodes()) {
            if (node.getAttributes().getNamedItem("href") != null) {
                for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                    Node item = node.getChildNodes().item(i);
                    if (item.hasChildNodes()) {
                        List<Field> fieldList = getAllDeclaredFields(cm.retrieveFromCache(node.getAttributes().getNamedItem("href").getNodeValue()).getClass());
                        Iterator<Field> iter = fieldList.iterator();
                        while (iter.hasNext()) {
                            Field next = iter.next();
                            if (next.getName().contentEquals(item.getNodeName())) {
                                visitNodeWithParent(next.getType(), item);
                            }
                        }

                    }
                }
                try {
                    methode.invoke(obj, cm.retrieveFromCache(node.getAttributes().getNamedItem("href").getNodeValue()));
                } catch (IllegalArgumentException e) {
                    field.setAccessible(true);
                    field.set(obj, cm.retrieveFromCache(node.getAttributes().getNamedItem("href").getNodeValue()));
                }

            } else {
                Object object = visitnode(node, new Object());
                if (object != null) {
                    for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                        Node item = node.getChildNodes().item(i);
                        if (item.getNodeType() == 1) {
                            List<Field> fieldList = getAllDeclaredFields(object.getClass());
                            Iterator<Field> iter = fieldList.iterator();
                            while (iter.hasNext()) {
                                Field next = iter.next();
                                if (next.getName().contentEquals(item.getNodeName())) {
                                    visitNodeWithParent(next.getType(), item);
                                }
                            }

                        }
                    }
                    if (node.getAttributes().getNamedItem("modelElementId") != null) {
                        cm.putInCache(node.getAttributes().getNamedItem("modelElementId").getNodeValue(), object);
                        try {
                            methode.invoke(obj, object);
                        } catch (IllegalArgumentException e) {
                            field.setAccessible(true);
                            field.set(obj, object);
                        }
                    }
                }
            }
        }
    }

    /**
     * constructs and returns the Map to the Object
     * 
     * @param clazz
     * @param children
     * @param node
     * @param field
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws DOMException
     */
    public Object[] buildMapElement(Class<?>[] clazz, NodeList children, Node node, Field field)
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

            if (item.hasAttributes()) {
                NamedNodeMap attributes = item.getAttributes();
                if (item.getNodeName().contentEquals("key")) {
                    Node att = attributes.getNamedItem("href");
                    if (att != null) {
                        key = cm.retrieveFromCache(att.getNodeValue());

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

    /*--------------------------------------------------------------------------------------------------------
     Getters and Setters
     ---------------------------------------------------------------------------------------------------------* */

    public NameSpaceResolver getResolver() {
        return resolver;
    }

    public void setResolver(NameSpaceResolver resolver) {
        this.resolver = resolver;
    }

    public Map<String, Object> getSpeicher() {
        return speicher;
    }

    public void setSpeicher(Map<String, Object> speicher) {
        this.speicher = speicher;
    }

    /*--------------------------------------------------------------------------------------------------------
    Help Methods
    ---------------------------------------------------------------------------------------------------------* */

    // returns true when the node contains a child with the given name
    public boolean containsChild(Node node, String childName) {
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            if (node.getChildNodes().item(i).getNodeName().contentEquals(childName))
                return true;
        }
        return false;
    }

    // return a list containing the childnodes with the given name
    public List<Node> getChildNodesWithName(Node parent, String childName) {
        List<Node> result = new ArrayList<Node>();
        for (int i = 0; i < parent.getChildNodes().getLength(); i++) {
            Node item = parent.getChildNodes().item(i);
            if (item.getNodeName().contentEquals(childName) && (item.getNodeType() == 1))
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

    // returns a list containing both elements prefix and namespace
    public List<String> splitString(String s) {
        List<String> result = new ArrayList<String>();
        result.add(0, s.substring(0, s.indexOf(':')));
        result.add(1, s.substring(s.indexOf(':') + 1, s.length()));
        return result;
    }

    @SuppressWarnings("unchecked")
    public String buildFullyQualifiedName(Object list, String className) {
        List<Object> l = (List<Object>) list;
        if (l != null) {
            return l.get(1) + "." + l.get(0) + "." + className;
        }
        return null;
    }

    // suppress unwanted expressions after the attribute href leaving only the ID
    public InputStream correctHref(InputStream input) {

        BufferedReader br = null;
        String newString = "";
        StringBuilder strTotale = new StringBuilder();
        try {

            br = new BufferedReader(new InputStreamReader(input));
            while ((newString = br.readLine()) != null) {
                if (newString.contains("href=")) {
                    newString = newString.replace(StringUtils.substringBetween(newString, "href=", "_"), "\"");
                }

                strTotale.append(newString + '\n');
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
        } // calls it
        return new ByteArrayInputStream(strTotale.toString().getBytes(StandardCharsets.UTF_8));

    }

    public void findReferencedFiles(InputStream input) {

        BufferedReader br = null;
        String newString = "";
        try {

            br = new BufferedReader(new InputStreamReader(input));
            while ((newString = br.readLine()) != null) {
                if (newString.contains("href=")) {
                    String fullPath = StringUtils.substringBetween(newString, "href=", "#");
                    int index = fullPath.lastIndexOf("/");
                    if (!referencedXml.contains("/" + fullPath.substring(index + 1))) {
                        referencedXml.add("/" + fullPath.substring(index + 1));
                    }
                }
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
        } // calls it
    }

    // copy the given file to the classpath
    public InputStream copyFile(String xmlPath) {
        return this.getClass().getResourceAsStream(xmlPath);

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
        if (valueObj != null) {
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
        return null;
    }

    // returns true if every single node's attribute match a field of the given class
    public boolean nodeAttMatchClassAtt(Class<?> cl, Node node) {
        List<Field> fields = getAllDeclaredFields(cl);
        List<String> fieldsName = new ArrayList<String>();
        List<String> attNames = new ArrayList<String>();

        for (int i = 0; i < fields.size(); i++) {
            fieldsName.add(fields.get(i).getName());
        }

        for (int j = 0; j < node.getChildNodes().getLength(); j++) {
            attNames.add(node.getChildNodes().item(j).getNodeName());
        }

        return fieldsName.containsAll(attNames);

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
