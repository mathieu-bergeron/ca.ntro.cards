package ca.ntro.cards.freesort.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.freesort.messages.MsgUpdateList;
import ca.ntro.cards.freesort.models.FreesortCardsModel;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

public class FreesortModifyCardsModel {

	public static void updateList(BackendTasks tasks) {
		tasks.task("updateList")

		     .waitsFor(message(MsgUpdateList.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 FreesortCardsModel freesortModel     = inputs.get(model(FreesortCardsModel.class));
		    	 MsgUpdateList msgUpdateList = inputs.get(message(MsgUpdateList.class));
		    	 
		    	 msgUpdateList.applyTo(freesortModel);
		    	 
		    	 if (msgUpdateList.isSorted() ) {
		    		 // envoyer msgUpdateIsSorted
		    		 // modifier dashboard model (contient isSorted)
		    	 }
		    	 
		     });
	}

}
