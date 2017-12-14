package adapters;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;

import adapters.EListFeldelementeigenschafzuordnungAdapter.AdaptedFeldelementeigenschafzuordnungEList;
import insure.infoservice.feldsteuerung.IFeldelementeigenschaftenzuordnung;
import insure.infoservice.feldsteuerung.impl.Feldelementeigenschaftenzuordnung;

public class EListFeldelementeigenschafzuordnungAdapter extends XmlAdapter<AdaptedFeldelementeigenschafzuordnungEList, EList<IFeldelementeigenschaftenzuordnung>> {
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AdaptedFeldelementeigenschafzuordnungEList {


        @XmlElement(name = "Eingabeelementeigenschaften")
        public List<IFeldelementeigenschaftenzuordnung> feldelementeigenschaften = new ArrayList<IFeldelementeigenschaftenzuordnung>();

        public List<IFeldelementeigenschaftenzuordnung> getFeldelementeigenschaften() {
            return feldelementeigenschaften;
        }

        public void setFeldelementeigenschaften(List<IFeldelementeigenschaftenzuordnung> feldelementeigenschaften) {
            this.feldelementeigenschaften = feldelementeigenschaften;
        }

    }


    @Override
    public EList<IFeldelementeigenschaftenzuordnung> unmarshal(AdaptedFeldelementeigenschafzuordnungEList v) throws Exception {
        EList<IFeldelementeigenschaftenzuordnung> list = new EObjectContainmentEList<IFeldelementeigenschaftenzuordnung>(Feldelementeigenschaftenzuordnung.class, null, 0);

        for (IFeldelementeigenschaftenzuordnung entry : v.getFeldelementeigenschaften()) {
            Feldelementeigenschaftenzuordnung eig = new Feldelementeigenschaftenzuordnung();
            eig.setKey(entry.getKey());
            eig.setValue(entry.getValue());
            list.add(eig);
        }
        return list;
    }

    @Override
    public AdaptedFeldelementeigenschafzuordnungEList marshal(EList<IFeldelementeigenschaftenzuordnung> v) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
