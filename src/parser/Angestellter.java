/**
 */
package parser;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Angestellter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link parser.Angestellter#getName <em>Name</em>}</li>
 *   <li>{@link parser.Angestellter#getAlter <em>Alter</em>}</li>
 *   <li>{@link parser.Angestellter#getGehalt <em>Gehalt</em>}</li>
 * </ul>
 *
 * @see parser.ParserPackage#getAngestellter()
 * @model
 * @generated
 */
public interface Angestellter extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see parser.ParserPackage#getAngestellter_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link parser.Angestellter#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Alter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Alter</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Alter</em>' attribute.
	 * @see #setAlter(int)
	 * @see parser.ParserPackage#getAngestellter_Alter()
	 * @model
	 * @generated
	 */
	int getAlter();

	/**
	 * Sets the value of the '{@link parser.Angestellter#getAlter <em>Alter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Alter</em>' attribute.
	 * @see #getAlter()
	 * @generated
	 */
	void setAlter(int value);

	/**
	 * Returns the value of the '<em><b>Gehalt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gehalt</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gehalt</em>' attribute.
	 * @see #setGehalt(double)
	 * @see parser.ParserPackage#getAngestellter_Gehalt()
	 * @model
	 * @generated
	 */
	double getGehalt();

	/**
	 * Sets the value of the '{@link parser.Angestellter#getGehalt <em>Gehalt</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gehalt</em>' attribute.
	 * @see #getGehalt()
	 * @generated
	 */
	void setGehalt(double value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	double BerechneGehalt();

} // Angestellter
