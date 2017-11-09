package tools;


import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.xsd.ecore.exporter.XSDExporter;



public class Ecore2XSDGenerator {

	private IFile genModel;

	private File targetDir;
	
	public Ecore2XSDGenerator(IFile genModel){
		this.genModel = genModel;
	}

	public void execute()  {
		EcorePackage.eINSTANCE.getClass();
		GenModelPackage.eINSTANCE.getClass();

		XSDExporter modelExporter = new XSDExporter();
		modelExporter.setDirectoryURI(URI.createFileURI(((File) targetDir).getAbsolutePath()) + "/temp");

		try {

			modelExporter.loadGenModel(URI.createFileURI(((File) genModel).getAbsolutePath()));

			modelExporter.export(null);
		} catch (Exception e) {
			
		}
	}

	public IFile getGenModel() {
		return genModel;
	}

	public void setGenModel(IFile genModel) {
		this.genModel = genModel;
	}

	public File getTargetDir() {
		return targetDir;
	}

	public void setTargetDir(File targetDir2) {
		this.targetDir = targetDir2;
	}

}