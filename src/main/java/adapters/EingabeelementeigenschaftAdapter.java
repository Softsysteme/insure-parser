package adapters;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import adapters.EingabeelementeigenschaftAdapter.AdaptedEingabeelementEigenschaft;
import insure.infoservice.feldsteuerung.IEingabeelementeigenschaft;
import insure.infoservice.feldsteuerung.impl.Eingabeelementeigenschaft;

@XmlAccessorType(XmlAccessType.FIELD)
public class EingabeelementeigenschaftAdapter extends XmlAdapter<AdaptedEingabeelementEigenschaft, IEingabeelementeigenschaft> {
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AdaptedEingabeelementEigenschaft {
        public AdaptedEingabeelementEigenschaft() {

        }
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

        @XmlAttribute(name = "notwendig", required = true)
        public boolean notwendig;
        @XmlAttribute(name = "editierbar", required = true)
        public boolean editierbar;
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

        public boolean isNotwendig() {
            return notwendig;
        }

        public void setNotwendig(boolean notwendig) {
            this.notwendig = notwendig;

        }

        public boolean isEditierbar() {
            return editierbar;
        }

        public void setEditierbar(boolean editierbar) {
            this.editierbar = editierbar;
        }

        public boolean isSichtbar() {
            return sichtbar;
        }

        public void setSichtbar(boolean sichtbar) {
            this.sichtbar = sichtbar;
        }
    }

    @Override
    public IEingabeelementeigenschaft unmarshal(AdaptedEingabeelementEigenschaft v) throws Exception {
        IEingabeelementeigenschaft eig = new Eingabeelementeigenschaft();
        eig.setBeschreibung(v.getBeschreibung());
        System.out.println(v.getBeschreibung() + "jdsffdskjk");
        eig.setName(v.getName());
        eig.setModelElementId(v.getModelElementId());
        eig.setEditierbar(v.isEditierbar());
        eig.setSichtbar(v.isSichtbar());
        eig.setNotwendig(v.isNotwendig());
        return eig;
    }

    @Override
    public AdaptedEingabeelementEigenschaft marshal(IEingabeelementeigenschaft v) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
