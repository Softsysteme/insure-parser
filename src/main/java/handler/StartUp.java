package handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
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
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.osgi.framework.Bundle;

import adapters.SuperRoot;
import insure.core.IPrototype;
import insure.core.IRepository;
import insure.core.ISimpleEnum;
import parser.PrintObjects;
import parser.XmlParser;

//import tools.DynamicJavaBuilder;

public class StartUp implements IApplication {

    private XmlParser parser;

    @Override
    public Object start(IApplicationContext context) throws Exception {
        String[] xmlPaths =
                new String[] { "/src/main/resources/model/infoservice.insure", "/src/main/resources/model/domain-reference.insure", "/src/main/resources/model/infoservice-reference.insure" };
        init("insure.core.modell", "de.adesso.ais.insure-parser", xmlPaths);
        return IApplication.EXIT_OK;

    }

    @Override
    public void stop() {
        // TODO Auto-generated msethod stub mergeFiles

    }

    public void init(String xsdBundleName, String xmlBundleName, String[] xmlpaths) {
        Class<?>[] elementClasses = { SuperRoot.class };
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
            parser.parseXml(reader, elementClasses[0], new PrintObjects());
            for (IRepository repo : XmlParser.getObjects().get(0).getRepositories()) {
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
                writer.write(createBuilder(xmlFiles[i], false).toString());
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
