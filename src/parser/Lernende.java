/**
 */
package parser;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Lernende</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link parser.Lernende#getName <em>Name</em>}</li>
 *   <li>{@link parser.Lernende#getAlter <em>Alter</em>}</li>
 *   <li>{@link parser.Lernende#getGeschlecht <em>Geschlecht</em>}</li>
 * </ul>
 *
 * @see parser.ParserPackage#getLernende()
 * @model
 * @generated
 */
public interface Lernende extends EObject {
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
	 * @see parser.ParserPackage#getLernende_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link parser.Lernende#getName <em>Name</em>}' attribute.
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
	 * @see parser.ParserPackage#getLernende_Alter()
	 * @model
	 * @generated
	 */
	int getAlter();

	/**
	 * Sets the value of the '{@link parser.Lernende#getAlter <em>Alter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Alter</em>' attribute.
	 * @see #getAlter()
	 * @generated
	 */
	void setAlter(int value);

	/**
	 * Returns the value of the '<em><b>Geschlecht</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Geschlecht</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Geschlecht</em>' attribute.
	 * @see #setGeschlecht(String)
	 * @see parser.ParserPackage#getLernende_Geschlecht()
	 * @model
	 * @generated
	 */
	String getGeschlecht();

	/**
	 * Sets the value of the '{@link parser.Lernende#getGeschlecht <em>Geschlecht</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Geschlecht</em>' attribute.
	 * @see #getGeschlecht()
	 * @generated
	 */
	void setGeschlecht(String value);

} // Lernende
