package insure;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import adapters.RepositoryAdapter;

@XmlRootElement
public class RootRepository {

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

    @XmlElement(name = "repositories")
    @XmlJavaTypeAdapter(RepositoryAdapter.class)
    public List<Repository> repositories = new ArrayList<Repository>();

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
