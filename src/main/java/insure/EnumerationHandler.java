package insure;

import java.io.StringWriter;

import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.annotation.DomHandler;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;

public class EnumerationHandler implements DomHandler<String, StreamResult> {

    private static final String ENUM_START_TAG = "<enumerations";
    private static final String ENUM_END_TAG = "/>";

    private StringWriter xmlWriter = new StringWriter();

    @Override
    public StreamResult createUnmarshaller(ValidationEventHandler errorHandler) {
        xmlWriter.getBuffer().setLength(0);
        return new StreamResult(xmlWriter);
    }

    @Override
    public String getElement(StreamResult rt) {
        String xml = rt.getWriter().toString();
        int beginIndex = xml.indexOf(ENUM_START_TAG) + ENUM_START_TAG.length();
        int endIndex = xml.indexOf(ENUM_END_TAG);
        return xml.substring(beginIndex, endIndex);
    }

    @Override
    public Source marshal(String n, ValidationEventHandler errorHandler) {
        // TODO Auto-generated method stub
        return null;
    }

}