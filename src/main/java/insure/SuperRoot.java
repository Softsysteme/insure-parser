package insure;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import adapters.RootRepositoryAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SuperRoot {
    public SuperRoot() {

    }

    @XmlElement(name = "RootRepository")
    @XmlJavaTypeAdapter(RootRepositoryAdapter.class)
    public List<RootRepository> RootRepository;

    public List<RootRepository> getRootRepository() {
        return RootRepository;
    }

    public void setRootRepository(List<RootRepository> superRoot) {
        this.RootRepository = superRoot;
    }

}
