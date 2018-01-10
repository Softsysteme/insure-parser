package adapters;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import adapters.PrototypeAdapter.AdaptedPrototype;
import insure.Prototype;
import insure.infoservice.feldsteuerung.Feldsteuerung;
import insure.infoservice.feldsteuerung.IEingabeelement;
import insure.infoservice.feldsteuerung.IEingabeelementeigenschaft;
import insure.infoservice.feldsteuerung.IFeldelementeigenschaften;
import insure.infoservice.feldsteuerung.IFeldsteuerungIdentifier;
import insure.infoservice.feldsteuerung.ISteuerelement;
import insure.infoservice.feldsteuerung.ISteuerelementeigenschaft;
import insure.infoservice.feldsteuerung.StandardFeldelementeigenschaften;
import insure.infoservice.feldsteuerung.TemplateFeldelementeigenschaften;
import tools.GlobalEnumMapFactory;

public class PrototypeAdapter extends XmlAdapter<AdaptedPrototype, Prototype> {

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AdaptedPrototype extends AdaptedClass {

        public AdaptedPrototype() {

        }

        public List<Eingabeelementeigenschaften> getEingabeelementeigenschaften() {
            if (eingabeelementeigenschaften == null) {
                return new ArrayList<Eingabeelementeigenschaften>();
            }
            return eingabeelementeigenschaften;
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

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public String getStandardSteuerelementeigenschaft() {
            return standardSteuerelementeigenschaft;
        }

        public void setStandardSteuerelementeigenschaft(String standardSteuerelementeigenschaft) {
            this.standardSteuerelementeigenschaft = standardSteuerelementeigenschaft;
        }

        // public Map<Feldelementeigenschaften, SimpleEnum> getFeldelementeigenschaften() {
        // return feldelementeigenschaften;
        // }

        // public void setFeldelementeigenschaften(Map<Feldelementeigenschaften, SimpleEnum> feldelementeigenschaften) {
        // this.feldelementeigenschaften = feldelementeigenschaften;
        // }

        public String getIdentifier() {
            return identifier;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }



        protected List<Eingabeelementeigenschaften> eingabeelementeigenschaften = new ArrayList<Eingabeelementeigenschaften>();

        // @XmlElement(name = "steuerelementeigenschaften", type = Steuerelementeigenschaften.class)
        protected List<Steuerelementeigenschaften> steuerelementeigenschaften;

        public List<Steuerelementeigenschaften> getSteuerelementeigenschaften() {
            return steuerelementeigenschaften;
        }

        public void setSteuerelementeigenschaften(List<Steuerelementeigenschaften> steuerelementeigenschaften) {
            this.steuerelementeigenschaften = steuerelementeigenschaften;
        }

        public void setEingabeelementeigenschaften(List<Eingabeelementeigenschaften> eingabeelementeigenschaften) {
            this.eingabeelementeigenschaften = eingabeelementeigenschaften;
        }

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


        public String template;

        public String getStandadEingabeelementeigenschaft() {
            return standadEingabeelementeigenschaft;
        }

        public void setStandadEingabeelementeigenschaft(String standadEingabeelementeigenschaft) {
            this.standadEingabeelementeigenschaft = standadEingabeelementeigenschaft;
        }

        @XmlElement(name = "standardEingabeelementeigenschaft")
        protected String standadEingabeelementeigenschaft;

        @XmlElement(name = "standardSteuerelementeigenschaft")
        protected String standardSteuerelementeigenschaft;

//        @XmlElement(name = "feldelementeigenschaften")
//        @XmlJavaTypeAdapter(FeldelementeigenschaftenMapAdapter.class)
        // protected Map<Feldelementeigenschaften, SimpleEnum> feldelementeigenschaften;


        @XmlAttribute
        public String identifier;

    }

    @SuppressWarnings("unused")
    @Override
    public Prototype unmarshal(AdaptedPrototype v) throws Exception {

        if (null == v) {
            return null;
        }
        if (!v.getType().contains("maskensteuerung") && !v.getType().contains("schnittstellensteuerun") && !v.getType().contains("insure")) {
            if (v.getType().endsWith("StandardFeldelementeigenschaften")) {
                StandardFeldelementeigenschaften std = new StandardFeldelementeigenschaften();
                std.setStandardEingabeelementeigenschaft((IEingabeelementeigenschaft) GlobalEnumMapFactory.INSTANCE.getEnumMap().retrieve(v.getStandadEingabeelementeigenschaft()));
                std.setStandardSteuerelementeigenschaft((ISteuerelementeigenschaft) GlobalEnumMapFactory.INSTANCE.getEnumMap().retrieve(v.getStandardSteuerelementeigenschaft()));
                if (v.getSteuerelementeigenschaften() != null) {
                    for (Steuerelementeigenschaften entry : v.getSteuerelementeigenschaften()) {
                        ISteuerelement key = (ISteuerelement) GlobalEnumMapFactory.INSTANCE.getEnumMap().retrieve(entry.getKey());
                        ISteuerelementeigenschaft value = (ISteuerelementeigenschaft) GlobalEnumMapFactory.INSTANCE.getEnumMap().retrieve(entry.getValue().getHref());
                        std.getSteuerelementeigenschaften().put(key, value);
                        System.out.println(entry.getKey() + "/" + entry.getValue().getHref().toString());
                    }
                }
                if (v.getEingabeelementeigenschaften() != null) {
                    for (Eingabeelementeigenschaften entry : v.getEingabeelementeigenschaften()) {
                        IEingabeelement key = (IEingabeelement) GlobalEnumMapFactory.INSTANCE.getEnumMap().retrieve(entry.getKey().getRef());
                        IEingabeelementeigenschaft value = (IEingabeelementeigenschaft) GlobalEnumMapFactory.INSTANCE.getEnumMap().retrieve(entry.getValue().getHref());
                        std.getEingabeelementeigenschaften().put(key, value);
                        System.out.println(entry.getKey() + "/" + entry.getValue().getHref().toString());
                    }
                }

                return (Prototype) std;

            }

            if (v.getType().endsWith("TemplateFeldelementeigenschaften")) {
                TemplateFeldelementeigenschaften templ = new TemplateFeldelementeigenschaften();
                 templ.setTemplate((IFeldelementeigenschaften) GlobalEnumMapFactory.INSTANCE.getEnumMap().retrieve(v.getTemplate()));

                 if (v.getSteuerelementeigenschaften() != null) {
                     for (Steuerelementeigenschaften entry : v.getSteuerelementeigenschaften()) {
                         ISteuerelement key = (ISteuerelement) GlobalEnumMapFactory.INSTANCE.getEnumMap().retrieve(entry.getKey());
                        ISteuerelementeigenschaft value = (ISteuerelementeigenschaft) GlobalEnumMapFactory.INSTANCE.getEnumMap().retrieve(entry.getValue().getHref());
                         templ.getSteuerelementeigenschaften().put(key, value);
                         System.out.println(entry.getKey() + "/" + entry.getValue().getHref().toString());
                     }
                 }
                 if (v.getEingabeelementeigenschaften() != null) {
                     for (Eingabeelementeigenschaften entry : v.getEingabeelementeigenschaften()) {
                         IEingabeelement key = (IEingabeelement) GlobalEnumMapFactory.INSTANCE.getEnumMap().retrieve(entry.getKey().getRef());
                         IEingabeelementeigenschaft value = (IEingabeelementeigenschaft) GlobalEnumMapFactory.INSTANCE.getEnumMap().retrieve(entry.getValue().getHref());
                         templ.getEingabeelementeigenschaften().put(key, value);
                         System.out.println(entry.getKey() + "/" + entry.getValue().getHref().toString());
                     }
                 }
                return (Prototype) templ;
            }

            if (v.getType().endsWith("Feldsteuerung")) {
                Feldsteuerung feldst = new Feldsteuerung();
                // if (v.getFeldelementeigenschaften() != null) {
                // for (Entry<Feldelementeigenschaften, SimpleEnum> entry : v.getFeldelementeigenschaften().entrySet()) {
                // IKontext value = GlobalEnumMapFactory.INSTANCE.getEnumMap().retrieve(entry.getValue().)
                // fldzu.setKey(entry.getKey());
                // fldzu.setValue((IFeldelementeigenschaften) entry.getValue());
                // feldst.getFeldelementeigenschaften().add(fldzu);
                // System.out.println(entry.getKey() + "/" + entry.getValue());
                // }
                // }
                if (v.getIdentifier() != null) {
                    feldst.setIdentifier((IFeldsteuerungIdentifier) GlobalEnumMapFactory.INSTANCE.getEnumMap().retrieve(v.getIdentifier()));

                }

                // return (Prototype) feldst;

            }

        }
        return null;
    }

    @Override
    public AdaptedPrototype marshal(Prototype v) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
