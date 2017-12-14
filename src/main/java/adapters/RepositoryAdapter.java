package adapters;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.emf.common.util.EList;

import adapters.RepositoryAdapter.AdaptedRepository;
import insure.core.IPrototype;
import insure.core.IRepository;
import insure.core.ISimpleEnum;
import insure.core.impl.Function;
import insure.core.impl.Repository;


public class RepositoryAdapter extends XmlAdapter<AdaptedRepository, Repository> {

    // @XmlAccessorType(XmlAccessType.FIELD)
    public static class AdaptedRepository {
        @XmlAttribute(name = "pattern")
        public String pattern;
        @XmlAttribute(name = "beschreibung")
        public String beschreibung;
        @XmlAttribute(name = "name")
        public String name;
        @XmlAttribute(name = "modelElementId")
        public String modelElementId;

        @XmlJavaTypeAdapter(EListRepositoryAdapter.class)
        public EList<IRepository> repositories;

        @XmlJavaTypeAdapter(EListPrototypeAdapter.class)
        public EList<IPrototype> prototypes;

        @XmlElementRef()
        public EList<Function> functions;

        @XmlJavaTypeAdapter(EListSimpleEnumAdapter.class)
        public EList<ISimpleEnum> enumerations;

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

        public void setRepositories(EList<IRepository> repos) {
            this.repositories = repos;
        }

        public void setPrototypes(EList<IPrototype> protos) {
            this.prototypes = protos;
        }

        public void setFunctions(EList<Function> funct) {
            this.functions = funct;
        }

        public void setEnumerations(EList<ISimpleEnum> enums) {
            this.enumerations = enums;
        }

        public EList<IRepository> getRepositories() {
            return repositories;
        }

        public EList<IPrototype> getPrototypes() {

            return prototypes;
        }

        public EList<Function> getFunctions() {
            return functions;
        }

        public EList<ISimpleEnum> getEnumerations() {

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
        for (IRepository r : v.getRepositories())
            repo.add(r);
        for (ISimpleEnum en : v.getEnumerations())
            repo.add(en);

        for (IPrototype prot : v.getPrototypes())
            repo.add(prot);

        for (Function func : v.getFunctions())
            repo.add(func);

        return repo;
    }

    @Override
    public AdaptedRepository marshal(Repository v) throws Exception {
        AdaptedRepository adaptedrepo = new AdaptedRepository();
        adaptedrepo.setName(v.getName());
        adaptedrepo.setModelElementId(v.getModelElementId());
        adaptedrepo.setPattern(v.getPattern());
        adaptedrepo.setBeschreibung(v.getBeschreibung());
        adaptedrepo.setName(v.getName());
        // ADAPTEDREPO.SETENUMERATIONS(V.GETENUMERATIONS());
        // ADAPTEDREPO.SETPROTOTYPES(V.GETPROTOTYPES());

        return adaptedrepo;

    }

}
