package adapters;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import adapters.SteuerelementAdapter.AdaptedSteuerelement;
import insure.infoservice.feldsteuerung.impl.Steuerelement;

public class SteuerelementAdapter extends XmlAdapter<AdaptedSteuerelement, Steuerelement> {

    public class AdaptedSteuerelement {

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

        @XmlAttribute(name = "modelElementId")
        public String modelElemtId;
        @XmlAttribute(name = "name")
        public String name;
        @XmlAttribute(name = "beschreibung")
        public String beschreibung;

    }

    @Override
    public Steuerelement unmarshal(AdaptedSteuerelement v) throws Exception {
        Steuerelement steuer = new Steuerelement();
        steuer.setBeschreibung(v.getBeschreibung());
        steuer.setName(v.getName());
        steuer.setModelElementId(v.getModelElemtId());

        return steuer;
    }

    @Override
    public AdaptedSteuerelement marshal(Steuerelement v) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
