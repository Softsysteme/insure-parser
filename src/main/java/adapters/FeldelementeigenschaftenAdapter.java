package adapters;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.emf.common.util.EList;

import adapters.FeldelementeigenschaftenAdapter.AdaptedFeldelementeigenschaften;
import insure.infoservice.feldsteuerung.IEingabeelementeigenschaftenzuordnung;
import insure.infoservice.feldsteuerung.ISteuerelementeigenschaftenzuordnung;
import insure.infoservice.feldsteuerung.impl.Feldelementeigenschaften;

public class FeldelementeigenschaftenAdapter extends XmlAdapter<AdaptedFeldelementeigenschaften, Feldelementeigenschaften> {

    public class AdaptedFeldelementeigenschaften {

        /**
         * Zuordnungen von Eingabeelementeigenschaften zu Eingabeelementen.
         */
        @XmlElement(name = "eingabeelementeigenschaften")
        @XmlJavaTypeAdapter(EListEingabeelementeigenschafzuordnungAdapter.class)
        protected EList<IEingabeelementeigenschaftenzuordnung> eingabeelementeigenschaften;
        @XmlElement(name = "steuerelementeigenschaften")
        @XmlJavaTypeAdapter(EListSteuerelementeigenschaftzuordnungAdapter.class)
        protected EList<ISteuerelementeigenschaftenzuordnung> steuerelementeigenschaften;
        @XmlAttribute(name = "name")
        public String name;

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

        @XmlAttribute(name = "beschreibung")
        public String beschreibung;
        @XmlAttribute(name = "modelElementId")
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
    public Feldelementeigenschaften unmarshal(AdaptedFeldelementeigenschaften v) throws Exception {
        Feldelementeigenschaften eig = new Feldelementeigenschaften();
        eig.setBeschreibung(v.getBeschreibung());
        eig.setName(v.getName());
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
    public AdaptedFeldelementeigenschaften marshal(Feldelementeigenschaften v) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
