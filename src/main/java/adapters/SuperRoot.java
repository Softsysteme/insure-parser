package adapters;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import insure.core.IRepository;
import insure.core.IRootRepository;
import insure.core.impl.NamedType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SuperRoot extends NamedType {
    public SuperRoot() {

}

    @XmlElements({ @XmlElement(name = "RootRepository") })
    @XmlJavaTypeAdapter(RootRepositoryAdapter.class)
    public List<IRootRepository> RootRepository;

    public List<IRootRepository> getRootRepository() {
        return RootRepository;
    }

    public void setRootRepository(List<IRootRepository> superRoot) {
        this.RootRepository = superRoot;
    }


    public String getBasePackage() {
        // TODO Auto-generated method stub
        return null;
    }


    public void setBasePackage(String value) {
        // TODO Auto-generated method stub

    }


    public List<IRepository> getRepositories() {
        // TODO Auto-generated method stub
        return null;
    }
}
