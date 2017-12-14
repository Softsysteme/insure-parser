package adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import insure.infoservice.feldsteuerung.IEingabeelement;
import insure.infoservice.feldsteuerung.IEingabeelementeigenschaft;
import insure.infoservice.feldsteuerung.impl.Eingabeelementeigenschaft;


public class EingabeelementMapAdapter extends XmlAdapter<EingabeelementMapAdapter.AdaptedMap, Map<IEingabeelement, IEingabeelementeigenschaft>> {
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AdaptedMap {
        @XmlElement(name = "eingabeelementeigenschaften")
        public List<Entry> eingabeelementeigenschaften = new ArrayList<Entry>();

        @XmlMixed
        public List<Entry> getEntry() {
            return eingabeelementeigenschaften;
        }

        public void setEntry(List<Entry> entry) {
            this.eingabeelementeigenschaften = entry;
        }

    }

    @XmlRootElement(name = "value")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Entry {

        public IEingabeelement getKey() {
            return key;
        }

        public void setKey(IEingabeelement key) {
            this.key = key;
        }

        public Eingabeelementeigenschaft getValue() {
            return value;
        }

        public void setValue(Eingabeelementeigenschaft value) {
            this.value = value;
        }
        public IEingabeelement key;
        @XmlElement(name = "value")
        public Eingabeelementeigenschaft value;

    }

    @Override
    public Map<IEingabeelement, IEingabeelementeigenschaft> unmarshal(AdaptedMap adaptedMap) throws Exception {
        Map<IEingabeelement, IEingabeelementeigenschaft> map = new HashMap<IEingabeelement, IEingabeelementeigenschaft>();
        for (Entry entry : adaptedMap.eingabeelementeigenschaften) {
            map.put(entry.key, entry.value);
        }
        return map;
    }

    @Override
    public AdaptedMap marshal(Map<IEingabeelement, IEingabeelementeigenschaft> map) throws Exception {
        return null;

    }
}
