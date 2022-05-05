package ca.ntro.cards.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.messages.MsgFlipCard;
import ca.ntro.cards.models.CardsModel;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

import ca.ntro.app.tasks.SubTasksLambda;

public class ModifyCardsModel {

	public static <CARDS_MODEL extends CardsModel> 
	
	       void createTasks(BackendTasks tasks,
			                Class<CARDS_MODEL> cardsModelClass,
			                SubTasksLambda<BackendTasks> subTasksLambda) {
		
		createFirstVersionIfNeeded(tasks, cardsModelClass);
		
		tasks.taskGroup("ModifyCardsModel")
		
		     .waitsFor("createFirstVersionIfNeeded")
		
		     .andContains(subTasks -> {

		    	 flipCard(subTasks, cardsModelClass);
		    	 
		    	 subTasksLambda.createSubTasks(subTasks);

		     });
	}

	private static <CARDS_MODEL extends CardsModel> 
	
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

}
