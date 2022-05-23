package ca.ntro.cards.common.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.common.messages.MsgExecutionEnded;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.models.values.execution_trace.ExecutionTraceFull;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

import ca.ntro.app.tasks.SubTasksLambda;

public class ModifyDashboardModel {

	public static <EXECUTABLE_MODEL extends CommonExecutableModel,
	               DASHBOARD_MODEL extends CommonDashboardModel>
	
	       void createTasks(BackendTasks tasks,
			                Class<DASHBOARD_MODEL> dashboardModelClass,
			                ExecutionTraceFull<EXECUTABLE_MODEL> executionTrace,
			                SubTasksLambda<BackendTasks> subTasksLambda) {
		
		
		tasks.taskGroup("ModifyDashboardModel")
		
		     .waitsFor("initializeDashboard")
		
		     .andContains(subTasks -> {

		    	 executionEnded(subTasks,
		    			        dashboardModelClass,
		    			        executionTrace);
		    	 
		    	 subTasksLambda.createSubTasks(subTasks);

		     });
	}


	public static <EXECUTABLE_MODEL extends CommonExecutableModel,
	               DASHBOARD_MODEL extends CommonDashboardModel>

	        void executionEnded(BackendTasks tasks,
	        		            Class<DASHBOARD_MODEL> dashboardModelClass,
			                    ExecutionTraceFull<EXECUTABLE_MODEL> executionTrace) {

		tasks.task("executionEndedDashboard")
		
		     .waitsFor(message(MsgExecutionEnded.class))

		     .thenExecutes(inputs -> {
		    	 
		    	 DASHBOARD_MODEL dashboardModel = inputs.get(model(dashboardModelClass));

		    	 executionTrace.updateDashboard(dashboardModel);
		    	
		    	 

		     });
	}

}
