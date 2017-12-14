package adapters;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import adapters.SimpleEnumAdapter.AdaptedSimpleEnum;
import insure.core.ISimpleEnum;
import insure.infoservice.feldsteuerung.impl.Eingabeelement;
import insure.infoservice.feldsteuerung.impl.Eingabeelementeigenschaft;
import insure.infoservice.feldsteuerung.impl.FeldsteuerungIdentifier;
import insure.infoservice.feldsteuerung.impl.Steuerelement;
import insure.infoservice.feldsteuerung.impl.Steuerelementeigenschaft;

public class SimpleEnumAdapter extends XmlAdapter<AdaptedSimpleEnum, ISimpleEnum> {

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AdaptedSimpleEnum {

        public AdaptedSimpleEnum() {

        }

        @XmlID
        @XmlAttribute(name = "modelElementId")
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
            this.editierbarMissing = false;
        }

        public boolean isSichtbar() {
            return sichtbar;
        }

        public void setSichtbar(boolean sichtbar) {
            this.sichtbar = sichtbar;
        }

        public boolean isAktiviert() {
            return aktiviert;
        }

        public void setAktiviert(boolean aktiviert) {
            this.aktiviert = aktiviert;
            this.aktiviertMissing = false;
        }

        @XmlAttribute(name = "name")
        public String name;
        @XmlAttribute(name = "beschreibung")
        public String beschreibung;
        @XmlAttribute(name = "notwendig")
        public boolean notwendig;
        @XmlAttribute(name = "editierbar")
        public boolean editierbar;
        @XmlAttribute(name = "sichtbar")
        public boolean sichtbar;
        @XmlAttribute(name = "aktiviert")
        public boolean aktiviert;
        public boolean editierbarMissing = true;
        @XmlAttribute(name = "key")
        protected Integer key;

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public boolean isAktiviertMissing() {
            return aktiviertMissing;
        }

        public void setAktiviertMissing(boolean aktiviertMissing) {
            this.aktiviertMissing = aktiviertMissing;
        }

        public boolean aktiviertMissing = true;

        public boolean isEditierbarMissing() {
            return editierbarMissing;
        }

        public void setEditierbarMissing(boolean editierbarMissing) {
            this.editierbarMissing = editierbarMissing;
        }

    }

    @Override
    public ISimpleEnum unmarshal(AdaptedSimpleEnum v) throws Exception {

        if (null == v) {
            return null;
        }
        if (null != v.getKey()) {
            FeldsteuerungIdentifier ident = new FeldsteuerungIdentifier();
            ident.setBeschreibung(v.getBeschreibung());
            ident.setName(v.getName());
            ident.setModelElementId(v.getModelElemtId());
            ident.setKey(v.getKey());
            return ident;
        }
        if (v.editierbarMissing) {

            if (!v.aktiviertMissing) {
                Steuerelementeigenschaft eig = new Steuerelementeigenschaft();
                eig.setBeschreibung(v.getBeschreibung());
                eig.setName(v.getName());
                eig.setModelElementId(v.getModelElemtId());
                eig.setAktiviert(v.isAktiviert());
                eig.setSichtbar(v.isSichtbar());
                return eig;
            }

            else {
                if (v.getName().contains("steuer")) {
                    Steuerelement steuer = new Steuerelement();
                    steuer.setBeschreibung(v.getBeschreibung());
                    steuer.setName(v.getName());
                    steuer.setModelElementId(v.getModelElemtId());
                    return steuer;
                }

                else {
                    Eingabeelement eing = new Eingabeelement();
                    eing.setBeschreibung(v.getBeschreibung());
                    eing.setName(v.getName());
                    eing.setModelElementId(v.getModelElemtId());
                    return eing;
                }

            }

        }

        else {
            Eingabeelementeigenschaft eig = new Eingabeelementeigenschaft();
            eig.setBeschreibung(v.getBeschreibung());
            eig.setName(v.getName());
            eig.setModelElementId(v.getModelElemtId());
            eig.setEditierbar(v.isEditierbar());
            eig.setSichtbar(v.isSichtbar());
            eig.setNotwendig(v.isNotwendig());
            return eig;
        }

    }

    @Override
    public AdaptedSimpleEnum marshal(ISimpleEnum v) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
