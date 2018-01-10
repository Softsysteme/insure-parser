package adapters;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import adapters.SimpleEnumAdapter.AdaptedSimpleEnum;
import insure.SimpleEnum;
import insure.infoservice.feldsteuerung.EingabeelementLiterals;
import insure.infoservice.feldsteuerung.EingabeelementeigenschaftLiterals;
import insure.infoservice.feldsteuerung.SteuerelementLiterals;
import insure.infoservice.feldsteuerung.SteuerelementeigenschaftLiterals;
import tools.GlobalEnumMapFactory;

public class SimpleEnumAdapter extends XmlAdapter<AdaptedSimpleEnum, SimpleEnum> {

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

        public String getBeschreibung() {
            return beschreibung;
        }

        public void setBeschreibung(String beschreibung) {
            this.beschreibung = beschreibung;
        }

        public Boolean isNotwendig() {
            return notwendig;
        }

        public void setNotwendig(Boolean notwendig) {
            this.notwendig = notwendig;
        }

        public Boolean isEditierbar() {
            return editierbar;
        }

        public void setEditierbar(Boolean editierbar) {
            this.editierbar = editierbar;
        }

        public Boolean isSichtbar() {
            return sichtbar;
        }

        public void setSichtbar(Boolean sichtbar) {
            this.sichtbar = sichtbar;
        }

        public Boolean isAktiviert() {
            return aktiviert;
        }

        public void setAktiviert(Boolean aktiviert) {
            this.aktiviert = aktiviert;
        }

        @XmlAttribute(name = "name")
        public String name;

        @XmlAttribute(name = "beschreibung")
        public String beschreibung;

        @XmlAttribute(name = "notwendig")
        public Boolean notwendig;

        @XmlAttribute(name = "editierbar")
        public Boolean editierbar;

        @XmlAttribute(name = "sichtbar")
        public Boolean sichtbar;

        @XmlAttribute(name = "aktiviert")
        public Boolean aktiviert;

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

        public Boolean getNotwendig() {
            return notwendig;
        }

        public Boolean getEditierbar() {
            return editierbar;
        }

        public Boolean getSichtbar() {
            return sichtbar;
        }

        public Boolean getAktiviert() {
            return aktiviert;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

    }

    @Override
    public SimpleEnum unmarshal(AdaptedSimpleEnum v) throws Exception {
        AdaptedSimpleEnum adapted = (AdaptedSimpleEnum) v;

        String atype = adapted.getType();

        if (adapted.getName() != null) {
            if (atype != null) {
                if (atype.contains("insure")) {
                    adapted.getNameSpaceMap().put(atype.substring(0, atype.indexOf(":")), "de.adesso.ais.domainreference.tickets.");
                }
                atype = atype.substring(atype.indexOf(":") + 1);

                if (atype.equals("Eingabelement")) {
                    EingabeelementLiterals.getInstance();
                    GlobalEnumMapFactory.INSTANCE.getEnumMap().store(adapted.modelElementId, (SimpleEnum) EingabeelementLiterals.valueOf(adapted.getName()));
                    EingabeelementLiterals.getInstance();
                    return (SimpleEnum) EingabeelementLiterals.valueOf(adapted.getName());
                }

                if (atype.equals("Steuerelement")) {
                    SteuerelementLiterals.getInstance();
                    GlobalEnumMapFactory.INSTANCE.getEnumMap().store(adapted.modelElementId, (SimpleEnum) SteuerelementLiterals.valueOf(adapted.getName()));
                    SteuerelementLiterals.getInstance();
                    return (SimpleEnum) SteuerelementLiterals.valueOf(adapted.getName());
                }

                if (atype.equals("Steuerelementeigenschaft")) {
                    SteuerelementeigenschaftLiterals.getInstance();
                    GlobalEnumMapFactory.INSTANCE.getEnumMap().store(adapted.modelElementId, (SimpleEnum) SteuerelementeigenschaftLiterals.valueOf(adapted.getName()));
                    SteuerelementeigenschaftLiterals.getInstance();
                    return (SimpleEnum) SteuerelementeigenschaftLiterals.valueOf(adapted.getName());
                }

                if (atype.equals("Eingabeelementeigenschaft")) {
                    EingabeelementeigenschaftLiterals.getInstance();
                    GlobalEnumMapFactory.INSTANCE.getEnumMap().store(adapted.modelElementId, (SimpleEnum) EingabeelementeigenschaftLiterals.valueOf(adapted.getName()));
                    EingabeelementeigenschaftLiterals.getInstance();
                    return (SimpleEnum) EingabeelementeigenschaftLiterals.valueOf(adapted.getName());
                }
            }
        }

        return null;
    }

    @Override
    public AdaptedSimpleEnum marshal(SimpleEnum v) throws Exception {
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
