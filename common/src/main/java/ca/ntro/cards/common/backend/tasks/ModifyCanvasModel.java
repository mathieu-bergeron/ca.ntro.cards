package ca.ntro.cards.common.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;

import ca.ntro.cards.common.models.CommonCanvasModel;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonExecutableModel;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

import ca.ntro.app.tasks.SubTasksLambda;

public class ModifyCanvasModel {
	
	public static <EXECUTABLE_MODEL extends CommonExecutableModel,
	               CANVAS_MODEL     extends CommonCanvasModel,
	               DASHBOARD_MODEL  extends CommonDashboardModel>

	       void createTasks(BackendTasks tasks,
			                Class<CANVAS_MODEL> canvasModelClass,
			                SubTasksLambda<BackendTasks> subTasksLambda) {
		
		
		tasks.taskGroup("ModifyCanvasModel")
		
		     .waitsFor(model(canvasModelClass))
		
		     .andContains(subTasks -> {

		    	 subTasksLambda.createSubTasks(subTasks);

		     });
	}
	
}
