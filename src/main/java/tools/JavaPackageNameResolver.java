package tools;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class JavaPackageNameResolver {

    private Map<String, String> packageName2Uri = new HashMap<String, String>();
    private Map<String, String> uri2PackageName = new HashMap<String, String>();
    private String basePackage;

    public JavaPackageNameResolver(Document document) {
        findBasePackageName(document);
        NodeList nList = document.getElementsByTagName("eSubpackages");
        for (int i = 0; i < nList.getLength(); i++) {
            Node item = nList.item(i);
            visitPackage(item);
        }
    }

    public void visitPackage(Node node) {
        String packageName = contructPackageName(node);
        String NsUri = node.getAttributes().getNamedItem("nsURI").getNodeValue();
        putInCache(packageName, NsUri);
    }

    public Map<String, String> getUri2PackageName() {
        return uri2PackageName;
    }

    public void setUri2PackageName(Map<String, String> uri2PackageName) {
        this.uri2PackageName = uri2PackageName;
    }

    public String contructPackageName(Node node) {
        String result = node.getAttributes().getNamedItem("name").getNodeValue();

        while (!node.getParentNode().getNodeName().contentEquals("ecore:EPackage")) {
            result = node.getParentNode().getAttributes().getNamedItem("name").getNodeValue() + "." + result;
            node = node.getParentNode();
        }

        return node.getParentNode().getAttributes().getNamedItem("name").getNodeValue() + "." + result;
    }

    public Map<String, String> getPackageName2Uri() {
        return packageName2Uri;
    }

    public void setPackageName2Uri(Map<String, String> packageName2Uri) {
        this.packageName2Uri = packageName2Uri;
    }

    private void putInCache(String packageName, String nsUri) {
        packageName2Uri.put(packageName, nsUri);
        uri2PackageName.put(nsUri, packageName);
    }

    public String retrieveFromCache(String nsUri) {
        return uri2PackageName.get(nsUri);
    }

    public void findBasePackageName(Document doc) {
        NodeList nList = doc.getElementsByTagName("eAnnotations");
        for (int i = 0; i < nList.getLength(); i++) {
            Node item = nList.item(i);
            if (item.getAttributes() != null) {
                Node nodesrc = item.getAttributes().getNamedItem("source");
                if (nodesrc != null) {
                    if (nodesrc.getNodeValue().contentEquals("http://insure/basepackage")) {
                        if (item.hasChildNodes()) {
                            for (int j = 0; j < item.getChildNodes().getLength(); j++) {
                                Node item2 = item.getChildNodes().item(j);
                                if (item2.getNodeName().equals("details")) {
                                    if (item2.hasAttributes()) {
                                        this.setBasePackage(item2.getAttributes().getNamedItem("value") != null ? item2.getAttributes().getNamedItem("value").getNodeValue() : null);
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }

    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

}
