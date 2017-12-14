package adapters;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import adapters.EingabeelementAdapter.AdaptedEingabeelement;
import insure.infoservice.feldsteuerung.IEingabeelement;
import insure.infoservice.feldsteuerung.impl.Eingabeelement;

public class EingabeelementAdapter extends XmlAdapter<AdaptedEingabeelement, IEingabeelement> {
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AdaptedEingabeelement {

        public String getModelElemtId() {
            return modelElemtId;
        }

        public void setModelElemtId(String modelElemtId) {
            this.modelElemtId = modelElemtId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBeschreibung() {
            return beschreibung;
        }

        public void setBeschreibung(String beschreibung) {
            this.beschreibung = beschreibung;
        }

        @XmlID
        @XmlAttribute(name = "modelElementId")
        public String modelElemtId;
        @XmlAttribute(name = "name")
        public String name;
        @XmlAttribute(name = "beschreibung")
        public String beschreibung;
    }

    @Override
    public IEingabeelement unmarshal(AdaptedEingabeelement v) throws Exception {
        Eingabeelement eing = new Eingabeelement();
        eing.setBeschreibung(v.getBeschreibung());
        eing.setName(v.getName());
        eing.setModelElementId(v.getModelElemtId());

        return eing;
    }

    @Override
    public AdaptedEingabeelement marshal(IEingabeelement v) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
