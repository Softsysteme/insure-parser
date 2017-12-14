package adapters;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.emf.common.util.EList;

import adapters.TemplateFeldelementeigenschaftenAdapter.AdaptedTemplateFeldelementeigenschaften;
import insure.infoservice.feldsteuerung.IEingabeelementeigenschaftenzuordnung;
import insure.infoservice.feldsteuerung.ISteuerelementeigenschaftenzuordnung;
import insure.infoservice.feldsteuerung.impl.Feldelementeigenschaften;
import insure.infoservice.feldsteuerung.impl.TemplateFeldelementeigenschaften;

public class TemplateFeldelementeigenschaftenAdapter extends XmlAdapter<AdaptedTemplateFeldelementeigenschaften, TemplateFeldelementeigenschaften> {

    public class AdaptedTemplateFeldelementeigenschaften {
        // @XmlElement(name = "eingabeelementeigenschaften")
        @XmlJavaTypeAdapter(EListEingabeelementeigenschafzuordnungAdapter.class)
        protected EList<IEingabeelementeigenschaftenzuordnung> eingabeelementeigenschaften;
        @XmlElement(name = "steuerelementeigenschaften")
        @XmlJavaTypeAdapter(EListSteuerelementeigenschaftzuordnungAdapter.class)
        protected EList<ISteuerelementeigenschaftenzuordnung> steuerelementeigenschaften;

        @XmlAttribute(name = "name")
        public String name;

        @XmlIDREF
        @XmlAttribute(name = "template")
        public Feldelementeigenschaften template;

        public Feldelementeigenschaften getTemplate() {
            return template;
        }

        public void setTemplate(Feldelementeigenschaften template) {
            this.template = template;
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

        public String getModelElementId() {
            return modelElementId;
        }

        public void setModelElementId(String modelElementId) {
            this.modelElementId = modelElementId;
        }

        @XmlAttribute
        public String beschreibung;
        @XmlAttribute
        @XmlID
        public String modelElementId;

        public EList<IEingabeelementeigenschaftenzuordnung> getEingabeelementeigenschaften() {
            return eingabeelementeigenschaften;
        }

        public void setEingabeelementeigenschaften(EList<IEingabeelementeigenschaftenzuordnung> eingabeelementeigenschaften) {
            this.eingabeelementeigenschaften = eingabeelementeigenschaften;
        }

        public EList<ISteuerelementeigenschaftenzuordnung> getSteuerelementeigenschaften() {
            return steuerelementeigenschaften;
        }

        public void setSteuerelementeigenschaften(EList<ISteuerelementeigenschaftenzuordnung> steuerelementeigenschaften) {
            this.steuerelementeigenschaften = steuerelementeigenschaften;
        }

    }

    @Override
    public TemplateFeldelementeigenschaften unmarshal(AdaptedTemplateFeldelementeigenschaften v) throws Exception {
        TemplateFeldelementeigenschaften eig = new TemplateFeldelementeigenschaften();
        eig.setBeschreibung(v.getBeschreibung());
        eig.setName(v.getName());
        eig.setTemplate(v.getTemplate());
        eig.setModelElementId(v.getModelElementId());
        for (IEingabeelementeigenschaftenzuordnung entry : v.getEingabeelementeigenschaften()) {
            eig.getEingabeelementeigenschaften().add(entry);
        }
        for (ISteuerelementeigenschaftenzuordnung entry : v.getSteuerelementeigenschaften()) {
            eig.getSteuerelementeigenschaften().add(entry);
        }
        return eig;
    }

    @Override
    public AdaptedTemplateFeldelementeigenschaften marshal(TemplateFeldelementeigenschaften v) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
