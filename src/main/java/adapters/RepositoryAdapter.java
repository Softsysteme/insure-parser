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
import insure.core.IPrototype;
import insure.core.IRepository;
import insure.core.ISimpleEnum;
import insure.core.impl.Function;
import insure.core.impl.Repository;

public class RepositoryAdapter extends XmlAdapter<AdaptedRepository, IRepository> {

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

        @XmlElement
        @XmlJavaTypeAdapter(RepositoryAdapter.class)
        public List<IRepository> repositories;

        @XmlElement(name = "prototypes")
        @XmlJavaTypeAdapter(PrototypeAdapter.class)
        public List<IPrototype> prototypes;

        @XmlElement
        public List<Function> functions;

        @XmlElement(name = "enumerations")
        @XmlJavaTypeAdapter(SimpleEnumAdapter.class)
        public List<ISimpleEnum> enumerations;

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

        public void setRepositories(List<IRepository> repos) {
            this.repositories = repos;
        }

        public void setPrototypes(List<IPrototype> protos) {
            this.prototypes = protos;
        }

        public void setFunctions(List<Function> funct) {
            this.functions = funct;
        }

        public void setEnumerations(List<ISimpleEnum> enums) {
            this.enumerations = enums;
        }

        public List<IRepository> getRepositories() {
            if(repositories==null){
                return new ArrayList<IRepository>();
            }
            return repositories;
        }

        public List<IPrototype> getPrototypes() {
            if (prototypes == null) {
                return new ArrayList<IPrototype>();
            }
            return prototypes;
        }

        public List<Function> getFunctions() {
            return functions;
        }

        public List<ISimpleEnum> getEnumerations() {
            if (enumerations == null) {
                return new ArrayList<ISimpleEnum>();
            }
            return enumerations;
        }

    }

    @Override
    public IRepository unmarshal(AdaptedRepository v) throws Exception {

        Repository repo = new Repository();
        repo.setBeschreibung(v.getBeschreibung());
        repo.setModelElementId(v.getModelElementId());
        repo.setBeschreibung(v.getBeschreibung());
        repo.setName(v.getName());
        repo.setPattern(v.getPattern());
        if (v.getRepositories() != null) {
            for (IRepository r : v.getRepositories()) {
                repo.add(r);

            }
        }
        if (v.getEnumerations() != null) {
            for (ISimpleEnum en : v.getEnumerations()) {

                repo.add(en);
            }
        }
        if (v.getPrototypes() != null) {
            for (IPrototype prot : v.getPrototypes()) {
                repo.add(prot);
            }
        }
        if (v.getFunctions() != null) {
            for (Function func : v.getFunctions())
                repo.add(func);
        }

        return repo;
    }

    @Override
    public AdaptedRepository marshal(IRepository v) throws Exception {

        return null;

    }

}
