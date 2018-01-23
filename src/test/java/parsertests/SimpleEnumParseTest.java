package parsertests;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.junit.Test;

import caches.InsureParserCacheManager;
import insure.SuperRoot;
import parser.PrintObjects;
import parser.XmlParser;

public class SimpleEnumParseTest {

    @Test
    public void somthingWasParsed() {
        XmlParser parser = new XmlParser();
        String[] paths = { "/infoservice.insure" };
        try {
            parser.parseXml(paths, SuperRoot.class, new PrintObjects());
        } catch (XMLStreamException | JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assert (!parser.getObjects().isEmpty());
    }

    @Test
    public void cacheNotEmpty() {
        InsureParserCacheManager cm = InsureParserCacheManager.INSTANCE;
        assert (cm.getKeys().isEmpty());
    }

}
