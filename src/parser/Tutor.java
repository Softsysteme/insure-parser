/**
 */
package parser;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tutor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link parser.Tutor#getBonus <em>Bonus</em>}</li>
 *   <li>{@link parser.Tutor#getStundenzahl <em>Stundenzahl</em>}</li>
 *   <li>{@link parser.Tutor#getLernende <em>Lernende</em>}</li>
 * </ul>
 *
 * @see parser.ParserPackage#getTutor()
 * @model
 * @generated
 */
public interface Tutor extends Angestellter {
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
	 * @see parser.ParserPackage#getTutor_Bonus()
	 * @model
	 * @generated
	 */
	double getBonus();

	/**
	 * Sets the value of the '{@link parser.Tutor#getBonus <em>Bonus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bonus</em>' attribute.
	 * @see #getBonus()
	 * @generated
	 */
	void setBonus(double value);

	/**
	 * Returns the value of the '<em><b>Stundenzahl</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stundenzahl</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stundenzahl</em>' attribute.
	 * @see #setStundenzahl(int)
	 * @see parser.ParserPackage#getTutor_Stundenzahl()
	 * @model
	 * @generated
	 */
	int getStundenzahl();

	/**
	 * Sets the value of the '{@link parser.Tutor#getStundenzahl <em>Stundenzahl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stundenzahl</em>' attribute.
	 * @see #getStundenzahl()
	 * @generated
	 */
	void setStundenzahl(int value);

	/**
	 * Returns the value of the '<em><b>Lernende</b></em>' reference list.
	 * The list contents are of type {@link parser.Lernende}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lernende</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lernende</em>' reference list.
	 * @see parser.ParserPackage#getTutor_Lernende()
	 * @model required="true" upper="30"
	 * @generated
	 */
	EList<Lernende> getLernende();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	double BerechneGehalt(double bonus);

} // Tutor
