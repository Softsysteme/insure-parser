package adapters;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import adapters.SimpleEnumAdapter.AdaptedSimpleEnum;
import insure.core.ISimpleEnum;
import insure.core.impl.EnumerationType;
import insure.infoservice.feldsteuerung.impl.Eingabeelement;
import insure.infoservice.feldsteuerung.impl.Eingabeelementeigenschaft;
import insure.infoservice.feldsteuerung.impl.Steuerelement;
import insure.infoservice.feldsteuerung.impl.Steuerelementeigenschaft;

public class SimpleEnumAdapter extends XmlAdapter<AdaptedSimpleEnum, ISimpleEnum> {

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AdaptedSimpleEnum extends AdaptedClass {
        public AdaptedSimpleEnum() {
            this.nameSpaceMap = new HashMap<String, String>();
            nameSpaceMap.put("core", "insure.core.");
            nameSpaceMap.put("enums", "de.adesso.ais.domainreference.");
            nameSpaceMap.put("enums_1", "insure.domain.");
            nameSpaceMap.put("feldsteuerung", "insure.infoservice.");
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
        protected Integer key;
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
    public ISimpleEnum unmarshal(AdaptedSimpleEnum v) throws Exception {

        AdaptedSimpleEnum adapted = (AdaptedSimpleEnum) v;
        // if (null == adapted) {
        // return null;
        // }
        // String href = adapted.getHref();
        // if (adapted.getName() == null && adapted.getBeschreibung() == null && adapted.getModelElementId() == null) {
        // if (href != null) {
        // href = (href.substring(href.lastIndexOf("#")));
        // System.out.println(href + lookup.containsKey(href));
        // if (lookup.containsKey(href)) {
        // ISimpleEnum ref = (ISimpleEnum) lookup.get(href);
        // if (ref instanceof Eingabeelementeigenschaft) {
        // return (Eingabeelementeigenschaft) ref;
        // }
        // if (ref instanceof Steuerelementeigenschaft) {
        // return (Steuerelementeigenschaft) ref;
        // }
        // if (ref instanceof Steuerelement) {
        // return (Steuerelement) ref;
        // }
        // if (ref instanceof Eingabeelement) {
        // return (Eingabeelement) ref;
        // }
        //
        // }
        // }
        //
        // }

        if (adapted.getType() != null) {
            if (adapted.getKey() != null && !adapted.getType().contains("MaskensteuerungIdentifier") && !adapted.getType().contains("SchnittstellensteuerungIdentifier")) {
                if (adapted.getType().contains("insure")) {
                    adapted.getNameSpaceMap().put(adapted.getType().substring(0, adapted.getType().indexOf(":")), "de.adesso.ais.domainreference.entity.tickets");
                }

                Class<?> clazz = Class.forName(FullyQualifiedName(adapted.getType(), adapted.getNameSpaceMap()));
                Constructor<?> ctor = clazz.getDeclaredConstructor();
                EnumerationType ident = (EnumerationType) ctor.newInstance();
                ident.setBeschreibung(adapted.getBeschreibung());
                ident.setName(adapted.getName());
                ident.setModelElementId(adapted.getModelElementId());
                ident.setKey(adapted.getKey() != null ? adapted.getKey() : null);
                /// insure.domain.modell/src/insure/domain/enums/impl/Zahlweise.java
                return ident;

            }

            if (adapted.getType().contains("Steuerelementeigenschaft")) {
                Steuerelementeigenschaft eig = new Steuerelementeigenschaft();
                eig.setBeschreibung(adapted.getBeschreibung());
                eig.setName(adapted.getName());
                eig.setModelElementId(adapted.getModelElementId());
                eig.setAktiviert(adapted.isAktiviert() != null ? adapted.isAktiviert() : false);
                eig.setSichtbar(adapted.isSichtbar() != null ? adapted.isSichtbar() : false);
                return eig;
            }

            if (adapted.getType().endsWith("Steuerelement")) {
                Steuerelement steuer = new Steuerelement();
                steuer.setBeschreibung(adapted.getBeschreibung());
                steuer.setName(adapted.getName());
                steuer.setModelElementId(adapted.getModelElementId());

                return steuer;
            }

            if (v.getType().endsWith("eingabeelement")) {
                Eingabeelement eing = new Eingabeelement();
                eing.setBeschreibung(adapted.getBeschreibung());
                eing.setName(adapted.getName());
                eing.setModelElementId(adapted.getModelElementId());

                return eing;
            }

            if (adapted.getType().contains("Eingabeelementeigenschaft")) {
                Eingabeelementeigenschaft eig = new Eingabeelementeigenschaft();
                eig.setBeschreibung(adapted.getBeschreibung());
                eig.setName(adapted.getName());
                eig.setModelElementId(adapted.getModelElementId());
                eig.setEditierbar(adapted.isEditierbar() != null ? adapted.isEditierbar() : false);
                eig.setSichtbar(adapted.isSichtbar() != null ? adapted.isSichtbar() : false);
                eig.setNotwendig(adapted.isNotwendig() != null ? adapted.isNotwendig() : false);

                return eig;

            }
        }

        return null;
    }

    @Override
    public AdaptedSimpleEnum marshal(ISimpleEnum v) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    public String FullyQualifiedName(String typefromxml, Map<String, String> map) {
        String newType = typefromxml;
        newType = newType.replaceAll(":", ".impl.");
        String h = map.get(typefromxml.substring(0, typefromxml.indexOf(":")));
        h += newType;
        return h;
    }

}
