package ca.ntro.cards.demo.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.demo.messages.MsgManualExecutionStep;
import ca.ntro.cards.demo.models.DemoCardsModel;
import ca.ntro.cards.demo.models.DemoProcedureDashboardModel;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

public class DemoModifyCardsModel {

	public static <STUDENT_MODEL extends DemoCardsModel> void updateList(BackendTasks tasks, Class<STUDENT_MODEL> cardsModelClass) {
		tasks.task("manualExecutionStep")

		     .waitsFor(message(MsgManualExecutionStep.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 STUDENT_MODEL          demoModel              = inputs.get(model(cardsModelClass));
		    	 MsgManualExecutionStep msgManualExecutionStep = inputs.get(message(MsgManualExecutionStep.class));
		    	 
		    	 msgManualExecutionStep.applyTo(demoModel);

		     });
	}


}
