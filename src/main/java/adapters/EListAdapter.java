package adapters;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;

import adapters.EListAdapter.AdaptedElist;

public class EListAdapter extends XmlAdapter<AdaptedElist, EList<Object>> {

    public static class AdaptedElist {

        @XmlElement
        @XmlElementWrapper
        List<Object> entries = new ArrayList<>();

        public List<Object> getEntries() {
            return entries;
        }
    }

    @Override
    public EList<Object> unmarshal(AdaptedElist v) throws Exception {
        @SuppressWarnings("unchecked")
        EList<Object> list = new EObjectContainmentEList<Object>(Object.class, null, 0);
        list.addAll(v.getEntries());

        return list;
    }

    @Override
    public AdaptedElist marshal(EList<Object> v) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }


}
