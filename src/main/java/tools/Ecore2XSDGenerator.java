package tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.crypto.Data;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.ecore.EcoreSchemaBuilder;
import org.eclipse.xsd.ecore.MapBuilder;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;
import org.eclipse.xsd.util.XSDResourceImpl;

public class Ecore2XSDGenerator {

    public Ecore2XSDGenerator() {

    }

    @SuppressWarnings("unused")
    public void exportEcoreToXSD(ResourceSet ecoreResources_p, String genpath_p) throws Exception {
        System.out.println(ecoreResources_p.getResources().toString());

        ecoreResources_p.getPackageRegistry().put(GenModelPackage.eNS_URI, GenModelPackage.eINSTANCE);

        ResourceSet resourceSet = new ResourceSetImpl();
        Map<Object, Object> loadOptions = resourceSet.getLoadOptions();
        loadOptions.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xsd", new XSDResourceFactoryImpl());
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("genmodel", new EcoreResourceFactoryImpl());
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
        // genpath_p = StringUtils.removeStart(genpath_p, "file:/");
        URI genModelURI = URI.createFileURI(genpath_p);
        Resource genmodelresource = resourceSet.getResource(genModelURI, true);
        genmodelresource.load(loadOptions);
        GenModel genModel = (GenModel) genmodelresource.getContents().get(0);

        MapBuilder mapBuilder = new EcoreSchemaBuilder(genModel.getExtendedMetaData());

        EPackage pack = null;
        URI xsdSchemaURI = null;
        int i = 0;
        for (Resource res : ecoreResources_p.getResources()) {
            EPackage current = (EPackage) res.getContents().get(0);
            pack = current;
            xsdSchemaURI = res.getURI().appendFileExtension("xsd"); //$NON-NLS-1$
            XSDSchema xsdSchema = ((EcoreSchemaBuilder) mapBuilder).getSchema(pack);
            Resource xsdResource = resourceSet.createResource(URI.createFileURI("src/main/resources/xsds/file" + i + ".xsd"));
            xsdResource.getContents().add(xsdSchema);
            try {
                Map<String, Boolean> options = new HashMap<String, Boolean>();
                options.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
                xsdResource.save(options);
            } catch (IOException e) {
                e.printStackTrace();
            }
            i++;
        }

    }

    public File loadXSD(String filePath) {

        XSDResourceImpl resource = new XSDResourceImpl();
        File source = new File(filePath);
        try {
            resource.load(new FileInputStream(source), new HashMap<Object, Object>());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Data data = (Data) resource.getContents().get(0);

        return source;

    }

}
