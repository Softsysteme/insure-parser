package adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import adapters.XmlSteuerelementMapAdapter.MapType2;

public class XmlSteuerelementMapAdapter<Steuerelement, Steuerelementeigenschaft>
        extends XmlAdapter<MapType2<Steuerelement, Steuerelementeigenschaft>, Map<Steuerelement, Steuerelementeigenschaft>> {

    @Override
    public Map<Steuerelement, Steuerelementeigenschaft> unmarshal(MapType2<Steuerelement, Steuerelementeigenschaft> v) throws Exception {
        Map<Steuerelement, Steuerelementeigenschaft> map = new HashMap<Steuerelement, Steuerelementeigenschaft>();

        for (MapEntryType2<Steuerelement, Steuerelementeigenschaft> mapEntryType : v.getEntry()) {
            map.put(mapEntryType.getKey(), mapEntryType.getValue());
        }
        return map;
    }

    @Override
    public MapType2<Steuerelement, Steuerelementeigenschaft> marshal(Map<Steuerelement, Steuerelementeigenschaft> v) throws Exception {
        MapType2<Steuerelement, Steuerelementeigenschaft> mapType = new MapType2<Steuerelement, Steuerelementeigenschaft>();

        for (Map.Entry<Steuerelement, Steuerelementeigenschaft> entry : v.entrySet()) {
            MapEntryType2<Steuerelement, Steuerelementeigenschaft> mapEntryType = new MapEntryType2<Steuerelement, Steuerelementeigenschaft>();
            mapEntryType.setKey(entry.getKey());
            mapEntryType.setValue(entry.getValue());
            mapType.getEntry().add(mapEntryType);
        }
        return mapType;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class MapType2<Steuerelement, Steuerelementeigenschaft> {
        @XmlElement(name = "steuerelementeigenschaften")
        private List<MapEntryType2<Steuerelement, Steuerelementeigenschaft>> entry = new ArrayList<MapEntryType2<Steuerelement, Steuerelementeigenschaft>>();

        public MapType2() {
        }

        public MapType2(Map<Steuerelement, Steuerelementeigenschaft> map) {
            for (Map.Entry<Steuerelement, Steuerelementeigenschaft> e : map.entrySet()) {
                entry.add(new MapEntryType2<Steuerelement, Steuerelementeigenschaft>(e));
            }
        }


        public List<MapEntryType2<Steuerelement, Steuerelementeigenschaft>> getEntry() {
            return entry;
        }

        public void setEntry(List<MapEntryType2<Steuerelement, Steuerelementeigenschaft>> entry) {
            this.entry = entry;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class MapEntryType2<Steuerelement, Steuerelementeigenschaft> {
        @XmlElement(name = "key")
        private Steuerelement key;

        @XmlElement(name = "value")
        private Steuerelementeigenschaft value;

        public MapEntryType2() {
        }

        public MapEntryType2(Map.Entry<Steuerelement, Steuerelementeigenschaft> e) {
            key = e.getKey();
            value = e.getValue();
        }


        public Steuerelement getKey() {
            return key;
        }

        public void setKey(Steuerelement key) {
            this.key = key;
        }


        public Steuerelementeigenschaft getValue() {
            return value;
        }

        public void setValue(Steuerelementeigenschaft value) {
            this.value = value;
        }
    }
}
