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
import insure.core.IEnumeration;
import insure.core.IPrototype;

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
    public List<IPrototype> prototypes = new ArrayList<IPrototype>();

    @XmlAnyElement
    public List<Function> functions = new ArrayList<Function>();

    @XmlElement
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

    public void setPrototypes(List<IPrototype> protos) {
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

    public List<IPrototype> getPrototypes() {
        if (prototypes == null) {
            return new ArrayList<IPrototype>();
        }
        return prototypes;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public List<IEnumeration> getEnumerations() {
        if (enumerations == null) {
            return new ArrayList<IEnumeration>();
        }
        return enumerations;
    }

    public boolean add(Object element) {
        // TODO: implement this method
        if (element instanceof Repository) {
            this.getRepositories().add((Repository) element);
        }
        if (element instanceof IPrototype) {
            return this.getPrototypes().add((IPrototype) element);
        }
        if (element instanceof Function) {
            return this.getFunctions().add((Function) element);
        }
        if (element instanceof IEnumeration) {
            return this.getEnumerations().add((IEnumeration) element);
        }
        return false;
    }

}
