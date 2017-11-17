/**
 */
package parser.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import parser.Angestellter;
import parser.AngestellterRepyosito;
import parser.Dozent;
import parser.Lernende;
import parser.LernendenRepository;
import parser.ParserFactory;
import parser.ParserPackage;
import parser.Repository;
import parser.RootRepository;
import parser.Schueler;
import parser.Student;
import parser.Tutor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ParserPackageImpl extends EPackageImpl implements ParserPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass angestellterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dozentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass studentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass schuelerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass lernendeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tutorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass repositoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rootRepositoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass angestellterRepyositoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass lernendenRepositoryEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see parser.ParserPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ParserPackageImpl() {
		super(eNS_URI, ParserFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ParserPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ParserPackage init() {
		if (isInited) return (ParserPackage)EPackage.Registry.INSTANCE.getEPackage(ParserPackage.eNS_URI);

		// Obtain or create and register package
		ParserPackageImpl theParserPackage = (ParserPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ParserPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ParserPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theParserPackage.createPackageContents();

		// Initialize created meta-data
		theParserPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theParserPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ParserPackage.eNS_URI, theParserPackage);
		return theParserPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAngestellter() {
		return angestellterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAngestellter_Name() {
		return (EAttribute)angestellterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAngestellter_Alter() {
		return (EAttribute)angestellterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAngestellter_Gehalt() {
		return (EAttribute)angestellterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getAngestellter__BerechneGehalt() {
		return angestellterEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDozent() {
		return dozentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDozent_Bonus() {
		return (EAttribute)dozentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDozent_Studenten() {
		return (EReference)dozentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getDozent__BerechneGehalt__double() {
		return dozentEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStudent() {
		return studentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSchueler() {
		return schuelerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLernende() {
		return lernendeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLernende_Name() {
		return (EAttribute)lernendeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLernende_Alter() {
		return (EAttribute)lernendeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLernende_Geschlecht() {
		return (EAttribute)lernendeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTutor() {
		return tutorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTutor_Bonus() {
		return (EAttribute)tutorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTutor_Stundenzahl() {
		return (EAttribute)tutorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTutor_Lernende() {
		return (EReference)tutorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getTutor__BerechneGehalt__double() {
		return tutorEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRepository() {
		return repositoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRepository_Name() {
		return (EAttribute)repositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRootRepository() {
		return rootRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRootRepository_Repositories() {
		return (EReference)rootRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAngestellterRepyosito() {
		return angestellterRepyositoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAngestellterRepyosito_Angestellter() {
		return (EReference)angestellterRepyositoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLernendenRepository() {
		return lernendenRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLernendenRepository_Lernenden() {
		return (EReference)lernendenRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParserFactory getParserFactory() {
		return (ParserFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		angestellterEClass = createEClass(ANGESTELLTER);
		createEAttribute(angestellterEClass, ANGESTELLTER__NAME);
		createEAttribute(angestellterEClass, ANGESTELLTER__ALTER);
		createEAttribute(angestellterEClass, ANGESTELLTER__GEHALT);
		createEOperation(angestellterEClass, ANGESTELLTER___BERECHNE_GEHALT);

		dozentEClass = createEClass(DOZENT);
		createEAttribute(dozentEClass, DOZENT__BONUS);
		createEReference(dozentEClass, DOZENT__STUDENTEN);
		createEOperation(dozentEClass, DOZENT___BERECHNE_GEHALT__DOUBLE);

		studentEClass = createEClass(STUDENT);

		schuelerEClass = createEClass(SCHUELER);

		lernendeEClass = createEClass(LERNENDE);
		createEAttribute(lernendeEClass, LERNENDE__NAME);
		createEAttribute(lernendeEClass, LERNENDE__ALTER);
		createEAttribute(lernendeEClass, LERNENDE__GESCHLECHT);

		tutorEClass = createEClass(TUTOR);
		createEAttribute(tutorEClass, TUTOR__BONUS);
		createEAttribute(tutorEClass, TUTOR__STUNDENZAHL);
		createEReference(tutorEClass, TUTOR__LERNENDE);
		createEOperation(tutorEClass, TUTOR___BERECHNE_GEHALT__DOUBLE);

		repositoryEClass = createEClass(REPOSITORY);
		createEAttribute(repositoryEClass, REPOSITORY__NAME);

		rootRepositoryEClass = createEClass(ROOT_REPOSITORY);
		createEReference(rootRepositoryEClass, ROOT_REPOSITORY__REPOSITORIES);

		angestellterRepyositoEClass = createEClass(ANGESTELLTER_REPYOSITO);
		createEReference(angestellterRepyositoEClass, ANGESTELLTER_REPYOSITO__ANGESTELLTER);

		lernendenRepositoryEClass = createEClass(LERNENDEN_REPOSITORY);
		createEReference(lernendenRepositoryEClass, LERNENDEN_REPOSITORY__LERNENDEN);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		dozentEClass.getESuperTypes().add(this.getAngestellter());
		studentEClass.getESuperTypes().add(this.getLernende());
		schuelerEClass.getESuperTypes().add(this.getLernende());
		tutorEClass.getESuperTypes().add(this.getAngestellter());
		rootRepositoryEClass.getESuperTypes().add(this.getRepository());
		angestellterRepyositoEClass.getESuperTypes().add(this.getRepository());
		lernendenRepositoryEClass.getESuperTypes().add(this.getRepository());

		// Initialize classes, features, and operations; add parameters
		initEClass(angestellterEClass, Angestellter.class, "Angestellter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAngestellter_Name(), ecorePackage.getEString(), "name", null, 0, 1, Angestellter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAngestellter_Alter(), ecorePackage.getEInt(), "Alter", null, 0, 1, Angestellter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAngestellter_Gehalt(), ecorePackage.getEDouble(), "Gehalt", null, 0, 1, Angestellter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getAngestellter__BerechneGehalt(), ecorePackage.getEDouble(), "BerechneGehalt", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(dozentEClass, Dozent.class, "Dozent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDozent_Bonus(), ecorePackage.getEDouble(), "bonus", null, 0, 1, Dozent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDozent_Studenten(), this.getStudent(), null, "studenten", null, 1, 100, Dozent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = initEOperation(getDozent__BerechneGehalt__double(), ecorePackage.getEDouble(), "BerechneGehalt", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDouble(), "bonus", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(studentEClass, Student.class, "Student", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(schuelerEClass, Schueler.class, "Schueler", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(lernendeEClass, Lernende.class, "Lernende", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLernende_Name(), ecorePackage.getEString(), "Name", null, 0, 1, Lernende.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLernende_Alter(), ecorePackage.getEInt(), "alter", null, 0, 1, Lernende.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLernende_Geschlecht(), ecorePackage.getEString(), "geschlecht", null, 0, 1, Lernende.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tutorEClass, Tutor.class, "Tutor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTutor_Bonus(), ecorePackage.getEDouble(), "bonus", null, 0, 1, Tutor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTutor_Stundenzahl(), ecorePackage.getEInt(), "Stundenzahl", null, 0, 1, Tutor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTutor_Lernende(), this.getLernende(), null, "lernende", null, 1, 30, Tutor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = initEOperation(getTutor__BerechneGehalt__double(), ecorePackage.getEDouble(), "BerechneGehalt", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDouble(), "bonus", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(repositoryEClass, Repository.class, "Repository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRepository_Name(), ecorePackage.getEString(), "name", null, 0, 1, Repository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rootRepositoryEClass, RootRepository.class, "RootRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRootRepository_Repositories(), this.getRepository(), null, "repositories", null, 0, -1, RootRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(angestellterRepyositoEClass, AngestellterRepyosito.class, "AngestellterRepyosito", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAngestellterRepyosito_Angestellter(), this.getAngestellter(), null, "angestellter", null, 1, -1, AngestellterRepyosito.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(lernendenRepositoryEClass, LernendenRepository.class, "LernendenRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLernendenRepository_Lernenden(), this.getLernende(), null, "lernenden", null, 1, -1, LernendenRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //ParserPackageImpl
