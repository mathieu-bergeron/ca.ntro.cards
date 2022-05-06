package ca.ntro.cards.foo.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.foo.messages.MsgUpdateList;
import ca.ntro.cards.foo.models.FooCardsModel;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

public class FooModifyCardsModel {

	public static void updateList(BackendTasks tasks) {
		tasks.task("updateList")

		     .waitsFor(message(MsgUpdateList.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 FooCardsModel fooModel     = inputs.get(model(FooCardsModel.class));
		    	 MsgUpdateList msgUpdateList = inputs.get(message(MsgUpdateList.class));
		    	 
		    	 msgUpdateList.applyTo(fooModel);
		    	 
		     });
	}

}
