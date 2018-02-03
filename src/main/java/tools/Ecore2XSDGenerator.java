package tools;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

public class Ecore2XSDGenerator {

    public Ecore2XSDGenerator() {

    }

    @SuppressWarnings("unused")
    public void exportEcoreToXSD(ResourceSet ecoreResources_p, String genpath_p) throws Exception {

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

        int i = 0;
        for (Resource res : ecoreResources_p.getResources()) {
            System.out.println(res);
            EPackage current = (EPackage) res.getContents().get(0);
            verarbeiteEPackage(mapBuilder, resourceSet, res, current, i++);
            Iterator<EPackage> it1 = current.getESubpackages().iterator();
            Iterator<?> it2 = genModel.getUsedGenPackages().iterator();
            while (it1.hasNext() && it2.hasNext()) {
                MapBuilder mapB = new EcoreSchemaBuilder(((GenModel) it2.next()).getExtendedMetaData());
                verarbeiteEPackage(mapB, resourceSet, res, it1.next(), i);
                i++;
            }

        }
    }

    public void verarbeiteEPackage(MapBuilder mapBuilder, ResourceSet resourceSet, Resource res, EPackage pack, int filenumber) {
        URI xsdSchemaURI = null;
        xsdSchemaURI = res.getURI().appendFileExtension("xsd"); //$NON-NLS-1$
        XSDSchema xsdSchema = ((EcoreSchemaBuilder) mapBuilder).getSchema(pack);
        Resource xsdResource = resourceSet.createResource(URI.createFileURI("src/main/resources/xsds/file" + filenumber + ".xsd"));
        xsdResource.getContents().add(xsdSchema);
        System.out.println(xsdResource.getContents());
        try {
            Map<String, Boolean> options = new HashMap<String, Boolean>();
            options.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
            xsdResource.save(options);
        } catch (IOException e) {
        }

    }

}
