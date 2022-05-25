package ca.ntro.cards.common.backend;

import java.util.concurrent.locks.ReentrantLock;
import static ca.ntro.app.tasks.backend.BackendTasks.*;

import ca.ntro.app.backend.LocalBackendNtro;
import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.common.CommonConstants;
import ca.ntro.cards.common.backend.tasks.ModifyCanvasModel;
import ca.ntro.cards.common.backend.tasks.ModifyDashboardModel;
import ca.ntro.cards.common.backend.tasks.ModifySettingsModel;
import ca.ntro.cards.common.backend.tasks.ModifyTestCasesModel;
import ca.ntro.cards.common.models.CommonCanvasModel;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.models.CommonSettingsModel;
import ca.ntro.cards.common.test_cases.TestCase;
import ca.ntro.cards.common.test_cases.TestCasesModel;
import ca.ntro.cards.common.test_cases.execution.Execution;
import ca.ntro.cards.common.test_cases.execution.TestCaseJobEngine;
import ca.ntro.cards.common.test_cases.execution_trace.ExecutionTraceFull;
import ca.ntro.core.initialization.Ntro;

public abstract class CommonBackend<EXECUTABLE_MODEL extends CommonExecutableModel,
                                    STUDENT_MODEL    extends EXECUTABLE_MODEL,
                                    CANVAS_MODEL     extends CommonCanvasModel,
                                    TEST_CASE        extends TestCase,
                                    TEST_CASES_MODEL extends TestCasesModel,
                                    DASHBOARD_MODEL  extends CommonDashboardModel,
                                    SETTINGS_MODEL   extends CommonSettingsModel>

       extends LocalBackendNtro {

	private TestCaseJobEngine<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> executionEngine = new TestCaseJobEngine<>();
	private TEST_CASES_MODEL testCasesModel;
	
	private Class<EXECUTABLE_MODEL> executableModelClass;
	private Class<STUDENT_MODEL> studentModelClass;
	private Class<CANVAS_MODEL> canvasModelClass;
	private Class<TEST_CASE> testCaseClass;
	private Class<TEST_CASES_MODEL> testCasesModelClass;
	private Class<DASHBOARD_MODEL> dashboardModelClass;
	private Class<SETTINGS_MODEL> settingsModelClass;
	
	protected TEST_CASES_MODEL testCasesModel() {
		return testCasesModel;
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
	
	public void initialize() {
		testCasesModel = Ntro.factory().newInstance(testCasesModelClass);
		testCasesModel.registerExecutableModelClass(executableModelClass);
		testCasesModel.registerStudentModelClass(studentModelClass);
		testCasesModel.registerTestCaseClass(testCaseClass);
		testCasesModel.registerShouldWriteJson(false);
		
		testCasesModel.registerExecutionEngine(executionEngine);
		
		
		
	}

	@Override
	public void createTasks(BackendTasks tasks) {
		
		ModifyCanvasModel.createTasks(tasks, 
				                      canvasModelClass,
				                      subTasks -> {
				                    	 
				                    	 addSubTasksToModifyCardsModel(subTasks);
				                    	 
				                     });

		ModifyTestCasesModel.createTasks(tasks, 
				                         testCasesModel,
							             subTasks -> {
										
										      addSubTasksToModifyTestCasesModel(subTasks);

							             });

		ModifyDashboardModel.createTasks(tasks,
				                         dashboardModelClass,

				                          subTasks -> {

				                        	addSubTasksToModifyDashboardModel(subTasks);

				                        });
		


		ModifySettingsModel.createTasks(tasks,
				                        settingsModelClass,
				                        subTasks -> {
				                        	
				                        	addSubTasksToModifySettingsModel(subTasks);

				                        });
		
		 createAdditionalTasks(tasks);

	}

	protected abstract void addSubTasksToModifyTestCasesModel(BackendTasks subTasks);
	protected abstract void addSubTasksToModifyCardsModel(BackendTasks subTasks);
	protected abstract void addSubTasksToModifyDashboardModel(BackendTasks subTasks);
	protected abstract void addSubTasksToModifySettingsModel(BackendTasks subTasks);
	
	protected abstract void createAdditionalTasks(BackendTasks tasks);


	@Override
	public void execute() {

		int numberOfThreads = Execution.determineNumberOfThreads(CommonConstants.DEFAULT_NUMBER_OF_EXECUTION_THREADS);
		
		executionEngine.registerExecutableModelClass(executableModelClass);
		executionEngine.registerStudentModelClass(studentModelClass);
		executionEngine.registerTestCaseClass(testCaseClass);

		executionEngine.initialize(numberOfThreads);
		
		executionEngine.start();

		System.out.println("\n\n[LOADING TEST CASES]");
		System.out.println(String.format("\n... using %s threads\n\n", numberOfThreads));
		System.out.flush();

		testCasesModel.loadFromDbDir();

	}

}
