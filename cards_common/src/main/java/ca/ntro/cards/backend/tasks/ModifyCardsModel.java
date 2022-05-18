package ca.ntro.cards.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.backend.CommonBackend;
import ca.ntro.cards.messages.MsgExecutionBackStep;
import ca.ntro.cards.messages.MsgFlipCard;
import ca.ntro.cards.messages.MsgRegisterSimpleOperation;
import ca.ntro.cards.models.CardsModel;
import ca.ntro.cards.models.DashboardModel;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.app.tasks.SubTasksLambda;

public class ModifyCardsModel {
	
	public static <CARDS_MODEL extends CardsModel,
	               DASHBOARD_MODEL extends DashboardModel,
	               MSG_REGISTER_SIMPLE_OPERATION extends MsgRegisterSimpleOperation<CARDS_MODEL, DASHBOARD_MODEL>> 

	       void createTasks(BackendTasks tasks,
			                Class<CARDS_MODEL> cardsModelClass,
			                Class<MSG_REGISTER_SIMPLE_OPERATION> msgRegisterSimpleOperationClass,
			                List<CARDS_MODEL> modelHistory,
			                SubTasksLambda<BackendTasks> subTasksLambda) {
		
		createFirstVersionIfNeeded(tasks, cardsModelClass);
		
		tasks.taskGroup("ModifyCardsModel")
		
		     .waitsFor("createFirstVersionIfNeeded")
		
		     .andContains(subTasks -> {

		    	 flipCard(subTasks, cardsModelClass);

		    	 registerSimpleOperation(subTasks, 
		    			                 cardsModelClass,
		    			                 msgRegisterSimpleOperationClass,
		    			                 modelHistory);

		    	 executionBackStep(subTasks, 
		    			           cardsModelClass,
		    			           modelHistory);
		    	 
		    	 subTasksLambda.createSubTasks(subTasks);

		     });
	}

	public static <CARDS_MODEL extends CardsModel,
	               DASHBOARD_MODEL extends DashboardModel,
	               MSG_REGISTER_SIMPLE_OPERATION extends MsgRegisterSimpleOperation<CARDS_MODEL, DASHBOARD_MODEL>> 
	
	        void createFirstVersionIfNeeded(BackendTasks tasks,
	        		                        Class<CARDS_MODEL> cardsModelClass) {

		tasks.task("createFirstVersionIfNeeded")

		     .waitsFor(model(cardsModelClass))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 CARDS_MODEL gameModel = inputs.get(model(cardsModelClass));

		    	 gameModel.createFirstVersionIfNeeded();
		    	 
		     });
	}


	private static <CARDS_MODEL extends CardsModel> 

	        void flipCard(BackendTasks tasks,
	        		      Class<CARDS_MODEL> cardsModelClass) {

		tasks.task("flipCard")

		     .waitsFor(message(MsgFlipCard.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 CARDS_MODEL cardsModel  = inputs.get(model(cardsModelClass));
		    	 MsgFlipCard msgFlipCard = inputs.get(message(MsgFlipCard.class));
		    	 
		    	 msgFlipCard.applyTo(cardsModel);

		     });
	}

	public static <CARDS_MODEL extends CardsModel,
	               DASHBOARD_MODEL extends DashboardModel,
	               MSG_REGISTER_SIMPLE_OPERATION extends MsgRegisterSimpleOperation<CARDS_MODEL, DASHBOARD_MODEL>> 

	        void registerSimpleOperation(BackendTasks tasks,
	        		                     Class<CARDS_MODEL> cardsModelClass,
			                             Class<MSG_REGISTER_SIMPLE_OPERATION> msgRegisterSimpleOperationClass,
			                             List<CARDS_MODEL> modelHistory) {

		tasks.task("registerSimpleOperation")
		
		     .waitsFor(message(msgRegisterSimpleOperationClass))

		     .thenExecutes(inputs -> {
		    	 
		    	 CARDS_MODEL                   cardsModel                 = inputs.get(model(cardsModelClass));
		    	 MSG_REGISTER_SIMPLE_OPERATION msgRegisterSimpleOperation = inputs.get(message(msgRegisterSimpleOperationClass));
		    	 
		    	 msgRegisterSimpleOperation.applyTo(cardsModel, modelHistory);
		    	 
		    	 CommonBackend.indexCurrentModel = modelHistory.size() - 1;

		     });
	}

	public static <CARDS_MODEL extends CardsModel,
	               DASHBOARD_MODEL extends DashboardModel,
	               MSG_REGISTER_SIMPLE_OPERATION extends MsgRegisterSimpleOperation<CARDS_MODEL, DASHBOARD_MODEL>> 

	        void executionBackStep(BackendTasks tasks,
	        		               Class<CARDS_MODEL> cardsModelClass,
			                       List<CARDS_MODEL> modelHistory) {

		tasks.task("executionBackStep")
		
		     .waitsFor(message(MsgExecutionBackStep.class))

		     .thenExecutes(inputs -> {
		    	 
		    	 CARDS_MODEL                   cardsModel                 = inputs.get(model(cardsModelClass));
		    	 
		    	 CommonBackend.indexCurrentModel--;
		    	 cardsModel.copyDataFrom(modelHistory.get(CommonBackend.indexCurrentModel));

		     });
	}

}
