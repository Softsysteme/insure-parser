package adapters;

public class FeldelementeigenschaftenMapAdapter {// extends XmlAdapter<AdaptedFeldelementeigenschaftenMap, Map<Feldelementeigenschaften, EnumerationType>> {
    // @XmlAccessorType(XmlAccessType.FIELD)
    // public static class AdaptedFeldelementeigenschaftenMap {
    // // @XmlElements({ @XmlElement(name = "key"), @XmlElement(name = "value") })
    // @XmlElement
    // public List<FeldelementeigenschaftenEntry> feldelementeigenschaften;
    //
    //
    // @XmlAttribute(name = "value")
    // public EnumerationType value;
    //
    //
    // @XmlElement(name = "key")
    // public Feldelementeigenschaften key;
    //
    // public Feldelementeigenschaften getKey() {
    // return key;
    // }
    //
    // public void setKey(Feldelementeigenschaften key) {
    // this.key = key;
    // }
    //
    // public EnumerationType getValue() {
    // return value;
    // }
    //
    // public void setValue(EnumerationType value) {
    // this.value = value;
    // }
    //
    // public List<FeldelementeigenschaftenEntry> getFeldelementeigenschaften() {
    // if (feldelementeigenschaften == null) {
    // return new ArrayList<FeldelementeigenschaftenEntry>();
    // }
    // return feldelementeigenschaften;
    // }
    //
    // public void setFeldelementeigenschaften(List<FeldelementeigenschaftenEntry> entry) {
    // this.feldelementeigenschaften = entry;
    // }
    //
    // }
    //
    // @XmlAccessorType(XmlAccessType.FIELD)
    // public static class FeldelementeigenschaftenEntry {
    //
    // public Feldelementeigenschaften getKey() {
    // return key;
    // }
    //
    // public void setKey(Feldelementeigenschaften key) {
    // this.key = key;
    // }
    //
    // public EnumerationType getValue() {
    // return value;
    // }
    //
    // public void setValue(EnumerationType value) {
    // this.value = value;
    // }
    //
    //
    // @XmlElement(name = "key")
    // public Feldelementeigenschaften key;
    //
    //
    // @XmlAttribute(name = "value")
    // public EnumerationType value;
    //
    // }
    //
    //
    // @Override
    // public Map<Feldelementeigenschaften, EnumerationType> unmarshal(AdaptedFeldelementeigenschaftenMap v) throws Exception {
    // Map<Feldelementeigenschaften, EnumerationType> map = new HashMap<Feldelementeigenschaften, EnumerationType>();
    // for (FeldelementeigenschaftenEntry entry : v.getFeldelementeigenschaften()) {
    // entry.setValue(v.getValue());
    // entry.setKey(v.getKey());
    // map.put(entry.key, entry.value);
    // }
    // return map;
    // }
    //
    // @Override
    // public AdaptedFeldelementeigenschaftenMap marshal(Map<Feldelementeigenschaften, EnumerationType> v) throws Exception {
    // // TODO Auto-generated method stub
    // return null;
    // }

}
