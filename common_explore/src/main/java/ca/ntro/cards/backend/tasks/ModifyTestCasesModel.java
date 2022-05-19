package ca.ntro.cards.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.models.CardsModel;
import ca.ntro.cards.models.DashboardModel;
import ca.ntro.cards.models.TestCasesModel;
import ca.ntro.cards.models.values.TestCase;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

import ca.ntro.app.tasks.SubTasksLambda;

public class ModifyTestCasesModel {
	
	public static <CARDS_MODEL extends CardsModel,
	               TEST_CASE extends TestCase<CARDS_MODEL>,
	               TEST_CASES_MODEL extends TestCasesModel<CARDS_MODEL, TEST_CASE>,
	               DASHBOARD_MODEL extends DashboardModel> 

	       void createTasks(BackendTasks tasks,
			                Class<TEST_CASES_MODEL> testCasesModelClass,
			                SubTasksLambda<BackendTasks> subTasksLambda) {
		
		createFirstVersionIfNeeded(tasks, testCasesModelClass);
		
		tasks.taskGroup("ModifyTestCasesModel")
		
		     .waitsFor("createFirstVersionIfNeeded")
		
		     .andContains(subTasks -> {

		     });
	}

	@SuppressWarnings("unchecked")
	public static <CARDS_MODEL extends CardsModel,
	               TEST_CASE extends TestCase<CARDS_MODEL>,
		           TEST_CASES_MODEL extends TestCasesModel<CARDS_MODEL, TEST_CASE>,
	               DASHBOARD_MODEL extends DashboardModel>
	
	        void createFirstVersionIfNeeded(BackendTasks tasks,
	        		                        Class<TEST_CASES_MODEL> testCasesModelClass) {

		tasks.task("createFirstVersionIfNeeded")

		     .waitsFor(model(testCasesModelClass))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 TEST_CASES_MODEL testCasesModel = inputs.get(model(testCasesModelClass));
		    	 
		    	 testCasesModel.generateFirstVersionIfNeeded();

		     });
	}
}
