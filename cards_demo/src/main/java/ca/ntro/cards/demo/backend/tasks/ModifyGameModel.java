package ca.ntro.cards.demo.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.demo.messages.MsgUpdateList;
import ca.ntro.cards.demo.models.DemoGameModel;
import ca.ntro.cards.messages.MsgFlipCard;
import ca.ntro.cards.models.GameModel;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

public class ModifyGameModel {

	public static void createTasks(BackendTasks tasks) {
		
		createFirstVersionIfNeeded(tasks);
		
		tasks.taskGroup("ModifyModel")
		
		     .waitsFor("createFirstVersionIfNeeded")
		
		     .andContains(subTasks -> {

		    	 updateList(subTasks);

		    	 flipCard(subTasks);

		     });
	}

	private static void createFirstVersionIfNeeded(BackendTasks tasks) {
		tasks.task("createFirstVersionIfNeeded")

		     .waitsFor(model(DemoGameModel.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 GameModel gameModel = inputs.get(model(DemoGameModel.class));
		    	 
		    	 gameModel.createFirstVersionIfNeeded();
		    	 
		     });
	}

	private static void updateList(BackendTasks tasks) {
		tasks.task("updateList")

		     .waitsFor(message(MsgUpdateList.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 DemoGameModel demoModel     = inputs.get(model(DemoGameModel.class));
		    	 MsgUpdateList msgUpdateList = inputs.get(message(MsgUpdateList.class));
		    	 
		    	 msgUpdateList.applyTo(demoModel);
		    	 
		     });
	}

	private static void flipCard(BackendTasks tasks) {
		tasks.task("flipCard")

		     .waitsFor(message(MsgFlipCard.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 GameModel   gameModel   = inputs.get(model(DemoGameModel.class));
		    	 MsgFlipCard msgFlipCard = inputs.get(message(MsgFlipCard.class));
		    	 
		    	 msgFlipCard.applyTo(gameModel);
		    	 
		     });
	}

}
