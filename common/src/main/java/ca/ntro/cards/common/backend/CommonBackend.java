package ca.ntro.cards.common.backend;

import java.util.concurrent.locks.ReentrantLock;
import static ca.ntro.app.tasks.backend.BackendTasks.*;

import ca.ntro.app.backend.LocalBackendNtro;
import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.common.backend.tasks.InitializeModels;
import ca.ntro.cards.common.backend.tasks.ManageThread;
import ca.ntro.cards.common.backend.tasks.ModifyCardsModel;
import ca.ntro.cards.common.backend.tasks.ModifyDashboardModel;
import ca.ntro.cards.common.backend.tasks.ModifySettingsModel;
import ca.ntro.cards.common.backend.tasks.ModifyTestCasesModel;
import ca.ntro.cards.common.models.CommonCanvasModel;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.models.CommonSettingsModel;
import ca.ntro.cards.common.models.TestCasesModel;
import ca.ntro.cards.common.models.values.execution_trace.ExecutionTraceFull;
import ca.ntro.cards.common.models.values.test_cases.TestCase;

public abstract class CommonBackend<EXECUTABLE_MODEL extends CommonExecutableModel,
                                    STUDENT_MODEL    extends EXECUTABLE_MODEL,
                                    CANVAS_MODEL     extends CommonCanvasModel,
                                    TEST_CASE        extends TestCase<EXECUTABLE_MODEL>,
                                    TEST_CASES_MODEL extends TestCasesModel,
                                    DASHBOARD_MODEL  extends CommonDashboardModel,
                                    SETTINGS_MODEL   extends CommonSettingsModel>

       extends LocalBackendNtro {
	
	public static int indexCurrentModel = 0;
	
	private Class<EXECUTABLE_MODEL> executableModelClass;
	private Class<STUDENT_MODEL> studentModelClass;
	private Class<CANVAS_MODEL> canvasModelClass;
	private Class<TEST_CASE> testCaseClass;
	private Class<TEST_CASES_MODEL> testCasesModelClass;
	private Class<DASHBOARD_MODEL> dashboardModelClass;
	private Class<SETTINGS_MODEL> settingsModelClass;
	
	private ReentrantLock lock = new ReentrantLock();
	private ExecutionTraceFull<EXECUTABLE_MODEL> modelHistory = new ExecutionTraceFull<>();

	protected ExecutionTraceFull<EXECUTABLE_MODEL> getModelHistory(){
		return modelHistory;
	}

	public Class<STUDENT_MODEL> getStudentModelClass() {
		return studentModelClass;
	}

	public void setStudentModelClass(Class<STUDENT_MODEL> studentModelClass) {
		this.studentModelClass = studentModelClass;
	}



	public void setExecutableModelClass(Class<EXECUTABLE_MODEL> executableModelClass) {
		this.executableModelClass = executableModelClass;
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

	public Class<EXECUTABLE_MODEL> getExecutableModelClass() {
		return executableModelClass;
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

	public Class<CANVAS_MODEL> getCanvasModelClass() {
		return canvasModelClass;
	}

	public void setCanvasModelClass(Class<CANVAS_MODEL> canvasModelClass) {
		this.canvasModelClass = canvasModelClass;
	}

	@Override
	public void createTasks(BackendTasks tasks) {
		
		InitializeModels.initializeTestCases(tasks, executableModelClass, testCasesModelClass);
		
		initializeCanvasModelTask(tasks);

		InitializeModels.initializeDashboard(tasks, dashboardModelClass, modelHistory);

		ModifyCardsModel.createTasks(tasks, 
				                     executableModelClass,
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

	protected void initializeCanvasModelTask(BackendTasks tasks) {
		tasks.task("initializeCanvasModel")

		     .waitsFor(model(canvasModelClass))

		     .waitsFor("initializeTestCases")
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 CANVAS_MODEL canvasModel = inputs.get(model(canvasModelClass));
		    	 
		    	 initializeCanvasModel(canvasModel);


		     });
		
		
	}

	protected abstract void initializeCanvasModel(CANVAS_MODEL canvasModel);

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
