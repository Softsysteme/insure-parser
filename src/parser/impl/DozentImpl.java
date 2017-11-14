/**
 */
package parser.impl;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import parser.Dozent;
import parser.ParserPackage;
import parser.Student;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dozent</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link parser.impl.DozentImpl#getBonus <em>Bonus</em>}</li>
 *   <li>{@link parser.impl.DozentImpl#getStudenten <em>Studenten</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DozentImpl extends AngestellterImpl implements Dozent {
	/**
	 * The default value of the '{@link #getBonus() <em>Bonus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBonus()
	 * @generated
	 * @ordered
	 */
	protected static final double BONUS_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getBonus() <em>Bonus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBonus()
	 * @generated
	 * @ordered
	 */
	protected double bonus = BONUS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getStudenten() <em>Studenten</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStudenten()
	 * @generated
	 * @ordered
	 */
	protected EList<Student> studenten;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DozentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ParserPackage.Literals.DOZENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getBonus() {
		return bonus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBonus(double newBonus) {
		double oldBonus = bonus;
		bonus = newBonus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ParserPackage.DOZENT__BONUS, oldBonus, bonus));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Student> getStudenten() {
		if (studenten == null) {
			studenten = new EObjectResolvingEList<Student>(Student.class, this, ParserPackage.DOZENT__STUDENTEN);
		}
		return studenten;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double BerechneGehalt(double bonus) {
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
			case ParserPackage.DOZENT__BONUS:
				return getBonus();
			case ParserPackage.DOZENT__STUDENTEN:
				return getStudenten();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ParserPackage.DOZENT__BONUS:
				setBonus((Double)newValue);
				return;
			case ParserPackage.DOZENT__STUDENTEN:
				getStudenten().clear();
				getStudenten().addAll((Collection<? extends Student>)newValue);
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
			case ParserPackage.DOZENT__BONUS:
				setBonus(BONUS_EDEFAULT);
				return;
			case ParserPackage.DOZENT__STUDENTEN:
				getStudenten().clear();
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
			case ParserPackage.DOZENT__BONUS:
				return bonus != BONUS_EDEFAULT;
			case ParserPackage.DOZENT__STUDENTEN:
				return studenten != null && !studenten.isEmpty();
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
			case ParserPackage.DOZENT___BERECHNE_GEHALT__DOUBLE:
				return BerechneGehalt((Double)arguments.get(0));
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
		result.append(" (bonus: ");
		result.append(bonus);
		result.append(')');
		return result.toString();
	}

} //DozentImpl
