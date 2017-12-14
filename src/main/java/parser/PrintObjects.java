package parser;



import org.eclipse.emf.ecore.impl.BasicEObjectImpl;

public class PrintObjects implements objectVerarbeitung{

	@Override
	public void verarbeite(Object element) {
        System.out.println(element.toString() + "" + ((BasicEObjectImpl) element).eContents().toString());

	}

}
