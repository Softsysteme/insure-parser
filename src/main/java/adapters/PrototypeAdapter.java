package adapters;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlID;
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
import insure.infoservice.feldsteuerung.impl.TemplateFeldelementeigenschaften;

public class PrototypeAdapter extends XmlAdapter<AdaptedPrototype, IPrototype> {
    // @XmlRootElement(name = "prototypes")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AdaptedPrototype {

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

        public String getModelElementId() {
            return modelElementId;
        }

        public void setModelElementId(String modelElementId) {
            this.modelElementId = modelElementId;
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
        @XmlID
        public String modelElementId;

        @XmlAttribute(name = "name")
        public String name;

        @XmlElement(name = "template", type = Feldelementeigenschaften.class)
        public Feldelementeigenschaften template;

        @XmlElementRef(name = "standardEingabeelementeigenschaft", type = Eingabeelementeigenschaft.class)
        protected Eingabeelementeigenschaft standadEingabeelementeigenschaft;

        @XmlElementRef(name = "standardSteuerelementeigenschaft", type = Steuerelementeigenschaft.class)
        protected Steuerelementeigenschaft standardSteuerelementeigenschaft;

        // @XmlElement(name = "feldelementeigenschaften")
        @XmlJavaTypeAdapter(EListFeldelementeigenschafzuordnungAdapter.class)
        protected EList<IFeldelementeigenschaftenzuordnung> feldelementeigenschaften;

        @XmlElement(name = "identifier", type = FeldsteuerungIdentifier.class)
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

        if (null != v.getTemplate()) {
            System.out.println("mnvbmv");
            TemplateFeldelementeigenschaften temp = new TemplateFeldelementeigenschaften();
            temp.setBeschreibung(v.getBeschreibung());
            temp.setName(v.getName());
            temp.setModelElementId(v.getModelElementId());
            temp.setTemplate(v.getTemplate());
            temp.getSteuerelementeigenschaften().addAll(v.getSteuerelementeigenschaften());
            temp.getEingabeelementeigenschaften().addAll(v.getEingabeelementeigenschaften());
            return temp;

        }

        if (null != v.getStandardEingabeelementeigenschaft() || null != v.getStandardSteuerelementeigenschaft()) {
            System.out.println("yy");
            StandardFeldelementeigenschaften std = new StandardFeldelementeigenschaften();
            std.setBeschreibung(v.getBeschreibung());
            std.setName(v.getName());
            std.setModelElementId(v.getModelElementId());
            std.setStandardEingabeelementeigenschaft((IEingabeelementeigenschaft) v.getStandardEingabeelementeigenschaft());
            std.setStandardSteuerelementeigenschaft(v.getStandardSteuerelementeigenschaft());
            std.getSteuerelementeigenschaften().addAll(v.getSteuerelementeigenschaften());
            std.getEingabeelementeigenschaften().addAll(v.getEingabeelementeigenschaften());
            return std;

        }
        if (null != v.getIdentifier()) {
            System.out.println("dede");
            Feldsteuerung feldst = new Feldsteuerung();
            feldst.setBeschreibung(v.getBeschreibung());
            feldst.setName(v.getName());
            feldst.setModelElementId(v.getModelElementId());
            feldst.getFeldelementeigenschaften().addAll(v.getFeldelementeigenschaften());
            feldst.setIdentifier(v.getIdentifier());
            return feldst;

        }

        if (v.getEingabeelementeigenschaften() != null || v.getSteuerelementeigenschaften() != null) {
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
