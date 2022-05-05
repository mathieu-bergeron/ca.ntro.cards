package ca.ntro.cards.playground.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.messages.MsgFlipCard;
import ca.ntro.cards.models.GameModel;
import ca.ntro.cards.playground.models.PlaygroundGameModel;

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

		     .waitsFor(model(PlaygroundGameModel.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 GameModel demoModel = inputs.get(model(PlaygroundGameModel.class));
		    	 
		    	 demoModel.createFirstVersionIfNeeded();
		    	 
		     });
	}


	private static void flipCard(BackendTasks tasks) {
		tasks.task("flipCard")

		     .waitsFor(message(MsgFlipCard.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 GameModel   demoModel   = inputs.get(model(PlaygroundGameModel.class));
		    	 MsgFlipCard msgFlipCard = inputs.get(message(MsgFlipCard.class));
		    	 
		    	 msgFlipCard.applyTo(demoModel);
		    	 
		     });
	}

}
