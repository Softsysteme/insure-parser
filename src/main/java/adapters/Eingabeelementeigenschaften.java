package adapters;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "eingabeelementeigenschaften")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Eingabeelementeigenschaften")
public class Eingabeelementeigenschaften {

    public Eingabeelementeigenschaften() {

    }

    @XmlElement(required = true)
    public Key key;
    public Value value;

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }



    @XmlRootElement(name = "key")
    @XmlType(name = "Key")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Key {


        @XmlAttribute

        private String ref;

        public String getRef() {
            return ref;
        }

        public void setRef(String ref) {
            this.ref = ref;
        }
    }

    @XmlRootElement(name = "value")
    @XmlType(name = "Value")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Value {

        @XmlAttribute
        private String href;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }

}
