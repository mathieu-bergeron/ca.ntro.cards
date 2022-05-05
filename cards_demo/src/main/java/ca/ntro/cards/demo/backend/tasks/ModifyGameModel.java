package ca.ntro.cards.demo.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.demo.messages.MsgUpdateList;
import ca.ntro.cards.demo.models.DemoCardsModel;
import ca.ntro.cards.messages.MsgFlipCard;
import ca.ntro.cards.models.CardsModel;

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

		     .waitsFor(model(DemoCardsModel.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 CardsModel gameModel = inputs.get(model(DemoCardsModel.class));
		    	 
		    	 gameModel.createFirstVersionIfNeeded();
		    	 
		     });
	}

	private static void updateList(BackendTasks tasks) {
		tasks.task("updateList")

		     .waitsFor(message(MsgUpdateList.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 DemoCardsModel demoModel     = inputs.get(model(DemoCardsModel.class));
		    	 MsgUpdateList msgUpdateList = inputs.get(message(MsgUpdateList.class));
		    	 
		    	 msgUpdateList.applyTo(demoModel);
		    	 
		     });
	}

	private static void flipCard(BackendTasks tasks) {
		tasks.task("flipCard")

		     .waitsFor(message(MsgFlipCard.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 CardsModel   gameModel   = inputs.get(model(DemoCardsModel.class));
		    	 MsgFlipCard msgFlipCard = inputs.get(message(MsgFlipCard.class));
		    	 
		    	 msgFlipCard.applyTo(gameModel);
		    	 
		     });
	}

}
