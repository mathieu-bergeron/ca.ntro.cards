package ca.ntro.cards.common.backend.tasks;


import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.common.backend.model_history.ModelHistory;
import ca.ntro.cards.common.models.CommonCanvasModel;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.models.TestCasesModel;
import ca.ntro.cards.common.models.values.TestCase;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

public class InitializeModels {

	@SuppressWarnings("rawtypes")
	public static <EXECUTABLE_MODEL extends CommonExecutableModel,
	               CANVAS_MODEL     extends CommonCanvasModel,
	               TEST_CASE        extends TestCase<EXECUTABLE_MODEL>,
		           TEST_CASES_MODEL extends TestCasesModel<EXECUTABLE_MODEL, TEST_CASE>,
	               DASHBOARD_MODEL  extends CommonDashboardModel>
	
	        void initializeTestCases(BackendTasks tasks,
	        		                 Class<EXECUTABLE_MODEL> executableModelClass,
	        		                 Class<TEST_CASES_MODEL> testCasesModelClass) {

		tasks.task("initializeTestCases")

		     .waitsFor(model(testCasesModelClass))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 TEST_CASES_MODEL testCasesModel = inputs.get(model(testCasesModelClass));
		    	 
		    	 testCasesModel.generateFirstVersionIfNeeded(executableModelClass);

		     });
	}

	@SuppressWarnings("rawtypes")
	public static <CARDS_MODEL extends CommonCanvasModel,
	               DASHBOARD_MODEL extends CommonDashboardModel>
	
	        void initializeDashboard(BackendTasks tasks,
	        		                 Class<DASHBOARD_MODEL> dashboardModelClass,
	        		                 ModelHistory<CARDS_MODEL> modelHistory) {

		tasks.task("initializeDashboard")

		     .waitsFor(model(dashboardModelClass))

		     .waitsFor("initializeCanvasModel")
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 DASHBOARD_MODEL dashboardModel = inputs.get(model(dashboardModelClass));
		    	 
		    	 modelHistory.updateDashboard(dashboardModel);
		    	 
		     });
	}

}
