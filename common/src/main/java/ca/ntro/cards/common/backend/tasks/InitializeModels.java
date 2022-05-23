package ca.ntro.cards.common.backend.tasks;


import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.common.models.CommonCanvasModel;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.models.TestCasesModel;
import ca.ntro.cards.common.models.values.execution_trace.ExecutionTrace;
import ca.ntro.cards.common.models.values.test_cases.TestCase;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

public class InitializeModels {

	@SuppressWarnings("rawtypes")
	public static <EXECUTABLE_MODEL extends CommonExecutableModel,
	               STUDENT_MODEL    extends EXECUTABLE_MODEL,
	               CANVAS_MODEL     extends CommonCanvasModel,
	               TEST_CASE        extends TestCase<EXECUTABLE_MODEL>,
		           TEST_CASES_MODEL extends TestCasesModel<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE>,
	               DASHBOARD_MODEL  extends CommonDashboardModel>
	
	        void initializeTestCases(BackendTasks tasks,
	        		                 Class<EXECUTABLE_MODEL> executableModelClass,
	        		                 Class<STUDENT_MODEL> studentModelClass,
	        		                 Class<TEST_CASES_MODEL> testCasesModelClass) {

		tasks.task("initializeTestCases")

		     .waitsFor(model(testCasesModelClass))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 TEST_CASES_MODEL testCasesModel = inputs.get(model(testCasesModelClass));
		    	 
		    	 testCasesModel.registerExecutableModelClass(executableModelClass);
		    	 testCasesModel.registerStudentModelClass(studentModelClass);

		    	 testCasesModel.generateFirstVersionIfNeeded();

		     });
	}

	@SuppressWarnings("rawtypes")
	public static <EXECUTABLE_MODEL extends CommonExecutableModel,
	               DASHBOARD_MODEL extends CommonDashboardModel>
	
	        void initializeDashboard(BackendTasks tasks,
	        		                 Class<DASHBOARD_MODEL> dashboardModelClass,
	        		                 ExecutionTrace<EXECUTABLE_MODEL> executionTrace) {

		tasks.task("initializeDashboard")

		     .waitsFor(model(dashboardModelClass))

		     .waitsFor("initializeCanvasModel")
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 DASHBOARD_MODEL dashboardModel = inputs.get(model(dashboardModelClass));
		    	 
		    	 executionTrace.updateDashboard(dashboardModel);
		    	 
		     });
	}

}
