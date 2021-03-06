package parser;

import java.io.IOException;
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
import insure.core.IContainment;
import insure.core.IEnumeration;
import insure.core.IFunction;
import insure.core.factory.function.IFunctionCreator;
import insure.core.factory.function.IFunctionFactoryContributor;
import tools.NameSpaceResolver;

/**
 * 
 * @author mpouma
 *
 */
public class DomParser extends AbstractDomParser {

    private static transient Object prototype;
    final static Logger logger = LoggerFactory.getLogger(DomParser.class);

    private NameSpaceResolver resolver;
    InsureParserCacheManager cm = InsureParserCacheManager.INSTANCE;

    // constructor
    public DomParser(String filePath) {
        super(filePath);
    }

    @Override
    public void parseFileAndReferencedFiles() {
        referencedXml = this.findReferencedFiles(copyFile(filePath));
        // all referenced xml data are first parsed
        Iterator<String> iter = referencedXml.iterator();
        while (iter.hasNext()) {
            String next = iter.next();
            if (findReferencedFiles(copyFile(next)).size() == 0) {
                parseXml(next, false);
            }
        }
        Iterator<String> iter2 = referencedXml.iterator();
        while (iter2.hasNext()) {
            String next = iter2.next();
            List<String> findReferencedFiles = findReferencedFiles(copyFile(next));
            if (findReferencedFiles.size() > 0) {
                for (int i = 0; i < findReferencedFiles.size(); i++) {
                    parseXml(findReferencedFiles.get(i), false);
                }
            }
            parseXml(next, false);
        }
        parseXml(filePath, true);
    }

    /**
     * 
     * @param filePath
     *
     */
    @Override
    public void parseXml(String filePath, boolean keepInMemory) {
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
        // parse prototypes and functions two times to be sure all references are resolved
        try {
            parseEnums(nListEnum, keepInMemory);
            parsePrototypes(nListPrototypes, keepInMemory);
            parseFunction(nListFunctions, keepInMemory);
            parsePrototypes(nListPrototypes, keepInMemory);
            parseFunction(nListFunctions, keepInMemory);
        } catch (IllegalArgumentException | DOMException e) {
        }

    }

