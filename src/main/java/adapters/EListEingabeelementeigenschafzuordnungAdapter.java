package adapters;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;

import adapters.EListEingabeelementeigenschafzuordnungAdapter.AdaptedEingabeelementeigenschafzuordnungEList;
import insure.core.ICorePackage;
import insure.infoservice.feldsteuerung.IEingabeelementeigenschaftenzuordnung;
import insure.infoservice.feldsteuerung.impl.Eingabeelementeigenschaftenzuordnung;

public class EListEingabeelementeigenschafzuordnungAdapter extends XmlAdapter<AdaptedEingabeelementeigenschafzuordnungEList, EList<IEingabeelementeigenschaftenzuordnung>> {
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AdaptedEingabeelementeigenschafzuordnungEList {


        @XmlElement(name = "Eingabeelementeigenschaften")
        public List<IEingabeelementeigenschaftenzuordnung> eingabeelementeigenschaften = new ArrayList<IEingabeelementeigenschaftenzuordnung>();

        public List<IEingabeelementeigenschaftenzuordnung> getEingabeelementeigenschaften() {
            return eingabeelementeigenschaften;
        }

        public void setEingabeelementeigenschaften(List<IEingabeelementeigenschaftenzuordnung> eingabeelementeigenschaften) {
            this.eingabeelementeigenschaften = eingabeelementeigenschaften;
        }


    }

    @Override
    public EList<IEingabeelementeigenschaftenzuordnung> unmarshal(AdaptedEingabeelementeigenschafzuordnungEList v) throws Exception {
        EList<IEingabeelementeigenschaftenzuordnung> list = new EObjectContainmentEList<IEingabeelementeigenschaftenzuordnung>(Eingabeelementeigenschaftenzuordnung.class,
            new Eingabeelementeigenschaftenzuordnung(), ICorePackage.REPOSITORY__PROTOTYPES);

        for (IEingabeelementeigenschaftenzuordnung entry : v.getEingabeelementeigenschaften()) {
            Eingabeelementeigenschaftenzuordnung eig = new Eingabeelementeigenschaftenzuordnung();
            eig.setKey(entry.getKey());
            eig.setValue(entry.getValue());
            list.add(eig);
        }
        return list;
    }

    @Override
    public AdaptedEingabeelementeigenschafzuordnungEList marshal(EList<IEingabeelementeigenschaftenzuordnung> v) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
