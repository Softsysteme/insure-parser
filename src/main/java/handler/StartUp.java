package handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.GenericXMLResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import JAXBandStAX.PrintObjects;
import JAXBandStAX.XmlToJavaParserMitStaxUndJaxb;
import tools.XXXResourceImpl;

//import tools.DynamicJavaBuilder;

public class StartUp implements IApplication {

	@Override
	public Object start(IApplicationContext context) throws Exception {

		convertXMItoXML("src/main/resources/model/My.packagea", "src/main/resources/model/package.xml");

		try {
			XmlToJavaParserMitStaxUndJaxb.parseXml(readFile("src/main/resources/model/parser.xsd", "UTF-8"),
					readFile("src/main/resources/model/package.xml", "ASCII"), "ASCII", "packageA", new PrintObjects());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return IApplication.EXIT_OK;

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	public void convertXMItoXML(String oldpath, String newpath) {
		Bundle bundle = Platform.getBundle("de.adesso.ais.insure-parser");
		// Create a resource set to hold the resources.
		//
		ResourceSet resourceSet = new ResourceSetImpl();
		// Register the appropriate resource factory to handle all file extensions.
		//
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(XMLResource.OPTION_EXTENDED_META_DATA,
				new GenericXMLResourceFactoryImpl());

		File oldfile = null;
		XXXResourceImpl resourcexmi = null;
		try {
			oldfile = new File((FileLocator.resolve(bundle.getEntry(oldpath)).toURI()));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		URI uri = oldfile.isFile() ? URI.createFileURI(oldfile.getAbsolutePath()) : URI.createURI(oldpath);

		try {
			// Demand load resource for this file.
			//
			resourcexmi = new XXXResourceImpl(uri);
			System.out.println("Loaded " + uri);

			// Validate the contents of the loaded resource.
			//
			for (EObject eObject : resourcexmi.getContents()) {
				Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eObject);
				if (diagnostic.getSeverity() != Diagnostic.OK) {
					printDiagnostic(diagnostic, "");
				}
			}
		} catch (RuntimeException exception) {
			System.out.println("Problem loading " + uri);
			exception.printStackTrace();
		}

		try {

			URI newuri = URI.createURI("http://de.adesso.ais.parser");
			XMLResourceImpl resourcexml = new XMLResourceImpl(newuri);
			resourcexml = (XMLResourceImpl) resourceSet.createResource(newuri);

			// TODO add some content here
			resourcexml.getContents().addAll(resourcexmi.getContents());
			
			try {
				Map<Object, Object> options = new HashMap<Object, Object>();
				options.put(XMIResource.OPTION_SUPPRESS_XMI, true);
				resourcexml.save(new FileOutputStream(bundle.getLocation() + newpath), options);

			} catch (IOException e) {
				java.lang.System.out.print("no");

			}
		} catch (RuntimeException exception) {
			System.out.println("Problem loading new resource");
			exception.printStackTrace();
		}
	}

	protected static void printDiagnostic(Diagnostic diagnostic, String indent) {
		System.out.print(indent);
		System.out.println(diagnostic.getMessage());
		for (Diagnostic child : diagnostic.getChildren()) {
			printDiagnostic(child, indent + "  ");
		}
	}

	String readFile(String path, String encoding) throws IOException {
		Bundle bundle = Platform.getBundle("de.adesso.ais.insure-parser");
		byte[] encoded = null;
		try {
			encoded = Files.readAllBytes(new java.io.File(FileLocator.resolve(bundle.getEntry(path)).toURI()).toPath());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(encoded, encoding);
	}

}
