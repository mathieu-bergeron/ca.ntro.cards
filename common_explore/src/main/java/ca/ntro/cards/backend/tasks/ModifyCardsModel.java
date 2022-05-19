package ca.ntro.cards.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.backend.ModelThread;
import ca.ntro.cards.backend.model_history.ModelHistory;
import ca.ntro.cards.backend.model_history.ModelHistoryFull;
import ca.ntro.cards.messages.MsgExecutionStepBack;
import ca.ntro.cards.messages.MsgExecutionStepForward;
import ca.ntro.cards.messages.MsgFlipCard;
import ca.ntro.cards.messages.MsgRegisterSimpleOperation;
import ca.ntro.cards.models.CardsModel;
import ca.ntro.cards.models.DashboardModel;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

import java.util.concurrent.locks.ReentrantLock;

import ca.ntro.app.tasks.SubTasksLambda;

public class ModifyCardsModel {
	
	public static <CARDS_MODEL extends CardsModel,
	               DASHBOARD_MODEL extends DashboardModel,
	               MSG_REGISTER_SIMPLE_OPERATION extends MsgRegisterSimpleOperation<CARDS_MODEL, DASHBOARD_MODEL>> 

	       void createTasks(BackendTasks tasks,
			                Class<CARDS_MODEL> cardsModelClass,
			                Class<MSG_REGISTER_SIMPLE_OPERATION> msgRegisterSimpleOperationClass,
			                ModelHistoryFull<CARDS_MODEL> modelHistory,
			                ReentrantLock lock,
			                ModelThread<CARDS_MODEL> modelThread,
			                SubTasksLambda<BackendTasks> subTasksLambda) {
		
		createFirstVersionIfNeeded(tasks, cardsModelClass, modelHistory, lock, modelThread);
		
		tasks.taskGroup("ModifyCardsModel")
		
		     .waitsFor("createFirstVersionIfNeeded")
		
		     .andContains(subTasks -> {

		    	 flipCard(subTasks, cardsModelClass);

		    	 registerSimpleOperation(subTasks, 
		    			                 cardsModelClass,
		    			                 msgRegisterSimpleOperationClass,
		    			                 modelHistory);

		    	 executionStepBack(subTasks, 
		    			           cardsModelClass,
		    			           modelHistory);

		    	 executionStepForward(subTasks, 
		    			              cardsModelClass,
		    			              modelHistory);
		    	 
		    	 subTasksLambda.createSubTasks(subTasks);

		     });
	}

	@SuppressWarnings("unchecked")
	public static <CARDS_MODEL extends CardsModel,
	               DASHBOARD_MODEL extends DashboardModel,
	               MSG_REGISTER_SIMPLE_OPERATION extends MsgRegisterSimpleOperation<CARDS_MODEL, DASHBOARD_MODEL>> 
	
	        void createFirstVersionIfNeeded(BackendTasks tasks,
	        		                        Class<CARDS_MODEL> cardsModelClass,
	        		                        ModelHistory<CARDS_MODEL> modelHistory, 
	        		                        ReentrantLock lock,
	        		                        ModelThread<CARDS_MODEL> modelThread) {

		tasks.task("createFirstVersionIfNeeded")

		     .waitsFor(model(cardsModelClass))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 CARDS_MODEL cardsModel = inputs.get(model(cardsModelClass));

		    	 cardsModel.createFirstVersionIfNeeded();
				 cardsModel.registerLock(lock);
				 cardsModel.registerModelHistory(modelHistory);
		    	 
		    	 modelHistory.pushCopyOf((CARDS_MODEL) cardsModel);

				 modelThread.setModel(cardsModel);
				 modelThread.start();

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
			                             ModelHistoryFull<CARDS_MODEL> modelHistory) {

		tasks.task("registerSimpleOperation")
		
		     .waitsFor(message(msgRegisterSimpleOperationClass))

		     .thenExecutes(inputs -> {
		    	 
		    	 CARDS_MODEL                   cardsModel                 = inputs.get(model(cardsModelClass));
		    	 MSG_REGISTER_SIMPLE_OPERATION msgRegisterSimpleOperation = inputs.get(message(msgRegisterSimpleOperationClass));
		    	 
		    	 msgRegisterSimpleOperation.applyTo(cardsModel, modelHistory);
		    	 
		     });
	}

	public static <CARDS_MODEL extends CardsModel,
	               DASHBOARD_MODEL extends DashboardModel,
	               MSG_REGISTER_SIMPLE_OPERATION extends MsgRegisterSimpleOperation<CARDS_MODEL, DASHBOARD_MODEL>> 

	        void executionStepBack(BackendTasks tasks,
	        		               Class<CARDS_MODEL> cardsModelClass,
			                       ModelHistoryFull<CARDS_MODEL> modelHistory) {

		tasks.task("executionStepBack")
		
		     .waitsFor(message(MsgExecutionStepBack.class))

		     .thenExecutes(inputs -> {
		    	 
		    	 CARDS_MODEL cardsModel = inputs.get(model(cardsModelClass));
		    	 
		    	 modelHistory.stepBackward();
		    	 cardsModel.copyDataFrom(modelHistory.currentModel());

		     });
	}

	public static <CARDS_MODEL extends CardsModel>

	        void executionStepForward(BackendTasks tasks,
	        		                  Class<CARDS_MODEL> cardsModelClass,
			                          ModelHistoryFull<CARDS_MODEL> modelHistory) {

		tasks.task("executionStepForward")
		
		     .waitsFor(message(MsgExecutionStepForward.class))

		     .thenExecutes(inputs -> {
		    	 
		    	 CARDS_MODEL cardsModel = inputs.get(model(cardsModelClass));
		    	 
		    	 modelHistory.stepForward();
		    	 cardsModel.copyDataFrom(modelHistory.currentModel());

		     });
	}

}
