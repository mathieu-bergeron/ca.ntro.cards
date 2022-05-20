package ca.ntro.cards.common.backend;


import java.util.concurrent.locks.ReentrantLock;

import ca.ntro.app.backend.LocalBackendNtro;
import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.common.backend.model_history.ModelHistoryFull;
import ca.ntro.cards.common.backend.tasks.InitializeModels;
import ca.ntro.cards.common.backend.tasks.ManageThread;
import ca.ntro.cards.common.backend.tasks.ModifyCardsModel;
import ca.ntro.cards.common.backend.tasks.ModifyDashboardModel;
import ca.ntro.cards.common.backend.tasks.ModifySettingsModel;
import ca.ntro.cards.common.backend.tasks.ModifyTestCasesModel;
import ca.ntro.cards.common.models.CommonCanvasModel;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonSettingsModel;
import ca.ntro.cards.common.models.TestCasesModel;
import ca.ntro.cards.common.models.values.TestCase;
import ca.ntro.core.initialization.Ntro;

public abstract class CommonBackend<CARDS_MODEL      extends CommonCanvasModel,
                                    TEST_CASE        extends TestCase<CARDS_MODEL>,
                                    TEST_CASES_MODEL extends TestCasesModel<CARDS_MODEL, TEST_CASE>,
                                    DASHBOARD_MODEL  extends CommonDashboardModel,
                                    SETTINGS_MODEL   extends CommonSettingsModel>

       extends LocalBackendNtro {
	
	public static int indexCurrentModel = 0;
	
	private Class<CARDS_MODEL> cardsModelClass;
	private Class<TEST_CASE> testCaseClass;
	private Class<TEST_CASES_MODEL> testCasesModelClass;
	private Class<DASHBOARD_MODEL> dashboardModelClass;
	private Class<SETTINGS_MODEL> settingsModelClass;
	
	private ReentrantLock lock = new ReentrantLock();
	private ModelHistoryFull<CARDS_MODEL> modelHistory = new ModelHistoryFull<>();

	protected ModelHistoryFull<CARDS_MODEL> getModelHistory(){
		return modelHistory;
	}

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

	@Override
	public void createTasks(BackendTasks tasks) {
		
		InitializeModels.initializeTestCases(tasks, testCasesModelClass);
		
		initializeCanvasModel(tasks);

		InitializeModels.initializeDashboard(tasks, dashboardModelClass, modelHistory);

		ModifyCardsModel.createTasks(tasks, 
				                     cardsModelClass,
				                     modelHistory,
				                     lock,
				                     subTasks -> {
				                    	 
				                    	 addSubTasksToModifyCardsModel(subTasks);
				                    	 
				                     });

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

	protected abstract void initializeCanvasModel(BackendTasks tasks);

	/*
	private void initializeCanvasModel(BackendTasks tasks) {
	}
	*/
	
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
