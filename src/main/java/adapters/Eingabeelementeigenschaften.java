package adapters;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import insure.infoservice.feldsteuerung.impl.Eingabeelement;
import insure.infoservice.feldsteuerung.impl.Eingabeelementeigenschaft;

@XmlRootElement(name = "eingabeelementeigenschaften")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Eingabeelementeigenschaften", propOrder = {
        "key",
        "value"
})
public class Eingabeelementeigenschaften {

    public Eingabeelementeigenschaften(Eingabeelement key, Value value) {
        this.key = key;
        this.value = value;
    }

    public Eingabeelementeigenschaften() {

    }

    public Eingabeelement getKey() {
        return key;
    }

    public void setKey(Eingabeelement key) {
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
    private Eingabeelement key;

    public Value value;

    @XmlRootElement(name = "EingabeelementeigenschaftenValue")
    @XmlType(name = "Value", propOrder = {
            "href",

    })
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Value {
        @XmlIDREF
        @XmlAttribute
        @XmlJavaTypeAdapter(SimpleEnumAdapter.class)
        private Eingabeelementeigenschaft href;

        public Eingabeelementeigenschaft getHref() {
            return href;
        }

        public void setValue(Eingabeelementeigenschaft href) {
            this.href = href;
        }
    }

}
