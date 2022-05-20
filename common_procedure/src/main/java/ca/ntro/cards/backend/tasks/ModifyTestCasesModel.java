package ca.ntro.cards.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.messages.MsgGenerateTestCase;
import ca.ntro.cards.models.ExploreCardsModel;
import ca.ntro.cards.models.DashboardModel;
import ca.ntro.cards.models.TestCasesModel;
import ca.ntro.cards.models.values.TestCase;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

import ca.ntro.app.tasks.SubTasksLambda;

public class ModifyTestCasesModel {
	
	public static <CARDS_MODEL extends ExploreCardsModel,
	               TEST_CASE extends TestCase<CARDS_MODEL>,
	               TEST_CASES_MODEL extends TestCasesModel<CARDS_MODEL, TEST_CASE>,
	               DASHBOARD_MODEL extends DashboardModel> 

	       void createTasks(BackendTasks tasks,
			                Class<TEST_CASES_MODEL> testCasesModelClass,
			                SubTasksLambda<BackendTasks> subTasksLambda) {
		
		tasks.taskGroup("ModifyTestCasesModel")
		
		     .waitsFor("initializeCards")
		
		     .andContains(subTasks -> {
		    	 
		    	 generateTestCase(subTasks, testCasesModelClass);

		     });
	}


	@SuppressWarnings("unchecked")
	public static <CARDS_MODEL extends ExploreCardsModel,
	               TEST_CASE extends TestCase<CARDS_MODEL>,
		           TEST_CASES_MODEL extends TestCasesModel<CARDS_MODEL, TEST_CASE>>
	
	        void generateTestCase(BackendTasks tasks,
	        		              Class<TEST_CASES_MODEL> testCasesModelClass) {

		tasks.task("generateTestCase")
		
		     .waitsFor(message(MsgGenerateTestCase.class))

		     .thenExecutes(inputs -> {
		    	 
		    	 MsgGenerateTestCase msgGenerateTestCase = inputs.get(message(MsgGenerateTestCase.class));
		    	 TEST_CASES_MODEL    testCasesModel      = inputs.get(model(testCasesModelClass));
		    	 
		    	 msgGenerateTestCase.applyTo(testCasesModel);
		    	 
		    	 

		     });
	}
}
