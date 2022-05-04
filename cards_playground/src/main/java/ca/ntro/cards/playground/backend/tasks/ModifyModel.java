package ca.ntro.cards.playground.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.playground.messages.MsgUpdateList;
import ca.ntro.cards.playground.models.PlaygroundModel;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

public class ModifyModel {

	public static void createTasks(BackendTasks tasks) {
		tasks.taskGroup("ModifyModel")
		
		     .contains(subTasks -> {
		    	 
		    	 updateList(subTasks);

		     });
	}

	private static void updateList(BackendTasks tasks) {
		tasks.task("updateList")

		     .waitsFor(model(PlaygroundModel.class))
		     
		     .waitsFor(message(MsgUpdateList.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 PlaygroundModel playgroundModel = inputs.get(model(PlaygroundModel.class));
		    	 MsgUpdateList   msgUpdateList   = inputs.get(message(MsgUpdateList.class));
		    	 
		    	 msgUpdateList.applyTo(playgroundModel);
		    	 
		     });
	}

}
