/**
 */
package parser;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dozent</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link parser.Dozent#getBonus <em>Bonus</em>}</li>
 *   <li>{@link parser.Dozent#getStudenten <em>Studenten</em>}</li>
 * </ul>
 *
 * @see parser.ParserPackage#getDozent()
 * @model
 * @generated
 */
public interface Dozent extends Angestellter {
	/**
	 * Returns the value of the '<em><b>Bonus</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bonus</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bonus</em>' attribute.
	 * @see #setBonus(double)
	 * @see parser.ParserPackage#getDozent_Bonus()
	 * @model
	 * @generated
	 */
	double getBonus();

	/**
	 * Sets the value of the '{@link parser.Dozent#getBonus <em>Bonus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bonus</em>' attribute.
	 * @see #getBonus()
	 * @generated
	 */
	void setBonus(double value);

	/**
	 * Returns the value of the '<em><b>Studenten</b></em>' reference list.
	 * The list contents are of type {@link parser.Student}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Studenten</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Studenten</em>' reference list.
	 * @see parser.ParserPackage#getDozent_Studenten()
	 * @model required="true" upper="100"
	 * @generated
	 */
	EList<Student> getStudenten();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	double BerechneGehalt(double bonus);

} // Dozent
