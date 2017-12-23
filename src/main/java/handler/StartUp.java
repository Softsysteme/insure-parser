package handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.GenericXMLResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.osgi.framework.Bundle;

import adapters.SuperRoot;
import insure.core.IPrototype;
import insure.core.IRepository;
import insure.core.ISimpleEnum;
import insure.core.impl.Repository;
import insure.infoservice.feldsteuerung.impl.Eingabeelement;
import insure.infoservice.feldsteuerung.impl.Eingabeelementeigenschaft;
import insure.infoservice.feldsteuerung.impl.Feldelementeigenschaften;
import insure.infoservice.feldsteuerung.impl.Feldsteuerung;
import insure.infoservice.feldsteuerung.impl.FeldsteuerungIdentifier;
import insure.infoservice.feldsteuerung.impl.StandardFeldelementeigenschaften;
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
        String[] xmlPaths =
                new String[] { "/src/main/resources/model/infoservice-reference.insure" };
        // init("insure.core.modell", "de.adesso.ais.insure-parser", "/src/main/resources/model/infoservice.insure", "model/core.xsd", "insure.core.impl");
        init("insure.core.modell", "de.adesso.ais.insure-parser", xmlPaths);
        return IApplication.EXIT_OK;

    }

    @Override
    public void stop() {
        // TODO Auto-generated msethod stub mergeFiles

    }

    public void init(String xsdBundleName, String xmlBundleName, String[] xmlpaths) {
        Class<?>[] elementClasses = { Eingabeelementeigenschaft.class, Feldsteuerung.class, Feldsteuerung.class, Feldelementeigenschaften.class,
                StandardFeldelementeigenschaften.class, FeldsteuerungIdentifier.class, Eingabeelement.class, Steuerelement.class,
                Steuerelementeigenschaft.class, Repository.class, TemplateFeldelementeigenschaften.class, SuperRoot.class };
        // Bundle xsdbundle = Platform.getBundle(xsdBundleName);
        Bundle xmlbundle = Platform.getBundle(xmlBundleName);
        URL[] xmlURL = new URL[xmlpaths.length];
        File[] xmlFiles = new File[xmlpaths.length];
        for (int i = 0; i < xmlpaths.length; i++) {
            xmlURL[i] = xmlbundle.getEntry(xmlpaths[i]);
            try {
                xmlFiles[i] = new File(FileLocator.toFileURL(xmlURL[i]).toURI());
                xmlFiles[i] = supressXsi(xmlFiles[i], "/src/main/resources/model/modified" + i);

            } catch (URISyntaxException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
        try {
            Reader reader = new InputStreamReader(new FileInputStream(mergeFiles(xmlFiles)), "UTF-8");
            XmlParser parser = new XmlParser();
            parser.parseXml(reader, elementClasses[11], new PrintObjects());
            // System.out.println(parser.getObjects().get(0).toString() + "fdxcgvcn");"
            for (IRepository repo : parser.getObjects().get(0).getRepositories()) {
                System.out.println(repo.getName());
                for (IRepository reposit : repo.getRepositories())
                    System.out.println(reposit.toString());
                for (IPrototype prot : repo.getPrototypes())
                    System.out.println(prot.toString());
                for (ISimpleEnum enu : repo.getEnumerations())
                    System.out.println(enu.toString());
                ;
                // System.out.println(repo.getPrototypes().toString());
            }
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

            try {
                resourcexmi.load(null);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("Loaded " + uri);

        } catch (RuntimeException exception) {
            System.out.println("Problem loading " + uri);
            exception.printStackTrace();
        }

        try {

            File newFile = new File(newpath);
            newFile.getParentFile().mkdirs();
            newFile.createNewFile();
            URI newuri = newFile.isFile() ? URI.createFileURI(newFile.getAbsolutePath()) : URI.createURI(newpath);

            XMIResourceImpl resourcexml = new XMIResourceImpl(newuri);

            // TODO add some content here

            resourcexml.getContents().addAll(resourcexmi.getContents());

            try {

                resourcexml.save(new FileOutputStream(newFile.getAbsolutePath()), null);

            } catch (IOException e) {
                java.lang.System.out.print("no");

            }

        } catch (RuntimeException | IOException exception) {
            System.out.println("Problem loading new resource");
            exception.printStackTrace();
        }
    }

    @SuppressWarnings("null")
    public File supressXsi(File inputXML, String outputPath) {

        BufferedReader br = null;
        String newString = "";
        StringBuilder strTotale = new StringBuilder();
        try {

            FileReader reader = new FileReader(inputXML);
            String search = "xsi:type";

            br = new BufferedReader(reader);
            while ((newString = br.readLine()) != null) {
                newString = newString.replaceAll(search, "type");
                if (newString.contains("href=")) {
                    newString = newString.replace(newString.substring((newString.indexOf("=") + 2), newString.indexOf("#") + 1), "");
                }
                // newString = newString.replaceAll("href", "");
                strTotale.append(newString + '\n');
            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // calls it
        finally {
            try {
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        File outputFile = new File(outputPath);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write(strTotale.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (writer != null)
                try {
                    writer.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }

        return outputFile;
    }

    public File mergeFiles(File[] xmlFiles) {
        File outputFile = new File("/src/main/resources/model/merged-file.xml");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write("<?xml version=" + "\"1.0\" " + "encoding=" + "\"UTF-8\"" + "?>" + '\n');
            writer.write(("<SuperRoot>" + '\n'));
            for (int i = 0; i < xmlFiles.length; i++) {
                writer.write(i == 0 ? createBuilder(xmlFiles[i], false).toString() : createBuilder(xmlFiles[i], false).toString());
            }
            writer.write(("</SuperRoot>" + '\n'));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (writer != null)
                try {
                    writer.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }

        BufferedReader br = null;
        String newString = "";
        StringBuilder strTotale = new StringBuilder();
        try {

            FileReader reader = new FileReader(outputFile);
            br = new BufferedReader(reader);
            while ((newString = br.readLine()) != null) {
                strTotale.append(newString + '\n');
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // calls it
        finally {
            System.out.println(strTotale.toString());
            try {
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return outputFile;

    }

    public StringBuilder createBuilder(File xmlFile, boolean removeXmlTag) {
        BufferedReader br = null;
        String newString = "";
        StringBuilder strTotale = new StringBuilder();
        try {

            FileReader reader = new FileReader(xmlFile);
            br = new BufferedReader(reader);
            while ((newString = br.readLine()) != null) {
                if (newString.contains("?xml") && !removeXmlTag) {
                    strTotale.append("" + '\n');
                } else {
                    strTotale.append(newString + '\n');
                }
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // calls it
        finally {
            try {
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return strTotale;

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
