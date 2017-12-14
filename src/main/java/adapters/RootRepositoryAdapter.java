package adapters;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.emf.common.util.EList;

import adapters.RootRepositoryAdapter.AdaptedRootRepository;
import insure.core.impl.Repository;
import insure.core.impl.RootRepository;

public class RootRepositoryAdapter extends XmlAdapter<AdaptedRootRepository, RootRepository> {

    public RootRepositoryAdapter() throws Exception {
        // documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }

    public static class AdaptedRootRepository {
        @XmlAttribute(name = "basePackage")
        public String basePackage;
        @XmlAttribute(name = "beschreibung")
        public String beschreibung;
        @XmlAttribute(name = "name")
        public String name;

        @XmlID
        @XmlAttribute(name = "modelElementId")
        public String modelElementId;


        @XmlElement(name = "repositories")
        public List<Repository> repositories;

        public String getBasePackage() {
            return basePackage;
        }

        public void setBasePackage(String basePackage) {
            this.basePackage = basePackage;
        }

        public void setBeschreibung(String value) {
            beschreibung = value;
        }

        public String getBeschreibung() {
            return beschreibung;
        }

        public void setName(String value) {
            name = value;
        }

        public String getModelElementId() {
            return modelElementId;
        }

        public void setModelElementId(String value) {
            modelElementId = value;
        }

        public String getName() {
            return name;
        }

        public void setRepositories(EList<Repository> repos) {
            this.repositories = repos;
        }

        public List<Repository> getRepositories() {
            return repositories;
        }

    }

    @Override
    public RootRepository unmarshal(AdaptedRootRepository v) throws Exception {
        RootRepository root = new RootRepository();
        root.setBasePackage(v.getBasePackage());
        root.setBeschreibung(v.getBeschreibung());
        root.setName(v.getName());
        root.setModelElementId(v.getModelElementId());
        for (Repository repo : v.getRepositories()) {
            root.getRepositories().add(repo);
        }
        return root;
    }

    @Override
    public AdaptedRootRepository marshal(RootRepository v) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
