package ca.ntro.cards.common.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.common.messages.MsgExecutionEnded;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.execution_trace.CommonExecutionTraceFull;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

import ca.ntro.app.tasks.SubTasksLambda;

public class ModifyDashboardModel {

	public static <EXECUTABLE_MODEL extends CommonExecutableModel,
	               DASHBOARD_MODEL extends CommonDashboardModel>
	
	       void createTasks(BackendTasks tasks,
			                Class<DASHBOARD_MODEL> dashboardModelClass,
			                SubTasksLambda<BackendTasks> subTasksLambda) {
		
		
		tasks.taskGroup("ModifyDashboardModel")
		
		     .waitsFor(model(dashboardModelClass))
		
		     .andContains(subTasks -> {

		    	 executionEnded(subTasks,
		    			        dashboardModelClass);
		    	 
		    	 subTasksLambda.createSubTasks(subTasks);

		     });
	}


	public static <EXECUTABLE_MODEL extends CommonExecutableModel,
	               DASHBOARD_MODEL extends CommonDashboardModel>

	        void executionEnded(BackendTasks tasks,
	        		            Class<DASHBOARD_MODEL> dashboardModelClass) {

		tasks.task("executionEndedDashboard")
		
		     .waitsFor(message(MsgExecutionEnded.class))

		     .thenExecutes(inputs -> {
		    	 
		    	 DASHBOARD_MODEL dashboardModel = inputs.get(model(dashboardModelClass));

		     });
	}

}
