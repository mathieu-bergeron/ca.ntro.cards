package ca.ntro.cards.playground.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.messages.MsgFlipCard;
import ca.ntro.cards.models.CardsModel;
import ca.ntro.cards.playground.models.PlaygroundCardsModel;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

public class ModifyGameModel {

	public static void createTasks(BackendTasks tasks) {
		
		createFirstVersionIfNeeded(tasks);
		
		tasks.taskGroup("ModifyGameModel")
		
		     .waitsFor("createFirstVersionIfNeeded")
		
		     .contains(subTasks -> {

		    	 flipCard(subTasks);

		     });
	}

	private static void createFirstVersionIfNeeded(BackendTasks tasks) {
		tasks.task("createFirstVersionIfNeeded")

		     .waitsFor(model(PlaygroundCardsModel.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 CardsModel demoModel = inputs.get(model(PlaygroundCardsModel.class));
		    	 
		    	 demoModel.createFirstVersionIfNeeded();
		    	 
		     });
	}


	private static void flipCard(BackendTasks tasks) {
		tasks.task("flipCard")

		     .waitsFor(message(MsgFlipCard.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 CardsModel   demoModel   = inputs.get(model(PlaygroundCardsModel.class));
		    	 MsgFlipCard msgFlipCard = inputs.get(message(MsgFlipCard.class));
		    	 
		    	 msgFlipCard.applyTo(demoModel);
		    	 
		     });
	}

}
