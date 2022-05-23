package ca.ntro.cards.common.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.common.models.CommonCanvasModel;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.models.values.execution_trace.ExecutionTraceFull;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

import java.util.concurrent.locks.ReentrantLock;

import ca.ntro.app.tasks.SubTasksLambda;

public class ModifyCardsModel {
	
	public static <EXECUTABLE_MODEL extends CommonExecutableModel,
	               DASHBOARD_MODEL extends CommonDashboardModel>

	       void createTasks(BackendTasks tasks,
			                Class<EXECUTABLE_MODEL> cardsModelClass,
			                ExecutionTraceFull<EXECUTABLE_MODEL> executionTrace,
			                ReentrantLock lock,
			                SubTasksLambda<BackendTasks> subTasksLambda) {
		
		
		tasks.taskGroup("ModifyCardsModel")
		
		     .waitsFor("initializeCanvasModel")
		
		     .andContains(subTasks -> {

		    	 flipCard(subTasks, cardsModelClass);

		    	 subTasksLambda.createSubTasks(subTasks);

		     });
	}
	


	private static <CARDS_MODEL extends CommonCanvasModel> 

	        void flipCard(BackendTasks tasks,
	        		      Class<CARDS_MODEL> cardsModelClass) {

	}

}
