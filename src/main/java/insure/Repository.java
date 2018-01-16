package insure;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import adapters.PrototypeAdapter;
import adapters.RepositoryAdapter;
import adapters.SimpleEnumAdapter;

public class Repository {

    @XmlAttribute(name = "pattern")
    public String pattern;

    @XmlAttribute(name = "beschreibung")
    public String beschreibung;
    @XmlAttribute(name = "name")
    public String name;
    @XmlID
    @XmlAttribute(name = "modelElementId")
    public String modelElementId;

    @XmlElement
    @XmlJavaTypeAdapter(RepositoryAdapter.class)
    public List<Repository> repositories = new ArrayList<Repository>();

    @XmlElement
    @XmlJavaTypeAdapter(PrototypeAdapter.class)
    public List<Prototype> prototypes = new ArrayList<Prototype>();

    @XmlAnyElement
    public List<Function> functions = new ArrayList<Function>();

    @XmlElement
    @XmlJavaTypeAdapter(SimpleEnumAdapter.class)
    public List<SimpleEnum> enumerations = new ArrayList<SimpleEnum>();

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

    public void setEnumerations(List<SimpleEnum> enums) {
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
        return functions;
    }

    public List<SimpleEnum> getEnumerations() {
        if (enumerations == null) {
            return new ArrayList<SimpleEnum>();
        }
        return enumerations;
    }

    public boolean add(Object element) {
        // TODO: implement this method
        if (element instanceof Repository) {
            this.getRepositories().add((Repository) element);
        }
        if (element instanceof Prototype) {
            return this.getPrototypes().add((Prototype) element);
        }
        if (element instanceof Function) {
            return this.getFunctions().add((Function) element);
        }
        if (element instanceof SimpleEnum) {
            return this.getEnumerations().add((SimpleEnum) element);
        }
        return false;
    }

}
