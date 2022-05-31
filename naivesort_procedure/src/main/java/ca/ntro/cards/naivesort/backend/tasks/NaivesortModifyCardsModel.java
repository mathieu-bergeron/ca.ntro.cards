package ca.ntro.cards.naivesort.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.naivesort.messages.MsgManualExecutionStep;
import ca.ntro.cards.naivesort.models.NaivesortCardsModel;
import ca.ntro.cards.naivesort.models.NaivesortProcedureDashboardModel;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

public class NaivesortModifyCardsModel {

	public static <STUDENT_MODEL extends NaivesortCardsModel> void updateList(BackendTasks tasks, Class<STUDENT_MODEL> cardsModelClass) {
		tasks.task("manualExecutionStep")

		     .waitsFor(message(MsgManualExecutionStep.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 STUDENT_MODEL          naivesortModel              = inputs.get(model(cardsModelClass));
		    	 MsgManualExecutionStep msgManualExecutionStep = inputs.get(message(MsgManualExecutionStep.class));
		    	 
		    	 msgManualExecutionStep.applyTo(naivesortModel);

		     });
	}


}
