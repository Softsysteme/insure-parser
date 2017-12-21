package adapters;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.emf.common.util.EList;

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
        @XmlJavaTypeAdapter(EListEingabeelementeigenschafzuordnungAdapter.class)
        protected EList<ISteuerelementeigenschaftenzuordnung> steuerelementeigenschaften;

        public EList<ISteuerelementeigenschaftenzuordnung> getSteuerelementeigenschaften() {
            return steuerelementeigenschaften;
        }

        public void setSteuerelementeigenschaften(EList<ISteuerelementeigenschaftenzuordnung> steuerelementeigenschaften) {
            this.steuerelementeigenschaften = steuerelementeigenschaften;
        }

        public EList<IEingabeelementeigenschaftenzuordnung> getEingabeelementeigenschaften() {
            return eingabeelementeigenschaften;
        }

        public void setEingabeelementeigenschaften(EList<IEingabeelementeigenschaftenzuordnung> eingabeelementeigenschaften) {
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

        public EList<IFeldelementeigenschaftenzuordnung> getFeldelementeigenschaften() {
            return feldelementeigenschaften;
        }

        public void setFeldelementeigenschaften(EList<IFeldelementeigenschaftenzuordnung> feldelementeigenschaften) {
            this.feldelementeigenschaften = feldelementeigenschaften;
        }

        public FeldsteuerungIdentifier getIdentifier() {
            return identifier;
        }

        public void setIdentifier(FeldsteuerungIdentifier identifier) {
            this.identifier = identifier;
        }

        // @XmlElement(name = "eingabeelementeigenschaften")
        @XmlJavaTypeAdapter(EListEingabeelementeigenschafzuordnungAdapter.class)
        protected EList<IEingabeelementeigenschaftenzuordnung> eingabeelementeigenschaften;

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
        @XmlJavaTypeAdapter(ModelElementAdapter.class)
        protected Eingabeelementeigenschaft standadEingabeelementeigenschaft;

        @XmlElement(name = "standardSteuerelementeigenschaft")
        @XmlJavaTypeAdapter(ModelElementAdapter.class)
        protected Steuerelementeigenschaft standardSteuerelementeigenschaft;

        // @XmlElement(name = "feldelementeigenschaften")
        @XmlJavaTypeAdapter(EListFeldelementeigenschafzuordnungAdapter.class)
        protected EList<IFeldelementeigenschaftenzuordnung> feldelementeigenschaften;

        @XmlIDREF
        @XmlAttribute
        @XmlJavaTypeAdapter(SimpleEnumAdapter.class)
        public FeldsteuerungIdentifier identifier;

        public Object getStandardEingabeelementeigenschaft() {
            // TODO Auto-generated method stub
            return null;
        }
    }

    @SuppressWarnings("unused")
    @Override
    public Prototype unmarshal(AdaptedPrototype v) throws Exception {

        if (null == v) {
            return null;
        }

        // if (v.getType().contains("template")) {
        // String temphref = v.getTemplate();
        // temphref = (temphref.substring(temphref.lastIndexOf("#")));
        //
        // if (lookup.containsKey(temphref)) {
        // Feldelementeigenschaften ref = (Feldelementeigenschaften) lookup.get(temphref);
        // TemplateFeldelementeigenschaften temp = new TemplateFeldelementeigenschaften();
        // temp.setBeschreibung(v.getBeschreibung());
        // temp.setName(v.getName());
        // temp.setModelElementId(v.getModelElementId());
        // temp.setTemplate(ref);
        // temp.getSteuerelementeigenschaften().addAll(v.getSteuerelementeigenschaften());
        // temp.getEingabeelementeigenschaften().addAll(v.getEingabeelementeigenschaften());
        // lookup.put(temp.getModelElementId(), temp);
        //
        // return temp;
        //
        // }
        // }

        if (v.getType().contains("StandardFeldelementeigenschaften")) {
            StandardFeldelementeigenschaften std = new StandardFeldelementeigenschaften();
            std.setBeschreibung(v.getBeschreibung());
            std.setName(v.getName());
            std.setModelElementId(v.getModelElementId());
            std.setStandardEingabeelementeigenschaft(
                (IEingabeelementeigenschaft) v.getStandardEingabeelementeigenschaft() != null ? (IEingabeelementeigenschaft) v.getStandardEingabeelementeigenschaft() : null);
            std.setStandardSteuerelementeigenschaft(v.getStandardSteuerelementeigenschaft() != null ? v.getStandardSteuerelementeigenschaft() : null);
            std.getSteuerelementeigenschaften().addAll(v.getSteuerelementeigenschaften() != null ? v.getSteuerelementeigenschaften() : null);
            std.getEingabeelementeigenschaften().addAll(v.getEingabeelementeigenschaften() != null ? v.getEingabeelementeigenschaften() : null);
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
            feldeig.getSteuerelementeigenschaften().addAll(v.getSteuerelementeigenschaften());
            feldeig.getEingabeelementeigenschaften().addAll(v.getEingabeelementeigenschaften());
            return feldeig;
        }
        return null;
    }

    @Override
    public AdaptedPrototype marshal(IPrototype v) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
