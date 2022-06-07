package ca.ntro.cards.common.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.common.messages.MsgStartExecutionEngine;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.CommonTestCaseDatabase;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

import ca.ntro.app.tasks.SubTasksLambda;

public class ModifyDashboardModel {

	public static <EXECUTABLE_MODEL extends CommonExecutableModel,
	               DASHBOARD_MODEL extends CommonDashboardModel,
                   TEST_CASE_DATABASE extends CommonTestCaseDatabase>
	
	       void createTasks(BackendTasks tasks,
			                Class<DASHBOARD_MODEL> dashboardModelClass,
						    TEST_CASE_DATABASE testCaseDatabase,
			                SubTasksLambda<BackendTasks> subTasksLambda) {
		
		
		tasks.taskGroup("ModifyDashboardModel")
		
		     .waitsFor(model(dashboardModelClass))
		
		     .andContains(subTasks -> {
		    	 
		    	 startExecutionEngine(subTasks, dashboardModelClass, testCaseDatabase);
		    	 
		    	 subTasksLambda.createSubTasks(subTasks);

		     });
	}

	public static <DASHBOARD_MODEL extends CommonDashboardModel,
			       TEST_CASE_DATABASE extends CommonTestCaseDatabase>
	
	       void startExecutionEngine(BackendTasks tasks, 
	    		                     Class<DASHBOARD_MODEL> dashboardModelClass, 
	    		                     TEST_CASE_DATABASE testCaseDatabase){

		tasks.task("startExecutionEngine")
		
		     .waitsFor(message(MsgStartExecutionEngine.class))

		     .thenExecutes(inputs -> {
		    	 
		    	 DASHBOARD_MODEL dashboardModel = inputs.get(model(dashboardModelClass));
		    	 
		    	 dashboardModel.loadDbFromDir(testCaseDatabase);
		    	 testCaseDatabase.startEngine();
		     });
		
	}
	
	
	




}
