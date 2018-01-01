package adapters;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;

public abstract class AdaptedClass {


    @XmlID
    @XmlAttribute(name = "modelElementId")
    public String modelElementId;

    public String getModelElementId() {
        return modelElementId;
    }

    public void setModelElemtId(String modelElementId) {
        this.modelElementId = modelElementId;
    }
}
