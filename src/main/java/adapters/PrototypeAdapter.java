package adapters;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import adapters.PrototypeAdapter.AdaptedPrototype;
import insure.core.IPrototype;
import insure.core.impl.Prototype;
import insure.infoservice.feldsteuerung.IEingabeelementeigenschaft;
import insure.infoservice.feldsteuerung.IEingabeelementeigenschaftenzuordnung;
import insure.infoservice.feldsteuerung.IFeldelementeigenschaftenzuordnung;
import insure.infoservice.feldsteuerung.ISteuerelementeigenschaft;
import insure.infoservice.feldsteuerung.ISteuerelementeigenschaftenzuordnung;
import insure.infoservice.feldsteuerung.impl.Eingabeelementeigenschaft;
import insure.infoservice.feldsteuerung.impl.Feldelementeigenschaften;
import insure.infoservice.feldsteuerung.impl.Feldsteuerung;
import insure.infoservice.feldsteuerung.impl.FeldsteuerungIdentifier;
import insure.infoservice.feldsteuerung.impl.StandardFeldelementeigenschaften;
import insure.infoservice.feldsteuerung.impl.Steuerelementeigenschaft;

public class PrototypeAdapter extends XmlAdapter<AdaptedPrototype, IPrototype> {

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AdaptedPrototype extends AdaptedClass {

        public AdaptedPrototype() {

        }

        @XmlElement(name = "steuerelementeigenschaften")
        @XmlJavaTypeAdapter(SteuerelementeigenschaftenzuordnungAdapter.class)
        protected List<ISteuerelementeigenschaftenzuordnung> steuerelementeigenschaften;

        public List<ISteuerelementeigenschaftenzuordnung> getSteuerelementeigenschaften() {
            return steuerelementeigenschaften;
        }

        public void setSteuerelementeigenschaften(List<ISteuerelementeigenschaftenzuordnung> steuerelementeigenschaften) {
            this.steuerelementeigenschaften = steuerelementeigenschaften;
        }

        public List<IEingabeelementeigenschaftenzuordnung> getEingabeelementeigenschaften() {
            return eingabeelementeigenschaften;
        }

        public void setEingabeelementeigenschaften(List<IEingabeelementeigenschaftenzuordnung> eingabeelementeigenschaften) {
            this.eingabeelementeigenschaften = eingabeelementeigenschaften;
        }

        public String getBeschreibung() {
            return beschreibung;
        }

        public void setBeschreibung(String beschreibung) {
            this.beschreibung = beschreibung;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Feldelementeigenschaften getTemplate() {
            return template;
        }

        public void setTemplate(Feldelementeigenschaften template) {
            this.template = template;
        }

        public ISteuerelementeigenschaft getStandardSteuerelementeigenschaft() {
            return standardSteuerelementeigenschaft;
        }

        public void setStandardSteuerelementeigenschaft(Steuerelementeigenschaft standardSteuerelementeigenschaft) {
            this.standardSteuerelementeigenschaft = standardSteuerelementeigenschaft;
        }

        public List<IFeldelementeigenschaftenzuordnung> getFeldelementeigenschaften() {
            return feldelementeigenschaften;
        }

        public void setFeldelementeigenschaften(List<IFeldelementeigenschaftenzuordnung> feldelementeigenschaften) {
            this.feldelementeigenschaften = feldelementeigenschaften;
        }

        public FeldsteuerungIdentifier getIdentifier() {
            return identifier;
        }

        public void setIdentifier(FeldsteuerungIdentifier identifier) {
            this.identifier = identifier;
        }

        @XmlElement(name = "eingabeelementeigenschaften")
        @XmlJavaTypeAdapter(EingabeelementeigenschaftenzuordnungAdapter.class)
        protected List<IEingabeelementeigenschaftenzuordnung> eingabeelementeigenschaften;

        @XmlAttribute
        public String beschreibung;
        @XmlAttribute
        public String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @XmlAttribute(name = "name")
        public String name;

        @XmlIDREF
        @XmlAttribute(name = "template")
        @XmlJavaTypeAdapter(PrototypeAdapter.class)
        public Feldelementeigenschaften template;

        public Eingabeelementeigenschaft getStandadEingabeelementeigenschaft() {
            return standadEingabeelementeigenschaft;
        }

        public void setStandadEingabeelementeigenschaft(Eingabeelementeigenschaft standadEingabeelementeigenschaft) {
            this.standadEingabeelementeigenschaft = standadEingabeelementeigenschaft;
        }


        @XmlElement(name = "standardEingabeelementeigenschaft")
        @XmlJavaTypeAdapter(SimpleEnumAdapter.class)
        protected Eingabeelementeigenschaft standadEingabeelementeigenschaft;


        @XmlElement(name = "standardSteuerelementeigenschaft")
        @XmlJavaTypeAdapter(SimpleEnumAdapter.class)
        protected Steuerelementeigenschaft standardSteuerelementeigenschaft;

        // @XmlElement(name = "feldelementeigenschaften")
        @XmlJavaTypeAdapter(FeldelementeigenschafzuordnungAdapter.class)
        protected List<IFeldelementeigenschaftenzuordnung> feldelementeigenschaften;

        @XmlIDREF
        @XmlAttribute
        @XmlJavaTypeAdapter(SimpleEnumAdapter.class)
        public FeldsteuerungIdentifier identifier;

        @XmlIDREF
        @XmlAttribute
        @XmlJavaTypeAdapter(SimpleEnumAdapter.class)
        public FeldsteuerungIdentifier value;

    }

    @SuppressWarnings("unused")
    @Override
    public Prototype unmarshal(AdaptedPrototype v) throws Exception {

        if (null == v) {
            return null;
        }
        if (!v.getType().contains("maskensteuerung") && !v.getType().contains("schnittstellensteuerun")) {
            if (v.getType().contains("StandardFeldelementeigenschaften")) {
                StandardFeldelementeigenschaften std = new StandardFeldelementeigenschaften();
                std.setBeschreibung(v.getBeschreibung());
                std.setName(v.getName());
                std.setModelElementId(v.getModelElementId());
                std.setStandardEingabeelementeigenschaft(
                    (IEingabeelementeigenschaft) v.getStandadEingabeelementeigenschaft() != null ? (IEingabeelementeigenschaft) v.getStandadEingabeelementeigenschaft() : null);
                std.setStandardSteuerelementeigenschaft(v.getStandardSteuerelementeigenschaft() != null ? v.getStandardSteuerelementeigenschaft() : null);
                if (v.getSteuerelementeigenschaften() != null)
                    std.getSteuerelementeigenschaften().addAll(v.getSteuerelementeigenschaften());
                if (v.getEingabeelementeigenschaften() != null)
                    std.getEingabeelementeigenschaften().addAll(v.getEingabeelementeigenschaften());
                return std;

            }

            if (v.getType().endsWith("Feldsteuerung")) {
                Feldsteuerung feldst = new Feldsteuerung();
                feldst.setBeschreibung(v.getBeschreibung());
                feldst.setName(v.getName());
                feldst.setModelElementId(v.getModelElementId());
                if (v.getFeldelementeigenschaften() != null)
                    feldst.getFeldelementeigenschaften().addAll(v.getFeldelementeigenschaften());
                if (v.getIdentifier() != null) {
                    feldst.setIdentifier(v.getIdentifier());
                    return feldst;

                }

                return feldst;

            }

            if (v.getType().contains(":Feldelementeigenschaften")) {
                System.out.println("merrrde");
                Feldelementeigenschaften feldeig = new Feldelementeigenschaften();
                feldeig.setBeschreibung(v.getBeschreibung());
                feldeig.setName(v.getName());
                feldeig.setModelElementId(v.getModelElementId());
                if (v.getSteuerelementeigenschaften() != null)
                    feldeig.getSteuerelementeigenschaften().addAll(v.getSteuerelementeigenschaften());
                if (v.getEingabeelementeigenschaften() != null)
                    feldeig.getEingabeelementeigenschaften().addAll(v.getEingabeelementeigenschaften());
                return feldeig;
            }
        }
        return null;
    }

    @Override
    public AdaptedPrototype marshal(IPrototype v) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
