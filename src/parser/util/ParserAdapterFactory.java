/**
 */
package parser.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import parser.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see parser.ParserPackage
 * @generated
 */
public class ParserAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ParserPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParserAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ParserPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ParserSwitch<Adapter> modelSwitch =
		new ParserSwitch<Adapter>() {
			@Override
			public Adapter caseAngestellter(Angestellter object) {
				return createAngestellterAdapter();
			}
			@Override
			public Adapter caseDozent(Dozent object) {
				return createDozentAdapter();
			}
			@Override
			public Adapter caseStudent(Student object) {
				return createStudentAdapter();
			}
			@Override
			public Adapter caseSchueler(Schueler object) {
				return createSchuelerAdapter();
			}
			@Override
			public Adapter caseLernende(Lernende object) {
				return createLernendeAdapter();
			}
			@Override
			public Adapter caseTutor(Tutor object) {
				return createTutorAdapter();
			}
			@Override
			public Adapter caseRepository(Repository object) {
				return createRepositoryAdapter();
			}
			@Override
			public Adapter caseRootRepository(RootRepository object) {
				return createRootRepositoryAdapter();
			}
			@Override
			public Adapter caseAngestellterRepyosito(AngestellterRepyosito object) {
				return createAngestellterRepyositoAdapter();
			}
			@Override
			public Adapter caseLernendenRepository(LernendenRepository object) {
				return createLernendenRepositoryAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link parser.Angestellter <em>Angestellter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see parser.Angestellter
	 * @generated
	 */
	public Adapter createAngestellterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link parser.Dozent <em>Dozent</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see parser.Dozent
	 * @generated
	 */
	public Adapter createDozentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link parser.Student <em>Student</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see parser.Student
	 * @generated
	 */
	public Adapter createStudentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link parser.Schueler <em>Schueler</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see parser.Schueler
	 * @generated
	 */
	public Adapter createSchuelerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link parser.Lernende <em>Lernende</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see parser.Lernende
	 * @generated
	 */
	public Adapter createLernendeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link parser.Tutor <em>Tutor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see parser.Tutor
	 * @generated
	 */
	public Adapter createTutorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link parser.Repository <em>Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see parser.Repository
	 * @generated
	 */
	public Adapter createRepositoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link parser.RootRepository <em>Root Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see parser.RootRepository
	 * @generated
	 */
	public Adapter createRootRepositoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link parser.AngestellterRepyosito <em>Angestellter Repyosito</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see parser.AngestellterRepyosito
	 * @generated
	 */
	public Adapter createAngestellterRepyositoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link parser.LernendenRepository <em>Lernenden Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see parser.LernendenRepository
	 * @generated
	 */
	public Adapter createLernendenRepositoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //ParserAdapterFactory
