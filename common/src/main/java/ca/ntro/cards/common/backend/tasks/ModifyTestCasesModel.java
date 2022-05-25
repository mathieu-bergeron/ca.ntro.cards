package ca.ntro.cards.common.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.common.messages.MsgGenerateTestCase;
import ca.ntro.cards.common.models.CommonCanvasModel;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.TestCase;
import ca.ntro.cards.common.test_cases.TestCasesDatabase;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

import ca.ntro.app.tasks.SubTasksLambda;

public class ModifyTestCasesModel {
	
	public static <EXECUTABLE_MODEL extends CommonExecutableModel,
	               STUDENT_MODEL    extends EXECUTABLE_MODEL,
	               TEST_CASE        extends TestCase,
	               TEST_CASES_MODEL extends TestCasesDatabase<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE>,
	               DASHBOARD_MODEL  extends CommonDashboardModel> 

	       void createTasks(BackendTasks tasks,
	    		            TEST_CASES_MODEL testCasesModel,
			                SubTasksLambda<BackendTasks> subTasksLambda) {
		
		tasks.taskGroup("ModifyTestCasesModel")
		
		     .contains(subTasks -> {
		    	 
		    	 generateTestCase(subTasks, testCasesModel);

		     });
	}


	@SuppressWarnings("unchecked")
	public static <EXECUTABLE_MODEL extends CommonExecutableModel,
	               STUDENT_MODEL    extends EXECUTABLE_MODEL,
	               TEST_CASE        extends TestCase,
		           TEST_CASES_MODEL extends TestCasesDatabase<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE>>
	
	        void generateTestCase(BackendTasks tasks,
	        		              TEST_CASES_MODEL testCasesModel) {

		tasks.task("generateTestCase")
		
		     .waitsFor(message(MsgGenerateTestCase.class))

		     .thenExecutes(inputs -> {
		    	 
		    	 MsgGenerateTestCase msgGenerateTestCase = inputs.get(message(MsgGenerateTestCase.class));
		    	 
		    	 msgGenerateTestCase.applyTo(testCasesModel);

		     });
	}
}
