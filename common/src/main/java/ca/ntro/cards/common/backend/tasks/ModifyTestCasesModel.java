package ca.ntro.cards.common.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.common.messages.MsgGenerateTestCase;
import ca.ntro.cards.common.models.CommonCardsModel;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonTestCasesModel;
import ca.ntro.cards.common.models.values.CommonTestCase;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

import ca.ntro.app.tasks.SubTasksLambda;

public class ModifyTestCasesModel {
	
	public static <CARDS_MODEL      extends CommonCardsModel,
	               TEST_CASE        extends CommonTestCase<CARDS_MODEL>,
	               TEST_CASES_MODEL extends CommonTestCasesModel<CARDS_MODEL, TEST_CASE>,
	               DASHBOARD_MODEL  extends CommonDashboardModel> 

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
	public static <CARDS_MODEL      extends CommonCardsModel,
	               TEST_CASE        extends CommonTestCase<CARDS_MODEL>,
		           TEST_CASES_MODEL extends CommonTestCasesModel<CARDS_MODEL, TEST_CASE>>
	
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
