package adapters;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import insure.RootRepository;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SuperRoot {
    public SuperRoot() {

    }

    @XmlElements({ @XmlElement(name = "RootRepository") })
    @XmlJavaTypeAdapter(RootRepositoryAdapter.class)
    public List<RootRepository> RootRepository;

    public List<RootRepository> getRootRepository() {
        return RootRepository;
    }

    public void setRootRepository(List<RootRepository> superRoot) {
        this.RootRepository = superRoot;
    }

    public String getBasePackage() {
        // TODO Auto-generated method stub
        return null;
    }

    public void setBasePackage(String value) {
        // TODO Auto-generated method stub

    }

    public List<insure.RootRepository> getRepositories() {
        // TODO Auto-generated method stub
        return null;
    }
}
