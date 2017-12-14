package adapters;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import adapters.FeldsteuerungIdentifierAdapter.AdaptedFeldsteuerungIdentifier;
import insure.infoservice.feldsteuerung.impl.FeldsteuerungIdentifier;

public class FeldsteuerungIdentifierAdapter extends XmlAdapter<AdaptedFeldsteuerungIdentifier, FeldsteuerungIdentifier> {

    public class AdaptedFeldsteuerungIdentifier {

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
        @XmlID
        public String modelElemtId;
        @XmlAttribute(name = "name")
        public String name;
        @XmlAttribute(name = "beschreibung")
        public String beschreibung;
        @XmlAttribute(name = "key")
        public int key;

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

    }

    @Override
    public FeldsteuerungIdentifier unmarshal(AdaptedFeldsteuerungIdentifier v) throws Exception {
        FeldsteuerungIdentifier identifier = new FeldsteuerungIdentifier();
        identifier.setBeschreibung(v.getBeschreibung());
        identifier.setName(v.getName());
        identifier.setModelElementId(v.getModelElemtId());
        identifier.setKey(v.getKey());

        return identifier;
    }

    @Override
    public AdaptedFeldsteuerungIdentifier marshal(FeldsteuerungIdentifier v) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
