package ca.ntro.cards.demo.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.demo.messages.MsgUpdateList;
import ca.ntro.cards.demo.models.DemoCardsModel;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

public class DemoModifyCardsModel {

	public static <STUDENT_MODEL extends DemoCardsModel> void loadStudentModel(BackendTasks tasks, 
			                                                                   Class<STUDENT_MODEL> cardsModelClass,
			                                                                   STUDENT_MODEL studentModel) {
		tasks.task("loadStudentModel")

		     .thenExecutes(inputs -> {
		    	 
		    	 STUDENT_MODEL demoModel     = inputs.get(model(cardsModelClass));
		    	 demoModel.copyDataFrom(studentModel);

		     });
	}

	public static <STUDENT_MODEL extends DemoCardsModel> void updateList(BackendTasks tasks, Class<STUDENT_MODEL> cardsModelClass) {
		tasks.task("updateList")

		     .waitsFor(message(MsgUpdateList.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 STUDENT_MODEL demoModel     = inputs.get(model(cardsModelClass));
		    	 MsgUpdateList msgUpdateList = inputs.get(message(MsgUpdateList.class));
		    	 
		    	 msgUpdateList.applyTo(demoModel);
		    	 
		     });
	}


}
