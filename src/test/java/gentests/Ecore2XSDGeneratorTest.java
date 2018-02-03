package gentests;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.junit.Test;

import tools.Ecore2XSDGenerator;

public class Ecore2XSDGeneratorTest {
    Ecore2XSDGenerator gen;

    public Ecore2XSDGeneratorTest() {

    }

    @Test
    public void xsdDataWasCorrectlyCreatedFromEcore() {

        Ecore2XSDGenerator gen = new Ecore2XSDGenerator();
        String[] ecorePaths = { "domain-reference.ecore" };
        try {
            gen.exportEcoreToXSD(createResourceSet(ecorePaths), ExportResource("infoservice.genmodel"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Assert.isTrue(new File("src/main/resources/xsds/file0.xsd").exists());
        Assert.isTrue(new File("src/main/resources/xsds").exists());
    }

    /**
     * Export a resource embedded into a Jar file to the local file path.
     *
     * @param resourceName
     *            ie.: "/SmartLibrary.dll"
     * @return The path to the exported resource
     * @throws Exception
     */
    public String ExportResource(String resourceName) throws Exception {
        InputStream stream = null;
        OutputStream resStreamOut = null;
        String jarFolder;
        try {
            stream = this.getClass().getClassLoader().getResourceAsStream(resourceName);// note that each / is a directory down in the "jar tree" been the jar the root of the tree
            if (stream == null) {
                throw new Exception("Cannot get resource \"" + resourceName + "\" from Jar file.");
            }

            int readBytes;
            byte[] buffer = new byte[4096];
            jarFolder = "src/main/resources/";
            resStreamOut = new FileOutputStream(jarFolder + resourceName);
            while ((readBytes = stream.read(buffer)) > 0) {
                resStreamOut.write(buffer, 0, readBytes);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            stream.close();
            resStreamOut.close();
        }
        return jarFolder + resourceName;
    }

    public ResourceSet createResourceSet(String[] paths) {
        ResourceSet resourceSet = new ResourceSetImpl();

        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore",
            new EcoreResourceFactoryImpl());

        resourceSet.getPackageRegistry().put(EcorePackage.eNS_URI,
            EcorePackage.eINSTANCE);
        File file = null;
        for (int i = 0; i < paths.length; i++) {
            try {
                file = new File(ExportResource(paths[i]));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {
                URI uri = (file.isFile() ? URI.createFileURI(file.getAbsolutePath()) : URI.createURI(ExportResource(paths[i])));
                Resource resource = resourceSet.getResource(uri, true);
                resource.save(Collections.EMPTY_MAP);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return resourceSet;

    }

}
