package tools;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMLSave;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLHelperImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLSaveImpl;

public class XXXResourceImpl extends XMIResourceImpl {

	public XXXResourceImpl(URI uri) {
		super(uri);
	}
	@Override
	protected XMLSave createXMLSave() {
		return new XMLSaveImpl(new XMLHelperImpl(this));
	}

	@Override
	protected boolean useUUIDs() {
		return false;
	}

	@Override
	protected boolean useIDs() {
		return false;
	}

}
