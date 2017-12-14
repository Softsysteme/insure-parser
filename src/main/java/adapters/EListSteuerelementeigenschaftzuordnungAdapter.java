package adapters;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;

import adapters.EListSteuerelementeigenschaftzuordnungAdapter.AdaptedSteuerelementeigenschafzuordnungEList;
import insure.core.ICorePackage;
import insure.infoservice.feldsteuerung.ISteuerelementeigenschaftenzuordnung;
import insure.infoservice.feldsteuerung.impl.Eingabeelementeigenschaftenzuordnung;
import insure.infoservice.feldsteuerung.impl.Steuerelementeigenschaftenzuordnung;

public class EListSteuerelementeigenschaftzuordnungAdapter extends XmlAdapter<AdaptedSteuerelementeigenschafzuordnungEList, EList<ISteuerelementeigenschaftenzuordnung>> {
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AdaptedSteuerelementeigenschafzuordnungEList {


        @XmlElement(name = "Steuerelementeigenschaften")
        public List<ISteuerelementeigenschaftenzuordnung> steuerelementeigenschaften = new ArrayList<ISteuerelementeigenschaftenzuordnung>();

        public List<ISteuerelementeigenschaftenzuordnung> getSteuerelementeigenschaften() {
            return steuerelementeigenschaften;
        }

        public void setSteuerelementeigenschaften(List<ISteuerelementeigenschaftenzuordnung> steuerelementeigenschaften) {
            this.steuerelementeigenschaften = steuerelementeigenschaften;
        }

    }

    @Override
    public EList<ISteuerelementeigenschaftenzuordnung> unmarshal(AdaptedSteuerelementeigenschafzuordnungEList v) throws Exception {
        EList<ISteuerelementeigenschaftenzuordnung> list = new EObjectContainmentEList<ISteuerelementeigenschaftenzuordnung>(Steuerelementeigenschaftenzuordnung.class,
            new Eingabeelementeigenschaftenzuordnung(), ICorePackage.REPOSITORY__PROTOTYPES);

        for (ISteuerelementeigenschaftenzuordnung entry : v.getSteuerelementeigenschaften()) {
            Steuerelementeigenschaftenzuordnung eig = new Steuerelementeigenschaftenzuordnung();
            eig.setKey(entry.getKey());
            eig.setValue(entry.getValue());
            list.add(eig);
        }
        return list;
    }

    @Override
    public AdaptedSteuerelementeigenschafzuordnungEList marshal(EList<ISteuerelementeigenschaftenzuordnung> v) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }


}

