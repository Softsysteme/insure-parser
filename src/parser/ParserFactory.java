/**
 */
package parser;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see parser.ParserPackage
 * @generated
 */
public interface ParserFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ParserFactory eINSTANCE = parser.impl.ParserFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Angestellter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Angestellter</em>'.
	 * @generated
	 */
	Angestellter createAngestellter();

	/**
	 * Returns a new object of class '<em>Dozent</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Dozent</em>'.
	 * @generated
	 */
	Dozent createDozent();

	/**
	 * Returns a new object of class '<em>Student</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Student</em>'.
	 * @generated
	 */
	Student createStudent();

	/**
	 * Returns a new object of class '<em>Schueler</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Schueler</em>'.
	 * @generated
	 */
	Schueler createSchueler();

	/**
	 * Returns a new object of class '<em>Lernende</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Lernende</em>'.
	 * @generated
	 */
	Lernende createLernende();

	/**
	 * Returns a new object of class '<em>Tutor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tutor</em>'.
	 * @generated
	 */
	Tutor createTutor();

	/**
	 * Returns a new object of class '<em>Repository</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Repository</em>'.
	 * @generated
	 */
	Repository createRepository();

	/**
	 * Returns a new object of class '<em>Root Repository</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Root Repository</em>'.
	 * @generated
	 */
	RootRepository createRootRepository();

	/**
	 * Returns a new object of class '<em>Angestellter Repyosito</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Angestellter Repyosito</em>'.
	 * @generated
	 */
	AngestellterRepyosito createAngestellterRepyosito();

	/**
	 * Returns a new object of class '<em>Lernenden Repository</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Lernenden Repository</em>'.
	 * @generated
	 */
	LernendenRepository createLernendenRepository();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ParserPackage getParserPackage();

} //ParserFactory
