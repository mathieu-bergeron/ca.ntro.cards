package ca.ntro.cards.foo.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.foo.messages.MsgManualExecutionStep;
import ca.ntro.cards.foo.models.FooCardsModel;
import ca.ntro.cards.foo.models.FooProcedureDashboardModel;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

public class FooModifyCardsModel {

	public static <STUDENT_MODEL extends FooCardsModel> void updateList(BackendTasks tasks, Class<STUDENT_MODEL> cardsModelClass) {
		tasks.task("manualExecutionStep")

		     .waitsFor(message(MsgManualExecutionStep.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 STUDENT_MODEL          fooModel              = inputs.get(model(cardsModelClass));
		    	 MsgManualExecutionStep msgManualExecutionStep = inputs.get(message(MsgManualExecutionStep.class));
		    	 
		    	 msgManualExecutionStep.applyTo(fooModel);

		     });
	}


}
