package ca.ntro.cards.demo.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.demo.messages.MsgUpdateList;
import ca.ntro.cards.demo.models.DemoModel;

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

		     .waitsFor(model(DemoModel.class))
		     
		     .waitsFor(message(MsgUpdateList.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 DemoModel demoModel = inputs.get(model(DemoModel.class));
		    	 MsgUpdateList   msgUpdateList   = inputs.get(message(MsgUpdateList.class));
		    	 
		    	 msgUpdateList.applyTo(demoModel);
		    	 
		     });
	}

}
