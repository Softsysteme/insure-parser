package adapters;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import insure.infoservice.feldsteuerung.impl.Steuerelement;
import insure.infoservice.feldsteuerung.impl.Steuerelementeigenschaft;

@XmlRootElement(name = "steuerelementeigenschaften")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Steuerelementeigenschaften", propOrder = {
        "key",
        "value"
})
public class Steuerelementeigenschaften {
    public Steuerelement getKey() {
        return key;
    }

    public void setKey(Steuerelement key) {
        this.key = key;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @XmlIDREF
    @XmlAttribute(name = "key")
    @XmlJavaTypeAdapter(SimpleEnumAdapter.class)
    private Steuerelement key;

    public Value value;

    @XmlRootElement(name = "value")
    @XmlType(name = "SteuerelementeigenschaftenValue", propOrder = {
            "href",

    })
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Value {
        @XmlIDREF
        @XmlAttribute
        @XmlJavaTypeAdapter(SimpleEnumAdapter.class)
        private Steuerelementeigenschaft href;

        public Steuerelementeigenschaft getHref() {
            return href;
        }

        public void setValue(Steuerelementeigenschaft href) {
            this.href = href;
        }
    }

}
