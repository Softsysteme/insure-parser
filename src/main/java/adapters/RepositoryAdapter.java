package adapters;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import adapters.RepositoryAdapter.AdaptedRepository;
import insure.Function;
import insure.Prototype;
import insure.Repository;
import insure.core.IEnumeration;

public class RepositoryAdapter extends XmlAdapter<AdaptedRepository, Repository> {

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AdaptedRepository {

        @XmlAttribute(name = "pattern")
        public String pattern;

        @XmlAttribute(name = "beschreibung")
        public String beschreibung;
        @XmlAttribute(name = "name")
        public String name;
        @XmlAttribute(name = "modelElementId")
        public String modelElementId;

        @XmlElement(name = "repositories")
        @XmlJavaTypeAdapter(RepositoryAdapter.class)
        public List<Repository> repositories = new ArrayList<Repository>();

        @XmlElement(name = "prototypes")
        @XmlJavaTypeAdapter(PrototypeAdapter.class)
        public List<Prototype> prototypes = new ArrayList<Prototype>();

        @XmlElement
        public List<Function> functions = new ArrayList<Function>();

        @XmlElement(name = "enumerations")
        @XmlJavaTypeAdapter(SimpleEnumAdapter.class)
        public List<IEnumeration> enumerations = new ArrayList<IEnumeration>();

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

        public void setPrototypes(List<Prototype> protos) {
            this.prototypes = protos;
        }

        public void setFunctions(List<Function> funct) {
            this.functions = funct;
        }

        public void setEnumerations(List<IEnumeration> enums) {
            this.enumerations = enums;
        }

        public List<Repository> getRepositories() {
            if (repositories == null) {
                return new ArrayList<Repository>();
            }
            return repositories;
        }

        public List<Prototype> getPrototypes() {
            if (prototypes == null) {
                return new ArrayList<Prototype>();
            }
            return prototypes;
        }

        public List<Function> getFunctions() {
            if (functions == null) {
                return new ArrayList<Function>();
            }
            return functions;
        }

        public List<IEnumeration> getEnumerations() {
            if (enumerations == null) {
                return new ArrayList<IEnumeration>();
            }
            return enumerations;
        }

    }

    @Override
    public Repository unmarshal(AdaptedRepository v) throws Exception {
        Repository repo = new Repository();
        repo.setBeschreibung(v.getBeschreibung());
        repo.setModelElementId(v.getModelElementId());
        repo.setBeschreibung(v.getBeschreibung());
        repo.setName(v.getName());
        repo.setPattern(v.getPattern());

        for (Repository r : v.getRepositories()) {
            (repo).add(r);

        }

        for (IEnumeration en : v.getEnumerations()) {
            repo.add(en);
        }

        for (Prototype prot : v.getPrototypes()) {
            repo.add(prot);
        }

        for (Function func : v.getFunctions())
            repo.add(func);

        return repo;
    }

    @Override
    public AdaptedRepository marshal(Repository v) throws Exception {

        return null;

    }

}
