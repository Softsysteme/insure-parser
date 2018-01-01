package adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import adapters.FeldelementeigenschaftenMapAdapter.AdaptedFeldelementeigenschaftenMap;
import insure.core.impl.EnumerationType;
import insure.infoservice.feldsteuerung.Feldelementeigenschaften;

public class FeldelementeigenschaftenMapAdapter extends XmlAdapter<AdaptedFeldelementeigenschaftenMap, Map<Feldelementeigenschaften, EnumerationType>> {
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AdaptedFeldelementeigenschaftenMap {
        // @XmlElements({ @XmlElement(name = "key"), @XmlElement(name = "value") })
        @XmlElement
        public List<FeldelementeigenschaftenEntry> feldelementeigenschaften;

        @XmlIDREF
        @XmlAttribute(name = "value")
        @XmlJavaTypeAdapter(SimpleEnumAdapter.class)
        public EnumerationType value;

        @XmlIDREF
        @XmlElement(name = "key")
        @XmlJavaTypeAdapter(SimpleEnumAdapter.class)
        public Feldelementeigenschaften key;

        public Feldelementeigenschaften getKey() {
            return key;
        }

        public void setKey(Feldelementeigenschaften key) {
            this.key = key;
        }

        public EnumerationType getValue() {
            return value;
        }

        public void setValue(EnumerationType value) {
            this.value = value;
        }

        public List<FeldelementeigenschaftenEntry> getFeldelementeigenschaften() {
            if (feldelementeigenschaften == null) {
                return new ArrayList<FeldelementeigenschaftenEntry>();
            }
            return feldelementeigenschaften;
        }

        public void setFeldelementeigenschaften(List<FeldelementeigenschaftenEntry> entry) {
            this.feldelementeigenschaften = entry;
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class FeldelementeigenschaftenEntry {

        public Feldelementeigenschaften getKey() {
            return key;
        }

        public void setKey(Feldelementeigenschaften key) {
            this.key = key;
        }

        public EnumerationType getValue() {
            return value;
        }

        public void setValue(EnumerationType value) {
            this.value = value;
        }

        @XmlIDREF
        @XmlElement(name = "key")
        @XmlJavaTypeAdapter(SimpleEnumAdapter.class)
        public Feldelementeigenschaften key;

        @XmlIDREF
        @XmlAttribute(name = "value")
        @XmlJavaTypeAdapter(SimpleEnumAdapter.class)
        public EnumerationType value;

    }


    @Override
    public Map<Feldelementeigenschaften, EnumerationType> unmarshal(AdaptedFeldelementeigenschaftenMap v) throws Exception {
        Map<Feldelementeigenschaften, EnumerationType> map = new HashMap<Feldelementeigenschaften, EnumerationType>();
        for (FeldelementeigenschaftenEntry entry : v.getFeldelementeigenschaften()) {
            entry.setValue(v.getValue());
            entry.setKey(v.getKey());
            map.put(entry.key, entry.value);
        }
        return map;
    }

    @Override
    public AdaptedFeldelementeigenschaftenMap marshal(Map<Feldelementeigenschaften, EnumerationType> v) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
