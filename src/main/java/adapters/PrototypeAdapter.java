package adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import adapters.PrototypeAdapter.AdaptedPrototype;
import insure.core.IPrototype;
import insure.core.ISimpleEnum;
import insure.core.impl.EnumerationType;
import insure.core.impl.Prototype;
import insure.domain.prototype.kontext.IKontext;
import insure.infoservice.feldsteuerung.Feldelementeigenschaften;
import insure.infoservice.feldsteuerung.IEingabeelement;
import insure.infoservice.feldsteuerung.IFeldelementeigenschaften;
import insure.infoservice.feldsteuerung.IFeldsteuerungIdentifier;
import insure.infoservice.feldsteuerung.ISteuerelement;
import insure.infoservice.feldsteuerung.ISteuerelementeigenschaft;
import insure.infoservice.feldsteuerung.impl.Eingabeelementeigenschaft;
import insure.infoservice.feldsteuerung.impl.Eingabeelementeigenschaftenzuordnung;
import insure.infoservice.feldsteuerung.impl.Feldelementeigenschaftenzuordnung;
import insure.infoservice.feldsteuerung.impl.Feldsteuerung;
import insure.infoservice.feldsteuerung.impl.StandardFeldelementeigenschaften;
import insure.infoservice.feldsteuerung.impl.Steuerelementeigenschaft;
import insure.infoservice.feldsteuerung.impl.Steuerelementeigenschaftenzuordnung;
import insure.infoservice.feldsteuerung.impl.TemplateFeldelementeigenschaften;

