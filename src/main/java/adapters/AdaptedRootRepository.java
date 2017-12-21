package adapters;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import insure.core.IRepository;

    @XmlRootElement(name = "RootRepository")
    @XmlAccessorType(XmlAccessType.FIELD)
    public  class AdaptedRootRepository {
        @XmlAttribute(name = "basePackage")
        public String basePackage;
        @XmlAttribute(name = "beschreibung")
        public String beschreibung;
        @XmlAttribute(name = "name")
        public String name;

        @XmlID
        @XmlAttribute(name = "modelElementId")
        public String modelElementId;


        @XmlJavaTypeAdapter(RepositoryAdapter.class)
        public List<IRepository> repositories;

        public String getBasePackage() {
            return basePackage;
        }

        public void setBasePackage(String basePackage) {
            this.basePackage = basePackage;
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

        public List<IRepository> getRepositories() {
            if (repositories == null) {
                return new ArrayList<IRepository>();
            }
            return repositories;
        }



}
