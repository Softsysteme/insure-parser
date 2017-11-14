/**
 */
package parser.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import parser.Lernende;
import parser.ParserPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Lernende</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link parser.impl.LernendeImpl#getName <em>Name</em>}</li>
 *   <li>{@link parser.impl.LernendeImpl#getAlter <em>Alter</em>}</li>
 *   <li>{@link parser.impl.LernendeImpl#getGeschlecht <em>Geschlecht</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LernendeImpl extends MinimalEObjectImpl.Container implements Lernende {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getAlter() <em>Alter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlter()
	 * @generated
	 * @ordered
	 */
	protected static final int ALTER_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getAlter() <em>Alter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlter()
	 * @generated
	 * @ordered
	 */
	protected int alter = ALTER_EDEFAULT;

	/**
	 * The default value of the '{@link #getGeschlecht() <em>Geschlecht</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeschlecht()
	 * @generated
	 * @ordered
	 */
	protected static final String GESCHLECHT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGeschlecht() <em>Geschlecht</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeschlecht()
	 * @generated
	 * @ordered
	 */
	protected String geschlecht = GESCHLECHT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LernendeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ParserPackage.Literals.LERNENDE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ParserPackage.LERNENDE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getAlter() {
		return alter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAlter(int newAlter) {
		int oldAlter = alter;
		alter = newAlter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ParserPackage.LERNENDE__ALTER, oldAlter, alter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGeschlecht() {
		return geschlecht;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGeschlecht(String newGeschlecht) {
		String oldGeschlecht = geschlecht;
		geschlecht = newGeschlecht;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ParserPackage.LERNENDE__GESCHLECHT, oldGeschlecht, geschlecht));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ParserPackage.LERNENDE__NAME:
				return getName();
			case ParserPackage.LERNENDE__ALTER:
				return getAlter();
			case ParserPackage.LERNENDE__GESCHLECHT:
				return getGeschlecht();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ParserPackage.LERNENDE__NAME:
				setName((String)newValue);
				return;
			case ParserPackage.LERNENDE__ALTER:
				setAlter((Integer)newValue);
				return;
			case ParserPackage.LERNENDE__GESCHLECHT:
				setGeschlecht((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ParserPackage.LERNENDE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ParserPackage.LERNENDE__ALTER:
				setAlter(ALTER_EDEFAULT);
				return;
			case ParserPackage.LERNENDE__GESCHLECHT:
				setGeschlecht(GESCHLECHT_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ParserPackage.LERNENDE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ParserPackage.LERNENDE__ALTER:
				return alter != ALTER_EDEFAULT;
			case ParserPackage.LERNENDE__GESCHLECHT:
				return GESCHLECHT_EDEFAULT == null ? geschlecht != null : !GESCHLECHT_EDEFAULT.equals(geschlecht);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (Name: ");
		result.append(name);
		result.append(", alter: ");
		result.append(alter);
		result.append(", geschlecht: ");
		result.append(geschlecht);
		result.append(')');
		return result.toString();
	}

} //LernendeImpl
