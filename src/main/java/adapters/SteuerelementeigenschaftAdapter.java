package adapters;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import adapters.SteuerelementeigenschaftAdapter.AdaptedSteuerelementEigenschaft;
import insure.infoservice.feldsteuerung.ISteuerelementeigenschaft;
import insure.infoservice.feldsteuerung.impl.Steuerelementeigenschaft;

public class SteuerelementeigenschaftAdapter extends XmlAdapter<AdaptedSteuerelementEigenschaft, ISteuerelementeigenschaft> {
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AdaptedSteuerelementEigenschaft {
        @XmlID
        @XmlAttribute(name = "modelElementId")
        public String modelElemtId;
        @XmlAttribute(name = "name")
        public String name;
        @XmlAttribute(name = "beschreibung")
        public String beschreibung;

        public String getBeschreibung() {
            return beschreibung;
        }

        public void setBeschreibung(String beschreibung) {
            this.beschreibung = beschreibung;
        }

        @XmlAttribute(name = "aktiviert", required = true)
        public boolean aktiviert;

        public boolean isAktiviert() {
            return aktiviert;
        }

        public void setAktiviert(boolean aktiviert) {
            this.aktiviert = aktiviert;
        }

        @XmlAttribute(name = "sichtbar", required = true)
        public boolean sichtbar;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String getModelElementId() {
            return modelElemtId;
        }

        public void setModelElemtId(String modelElemtId) {
            this.modelElemtId = modelElemtId;
        }

        public boolean isSichtbar() {
            return sichtbar;
        }

        public void setSichtbar(boolean sichtbar) {
            this.sichtbar = sichtbar;
        }
    }

    @Override
    public ISteuerelementeigenschaft unmarshal(AdaptedSteuerelementEigenschaft v) throws Exception {
        ISteuerelementeigenschaft eig = new Steuerelementeigenschaft();
        eig.setBeschreibung(v.getBeschreibung());
        eig.setName(v.getName());
        eig.setModelElementId(v.getModelElementId());
        eig.setAktiviert(v.isAktiviert());
        eig.setSichtbar(v.isSichtbar());
        return eig;
    }

    @Override
    public AdaptedSteuerelementEigenschaft marshal(ISteuerelementeigenschaft v) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
