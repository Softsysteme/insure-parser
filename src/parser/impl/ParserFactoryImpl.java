/**
 */
package parser.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import parser.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ParserFactoryImpl extends EFactoryImpl implements ParserFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ParserFactory init() {
		try {
			ParserFactory theParserFactory = (ParserFactory)EPackage.Registry.INSTANCE.getEFactory(ParserPackage.eNS_URI);
			if (theParserFactory != null) {
				return theParserFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ParserFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParserFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ParserPackage.ANGESTELLTER: return createAngestellter();
			case ParserPackage.DOZENT: return createDozent();
			case ParserPackage.STUDENT: return createStudent();
			case ParserPackage.SCHUELER: return createSchueler();
			case ParserPackage.LERNENDE: return createLernende();
			case ParserPackage.TUTOR: return createTutor();
			case ParserPackage.REPOSITORY: return createRepository();
			case ParserPackage.ROOT_REPOSITORY: return createRootRepository();
			case ParserPackage.ANGESTELLTER_REPYOSITO: return createAngestellterRepyosito();
			case ParserPackage.LERNENDEN_REPOSITORY: return createLernendenRepository();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Angestellter createAngestellter() {
		AngestellterImpl angestellter = new AngestellterImpl();
		return angestellter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Dozent createDozent() {
		DozentImpl dozent = new DozentImpl();
		return dozent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Student createStudent() {
		StudentImpl student = new StudentImpl();
		return student;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Schueler createSchueler() {
		SchuelerImpl schueler = new SchuelerImpl();
		return schueler;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Lernende createLernende() {
		LernendeImpl lernende = new LernendeImpl();
		return lernende;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Tutor createTutor() {
		TutorImpl tutor = new TutorImpl();
		return tutor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Repository createRepository() {
		RepositoryImpl repository = new RepositoryImpl();
		return repository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RootRepository createRootRepository() {
		RootRepositoryImpl rootRepository = new RootRepositoryImpl();
		return rootRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AngestellterRepyosito createAngestellterRepyosito() {
		AngestellterRepyositoImpl angestellterRepyosito = new AngestellterRepyositoImpl();
		return angestellterRepyosito;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LernendenRepository createLernendenRepository() {
		LernendenRepositoryImpl lernendenRepository = new LernendenRepositoryImpl();
		return lernendenRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParserPackage getParserPackage() {
		return (ParserPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ParserPackage getPackage() {
		return ParserPackage.eINSTANCE;
	}

} //ParserFactoryImpl