    /**
     * 
     * @param nList
     *            Enums parser
     */
    @SuppressWarnings("unchecked")
    public void parseEnums(NodeList nList, boolean keepInMemory) {
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
                        saveEnum((List<Object>) cm.retrieveFromCache(resolver.getNamespaceURI(list.get(0))), list.get(1), att_Name_Value, att_Key_Value, att_ID_Value, keepInMemory);
                    }
                }

            }
        }

    }

    /**
     * 
     * @param nList
     *            this method parse all prototypes
     */
    public void parsePrototypes(NodeList nList, boolean keepInMemory) {
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            String att_ID_Value = node.getAttributes().getNamedItem("modelElementId").getNodeValue();
            prototype = visitnode(node, prototype);
            cm.putInCache(att_ID_Value, prototype);
            if (keepInMemory) {
                speicher.put(att_ID_Value, prototype);
            }
        }
    }

    /**
     * Functions parser
     * 
     * @param nList
     */
    public void parseFunction(NodeList nList, boolean keepInMemory) {
        Object function = null;
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            String att_ID_Value = node.getAttributes().getNamedItem("modelElementId").getNodeValue();
            function = visitnode(node, new Object());
            cm.putInCache(att_ID_Value, function);
            if (keepInMemory) {
                speicher.put(att_ID_Value, function);
            }
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
     */
    @SuppressWarnings("unchecked")
    public void saveEnum(List<Object> list, String localName, String literalsName, String literalsKey, String IdValue, boolean keepInMemory) {
        IEnumeration enumeration = null;
        String name = buildFullyQualifiedName(list, localName);
        name += "Literals";
        @SuppressWarnings("rawtypes")
        Class cls = null;

        try {
            cls = Class.forName(name);
        } catch (ClassNotFoundException e2) {
            // TODO Auto-generated catch block
        } catch (NoSuchMethodError e) {
            System.out.println("no such method error was thrown");
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
                    if (literalsKey == null) {
                        literalsKey = "0";
                    } // default key
                    try {
                        Method method3 = cls.getMethod("fromKey", int.class);
                        enumeration = (IEnumeration) method3.invoke(obj, Integer.parseInt(literalsKey));
                        cm.putInCache(IdValue, enumeration);
                        if (keepInMemory) {
                            speicher.put(IdValue, enumeration);
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                    }
                } else {
                    cm.putInCache(IdValue, enumeration);
                    if (keepInMemory) {
                        speicher.put(IdValue, enumeration);
                    }
                }
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
                // TODO Auto-generated catch block
            }

        }

    }

    /**
     * 
     * @param parentClass
     * @param nodeClass
     * @param node
     * @return for nodes that doesn't have type attribute (often happens by List Elements) we refer to the parent to discover the child type
     */
    public Object visitNodeWithParent(Class<?> nodeClass, Node node) {

        // try to find a concrete class implementing the interface (waiting for better solution)
        nodeClass = searchConcreteClass(nodeClass);
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
            setObjectFields(nodeClass, result, node.getAttributes());
        }

        if (node.hasChildNodes() || node.getNodeType() == 1) {
            for (Field field : mapFields) {
                Map<Object, Object> map = new HashMap<Object, Object>();
                // only Element nodes getChildNodesWithName
                for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                    Node item = node.getChildNodes().item(i);
                    if (item.hasChildNodes() || item.getNodeType() == 1) {
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
                    if (item.hasChildNodes() || item.getNodeType() == 1) {
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
                            setRefObject(nodeClass, field, result, item);

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
        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        if (nodeMap.getNamedItem("href") != null) {
            cm.putInCache(nodeMap.getNamedItem("modelElementId").getNodeValue(), nodeMap.getNamedItem("href").getNodeValue());
            return nodeMap.getNamedItem("href").getNodeValue();
        } else {
            Class<?> clazz = null;
            List<Field> fields = null;
            List<Field> listFields = new ArrayList<Field>();
            List<Field> mapFields = new ArrayList<Field>();
            List<Field> otherFields = new ArrayList<Field>();
            if (nodeMap.getNamedItem("xsi:type") != null) {
                Node nodeType = nodeMap.getNamedItem("xsi:type");
                String objectName = nodeType.getNodeValue();
                List<String> list = splitString(objectName);
                String buildFullyQualifiedName = buildFullyQualifiedName(cm.retrieveFromCache(resolver.getNamespaceURI((list.get(0)))), list.get(1));
                try {
                    clazz = Class.forName(buildFullyQualifiedName);
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
                            if (it.hasChildNodes() || it.getNodeType() == 1) {
                                Object child = visitnode(it, new Object());
                                propertyRefs.put(it.getAttributes().getNamedItem("modelElementId").getNodeValue(), child);
                            }

                        }
                    }
                    propertyFields.put("modelElementId",
                        node.getAttributes().getNamedItem("modelElementId") != null ? node.getAttributes().getNamedItem("modelElementId").getNodeValue() : null);
                    propertyFields.put("name", node.getAttributes().getNamedItem("name") != null ? node.getAttributes().getNamedItem("name").getNodeValue() : null);
                    propertyFields.put("beschreibung",
                        node.getAttributes().getNamedItem("beschreibung") != null ? node.getAttributes().getNamedItem("beschreibung").getNodeValue() : null);

                    if (node.getNodeName().contentEquals("functions")) {
                        int count = 0;
                        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                            if (node.getChildNodes().item(i).getNodeType() == 1) {
                                count++;
                            }
                        }

                        if (count == 0) {
                            class ParsedFunctionCreator implements IFunctionCreator<ParsedFunction> {
                                @Override
                                public ParsedFunction create() {
                                    return new ParsedFunction(propertyFields.get("name"), propertyFields.get("beschreibung"));
                                }

                            }
                            class ParsedFunctionFactoryContributor implements IFunctionFactoryContributor {
                                @SuppressWarnings("unchecked")
                                @Override
                                public <T extends IFunction> T create(final String name) {
                                    if (buildFullyQualifiedName.equals(name)) {
                                        return (T) new ParsedFunctionCreator().create();
                                    }

                                    return null;
                                }
                            }

                            ParsedFunctionFactoryContributor contributor = new ParsedFunctionFactoryContributor();
                            ParsedFunction function = contributor.create(buildFullyQualifiedName);
                            cm.putInCache(propertyFields.get("modelElementId"), function);
                            return function;
                        }
                    }
                    ParsedContainment containment = new ParsedContainment(propertyFields.get("name"), propertyFields.get("beschreibung"), propertyRefs);
                    cm.putInCache(node.getAttributes().getNamedItem("modelElementId").getNodeValue(), containment);
                    return containment;

                } catch (

                InvocationTargetException e) {
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
                        setObjectFields(clazz, object, nodeMap);
                    }

                }

                if (node.hasChildNodes() || node.getNodeType() == 1) {
                    for (Field field : mapFields) {
                        Map<Object, Object> map = new HashMap<Object, Object>();
                        // only Element nodes getChildNodesWithName
                        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                            Node item = node.getChildNodes().item(i);
                            if (item.hasChildNodes() || item.getNodeType() == 1) {
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
                        if (item.hasChildNodes() || item.getNodeType() == 1) {
                            if (field.getName().contentEquals(item.getNodeName())) {
                                ParameterizedType pt = (ParameterizedType) field.getGenericType();
                                Type listType = pt.getActualTypeArguments()[0];
                                Class<?> listClass = null;
                                try {
                                    listClass = Class.forName(listType.getTypeName());
                                } catch (ClassNotFoundException e2) {
                                    // TODO Auto-generated catch block
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
                                } catch (DOMException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
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
                    Object parent = cm.retrieveFromCache(node.getParentNode().getAttributes().getNamedItem("modelElementId").getNodeValue());
                    if (parent != null) {
                        List<Field> fieldList = this.getAllDeclaredFields(parent.getClass());
                        Iterator<Field> iter = fieldList.iterator();
                        while (iter.hasNext()) {
                            Field next = iter.next();
                            if (next.getName().contentEquals(node.getNodeName())) {
                                object = visitNodeWithParent(next.getType(), node);
                            }
                        }
                    }
                }
            }
        }
        cm.putInCache(node.getAttributes().getNamedItem("modelElementId").getNodeValue(), object);
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
        if (item.getAttributes().getNamedItem("href") != null) {
            return cm.retrieveFromCache(item.getAttributes().getNamedItem("href").getNodeValue());
        }
        if (item.getAttributes().getNamedItem("xsi:type") != null) {
            return visitnode(item, new Object());
        }

        return visitNodeWithParent(listClass, item);
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
    @SuppressWarnings("unchecked")
    public void setObjectFields(Class<?> clazz, Object obj, NamedNodeMap attrList) {
        for (int i = 0; i < attrList.getLength(); i++) {
            Node item = attrList.item(i);
            String nodeN = item.getNodeName();
            Method methode = null;
            for (Field f : getAllDeclaredFields(clazz)) {
            }
            Type type = (attributCorrespondsToField(item, getAllDeclaredFields(clazz)));
            if (type != null) {
                Class<?> cl = null;
                if (type.getTypeName().contains("java.lang") || type.getTypeName().contains("java.util") || type.getTypeName().contains("double") || type.getTypeName().contains("int")) {
                    if (((Class<?>) type).isAssignableFrom(List.class)) {
                        int k = 0;
                        try {
                            methode = ArrayList.class.getDeclaredMethod("add", Object.class);
                        } catch (NoSuchMethodException | SecurityException e1) {
                            // TODO Auto-generated catch block
                        }
                        List<String> IdsList = new ArrayList<String>();
                        String objectId = item.getNodeValue();

                        while (StringUtils.indexOf(objectId, " ", k) != -1) {
                            IdsList.add(objectId.substring(k, StringUtils.indexOf(objectId, " ", k)));
                            k = StringUtils.indexOf(objectId, " ", k) + 1;
                        }
                        IdsList.add(objectId.substring(k, objectId.length()));

                        try {
                            Iterator<String> it = IdsList.iterator();
                            Field field = clazz.getDeclaredField(nodeN);
                            field.setAccessible(true);
                            List<?> list = new ArrayList<>();
                            while (it.hasNext()) {
                                String next = it.next();
                                if (cm.retrieveFromCache(next) != null) {
                                    methode.invoke(list, cm.retrieveFromCache(next));
                                }
                            }

                            field.set(obj, list);
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException | SecurityException | DOMException e) {
                            // TODO Auto-generated catch block
                        }

                    } else {
                        try {
                            methode = findMethod(clazz, "set" + Character.toUpperCase(nodeN.charAt(0)) + nodeN.substring(1, nodeN.length()),
                                attributCorrespondsToField(nodeN, this.getAllDeclaredFields(clazz)).getType());
                        } catch (NoSuchMethodException | SecurityException e1) {
                        }

                        try {
                            if (((Class<?>) type).isAssignableFrom(Integer.class)) {
                                methode.invoke(obj, Integer.parseInt(item.getNodeValue()));
                            }
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | DOMException e) {
                            // TODO Auto-generated catch block
                        }

                    }
                } else {
                    try {
                        cl = Class.forName(type.getTypeName());

                    } catch (ClassNotFoundException e) {
                    }

                    try {
                        methode = findMethod(clazz, "set" + Character.toUpperCase(nodeN.charAt(0)) + nodeN.substring(1, nodeN.length()), cl);
                    } catch (NoSuchMethodException e) {
                        // TODO Auto-generated catch block
                    }
                    Object object = cm.retrieveFromCache(item.getNodeValue());
                    if (object != null) {

                        try {
                            methode.invoke(obj, object);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block;
                            // e.printStackTrace();
                        }

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
            throws DOMException {
        Class<?> type = field.getType();
        Method methode = null;
        try {
            methode = findMethod(clazz, "set" + Character.toUpperCase(node.getNodeName().charAt(0)) + node.getNodeName().substring(1, node.getNodeName().length()), type);
        } catch (NoSuchMethodException e1) {
            // TODO Auto-generated catch block
        }
        if (node.getNodeType() == 1 || node.hasChildNodes()) {
            if (node.getAttributes().getNamedItem("href") != null) {
                try {
                    try {
                        methode.invoke(obj, cm.retrieveFromCache(node.getAttributes().getNamedItem("href").getNodeValue()));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        // TODO Auto-generated catch block
                    }
                } catch (IllegalArgumentException e) {
                }

            } else {
                Object object;
                if (node.getAttributes().getNamedItem("xsi:type") != null) {
                    object = visitnode(node, new Object());
                } else {
                    object = visitNodeWithParent(field.getType(), node);
                }
                if (object != null) {
                    for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                        Node item = node.getChildNodes().item(i);
                        if (item.getNodeType() == 1 || node.hasChildNodes()) {
                            List<Field> fieldList = getAllDeclaredFields(object.getClass());
                            Iterator<Field> iter = fieldList.iterator();
                            while (iter.hasNext()) {
                                Field next = iter.next();
                                if (next.getName().contentEquals(item.getNodeName())) {
                                    cm.putInCache(node.getAttributes().getNamedItem("modelElementId").getNodeValue(), visitNodeWithParent(next.getType(), item));
                                }
                            }

                        }
                    }
                    if (node.getAttributes().getNamedItem("modelElementId") != null) {
                        cm.putInCache(node.getAttributes().getNamedItem("modelElementId").getNodeValue(), object);
                        speicher.put(node.getAttributes().getNamedItem("modelElementId").getNodeValue(), object);
                        try {
                            try {
                                methode.invoke(obj, object);
                            } catch (IllegalAccessException e) {
                                // TODO Auto-generated catch block
                            } catch (InvocationTargetException e) {
                                // TODO Auto-generated catch block
                            }
                        } catch (IllegalArgumentException e) {

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
    Help Methods
    ---------------------------------------------------------------------------------------------------------* */

    public class ParsedContainment implements IFunction {
        private java.util.Map<String, insure.core.IContainment> containments = new HashMap<String, insure.core.IContainment>();
        private String name;
        private String beschreibung;

        public ParsedContainment(String name, String beschreibung, Map<String, Object> propertyRefs) {
            for (Map.Entry<String, Object> entry : propertyRefs.entrySet()) {
                containments.put(entry.getKey(), (IContainment) entry.getValue());
            }
            this.name = name;
            this.beschreibung = beschreibung;
        }

        public java.util.Map<String, insure.core.IContainment> getContainments() {
            return containments;
        }

        public void setContainments(java.util.Map<String, insure.core.IContainment> containments) {
            this.containments = containments;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBeschreibung() {
            return beschreibung;
        }

        public void setBeschreibung(String beschreibung) {
            this.beschreibung = beschreibung;
        }
    }

    public class ParsedFunction implements IFunction {
        private String name;
        private String beschreibung;

        public ParsedFunction(String name, String beschreibung) {
            this.name = name;
            this.beschreibung = beschreibung;
        }

        @SuppressWarnings("unused")
        public String getName() {
            return name;
        }

        @SuppressWarnings("unused")
        public void setName(String name) {
            this.name = name;
        }

        @SuppressWarnings("unused")
        public String getBeschreibung() {
            return beschreibung;
        }

        @SuppressWarnings("unused")
        public void setBeschreibung(String beschreibung) {
            this.beschreibung = beschreibung;
        }
    }
}
