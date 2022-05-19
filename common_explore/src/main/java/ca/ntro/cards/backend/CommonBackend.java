package ca.ntro.cards.backend;


import java.util.concurrent.locks.ReentrantLock;

import ca.ntro.app.backend.LocalBackendNtro;
import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.backend.model_history.ModelHistoryFull;
import ca.ntro.cards.backend.tasks.ManageThread;
import ca.ntro.cards.backend.tasks.ModifyCardsModel;
import ca.ntro.cards.backend.tasks.ModifyDashboardModel;
import ca.ntro.cards.backend.tasks.ModifySettingsModel;
import ca.ntro.cards.backend.tasks.ModifyTestCasesModel;
import ca.ntro.cards.models.CardsModel;
import ca.ntro.cards.models.DashboardModel;
import ca.ntro.cards.models.SettingsModel;
import ca.ntro.cards.models.TestCasesModel;
import ca.ntro.cards.models.values.TestCase;
import ca.ntro.core.initialization.Ntro;

public abstract class CommonBackend<CARDS_MODEL extends CardsModel,
                                    TEST_CASE extends TestCase<CARDS_MODEL>,
                                    TEST_CASES_MODEL extends TestCasesModel<CARDS_MODEL, TEST_CASE>,
                                    DASHBOARD_MODEL extends DashboardModel,
                                    SETTINGS_MODEL extends SettingsModel>

       extends LocalBackendNtro {
	
	public static int indexCurrentModel = 0;
	
	private Class<CARDS_MODEL> cardsModelClass;
	private Class<TEST_CASE> testCaseClass;
	private Class<TEST_CASES_MODEL> testCasesModelClass;
	private Class<DASHBOARD_MODEL> dashboardModelClass;
	private Class<SETTINGS_MODEL> settingsModelClass;
	
	private ReentrantLock lock = new ReentrantLock();
	private ModelThread<CARDS_MODEL> modelThread = new ModelThread<>();
	private ModelHistoryFull<CARDS_MODEL> modelHistory = new ModelHistoryFull<>();
	

	public void setCardsModelClass(Class<CARDS_MODEL> cardsModelClass) {
		this.cardsModelClass = cardsModelClass;
	}

	public void setDashboardModelClass(Class<DASHBOARD_MODEL> dashboardModelClass) {
		this.dashboardModelClass = dashboardModelClass;
	}

	public void setSettingsModelClass(Class<SETTINGS_MODEL> settingsModelClass) {
		this.settingsModelClass = settingsModelClass;
	}

	public void setTestCaseClass(Class<TEST_CASE> testCaseClass) {
		this.testCaseClass = testCaseClass;
	}

	public void setTestCasesModelClass(Class<TEST_CASES_MODEL> testCasesModelClass) {
		this.testCasesModelClass = testCasesModelClass;
	}

	public Class<CARDS_MODEL> getCardsModelClass() {
		return cardsModelClass;
	}

	public Class<TEST_CASE> getTestCaseClass() {
		return testCaseClass;
	}

	public Class<TEST_CASES_MODEL> getTestCasesModelClass() {
		return testCasesModelClass;
	}

	public Class<DASHBOARD_MODEL> getDashboardModelClass() {
		return dashboardModelClass;
	}

	public Class<SETTINGS_MODEL> getSettingsModelClass() {
		return settingsModelClass;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void createTasks(BackendTasks tasks) {

		ModifyTestCasesModel.createTasks(tasks, 
				                         testCasesModelClass,
							             subTasks -> {
										
										      addSubTasksToModifyTestCasesModel(subTasks);

							             });

		ModifyDashboardModel.createTasks(tasks,
				                         dashboardModelClass,
				                         modelHistory,

				                          subTasks -> {

				                        	addSubTasksToModifySettingsModel(subTasks);

				                        });
		
		ModifyCardsModel.createTasks(tasks, 
				                     cardsModelClass,
				                     modelHistory,
				                     lock,
				                     modelThread,
				                     subTasks -> {
				                    	 
				                    	 addSubTasksToModifyCardsModel(subTasks);
				                    	 
				                     });


		ModifySettingsModel.createTasks(tasks,
				                        settingsModelClass,
				                        subTasks -> {
				                        	
				                        	addSubTasksToModifySettingsModel(subTasks);

				                        });
		

		ManageThread.createTasks(tasks, 
				                 lock,
							     subTasks -> {
										
										addSubTasksToManageThread(subTasks);

							     });

		 createAdditionalTasks(tasks);

	}
	
	protected abstract void addSubTasksToModifyTestCasesModel(BackendTasks subTasks);
	protected abstract void addSubTasksToModifyCardsModel(BackendTasks subTasks);
	protected abstract void addSubTasksToModifyDashboardModel(BackendTasks subTasks);
	protected abstract void addSubTasksToModifySettingsModel(BackendTasks subTasks);
	protected abstract void addSubTasksToManageThread(BackendTasks subTasks);
	
	protected abstract void createAdditionalTasks(BackendTasks tasks);


	@Override
	public void execute() {
	}

}
