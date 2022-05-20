package ca.ntro.cards.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.backend.model_history.ModelHistory;
import ca.ntro.cards.backend.model_history.ModelHistoryFull;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.messages.MsgExecutionEnded;
import ca.ntro.cards.models.ProcedureCardsModel;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

import ca.ntro.app.tasks.SubTasksLambda;

public class ModifyDashboardModel {

	public static <CARDS_MODEL extends ProcedureCardsModel,
	               DASHBOARD_MODEL extends CommonDashboardModel>
	
	       void createTasks(BackendTasks tasks,
			                Class<DASHBOARD_MODEL> dashboardModelClass,
			                ModelHistoryFull<CARDS_MODEL> modelHistory,
			                SubTasksLambda<BackendTasks> subTasksLambda) {
		
		
		tasks.taskGroup("ModifyDashboardModel")
		
		     .waitsFor("initializeDashboard")
		
		     .andContains(subTasks -> {

		    	 executionEnded(subTasks,
		    			        dashboardModelClass,
		    			        modelHistory);
		    	 
		    	 subTasksLambda.createSubTasks(subTasks);

		     });
	}


	public static <CARDS_MODEL extends ProcedureCardsModel,
	               DASHBOARD_MODEL extends CommonDashboardModel>

	        void executionEnded(BackendTasks tasks,
	        		            Class<DASHBOARD_MODEL> dashboardModelClass,
			                    ModelHistoryFull<CARDS_MODEL> modelHistory) {

		tasks.task("executionEndedDashboard")
		
		     .waitsFor(message(MsgExecutionEnded.class))

		     .thenExecutes(inputs -> {
		    	 
		    	 DASHBOARD_MODEL dashboardModel = inputs.get(model(dashboardModelClass));

		    	 modelHistory.updateDashboard(dashboardModel);
		    	
		    	 

		     });
	}

}
