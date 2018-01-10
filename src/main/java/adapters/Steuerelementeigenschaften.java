package adapters;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "steuerelementeigenschaften")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Steuerelementeigenschaften", propOrder = {
        "key",
        "value"
})
public class Steuerelementeigenschaften {
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }


    @XmlAttribute(name = "key")
    private String key;

    public Value value;

    @XmlRootElement(name = "value")
    @XmlType(name = "SteuerelementeigenschaftenValue")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Value {

        private String href;

        public String getHref() {
            return href;
        }

        public void setValue(String href) {
            this.href = href;
        }
    }

}
