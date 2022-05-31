package ca.ntro.cards.freesort.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.freesort.messages.MsgManualExecutionStep;
import ca.ntro.cards.freesort.models.TriLibre;
import ca.ntro.cards.freesort.models.FreesortProcedureDashboardModel;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

public class FreesortModifyCardsModel {

	public static <STUDENT_MODEL extends TriLibre> void updateList(BackendTasks tasks, Class<STUDENT_MODEL> cardsModelClass) {
		tasks.task("manualExecutionStep")

		     .waitsFor(message(MsgManualExecutionStep.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 STUDENT_MODEL          freesortModel              = inputs.get(model(cardsModelClass));
		    	 MsgManualExecutionStep msgManualExecutionStep = inputs.get(message(MsgManualExecutionStep.class));
		    	 
		    	 msgManualExecutionStep.applyTo(freesortModel);

		     });
	}


}