public class PrototypeAdapter extends XmlAdapter<AdaptedPrototype, IPrototype> {

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AdaptedPrototype extends AdaptedClass {

        public AdaptedPrototype() {

        }

        public List<Eingabeelementeigenschaften> getEingabeelementeigenschaften() {
            if (teste == null) {
                return new ArrayList<Eingabeelementeigenschaften>();
            }
            return teste;
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

        public IFeldelementeigenschaften getTemplate() {
            return template;
        }

        public void setTemplate(IFeldelementeigenschaften template) {
            this.template = template;
        }

        public ISteuerelementeigenschaft getStandardSteuerelementeigenschaft() {
            return standardSteuerelementeigenschaft;
        }

        public void setStandardSteuerelementeigenschaft(Steuerelementeigenschaft standardSteuerelementeigenschaft) {
            this.standardSteuerelementeigenschaft = standardSteuerelementeigenschaft;
        }

        public Map<Feldelementeigenschaften, EnumerationType> getFeldelementeigenschaften() {
            return feldelementeigenschaften;
        }

        public void setFeldelementeigenschaften(Map<Feldelementeigenschaften, EnumerationType> feldelementeigenschaften) {
            this.feldelementeigenschaften = feldelementeigenschaften;
        }

        public EnumerationType getIdentifier() {
            return identifier;
        }

        public void setIdentifier(EnumerationType identifier) {
            this.identifier = identifier;
        }


        @XmlElement(name = "eingabeelementeigenschaften", type = Eingabeelementeigenschaften.class)
        protected List<Eingabeelementeigenschaften> teste = new ArrayList<Eingabeelementeigenschaften>();


        @XmlElement(name = "steuerelementeigenschaften", type = Steuerelementeigenschaften.class)
        protected List<Steuerelementeigenschaften> tests;

        public List<Steuerelementeigenschaften> getSteuerelementeigenschaften() {
            return tests;
        }

        public void setSteuerelementeigenschaften(List<Steuerelementeigenschaften> steuerelementeigenschaften) {
            this.tests = steuerelementeigenschaften;
        }

        public void setEingabeelementeigenschaften(List<Eingabeelementeigenschaften> eingabeelementeigenschaften) {
            this.teste = eingabeelementeigenschaften;
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

        @XmlIDREF
        @XmlAttribute(name = "template")
        @XmlJavaTypeAdapter(PrototypeAdapter.class)
        public IFeldelementeigenschaften template;

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

        @XmlElement(name = "feldelementeigenschaften")
        @XmlJavaTypeAdapter(FeldelementeigenschaftenMapAdapter.class)
        protected Map<Feldelementeigenschaften, EnumerationType> feldelementeigenschaften;

        @XmlIDREF
        @XmlAttribute
        @XmlJavaTypeAdapter(SimpleEnumAdapter.class)
        public EnumerationType identifier;

        @XmlIDREF
        @XmlAttribute
        @XmlJavaTypeAdapter(SimpleEnumAdapter.class)
        public ISimpleEnum key;

        public ISimpleEnum getKey() {
            return key;
        }

        public void setKey(ISimpleEnum key) {
            this.key = key;
        }

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
                std.setBeschreibung(v.getBeschreibung());
                std.setName(v.getName());
                std.setModelElementId(v.getModelElementId());
                std.setStandardEingabeelementeigenschaft(
                    v.getStandadEingabeelementeigenschaft() != null ? v.getStandadEingabeelementeigenschaft() : null);
                std.setStandardSteuerelementeigenschaft(v.getStandardSteuerelementeigenschaft() != null ? v.getStandardSteuerelementeigenschaft() : null);
                if (v.getSteuerelementeigenschaften() != null) {

                    for (Steuerelementeigenschaften entry : v.getSteuerelementeigenschaften()) {
                        Steuerelementeigenschaftenzuordnung stzu = new Steuerelementeigenschaftenzuordnung();
                        stzu.setKey((ISteuerelement) entry.getKey());
                        stzu.setValue(entry.getValue().getHref());
                        std.getSteuerelementeigenschaften().add(stzu);
                        System.out.println(entry.getKey() + "/" + entry.getValue().getHref().toString());
                    }
                }
                if (v.getEingabeelementeigenschaften() != null) {
                    for (Eingabeelementeigenschaften entry : v.getEingabeelementeigenschaften()) {
                        Eingabeelementeigenschaftenzuordnung eizu = new Eingabeelementeigenschaftenzuordnung();
                        eizu.setKey((IEingabeelement) entry.getKey());
                        eizu.setValue(entry.getValue().getHref());
                        std.getEingabeelementeigenschaften().add(eizu);
                        System.out.println(entry.getKey() + "/" + entry.getValue().getHref().toString());
                    }
                }

                return std;

            }

            if (v.getType().endsWith("TemplateFeldelementeigenschaften")) {
                TemplateFeldelementeigenschaften templ = new TemplateFeldelementeigenschaften();
                templ.setBeschreibung(v.getBeschreibung());
                templ.setName(v.getName());
                templ.setModelElementId(v.getModelElementId());
                templ.setTemplate(v.getTemplate() != null ? v.getTemplate() : null);

                if (v.getSteuerelementeigenschaften() != null) {

                    for (Steuerelementeigenschaften entry : v.getSteuerelementeigenschaften()) {
                        Steuerelementeigenschaftenzuordnung stzu = new Steuerelementeigenschaftenzuordnung();
                        stzu.setKey((ISteuerelement) entry.getKey());
                        stzu.setValue(entry.getValue().getHref());
                        templ.getSteuerelementeigenschaften().add(stzu);
                        System.out.println(entry.getKey() + "/" + entry.getValue().getHref().toString());
                    }
                }
                if (v.getEingabeelementeigenschaften() != null) {
                    for (Eingabeelementeigenschaften entry : v.getEingabeelementeigenschaften()) {
                        Eingabeelementeigenschaftenzuordnung eizu = new Eingabeelementeigenschaftenzuordnung();
                        eizu.setKey((IEingabeelement) entry.getKey());
                        eizu.setValue(entry.getValue().getHref());
                        templ.getEingabeelementeigenschaften().add(eizu);
                        System.out.println(entry.getKey() + "/" + entry.getValue().getHref().toString());
                    }
                }
                return templ;
            }

            if (v.getType().endsWith("Feldsteuerung")) {
                Feldsteuerung feldst = new Feldsteuerung();
                feldst.setBeschreibung(v.getBeschreibung());
                feldst.setName(v.getName());
                feldst.setModelElementId(v.getModelElementId());
                if (v.getFeldelementeigenschaften() != null) {
                    for (Entry<Feldelementeigenschaften, EnumerationType> entry : v.getFeldelementeigenschaften().entrySet()) {
                        Feldelementeigenschaftenzuordnung fldzu = new Feldelementeigenschaftenzuordnung();
                        fldzu.setKey((IKontext) entry.getKey());
                        fldzu.setValue((IFeldelementeigenschaften) entry.getValue());
                        feldst.getFeldelementeigenschaften().add(fldzu);
                        System.out.println(entry.getKey() + "/" + entry.getValue());
                    }
                }
                if (v.getIdentifier() != null) {
                    feldst.setIdentifier((IFeldsteuerungIdentifier) v.getIdentifier());
                    return feldst;

                }

                return feldst;

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
