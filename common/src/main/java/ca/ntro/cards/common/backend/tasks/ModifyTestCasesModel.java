package ca.ntro.cards.common.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.common.messages.MsgGenerateTestCase;
import ca.ntro.cards.common.models.CommonCanvasModel;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.models.TestCasesModel;
import ca.ntro.cards.common.models.values.TestCase;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

import ca.ntro.app.tasks.SubTasksLambda;

public class ModifyTestCasesModel {
	
	public static <EXECUTABLE_MODEL extends CommonExecutableModel,
	               TEST_CASE        extends TestCase<EXECUTABLE_MODEL>,
	               TEST_CASES_MODEL extends TestCasesModel<EXECUTABLE_MODEL, TEST_CASE>,
	               DASHBOARD_MODEL  extends CommonDashboardModel> 

	       void createTasks(BackendTasks tasks,
			                Class<TEST_CASES_MODEL> testCasesModelClass,
			                SubTasksLambda<BackendTasks> subTasksLambda) {
		
		tasks.taskGroup("ModifyTestCasesModel")
		
		     .waitsFor("initializeCanvasModel")
		
		     .andContains(subTasks -> {
		    	 
		    	 generateTestCase(subTasks, testCasesModelClass);

		     });
	}


	@SuppressWarnings("unchecked")
	public static <EXECUTABLE_MODEL extends CommonExecutableModel,
	               TEST_CASE        extends TestCase<EXECUTABLE_MODEL>,
		           TEST_CASES_MODEL extends TestCasesModel<EXECUTABLE_MODEL, TEST_CASE>>
	
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
