package ca.ntro.cards.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
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
			                SubTasksLambda<BackendTasks> subTasksLambda) {
		
		tasks.taskGroup("ModifyDashboardModel")
		
		     .contains(subTasks -> {

		    	 registerSimpleOperationOnDashboard(subTasks, 
		    			                            dashboardModelClass, 
		    			                            msgRegisterSimpleOperationClass);
		    	 
		    	 subTasksLambda.createSubTasks(subTasks);

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

		     .waitsFor(model(dashboardModelClass))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 DASHBOARD_MODEL               dashboardModel             = inputs.get(model(dashboardModelClass));
		    	 MSG_REGISTER_SIMPLE_OPERATION msgRegisterSimpleOperation = inputs.get(message(msgRegisterSimpleOperationClass));
		    	 
		    	 msgRegisterSimpleOperation.applyTo(dashboardModel);

		     });
	}

}
