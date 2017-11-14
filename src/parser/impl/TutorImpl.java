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

import parser.Lernende;
import parser.ParserPackage;
import parser.Tutor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tutor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link parser.impl.TutorImpl#getBonus <em>Bonus</em>}</li>
 *   <li>{@link parser.impl.TutorImpl#getStundenzahl <em>Stundenzahl</em>}</li>
 *   <li>{@link parser.impl.TutorImpl#getLernende <em>Lernende</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TutorImpl extends AngestellterImpl implements Tutor {
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
	 * The default value of the '{@link #getStundenzahl() <em>Stundenzahl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStundenzahl()
	 * @generated
	 * @ordered
	 */
	protected static final int STUNDENZAHL_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getStundenzahl() <em>Stundenzahl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStundenzahl()
	 * @generated
	 * @ordered
	 */
	protected int stundenzahl = STUNDENZAHL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLernende() <em>Lernende</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLernende()
	 * @generated
	 * @ordered
	 */
	protected EList<Lernende> lernende;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TutorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ParserPackage.Literals.TUTOR;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ParserPackage.TUTOR__BONUS, oldBonus, bonus));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getStundenzahl() {
		return stundenzahl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStundenzahl(int newStundenzahl) {
		int oldStundenzahl = stundenzahl;
		stundenzahl = newStundenzahl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ParserPackage.TUTOR__STUNDENZAHL, oldStundenzahl, stundenzahl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Lernende> getLernende() {
		if (lernende == null) {
			lernende = new EObjectResolvingEList<Lernende>(Lernende.class, this, ParserPackage.TUTOR__LERNENDE);
		}
		return lernende;
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
			case ParserPackage.TUTOR__BONUS:
				return getBonus();
			case ParserPackage.TUTOR__STUNDENZAHL:
				return getStundenzahl();
			case ParserPackage.TUTOR__LERNENDE:
				return getLernende();
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
			case ParserPackage.TUTOR__BONUS:
				setBonus((Double)newValue);
				return;
			case ParserPackage.TUTOR__STUNDENZAHL:
				setStundenzahl((Integer)newValue);
				return;
			case ParserPackage.TUTOR__LERNENDE:
				getLernende().clear();
				getLernende().addAll((Collection<? extends Lernende>)newValue);
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
			case ParserPackage.TUTOR__BONUS:
				setBonus(BONUS_EDEFAULT);
				return;
			case ParserPackage.TUTOR__STUNDENZAHL:
				setStundenzahl(STUNDENZAHL_EDEFAULT);
				return;
			case ParserPackage.TUTOR__LERNENDE:
				getLernende().clear();
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
			case ParserPackage.TUTOR__BONUS:
				return bonus != BONUS_EDEFAULT;
			case ParserPackage.TUTOR__STUNDENZAHL:
				return stundenzahl != STUNDENZAHL_EDEFAULT;
			case ParserPackage.TUTOR__LERNENDE:
				return lernende != null && !lernende.isEmpty();
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
			case ParserPackage.TUTOR___BERECHNE_GEHALT__DOUBLE:
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
		result.append(", Stundenzahl: ");
		result.append(stundenzahl);
		result.append(')');
		return result.toString();
	}

} //TutorImpl
