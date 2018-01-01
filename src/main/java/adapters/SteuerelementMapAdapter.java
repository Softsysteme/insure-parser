package adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import adapters.SteuerelementMapAdapter.AdaptedSteuerelementMap;
import insure.infoservice.feldsteuerung.ISteuerelement;
import insure.infoservice.feldsteuerung.ISteuerelementeigenschaft;
import insure.infoservice.feldsteuerung.impl.Steuerelementeigenschaft;

public class SteuerelementMapAdapter extends XmlAdapter<AdaptedSteuerelementMap, Map<ISteuerelement, ISteuerelementeigenschaft>> {
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AdaptedSteuerelementMap {

        @XmlElements({ @XmlElement(name = "steuerelementeigenschaften", type = SteuerelementeigenschaftenEntry.class), @XmlElement(name = "value") })
        public List<SteuerelementeigenschaftenEntry> steuerelementeigenschaften;

        public void setSteuerelementeigenschaften(List<SteuerelementeigenschaftenEntry> steuerelementeigenschaften) {
            this.steuerelementeigenschaften = steuerelementeigenschaften;
        }

        public List<SteuerelementeigenschaftenEntry> getSteuerelementeigenschaften() {
            if (steuerelementeigenschaften == null) {
                return new ArrayList<SteuerelementeigenschaftenEntry>();
            }
            return steuerelementeigenschaften;
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class SteuerelementeigenschaftenEntry {

        public ISteuerelement getKey() {
            return key;
        }

        public void setKey(ISteuerelement key) {
            this.key = key;
        }

        public Value getValue() {
            return value;
        }

        public void setValue(Value value) {
            this.value = value;
        }

        @XmlIDREF
        @XmlAttribute
        @XmlJavaTypeAdapter(SimpleEnumAdapter.class)
        public ISteuerelement key;

        public Value value;

        @XmlType(namespace = "adapters.SteuerelementMapAdapter.SteuerelementeigenschaftenEntry.Value")
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class Value {
            @XmlIDREF
            @XmlElement(name = "value", required = true)
            @XmlJavaTypeAdapter(SimpleEnumAdapter.class)
            private Steuerelementeigenschaft value;

            public Steuerelementeigenschaft getValue() {
                return value;
            }

            public void setValue(Steuerelementeigenschaft value) {
                this.value = value;
            }
        }

    }

    @Override
    public Map<ISteuerelement, ISteuerelementeigenschaft> unmarshal(AdaptedSteuerelementMap adaptedMap) throws Exception {
        Map<ISteuerelement, ISteuerelementeigenschaft> map = new HashMap<ISteuerelement, ISteuerelementeigenschaft>();
        for (SteuerelementeigenschaftenEntry mapEntryType : adaptedMap.getSteuerelementeigenschaften()) {
            if (mapEntryType.getKey() != null || mapEntryType.getValue() != null)
                map.put(mapEntryType.getKey(), mapEntryType.getValue().value);
        }

        return map;
    }

    @Override
    public AdaptedSteuerelementMap marshal(Map<ISteuerelement, ISteuerelementeigenschaft> v) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
