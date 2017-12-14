package adapters;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import adapters.EingabeelementeigenschaftenzuordnungAdapter.AdaptedEingabeelementeigenschaftzuordnung;
import insure.infoservice.feldsteuerung.IEingabeelementeigenschaftenzuordnung;
import insure.infoservice.feldsteuerung.impl.Eingabeelement;
import insure.infoservice.feldsteuerung.impl.Eingabeelementeigenschaft;
import insure.infoservice.feldsteuerung.impl.Eingabeelementeigenschaftenzuordnung;

public class EingabeelementeigenschaftenzuordnungAdapter extends XmlAdapter<AdaptedEingabeelementeigenschaftzuordnung, IEingabeelementeigenschaftenzuordnung> {
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AdaptedEingabeelementeigenschaftzuordnung {

        // @XmlValue
        @XmlIDREF
        public Eingabeelement key;

        @XmlElement(name = "value", required = true)
        @XmlIDREF
        public Eingabeelementeigenschaft value;

        public Eingabeelement getKey() {
            return key;
        }

        public void setKey(Eingabeelement key) {
            this.key = key;
        }

        public Eingabeelementeigenschaft getValue() {
            return value;
        }

        public void setValue(Eingabeelementeigenschaft value) {
            this.value = value;
        }
    }

    @Override
    public IEingabeelementeigenschaftenzuordnung unmarshal(AdaptedEingabeelementeigenschaftzuordnung v) throws Exception {
        Eingabeelementeigenschaftenzuordnung eing = new Eingabeelementeigenschaftenzuordnung();
        eing.setKey(v.getKey());
        eing.setValue(v.getValue());
        return eing;
    }

    @Override
    public AdaptedEingabeelementeigenschaftzuordnung marshal(IEingabeelementeigenschaftenzuordnung v) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }


}
