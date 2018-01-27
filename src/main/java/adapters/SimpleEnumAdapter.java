package adapters;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import adapters.SimpleEnumAdapter.AdaptedSimpleEnum;
import caches.InsureParserCacheManager;
import insure.core.IEnumeration;
import insure.infoservice.feldsteuerung.EingabeelementLiterals;
import insure.infoservice.feldsteuerung.EingabeelementeigenschaftLiterals;
import insure.infoservice.feldsteuerung.SteuerelementLiterals;
import insure.infoservice.feldsteuerung.SteuerelementeigenschaftLiterals;

public class SimpleEnumAdapter extends XmlAdapter<AdaptedSimpleEnum, IEnumeration> {

    InsureParserCacheManager cm = InsureParserCacheManager.INSTANCE;

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AdaptedSimpleEnum extends AdaptedClass {
        public AdaptedSimpleEnum() {
            this.nameSpaceMap = new HashMap<String, String>();
            nameSpaceMap.put("core", "insure.core.");
            nameSpaceMap.put("enums", "de.adesso.ais.domainreference.");
            nameSpaceMap.put("enums_1", "insure.domain.");
            nameSpaceMap.put("feldsteuerung", "insure.infoservice.");
            nameSpaceMap.put("konfiguration", "de.adesso.ais.domainreference.prototype.");

        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        @XmlAttribute
        public String href;
        @XmlAttribute
        public String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @XmlAttribute(name = "name")
        public String name;

        @XmlAttribute(name = "key")
        public Integer key;

        public Map<String, String> nameSpaceMap;

        public Map<String, String> getNameSpaceMap() {
            if (nameSpaceMap == null) {
                return new HashMap<String, String>();
            }
            return nameSpaceMap;
        }

        public void setNameSpaceMap(Map<String, String> nameSpaceMap) {
            this.nameSpaceMap = nameSpaceMap;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

    }

    @SuppressWarnings({ "static-access" })
    @Override
    public IEnumeration unmarshal(AdaptedSimpleEnum v) throws Exception {

        AdaptedSimpleEnum adapted = v;

        String atype = adapted.getType();

        if (adapted.getName() != null) {
            if (atype != null) {
                if (atype.contains("insure")) {
                    adapted.getNameSpaceMap().put(atype.substring(0, atype.indexOf(":")), "de.adesso.ais.domainreference.tickets.");
                }
                atype = atype.substring(atype.indexOf(":") + 1);

                if (atype.equals("Eingabelement")) {
                    EingabeelementLiterals INSTANCE = EingabeelementLiterals.getInstance();
                    cm.putInCache(adapted.modelElementId, INSTANCE.valueOf(adapted.getName()));
                    return INSTANCE.valueOf(adapted.getName());
                }

                if (atype.equals("Steuerelement")) {
                    SteuerelementLiterals INSTANCE = SteuerelementLiterals.getInstance();
                    cm.putInCache(adapted.modelElementId, INSTANCE.valueOf(adapted.getName()));
                    return INSTANCE.valueOf(adapted.getName());
                }

                if (atype.equals("Steuerelementeigenschaft")) {
                    SteuerelementeigenschaftLiterals INSTANCE = SteuerelementeigenschaftLiterals.getInstance();
                    cm.putInCache(adapted.modelElementId, INSTANCE.valueOf(adapted.getName()));
                    return INSTANCE.valueOf(adapted.getName());
                }

                if (atype.equals("Eingabeelementeigenschaft")) {
                    EingabeelementeigenschaftLiterals INSTANCE = EingabeelementeigenschaftLiterals.getInstance();
                    cm.putInCache(adapted.modelElementId, INSTANCE.valueOf(adapted.getName()));
                    return INSTANCE.valueOf(adapted.getName());
                }
            }
        }

        return null;
    }

    @Override
    public AdaptedSimpleEnum marshal(IEnumeration v) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    public String FullyQualifiedName(String typefromxml, Map<String, String> map) {
        String newType = typefromxml;
        newType = newType.replaceAll("enums_1", "enums");
        newType = newType.replaceAll(":", ".");
        String h = map.get(typefromxml.substring(0, typefromxml.indexOf(":")));
        h += newType;
        return h;
    }

}
