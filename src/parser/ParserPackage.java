/**
 */
package parser;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see parser.ParserFactory
 * @model kind="package"
 * @generated
 */
public interface ParserPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "parser";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://de.adesso.ais.insure-parser";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "parser";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ParserPackage eINSTANCE = parser.impl.ParserPackageImpl.init();

	/**
	 * The meta object id for the '{@link parser.impl.AngestellterImpl <em>Angestellter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see parser.impl.AngestellterImpl
	 * @see parser.impl.ParserPackageImpl#getAngestellter()
	 * @generated
	 */
	int ANGESTELLTER = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANGESTELLTER__NAME = 0;

	/**
	 * The feature id for the '<em><b>Alter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANGESTELLTER__ALTER = 1;

	/**
	 * The feature id for the '<em><b>Gehalt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANGESTELLTER__GEHALT = 2;

	/**
	 * The number of structural features of the '<em>Angestellter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANGESTELLTER_FEATURE_COUNT = 3;

	/**
	 * The operation id for the '<em>Berechne Gehalt</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANGESTELLTER___BERECHNE_GEHALT = 0;

	/**
	 * The number of operations of the '<em>Angestellter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANGESTELLTER_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link parser.impl.DozentImpl <em>Dozent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see parser.impl.DozentImpl
	 * @see parser.impl.ParserPackageImpl#getDozent()
	 * @generated
	 */
	int DOZENT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOZENT__NAME = ANGESTELLTER__NAME;

	/**
	 * The feature id for the '<em><b>Alter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOZENT__ALTER = ANGESTELLTER__ALTER;

	/**
	 * The feature id for the '<em><b>Gehalt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOZENT__GEHALT = ANGESTELLTER__GEHALT;

	/**
	 * The feature id for the '<em><b>Bonus</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOZENT__BONUS = ANGESTELLTER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Studenten</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOZENT__STUDENTEN = ANGESTELLTER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Dozent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOZENT_FEATURE_COUNT = ANGESTELLTER_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Berechne Gehalt</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOZENT___BERECHNE_GEHALT = ANGESTELLTER___BERECHNE_GEHALT;

	/**
	 * The operation id for the '<em>Berechne Gehalt</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOZENT___BERECHNE_GEHALT__DOUBLE = ANGESTELLTER_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Dozent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOZENT_OPERATION_COUNT = ANGESTELLTER_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link parser.impl.LernendeImpl <em>Lernende</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see parser.impl.LernendeImpl
	 * @see parser.impl.ParserPackageImpl#getLernende()
	 * @generated
	 */
	int LERNENDE = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LERNENDE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Alter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LERNENDE__ALTER = 1;

	/**
	 * The feature id for the '<em><b>Geschlecht</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LERNENDE__GESCHLECHT = 2;

	/**
	 * The number of structural features of the '<em>Lernende</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LERNENDE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Lernende</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LERNENDE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link parser.impl.StudentImpl <em>Student</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see parser.impl.StudentImpl
	 * @see parser.impl.ParserPackageImpl#getStudent()
	 * @generated
	 */
	int STUDENT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT__NAME = LERNENDE__NAME;

	/**
	 * The feature id for the '<em><b>Alter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT__ALTER = LERNENDE__ALTER;

	/**
	 * The feature id for the '<em><b>Geschlecht</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT__GESCHLECHT = LERNENDE__GESCHLECHT;

	/**
	 * The number of structural features of the '<em>Student</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_FEATURE_COUNT = LERNENDE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Student</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_OPERATION_COUNT = LERNENDE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link parser.impl.SchuelerImpl <em>Schueler</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see parser.impl.SchuelerImpl
	 * @see parser.impl.ParserPackageImpl#getSchueler()
	 * @generated
	 */
	int SCHUELER = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHUELER__NAME = LERNENDE__NAME;

	/**
	 * The feature id for the '<em><b>Alter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHUELER__ALTER = LERNENDE__ALTER;

	/**
	 * The feature id for the '<em><b>Geschlecht</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHUELER__GESCHLECHT = LERNENDE__GESCHLECHT;

	/**
	 * The number of structural features of the '<em>Schueler</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHUELER_FEATURE_COUNT = LERNENDE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Schueler</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHUELER_OPERATION_COUNT = LERNENDE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link parser.impl.TutorImpl <em>Tutor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see parser.impl.TutorImpl
	 * @see parser.impl.ParserPackageImpl#getTutor()
	 * @generated
	 */
	int TUTOR = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUTOR__NAME = ANGESTELLTER__NAME;

	/**
	 * The feature id for the '<em><b>Alter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUTOR__ALTER = ANGESTELLTER__ALTER;

	/**
	 * The feature id for the '<em><b>Gehalt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUTOR__GEHALT = ANGESTELLTER__GEHALT;

	/**
	 * The feature id for the '<em><b>Bonus</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUTOR__BONUS = ANGESTELLTER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Stundenzahl</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUTOR__STUNDENZAHL = ANGESTELLTER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Lernende</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUTOR__LERNENDE = ANGESTELLTER_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Tutor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUTOR_FEATURE_COUNT = ANGESTELLTER_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Berechne Gehalt</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUTOR___BERECHNE_GEHALT = ANGESTELLTER___BERECHNE_GEHALT;

	/**
	 * The operation id for the '<em>Berechne Gehalt</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUTOR___BERECHNE_GEHALT__DOUBLE = ANGESTELLTER_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Tutor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUTOR_OPERATION_COUNT = ANGESTELLTER_OPERATION_COUNT + 1;


	/**
	 * The meta object id for the '{@link parser.impl.RepositoryImpl <em>Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see parser.impl.RepositoryImpl
	 * @see parser.impl.ParserPackageImpl#getRepository()
	 * @generated
	 */
	int REPOSITORY = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY__NAME = 0;

	/**
	 * The number of structural features of the '<em>Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link parser.impl.RootRepositoryImpl <em>Root Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see parser.impl.RootRepositoryImpl
	 * @see parser.impl.ParserPackageImpl#getRootRepository()
	 * @generated
	 */
	int ROOT_REPOSITORY = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT_REPOSITORY__NAME = REPOSITORY__NAME;

	/**
	 * The feature id for the '<em><b>Repositories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT_REPOSITORY__REPOSITORIES = REPOSITORY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Root Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT_REPOSITORY_FEATURE_COUNT = REPOSITORY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Root Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT_REPOSITORY_OPERATION_COUNT = REPOSITORY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link parser.impl.AngestellterRepyositoImpl <em>Angestellter Repyosito</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see parser.impl.AngestellterRepyositoImpl
	 * @see parser.impl.ParserPackageImpl#getAngestellterRepyosito()
	 * @generated
	 */
	int ANGESTELLTER_REPYOSITO = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANGESTELLTER_REPYOSITO__NAME = REPOSITORY__NAME;

	/**
	 * The feature id for the '<em><b>Angestellter</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANGESTELLTER_REPYOSITO__ANGESTELLTER = REPOSITORY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Angestellter Repyosito</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANGESTELLTER_REPYOSITO_FEATURE_COUNT = REPOSITORY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Angestellter Repyosito</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANGESTELLTER_REPYOSITO_OPERATION_COUNT = REPOSITORY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link parser.impl.LernendenRepositoryImpl <em>Lernenden Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see parser.impl.LernendenRepositoryImpl
	 * @see parser.impl.ParserPackageImpl#getLernendenRepository()
	 * @generated
	 */
	int LERNENDEN_REPOSITORY = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LERNENDEN_REPOSITORY__NAME = REPOSITORY__NAME;

	/**
	 * The feature id for the '<em><b>Lernenden</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LERNENDEN_REPOSITORY__LERNENDEN = REPOSITORY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Lernenden Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LERNENDEN_REPOSITORY_FEATURE_COUNT = REPOSITORY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Lernenden Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LERNENDEN_REPOSITORY_OPERATION_COUNT = REPOSITORY_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link parser.Angestellter <em>Angestellter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Angestellter</em>'.
	 * @see parser.Angestellter
	 * @generated
	 */
	EClass getAngestellter();

	/**
	 * Returns the meta object for the attribute '{@link parser.Angestellter#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see parser.Angestellter#getName()
	 * @see #getAngestellter()
	 * @generated
	 */
	EAttribute getAngestellter_Name();

	/**
	 * Returns the meta object for the attribute '{@link parser.Angestellter#getAlter <em>Alter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Alter</em>'.
	 * @see parser.Angestellter#getAlter()
	 * @see #getAngestellter()
	 * @generated
	 */
	EAttribute getAngestellter_Alter();

	/**
	 * Returns the meta object for the attribute '{@link parser.Angestellter#getGehalt <em>Gehalt</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Gehalt</em>'.
	 * @see parser.Angestellter#getGehalt()
	 * @see #getAngestellter()
	 * @generated
	 */
	EAttribute getAngestellter_Gehalt();

	/**
	 * Returns the meta object for the '{@link parser.Angestellter#BerechneGehalt() <em>Berechne Gehalt</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Berechne Gehalt</em>' operation.
	 * @see parser.Angestellter#BerechneGehalt()
	 * @generated
	 */
	EOperation getAngestellter__BerechneGehalt();

	/**
	 * Returns the meta object for class '{@link parser.Dozent <em>Dozent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dozent</em>'.
	 * @see parser.Dozent
	 * @generated
	 */
	EClass getDozent();

	/**
	 * Returns the meta object for the attribute '{@link parser.Dozent#getBonus <em>Bonus</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bonus</em>'.
	 * @see parser.Dozent#getBonus()
	 * @see #getDozent()
	 * @generated
	 */
	EAttribute getDozent_Bonus();

	/**
	 * Returns the meta object for the reference list '{@link parser.Dozent#getStudenten <em>Studenten</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Studenten</em>'.
	 * @see parser.Dozent#getStudenten()
	 * @see #getDozent()
	 * @generated
	 */
	EReference getDozent_Studenten();

	/**
	 * Returns the meta object for the '{@link parser.Dozent#BerechneGehalt(double) <em>Berechne Gehalt</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Berechne Gehalt</em>' operation.
	 * @see parser.Dozent#BerechneGehalt(double)
	 * @generated
	 */
	EOperation getDozent__BerechneGehalt__double();

	/**
	 * Returns the meta object for class '{@link parser.Student <em>Student</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Student</em>'.
	 * @see parser.Student
	 * @generated
	 */
	EClass getStudent();

	/**
	 * Returns the meta object for class '{@link parser.Schueler <em>Schueler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Schueler</em>'.
	 * @see parser.Schueler
	 * @generated
	 */
	EClass getSchueler();

	/**
	 * Returns the meta object for class '{@link parser.Lernende <em>Lernende</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Lernende</em>'.
	 * @see parser.Lernende
	 * @generated
	 */
	EClass getLernende();

	/**
	 * Returns the meta object for the attribute '{@link parser.Lernende#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see parser.Lernende#getName()
	 * @see #getLernende()
	 * @generated
	 */
	EAttribute getLernende_Name();

	/**
	 * Returns the meta object for the attribute '{@link parser.Lernende#getAlter <em>Alter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Alter</em>'.
	 * @see parser.Lernende#getAlter()
	 * @see #getLernende()
	 * @generated
	 */
	EAttribute getLernende_Alter();

	/**
	 * Returns the meta object for the attribute '{@link parser.Lernende#getGeschlecht <em>Geschlecht</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Geschlecht</em>'.
	 * @see parser.Lernende#getGeschlecht()
	 * @see #getLernende()
	 * @generated
	 */
	EAttribute getLernende_Geschlecht();

	/**
	 * Returns the meta object for class '{@link parser.Tutor <em>Tutor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tutor</em>'.
	 * @see parser.Tutor
	 * @generated
	 */
	EClass getTutor();

	/**
	 * Returns the meta object for the attribute '{@link parser.Tutor#getBonus <em>Bonus</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bonus</em>'.
	 * @see parser.Tutor#getBonus()
	 * @see #getTutor()
	 * @generated
	 */
	EAttribute getTutor_Bonus();

	/**
	 * Returns the meta object for the attribute '{@link parser.Tutor#getStundenzahl <em>Stundenzahl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stundenzahl</em>'.
	 * @see parser.Tutor#getStundenzahl()
	 * @see #getTutor()
	 * @generated
	 */
	EAttribute getTutor_Stundenzahl();

	/**
	 * Returns the meta object for the reference list '{@link parser.Tutor#getLernende <em>Lernende</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Lernende</em>'.
	 * @see parser.Tutor#getLernende()
	 * @see #getTutor()
	 * @generated
	 */
	EReference getTutor_Lernende();

	/**
	 * Returns the meta object for the '{@link parser.Tutor#BerechneGehalt(double) <em>Berechne Gehalt</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Berechne Gehalt</em>' operation.
	 * @see parser.Tutor#BerechneGehalt(double)
	 * @generated
	 */
	EOperation getTutor__BerechneGehalt__double();

	/**
	 * Returns the meta object for class '{@link parser.Repository <em>Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Repository</em>'.
	 * @see parser.Repository
	 * @generated
	 */
	EClass getRepository();

	/**
	 * Returns the meta object for the attribute '{@link parser.Repository#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see parser.Repository#getName()
	 * @see #getRepository()
	 * @generated
	 */
	EAttribute getRepository_Name();

	/**
	 * Returns the meta object for class '{@link parser.RootRepository <em>Root Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Root Repository</em>'.
	 * @see parser.RootRepository
	 * @generated
	 */
	EClass getRootRepository();

	/**
	 * Returns the meta object for the containment reference list '{@link parser.RootRepository#getRepositories <em>Repositories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Repositories</em>'.
	 * @see parser.RootRepository#getRepositories()
	 * @see #getRootRepository()
	 * @generated
	 */
	EReference getRootRepository_Repositories();

	/**
	 * Returns the meta object for class '{@link parser.AngestellterRepyosito <em>Angestellter Repyosito</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Angestellter Repyosito</em>'.
	 * @see parser.AngestellterRepyosito
	 * @generated
	 */
	EClass getAngestellterRepyosito();

	/**
	 * Returns the meta object for the reference list '{@link parser.AngestellterRepyosito#getAngestellter <em>Angestellter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Angestellter</em>'.
	 * @see parser.AngestellterRepyosito#getAngestellter()
	 * @see #getAngestellterRepyosito()
	 * @generated
	 */
	EReference getAngestellterRepyosito_Angestellter();

	/**
	 * Returns the meta object for class '{@link parser.LernendenRepository <em>Lernenden Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Lernenden Repository</em>'.
	 * @see parser.LernendenRepository
	 * @generated
	 */
	EClass getLernendenRepository();

	/**
	 * Returns the meta object for the reference list '{@link parser.LernendenRepository#getLernenden <em>Lernenden</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Lernenden</em>'.
	 * @see parser.LernendenRepository#getLernenden()
	 * @see #getLernendenRepository()
	 * @generated
	 */
	EReference getLernendenRepository_Lernenden();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ParserFactory getParserFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link parser.impl.AngestellterImpl <em>Angestellter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see parser.impl.AngestellterImpl
		 * @see parser.impl.ParserPackageImpl#getAngestellter()
		 * @generated
		 */
		EClass ANGESTELLTER = eINSTANCE.getAngestellter();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANGESTELLTER__NAME = eINSTANCE.getAngestellter_Name();

		/**
		 * The meta object literal for the '<em><b>Alter</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANGESTELLTER__ALTER = eINSTANCE.getAngestellter_Alter();

		/**
		 * The meta object literal for the '<em><b>Gehalt</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANGESTELLTER__GEHALT = eINSTANCE.getAngestellter_Gehalt();

		/**
		 * The meta object literal for the '<em><b>Berechne Gehalt</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation ANGESTELLTER___BERECHNE_GEHALT = eINSTANCE.getAngestellter__BerechneGehalt();

		/**
		 * The meta object literal for the '{@link parser.impl.DozentImpl <em>Dozent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see parser.impl.DozentImpl
		 * @see parser.impl.ParserPackageImpl#getDozent()
		 * @generated
		 */
		EClass DOZENT = eINSTANCE.getDozent();

		/**
		 * The meta object literal for the '<em><b>Bonus</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOZENT__BONUS = eINSTANCE.getDozent_Bonus();

		/**
		 * The meta object literal for the '<em><b>Studenten</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOZENT__STUDENTEN = eINSTANCE.getDozent_Studenten();

		/**
		 * The meta object literal for the '<em><b>Berechne Gehalt</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOZENT___BERECHNE_GEHALT__DOUBLE = eINSTANCE.getDozent__BerechneGehalt__double();

		/**
		 * The meta object literal for the '{@link parser.impl.StudentImpl <em>Student</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see parser.impl.StudentImpl
		 * @see parser.impl.ParserPackageImpl#getStudent()
		 * @generated
		 */
		EClass STUDENT = eINSTANCE.getStudent();

		/**
		 * The meta object literal for the '{@link parser.impl.SchuelerImpl <em>Schueler</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see parser.impl.SchuelerImpl
		 * @see parser.impl.ParserPackageImpl#getSchueler()
		 * @generated
		 */
		EClass SCHUELER = eINSTANCE.getSchueler();

		/**
		 * The meta object literal for the '{@link parser.impl.LernendeImpl <em>Lernende</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see parser.impl.LernendeImpl
		 * @see parser.impl.ParserPackageImpl#getLernende()
		 * @generated
		 */
		EClass LERNENDE = eINSTANCE.getLernende();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LERNENDE__NAME = eINSTANCE.getLernende_Name();

		/**
		 * The meta object literal for the '<em><b>Alter</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LERNENDE__ALTER = eINSTANCE.getLernende_Alter();

		/**
		 * The meta object literal for the '<em><b>Geschlecht</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LERNENDE__GESCHLECHT = eINSTANCE.getLernende_Geschlecht();

		/**
		 * The meta object literal for the '{@link parser.impl.TutorImpl <em>Tutor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see parser.impl.TutorImpl
		 * @see parser.impl.ParserPackageImpl#getTutor()
		 * @generated
		 */
		EClass TUTOR = eINSTANCE.getTutor();

		/**
		 * The meta object literal for the '<em><b>Bonus</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TUTOR__BONUS = eINSTANCE.getTutor_Bonus();

		/**
		 * The meta object literal for the '<em><b>Stundenzahl</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TUTOR__STUNDENZAHL = eINSTANCE.getTutor_Stundenzahl();

		/**
		 * The meta object literal for the '<em><b>Lernende</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TUTOR__LERNENDE = eINSTANCE.getTutor_Lernende();

		/**
		 * The meta object literal for the '<em><b>Berechne Gehalt</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation TUTOR___BERECHNE_GEHALT__DOUBLE = eINSTANCE.getTutor__BerechneGehalt__double();

		/**
		 * The meta object literal for the '{@link parser.impl.RepositoryImpl <em>Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see parser.impl.RepositoryImpl
		 * @see parser.impl.ParserPackageImpl#getRepository()
		 * @generated
		 */
		EClass REPOSITORY = eINSTANCE.getRepository();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REPOSITORY__NAME = eINSTANCE.getRepository_Name();

		/**
		 * The meta object literal for the '{@link parser.impl.RootRepositoryImpl <em>Root Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see parser.impl.RootRepositoryImpl
		 * @see parser.impl.ParserPackageImpl#getRootRepository()
		 * @generated
		 */
		EClass ROOT_REPOSITORY = eINSTANCE.getRootRepository();

		/**
		 * The meta object literal for the '<em><b>Repositories</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROOT_REPOSITORY__REPOSITORIES = eINSTANCE.getRootRepository_Repositories();

		/**
		 * The meta object literal for the '{@link parser.impl.AngestellterRepyositoImpl <em>Angestellter Repyosito</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see parser.impl.AngestellterRepyositoImpl
		 * @see parser.impl.ParserPackageImpl#getAngestellterRepyosito()
		 * @generated
		 */
		EClass ANGESTELLTER_REPYOSITO = eINSTANCE.getAngestellterRepyosito();

		/**
		 * The meta object literal for the '<em><b>Angestellter</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANGESTELLTER_REPYOSITO__ANGESTELLTER = eINSTANCE.getAngestellterRepyosito_Angestellter();

		/**
		 * The meta object literal for the '{@link parser.impl.LernendenRepositoryImpl <em>Lernenden Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see parser.impl.LernendenRepositoryImpl
		 * @see parser.impl.ParserPackageImpl#getLernendenRepository()
		 * @generated
		 */
		EClass LERNENDEN_REPOSITORY = eINSTANCE.getLernendenRepository();

		/**
		 * The meta object literal for the '<em><b>Lernenden</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LERNENDEN_REPOSITORY__LERNENDEN = eINSTANCE.getLernendenRepository_Lernenden();

	}

} //ParserPackage
