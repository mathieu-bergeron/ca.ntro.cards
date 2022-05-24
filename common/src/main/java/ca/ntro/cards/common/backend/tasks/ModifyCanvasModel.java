package ca.ntro.cards.common.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.common.models.CommonCanvasModel;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.execution_trace.ExecutionTraceFull;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

import java.util.concurrent.locks.ReentrantLock;

import ca.ntro.app.tasks.SubTasksLambda;

public class ModifyCanvasModel {
	
	public static <EXECUTABLE_MODEL extends CommonExecutableModel,
	               CANVAS_MODEL     extends CommonCanvasModel,
	               DASHBOARD_MODEL  extends CommonDashboardModel>

	       void createTasks(BackendTasks tasks,
			                Class<CANVAS_MODEL> canvasModelClass,
			                ExecutionTraceFull<EXECUTABLE_MODEL> executionTrace,
			                ReentrantLock lock,
			                SubTasksLambda<BackendTasks> subTasksLambda) {
		
		
		tasks.taskGroup("ModifyCardsModel")
		
		     .waitsFor("initializeCanvasModel")
		
		     .andContains(subTasks -> {

		    	 flipCard(subTasks, canvasModelClass);

		    	 subTasksLambda.createSubTasks(subTasks);

		     });
	}
	


	private static <CARDS_MODEL extends CommonCanvasModel> 

	        void flipCard(BackendTasks tasks,
	        		      Class<CARDS_MODEL> cardsModelClass) {

	}

}
