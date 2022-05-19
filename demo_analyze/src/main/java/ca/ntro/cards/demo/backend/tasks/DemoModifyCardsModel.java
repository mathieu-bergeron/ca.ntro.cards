package ca.ntro.cards.demo.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.demo.messages.MsgUpdateList;
import ca.ntro.cards.demo.models.NaiveSort;
import ca.ntro.cards.demo.models.DemoDashboardModel;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

public class DemoModifyCardsModel {

	public static <STUDENT_MODEL extends NaiveSort> void loadStudentModel(BackendTasks tasks, 
			                                                                   Class<STUDENT_MODEL> cardsModelClass,
			                                                                   STUDENT_MODEL studentModel) {
		tasks.task("loadStudentModel")
		
		     .waitsFor("initializeDashboard")

		     .thenExecutes(inputs -> {
		    	 
		    	 DemoDashboardModel dashboardModel = inputs.get(model(DemoDashboardModel.class));
		    	 STUDENT_MODEL      demoModel      = inputs.get(model(cardsModelClass));

		    	 demoModel.copyDataFrom(studentModel);
		    	 dashboardModel.setNumberOfCards(demoModel.getSourceArray().size());

		     });
	}

	public static <STUDENT_MODEL extends NaiveSort> void updateList(BackendTasks tasks, Class<STUDENT_MODEL> cardsModelClass) {
		tasks.task("updateList")

		     .waitsFor(message(MsgUpdateList.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 STUDENT_MODEL demoModel     = inputs.get(model(cardsModelClass));
		    	 MsgUpdateList msgUpdateList = inputs.get(message(MsgUpdateList.class));
		    	 
		    	 msgUpdateList.applyTo(demoModel);
		    	 
		     });
	}


}
