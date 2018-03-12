package tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import caches.InsureParserCacheManager;

public class JavaPackageNameResolver {

    private String[] ecorePaths;

    InsureParserCacheManager cm = InsureParserCacheManager.INSTANCE;

    public JavaPackageNameResolver(String[] ecorePaths) {
        this.setEcorePaths(ecorePaths);
        for (int k = 0; k < ecorePaths.length; k++) {
            init(parseEcoreDoc(ecorePaths[k]));
        }
    }

    public Document parseEcoreDoc(String ecorePath) {
        // Get Document Builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e1) {
            // TODO Auto-generated catch block
        }
        // Build Document
        Document ecoreDocument = null;
        try {
            ecoreDocument = builder.parse(this.getClass().getResourceAsStream(ecorePath));
        } catch (SAXException | IOException e) {
            // TODO Auto-generated catch block
        }

        ecoreDocument.getDocumentElement().normalize();
        return ecoreDocument;
    }

    public void init(Document document) {
        NodeList nList = document.getElementsByTagName("eSubpackages");
        for (int i = 0; i < nList.getLength(); i++) {
            Node item = nList.item(i);
            visitPackage(item, document);
        }
    }

    public void visitPackage(Node node, Document document) {
        String packageName = contructPackageName(node);
        String NsUri = node.getAttributes().getNamedItem("nsURI").getNodeValue();
        List<Object> list = new ArrayList<Object>(2);
        list.add(0, packageName);
        String basePackage = findBasePackageName(document);
        list.add(1, basePackage != null ? basePackage : "insure");
        cm.putInCache(NsUri, list);
    }

    public String contructPackageName(Node node) {
        String result = node.getAttributes().getNamedItem("name").getNodeValue();

        while (!node.getParentNode().getNodeName().contentEquals("ecore:EPackage")) {
            result = node.getParentNode().getAttributes().getNamedItem("name").getNodeValue() + "." + result;
            node = node.getParentNode();
        }

        return node.getParentNode().getAttributes().getNamedItem("name").getNodeValue() + "." + result;
    }

    public String findBasePackageName(Document doc) {
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

                                        return item2.getAttributes().getNamedItem("value") != null ? item2.getAttributes().getNamedItem("value").getNodeValue() : null;
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
        return null;

    }

    public String[] getEcorePaths() {
        return ecorePaths;
    }

    public void setEcorePaths(String[] ecorePaths) {
        this.ecorePaths = ecorePaths;
    }

}
