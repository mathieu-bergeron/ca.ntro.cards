package ca.ntro.cards.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.messages.MsgFlipCard;
import ca.ntro.cards.models.CardsModel;
import ca.ntro.cards.models.DashboardModel;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

import ca.ntro.app.tasks.SubTasksLambda;

public class ModifyDashboardModel {

	public static <DASHBOARD_MODEL extends DashboardModel> 
	
	       void createTasks(BackendTasks tasks,
			                Class<DASHBOARD_MODEL> dashboardModelClass,
			                SubTasksLambda<BackendTasks> subTasksLambda) {
		
		tasks.taskGroup("ModifyDashboardModel")
		
		     .contains(subTasks -> {

		    	 registerSimpleOperationOnDashboard(subTasks, dashboardModelClass);
		    	 
		    	 subTasksLambda.createSubTasks(subTasks);

		     });
	}

	private static <DASHBOARD_MODEL extends DashboardModel> 

	        void registerSimpleOperationOnDashboard(BackendTasks tasks,
	        		                                Class<DASHBOARD_MODEL> dashboardModelClass) {

		tasks.task("registerSimpleOperationOnDashboard")

		     .waitsFor(model(dashboardModelClass))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 DASHBOARD_MODEL dashboardModel  = inputs.get(model(dashboardModelClass));

		     });
	}

}
