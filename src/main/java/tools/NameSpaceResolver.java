package tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NameSpaceResolver implements NamespaceContext {
    private static final String DEFAULT_NS = "DEFAULT";
    private Map<String, String> prefix2Uri = new HashMap<String, String>();
    private Map<String, String> uri2Prefix = new HashMap<String, String>();

    /**
     * This constructor parses the document and stores all namespaces it can find. If toplevelOnly is true, only namespaces in the root are used.
     */
    public NameSpaceResolver(Document document, boolean toplevelOnly) {
        examineNode(document.getFirstChild(), toplevelOnly);
    }

    /**
     * A single node is read, the namespace attributes are extracted and stored.
     * 
     */
    private void examineNode(Node node, boolean attributesOnly) {
        NamedNodeMap attributes = node.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Node attribute = attributes.item(i);
            storeAttribute((Attr) attribute);
        }

        if (!attributesOnly) {
            NodeList chields = node.getChildNodes();
            for (int i = 0; i < chields.getLength(); i++) {
                Node chield = chields.item(i);
                if (chield.getNodeType() == Node.ELEMENT_NODE)
                    examineNode(chield, false);
            }
        }
    }

    /**
     * This method looks at an attribute and stores it, if it is a namespace attribute.
     */
    private void storeAttribute(Attr attribute) {
        if (attribute.getNodeName().contains("xmlns")) {
            List<String> list = splitString(attribute.getNodeName());
            putInCache(list.get(1), attribute.getNodeValue());
        }

    }

    private void putInCache(String prefix, String uri) {
        prefix2Uri.put(prefix, uri);
        uri2Prefix.put(uri, prefix);
    }

    /**
     * This method is called by XPath. It returns the default namespace, if the prefix is null or "".
     * 
     */
    @Override
    public String getNamespaceURI(String prefix) {
        if (prefix == null || prefix.equals(XMLConstants.DEFAULT_NS_PREFIX)) {
            return prefix2Uri.get(DEFAULT_NS);
        } else {
            return prefix2Uri.get(prefix);
        }
    }

    public Map<String, String> getPrefix2Uri() {
        return prefix2Uri;
    }

    public void setPrefix2Uri(Map<String, String> prefix2Uri) {
        this.prefix2Uri = prefix2Uri;
    }

    public Map<String, String> getUri2Prefix() {
        return uri2Prefix;
    }

    public void setUri2Prefix(Map<String, String> uri2Prefix) {
        this.uri2Prefix = uri2Prefix;
    }

    /**
     * This method is not needed in this context, but can be implemented in a similar way.
     */
    @Override
    public String getPrefix(String namespaceURI) {
        return uri2Prefix.get(namespaceURI);
    }

    @Override
    public Iterator getPrefixes(String namespaceURI) {
        // Not implemented
        return null;
    }

    public List<String> splitString(String s) {
        List<String> result = new ArrayList<String>();
        result.add(0, s.substring(0, s.indexOf(':')));
        result.add(1, s.substring(s.indexOf(':') + 1, s.length()));
        return result;
    }

}
