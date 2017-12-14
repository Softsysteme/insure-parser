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

import adapters.EListRepositoryAdapter.AdaptedRepositoryEList;
import insure.core.ICorePackage;
import insure.core.IPrototype;
import insure.core.IRepository;
import insure.core.ISimpleEnum;
import insure.core.impl.Repository;

public class EListRepositoryAdapter extends XmlAdapter<AdaptedRepositoryEList, EList<IRepository>> {

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AdaptedRepositoryEList {

        @XmlElement(name = "repositories", type = Repository.class)
        List<IRepository> repositories = new ArrayList<IRepository>();

        @XmlElement(name = "enumerations")
        @XmlJavaTypeAdapter(SimpleEnumAdapter.class)
        protected List<ISimpleEnum> enumerations;

        @XmlElements({ @XmlElement(name = "feldsteuerung"),
                @XmlElement(name = "feldelementeigenschaften"),
                @XmlElement(name = "templateFeldelementeigenschaften"),
                @XmlElement(name = "standardFeldelementeigenschaften"), @XmlElement(name = "prototypes"),
                @XmlElement(name = "standardEingabeelementeigenschaft"),
                @XmlElement(name = "standardSteuerelementeigenschaft") })
        @XmlJavaTypeAdapter(PrototypeAdapter.class)
        protected List<IPrototype> prototypes;

        public List<IRepository> getRepositories() {
            return repositories;
        }

        public void setRepositories(List<IRepository> entries) {
            this.repositories = entries;
        }

    }

    @Override
    public EList<IRepository> unmarshal(AdaptedRepositoryEList v) throws Exception {
        EList<IRepository> repositories = new EObjectContainmentEList<IRepository>(IRepository.class, new Repository(), ICorePackage.REPOSITORY__REPOSITORIES);
        repositories.addAll(v.getRepositories());
        return repositories;
    }

    @Override
    public AdaptedRepositoryEList marshal(EList<IRepository> v) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
