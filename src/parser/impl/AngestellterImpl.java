/**
 */
package parser.impl;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import parser.Angestellter;
import parser.ParserPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Angestellter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link parser.impl.AngestellterImpl#getName <em>Name</em>}</li>
 *   <li>{@link parser.impl.AngestellterImpl#getAlter <em>Alter</em>}</li>
 *   <li>{@link parser.impl.AngestellterImpl#getGehalt <em>Gehalt</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AngestellterImpl extends MinimalEObjectImpl.Container implements Angestellter {
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
	 * The default value of the '{@link #getGehalt() <em>Gehalt</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGehalt()
	 * @generated
	 * @ordered
	 */
	protected static final double GEHALT_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getGehalt() <em>Gehalt</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGehalt()
	 * @generated
	 * @ordered
	 */
	protected double gehalt = GEHALT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AngestellterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ParserPackage.Literals.ANGESTELLTER;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ParserPackage.ANGESTELLTER__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ParserPackage.ANGESTELLTER__ALTER, oldAlter, alter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getGehalt() {
		return gehalt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGehalt(double newGehalt) {
		double oldGehalt = gehalt;
		gehalt = newGehalt;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ParserPackage.ANGESTELLTER__GEHALT, oldGehalt, gehalt));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double BerechneGehalt() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ParserPackage.ANGESTELLTER__NAME:
				return getName();
			case ParserPackage.ANGESTELLTER__ALTER:
				return getAlter();
			case ParserPackage.ANGESTELLTER__GEHALT:
				return getGehalt();
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
			case ParserPackage.ANGESTELLTER__NAME:
				setName((String)newValue);
				return;
			case ParserPackage.ANGESTELLTER__ALTER:
				setAlter((Integer)newValue);
				return;
			case ParserPackage.ANGESTELLTER__GEHALT:
				setGehalt((Double)newValue);
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
			case ParserPackage.ANGESTELLTER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ParserPackage.ANGESTELLTER__ALTER:
				setAlter(ALTER_EDEFAULT);
				return;
			case ParserPackage.ANGESTELLTER__GEHALT:
				setGehalt(GEHALT_EDEFAULT);
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
			case ParserPackage.ANGESTELLTER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ParserPackage.ANGESTELLTER__ALTER:
				return alter != ALTER_EDEFAULT;
			case ParserPackage.ANGESTELLTER__GEHALT:
				return gehalt != GEHALT_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case ParserPackage.ANGESTELLTER___BERECHNE_GEHALT:
				return BerechneGehalt();
		}
		return super.eInvoke(operationID, arguments);
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
		result.append(" (name: ");
		result.append(name);
		result.append(", Alter: ");
		result.append(alter);
		result.append(", Gehalt: ");
		result.append(gehalt);
		result.append(')');
		return result.toString();
	}

} //AngestellterImpl
