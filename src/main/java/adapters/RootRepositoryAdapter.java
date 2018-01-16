package adapters;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import adapters.RootRepositoryAdapter.AdaptedRootRepository;
import insure.Repository;
import insure.RootRepository;

public class RootRepositoryAdapter extends XmlAdapter<AdaptedRootRepository, RootRepository> {

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AdaptedRootRepository {

        @XmlAttribute(name = "pattern")
        public String pattern;

        @XmlAttribute(name = "beschreibung")
        public String beschreibung;
        @XmlAttribute(name = "name")
        public String name;
        @XmlID
        @XmlAttribute(name = "modelElementId")
        public String modelElementId;
        @XmlAttribute(name = "basePackage")
        public String basePackage;

        @XmlElement
        @XmlJavaTypeAdapter(RepositoryAdapter.class)
        public List<Repository> repositories;

        public void setPattern(String value) {
            pattern = value;
        }

        public String getPattern() {
            return pattern;
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

        public void setRepositories(List<Repository> repos) {
            this.repositories = repos;
        }

        public List<Repository> getRepositories() {
            if (repositories == null) {
                return new ArrayList<Repository>();
            }
            return repositories;
        }

        public String getBasePackage() {
            return basePackage;
        }

        public void setBasePackage(String basePackage) {
            this.basePackage = basePackage;
        }

    }

    @Override
    public RootRepository unmarshal(AdaptedRootRepository v) throws Exception {
        RootRepository repo = new RootRepository();
        repo.setModelElementId(v.getModelElementId());
        repo.setBeschreibung(v.getBeschreibung() != null ? v.getBeschreibung() : null);
        repo.setName(v.getName() != null ? v.getName() : null);
        repo.setBasePackage(v.getBasePackage() != null ? v.getBasePackage() : null);
        repo.getRepositories().addAll(v.getRepositories());
        return repo;
    }

    @Override
    public AdaptedRootRepository marshal(RootRepository v) throws Exception {
        return null;

    }

}
