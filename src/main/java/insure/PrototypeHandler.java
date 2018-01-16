package insure;

import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.annotation.DomHandler;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;

public class PrototypeHandler implements DomHandler<String, StreamResult> {

    @Override
    public StreamResult createUnmarshaller(ValidationEventHandler errorHandler) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getElement(StreamResult rt) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Source marshal(String n, ValidationEventHandler errorHandler) {
        // TODO Auto-generated method stub
        return null;
    }

}
