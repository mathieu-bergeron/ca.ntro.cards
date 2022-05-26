package ca.ntro.cards.demo.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.demo.messages.MsgUpdateList;
import ca.ntro.cards.demo.models.TriNaif;
import ca.ntro.cards.demo.models.DemoProcedureDashboardModel;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

public class DemoModifyCardsModel {

	public static <STUDENT_MODEL extends TriNaif> void loadStudentModel(BackendTasks tasks, 
			                                                                   Class<STUDENT_MODEL> cardsModelClass,
			                                                                   STUDENT_MODEL studentModel) {
		tasks.task("loadStudentModel")
		
		     .waitsFor("initializeDashboard")

		     .thenExecutes(inputs -> {
		    	 
		    	 DemoProcedureDashboardModel dashboardModel = inputs.get(model(DemoProcedureDashboardModel.class));
		    	 STUDENT_MODEL      demoModel      = inputs.get(model(cardsModelClass));

		    	 demoModel.copyDataFrom(studentModel);
		    	 dashboardModel.setNumberOfCards(demoModel.getSource().length);

		     });
	}

	public static <STUDENT_MODEL extends TriNaif> void updateList(BackendTasks tasks, Class<STUDENT_MODEL> cardsModelClass) {
		tasks.task("updateList")

		     .waitsFor(message(MsgUpdateList.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 System.out.println("DemoModifyCardsModel.updateList");
		    	 
		    	 /*
		    	 STUDENT_MODEL demoModel     = inputs.get(model(cardsModelClass));
		    	 MsgUpdateList msgUpdateList = inputs.get(message(MsgUpdateList.class));
		    	 
		    	 msgUpdateList.applyTo(demoModel);
		    	 */
		    	 
		     });
	}


}
