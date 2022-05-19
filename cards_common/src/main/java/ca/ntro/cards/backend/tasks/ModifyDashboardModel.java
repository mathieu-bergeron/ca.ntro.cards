package ca.ntro.cards.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.backend.ModelHistory;
import ca.ntro.cards.messages.MsgExecutionEnded;
import ca.ntro.cards.messages.MsgRegisterSimpleOperation;
import ca.ntro.cards.models.CardsModel;
import ca.ntro.cards.models.DashboardModel;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

import ca.ntro.app.tasks.SubTasksLambda;

public class ModifyDashboardModel {

	public static <CARDS_MODEL extends CardsModel,
	               DASHBOARD_MODEL extends DashboardModel,
	               MSG_REGISTER_SIMPLE_OPERATION extends MsgRegisterSimpleOperation<CARDS_MODEL, DASHBOARD_MODEL>> 
	
	       void createTasks(BackendTasks tasks,
			                Class<DASHBOARD_MODEL> dashboardModelClass,
			                Class<MSG_REGISTER_SIMPLE_OPERATION> msgRegisterSimpleOperationClass,
			                ModelHistory<CARDS_MODEL> modelHistory,
			                SubTasksLambda<BackendTasks> subTasksLambda) {
		
		initializeDashboard(tasks, dashboardModelClass);
		
		tasks.taskGroup("ModifyDashboardModel")
		
		     .waitsFor("initializeDashboard")
		
		     .andContains(subTasks -> {

		    	 registerSimpleOperationOnDashboard(subTasks, 
		    			                            dashboardModelClass, 
		    			                            msgRegisterSimpleOperationClass);

		    	 executionEnded(subTasks,
		    			        dashboardModelClass,
		    			        modelHistory);
		    	 
		    	 subTasksLambda.createSubTasks(subTasks);

		     });
	}

	public static <DASHBOARD_MODEL extends DashboardModel>
	
	        void initializeDashboard(BackendTasks tasks,
	        		                 Class<DASHBOARD_MODEL> dashboardModelClass) {

		tasks.task("initializeDashboard")


		     .waitsFor(model(dashboardModelClass))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 DASHBOARD_MODEL dashboardModel = inputs.get(model(dashboardModelClass));

		    	 dashboardModel.initialize();
		    	 
		     });
	}


	public static <CARDS_MODEL extends CardsModel,
	               DASHBOARD_MODEL extends DashboardModel,
	               MSG_REGISTER_SIMPLE_OPERATION extends MsgRegisterSimpleOperation<CARDS_MODEL, DASHBOARD_MODEL>> 

	        void registerSimpleOperationOnDashboard(BackendTasks tasks,
	        		                                Class<DASHBOARD_MODEL> dashboardModelClass,
			                                        Class<MSG_REGISTER_SIMPLE_OPERATION> msgRegisterSimpleOperationClass) {

		tasks.task("registerSimpleOperationOnDashboard")
		
		     .waitsFor(message(msgRegisterSimpleOperationClass))

		     .thenExecutes(inputs -> {
		    	 
		    	 DASHBOARD_MODEL               dashboardModel             = inputs.get(model(dashboardModelClass));
		    	 MSG_REGISTER_SIMPLE_OPERATION msgRegisterSimpleOperation = inputs.get(message(msgRegisterSimpleOperationClass));
		    	 
		    	 msgRegisterSimpleOperation.applyTo(dashboardModel);

		     });
	}

	public static <CARDS_MODEL extends CardsModel,
	               DASHBOARD_MODEL extends DashboardModel>

	        void executionEnded(BackendTasks tasks,
	        		            Class<DASHBOARD_MODEL> dashboardModelClass,
			                    ModelHistory<CARDS_MODEL> modelHistory) {

		tasks.task("executionEndedDashboard")
		
		     .waitsFor(message(MsgExecutionEnded.class))

		     .thenExecutes(inputs -> {
		    	 
		    	 DASHBOARD_MODEL dashboardModel = inputs.get(model(dashboardModelClass));

		    	 modelHistory.updateDashboard(dashboardModel);
		    	
		    	 

		     });
	}

}
