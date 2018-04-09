package parser;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Node;

import tools.NameSpaceResolver;

public abstract class AbstractDomParser {

    protected Map<String, Object> speicher;
    protected String filePath;
    protected List<String> referencedXml;
    protected NameSpaceResolver resolver;

    public AbstractDomParser(String filePath) {
        this.filePath = filePath;
        speicher = new HashMap<String, Object>();
    }

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

    public abstract void parseXml(String filePath, boolean keepInMemory);

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

    public Class<?> searchConcreteClass(Class<?> nodeClass) {
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
        return nodeClass;
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

    public List<String> findReferencedFiles(InputStream input) {

        List<String> referencesList = new ArrayList<String>();
        BufferedReader br = null;
        String newString = "";
        try {

            br = new BufferedReader(new InputStreamReader(input));
            while ((newString = br.readLine()) != null) {
                if (newString.contains("href=")) {
                    String fullPath = StringUtils.substringBetween(newString, "href=", "#");
                    int index = fullPath.lastIndexOf("/");
                    if (!referencesList.contains("/" + fullPath.substring(index + 1))) {
                        referencesList.add("/" + fullPath.substring(index + 1));
                    }
                }
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
        } // calls it
        return referencesList;
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

    // return the Type of the field corresponding to a node attribute
    public Field attributCorrespondsToField(String nodeName, List<Field> fields) {
        Iterator<Field> iter = fields.iterator();
        while (iter.hasNext()) {
            Field next = iter.next();
            if (next.getName().contentEquals(nodeName)) {
                return next;
            }
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

}
