package ca.ntro.cards.common.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.CommonTestCase;
import ca.ntro.cards.common.test_cases.CommonTestCaseDatabase;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

import ca.ntro.app.tasks.SubTasksLambda;

public class ModifyTestCaseDatabase {
	
	@SuppressWarnings("rawtypes")
	public static <EXECUTABLE_MODEL extends CommonExecutableModel,
	               STUDENT_MODEL    extends EXECUTABLE_MODEL,
	               TEST_CASE        extends CommonTestCase,
	               TEST_CASES_MODEL extends CommonTestCaseDatabase<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE, ?>,
	               DASHBOARD_MODEL  extends CommonDashboardModel> 

	       void createTasks(BackendTasks tasks,
	    		            TEST_CASES_MODEL testCasesModel,
			                SubTasksLambda<BackendTasks> subTasksLambda) {
		
		tasks.task("TestCasesDatabase").executes(inputs -> {});
		
		tasks.taskGroup("ModifyTestCasesDatabase")
		
		     .waitsFor("TestCasesDatabase")
		
		     .andContains(subTasks -> {

		     });
	}

}
