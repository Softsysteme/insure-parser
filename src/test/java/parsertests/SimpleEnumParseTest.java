package parsertests;

import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import caches.InsureParserCacheManager;
import insure.SuperRoot;
import insure.core.IEnumeration;
import parser.PrintObjects;
import parser.XmlParser;

public class SimpleEnumParseTest {

    // @Test
    public void somthingWasParsed() {
        XmlParser parser = new XmlParser();
        InsureParserCacheManager cm = InsureParserCacheManager.INSTANCE;
        String[] paths = { "/infoservice.insure" };
        try {
            parser.parseXml(paths, SuperRoot.class, new PrintObjects());
        } catch (XMLStreamException | JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assert (!parser.getObjects().isEmpty());
        assert (!cm.getKeys().isEmpty());
    }

    // @Test
    public void parsedObjectsAreFromTypeIEnumeration() {
        XmlParser parser = new XmlParser();
        InsureParserCacheManager cm = InsureParserCacheManager.INSTANCE;
        String[] paths = { "/infoservice.insure" };
        try {
            parser.parseXml(paths, SuperRoot.class, new PrintObjects());
        } catch (XMLStreamException | JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        List<String> keyList = cm.getKeys();
        for (String s : keyList) {
            assert (cm.retrieveFromCache(s) instanceof IEnumeration);
        }

    }

}
