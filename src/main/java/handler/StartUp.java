package handler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import JAXBandStAX.PrintObjects;
import JAXBandStAX.XmlToJavaParserMitStaxUndJaxb;

//import tools.DynamicJavaBuilder;

public class StartUp implements IApplication {

	@Override
	public Object start(IApplicationContext context) throws Exception {
		// IPath path = new Path("model.parser.genmodel");
		// Ecore2XSDGenerator gen = new
		// Ecore2XSDGenerator(ResourcesPlugin.getWorkspace().getRoot().getFile(path));
		// File targetDir = new File("model/parser.xml");
		// gen.setTargetDir(targetDir);
		// gen.execute();
		
		
		
		
		
		 //System.out.println(novo.getAllContents().toString());

		// Bundle bundle = Platform.getBundle("de.adesso.ais.insure-parser");
		Bundle bundle = FrameworkUtil.getBundle(getClass());
		//ClassLoader loader = Thread.currentThread().getContextClassLoader();
	
		System.out.println(new java.io.File(FileLocator.resolve(bundle.getEntry("src/main/resources/model/parser.xsd")).toURI()));
		System.out.println(new java.io.File(FileLocator.resolve(bundle.getEntry("src/main/resources/model/parser.xsd")).toURI()).exists());
		try {
			XmlToJavaParserMitStaxUndJaxb.parseXml(readFile("src/main/resources/model/parser.xsd", "UTF-8"),
					readFile("src/main/resources/model/My.packagea", "UTF-8"), "UTF-8", "packageA", new PrintObjects());
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
	
	public void convertXMItoXML(String path) {
		Bundle bundle = Platform.getBundle("de.adesso.ais.insure-parser");
		// Register the XMI resource factory for the .website extension

		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        Map<String, Object> m = reg.getExtensionToFactoryMap();
        m.put("*", new XMIResourceFactoryImpl());
     // Obtain a new resource set
        ResourceSet resSet = new ResourceSetImpl();

        // Get the resource
        Resource resource = null;
		try {
			resource = resSet.getResource(URI.createURI(new java.io.File(FileLocator.resolve(bundle.getEntry(path)).toURI()).getPath()), true);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	try {
    		resource.load(null);
    	}
    	catch (IOException exception) {
    		throw new WrappedException(exception);
    	}
    	
		 //XMIResourceImpl old = new XMIResourceImpl(uri);
		 
		 
		 
		 Resource.Factory.Registry reg2 = Resource.Factory.Registry.INSTANCE;
	        Map<String, Object> map2 = reg2.getExtensionToFactoryMap();
	        map2.put("*", new XMLResourceFactoryImpl());

	        // Obtain a new resource set
	        ResourceSet resSet2 = new ResourceSetImpl();

	        // create a resource
	        Resource resource2 = resSet2.createResource(URI
	                .createURI("src/main/resources/model/my.package.xml"));
	        // Get the first model element and cast it to the right type, in my
	        // example everything is hierarchical included in this first node
	        resource2.getContents().add(resource.getContents().get(0));

	        // now save the content.
	        try {
	            resource.save(Collections.EMPTY_MAP);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
	
		 
		 //XMLResourceImpl novo = old;
	

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
