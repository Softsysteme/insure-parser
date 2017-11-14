/**
 */
package packageA;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>C</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link packageA.C#getName <em>Name</em>}</li>
 *   <li>{@link packageA.C#getAs <em>As</em>}</li>
 *   <li>{@link packageA.C#getBs <em>Bs</em>}</li>
 * </ul>
 *
 * @see packageA.PackageAPackage#getC()
 * @model
 * @generated
 */
public interface C extends EObject {
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
	 * @see packageA.PackageAPackage#getC_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link packageA.C#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>As</b></em>' reference list.
	 * The list contents are of type {@link packageA.A}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>As</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>As</em>' reference list.
	 * @see packageA.PackageAPackage#getC_As()
	 * @model
	 * @generated
	 */
	EList<A> getAs();

	/**
	 * Returns the value of the '<em><b>Bs</b></em>' reference list.
	 * The list contents are of type {@link packageA.B}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bs</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bs</em>' reference list.
	 * @see packageA.PackageAPackage#getC_Bs()
	 * @model
	 * @generated
	 */
	EList<B> getBs();

} // C
