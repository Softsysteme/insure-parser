package parser;



import parser.objectVerarbeitung;

public class PrintObjects implements objectVerarbeitung{

	@Override
	public void verarbeite(Object element) {
		System.out.println(element.toString());
		
	}

}
