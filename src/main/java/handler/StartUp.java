package handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.GenericXMLResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.osgi.framework.Bundle;

import insure.core.impl.Repository;
import insure.infoservice.feldsteuerung.impl.Eingabeelement;
import insure.infoservice.feldsteuerung.impl.Eingabeelementeigenschaft;
import insure.infoservice.feldsteuerung.impl.Feldsteuerung;
import insure.infoservice.feldsteuerung.impl.Steuerelement;
import insure.infoservice.feldsteuerung.impl.Steuerelementeigenschaft;
import insure.infoservice.feldsteuerung.impl.TemplateFeldelementeigenschaften;
import parser.PrintObjects;
import parser.XmlParser;

//import tools.DynamicJavaBuilder;

public class StartUp implements IApplication {

    private XmlParser parser;

    @Override
    public Object start(IApplicationContext context) throws Exception {

        // init("insure.core.modell", "de.adesso.ais.insure-parser", "/src/main/resources/model/infoservice.insure", "model/core.xsd", "insure.core.impl");
        init("insure.core.modell", "de.adesso.ais.insure-parser", "/src/main/resources/model/infoservice-reference.insure", "model/core.xsd", "insure.core.impl");
        return IApplication.EXIT_OK;

    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub

    }

    @SuppressWarnings("static-access")
    public void init(String xsdBundleName, String xmlBundleName, String xmlpath, String xsdpath, String packageName) {
        Class<?>[] elementClasses = { Eingabeelementeigenschaft.class, Feldsteuerung.class,
                Eingabeelement.class, Steuerelement.class,
                Steuerelementeigenschaft.class, Repository.class, TemplateFeldelementeigenschaften.class, };
        Bundle xsdbundle = Platform.getBundle(xsdBundleName);
        Bundle xmlbundle = Platform.getBundle(xmlBundleName);
        System.out.println(xmlbundle);

        URL xmlURL = xmlbundle.getEntry(xmlpath);
        File xmlfile = null;
        URL xsdURL = xsdbundle.getEntry(xsdpath);
        File xsdfile = null;
        Reader xml = null;
        try {
            xmlfile = new File(FileLocator.toFileURL(xmlURL).toURI());
            xml = new InputStreamReader(new FileInputStream(xmlfile), "UTF-8");
            xsdfile = new File(FileLocator.toFileURL(xsdURL).toURI());
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            URL packageURL = xsdbundle.getEntry("/src/insure/core/impl");
            XmlParser parser = new XmlParser();
            parser.parseXml(xsdfile.getPath(),
                xmlfile.getPath(), "UTF-8", xsdBundleName, packageName,
                new PrintObjects());
            // XmlParser parser = new XmlParser();
            // parser.parseXml(xml, elementClasses, new PrintObjects());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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

        // Register the package to ensure it is available during loading.
        //
        // resourceSet.getPackageRegistry().put(ParserPackage.eNS_URI, ParserPackage.eINSTANCE);
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new XMIResourceFactoryImpl());

        File oldfile = null;
        XMIResourceImpl resourcexmi = null;
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
            resourcexmi = new XMIResourceImpl(uri);
            // Map<Object, Object> options = new HashMap<Object, Object>();
            // options.put(XMIResource.OPTION_DECLARE_XML, true);

            try {
                resourcexmi.load(null);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("Loaded " + uri);

            // Validate the contents of the loaded resource.
            //
            // for (EObject eObject : resourcexmi.getContents()) {
            // Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eObject);
            // System.out.println(eObject.toString());
            // if (diagnostic.getSeverity() != Diagnostic.OK) {
            // printDiagnostic(diagnostic, "");
            // }
            // }
        } catch (RuntimeException exception) {
            System.out.println("Problem loading " + uri);
            exception.printStackTrace();
        }

        try {

            File newFile = new File(newpath);
            newFile.getParentFile().mkdirs();
            newFile.createNewFile();
            URI newuri = newFile.isFile() ? URI.createFileURI(newFile.getAbsolutePath()) : URI.createURI(newpath);
            // URI newuri = URI.createURI(newpath);
            XMIResourceImpl resourcexml = new XMIResourceImpl(newuri);
            // resourcexml = resourcexmi;
            // resourcexml = (XMLResourceImpl) resourceSet.createResource(newuri);

            // TODO add some content here

            resourcexml.getContents().addAll(resourcexmi.getContents());

            try {
                Map<Object, Object> options = new HashMap<Object, Object>();
                options.put(XMIResource.OPTION_SUPPRESS_XMI, true);
                // options.put(XMLResource.OPTION_PROCESS_DANGLING_HREF, true);
                options.put(XMIResource.OPTION_PROCESS_DANGLING_HREF_RECORD, "RECORD");
                // options.put(XMLResource.OPTION_DECLARE_XML, true);
                // options.put(XMIResource.OPTION_ANY_SIMPLE_TYPE, true);
                resourcexml.save(new FileOutputStream(newFile.getAbsolutePath()), options);

            } catch (IOException e) {
                java.lang.System.out.print("no");

            }

        } catch (RuntimeException | IOException exception) {
            System.out.println("Problem loading new resource");
            exception.printStackTrace();
        }
    }

    protected static void printDiagnostic(Diagnostic diagnostic, String indent) {
        System.out.print(indent);
        System.out.println(diagnostic.getMessage());
        for (Diagnostic child : diagnostic.getChildren()) {
            printDiagnostic(child, indent + " ");
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
