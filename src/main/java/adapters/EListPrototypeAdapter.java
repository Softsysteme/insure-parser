package adapters;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;

import adapters.EListPrototypeAdapter.AdaptedPrototypeEList;
import insure.core.ICorePackage;
import insure.core.IPrototype;
import insure.infoservice.feldsteuerung.impl.Feldelementeigenschaften;

public class EListPrototypeAdapter extends XmlAdapter<AdaptedPrototypeEList, EList<IPrototype>> {

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AdaptedPrototypeEList {

        @XmlElements({ @XmlElement(name = "feldsteuerung"),
                @XmlElement(name = "feldelementeigenschaften"),
                @XmlElement(name = "templateFeldelementeigenschaften"),
                @XmlElement(name = "standardFeldelementeigenschaften"),
                @XmlElement(name = "prototypes"),
                @XmlElement(name = "standardEingabeelementeigenschaft"),
                @XmlElement(name = "standardSteuerelementeigenschaft") })
        @XmlJavaTypeAdapter(PrototypeAdapter.class)
        List<IPrototype> prototypes = new ArrayList<IPrototype>();

        public List<IPrototype> getPrototypes() {
            if (prototypes == null) {
                return new ArrayList<IPrototype>();
            }
            return prototypes;
        }

        public void setPrototypes(List<IPrototype> entries) {
            this.prototypes = entries;
        }

    }

    @Override
    public EList<IPrototype> unmarshal(AdaptedPrototypeEList v) throws Exception {
        EList<IPrototype> protList = new EObjectContainmentEList<IPrototype>(IPrototype.class, new Feldelementeigenschaften(), ICorePackage.REPOSITORY__PROTOTYPES);
        protList.addAll(v.getPrototypes());
        return protList;
    }

    @Override
    public AdaptedPrototypeEList marshal(EList<IPrototype> v) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
