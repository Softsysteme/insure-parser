package adapters;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.emf.common.util.EList;

import adapters.FeldsteuerungAdapter.AdaptedFeldsteuerung;
import insure.infoservice.feldsteuerung.IFeldelementeigenschaftenzuordnung;
import insure.infoservice.feldsteuerung.impl.Feldsteuerung;
import insure.infoservice.feldsteuerung.impl.FeldsteuerungIdentifier;

public class FeldsteuerungAdapter extends XmlAdapter<AdaptedFeldsteuerung, Feldsteuerung> {

    public static class AdaptedFeldsteuerung {

        @XmlAttribute(name = "modelElementId")
        @XmlID
        public String modelElemtId;

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

        public FeldsteuerungIdentifier getIdentifier() {
            return identifier;
        }

        public void setIdentifier(FeldsteuerungIdentifier identifier) {
            this.identifier = identifier;
        }

        @XmlAttribute(name = "name")
        public String name;

        @XmlAttribute(name = "beschreibung")
        public String beschreibung;

        @XmlElement(name = "identifier", type = FeldsteuerungIdentifier.class)
        public FeldsteuerungIdentifier identifier;

        @XmlElement(name = "feldelementeigenschaften")
        @XmlJavaTypeAdapter(EListFeldelementeigenschafzuordnungAdapter.class)
        protected EList<IFeldelementeigenschaftenzuordnung> feldelementeigenschaften;

        public EList<IFeldelementeigenschaftenzuordnung> getFeldelementeigenschaften() {
            return feldelementeigenschaften;
        }

        public void setFeldelementeigenschaften(EList<IFeldelementeigenschaftenzuordnung> feldelementeigenschaften) {
            this.feldelementeigenschaften = feldelementeigenschaften;
        }

    }

    @Override
    public Feldsteuerung unmarshal(AdaptedFeldsteuerung v) throws Exception {
        Feldsteuerung feldst = new Feldsteuerung();
        feldst.setBeschreibung(v.getBeschreibung());
        feldst.setName(v.getName());
        feldst.setIdentifier(v.getIdentifier());
        feldst.setModelElementId(v.getModelElemtId());
        return feldst;
    }

    @Override
    public AdaptedFeldsteuerung marshal(Feldsteuerung v) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
