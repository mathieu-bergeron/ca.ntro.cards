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
		
		initializeTestCases(tasks, testCasesModelClass);
		
		tasks.taskGroup("ModifyTestCasesModel")
		
		     .waitsFor("initializeTestCases")
		
		     .andContains(subTasks -> {
		    	 
		    	 addTestCase(subTasks, testCasesModelClass);

		     });
	}

	@SuppressWarnings("unchecked")
	public static <CARDS_MODEL extends CardsModel,
	               TEST_CASE extends TestCase<CARDS_MODEL>,
		           TEST_CASES_MODEL extends TestCasesModel<CARDS_MODEL, TEST_CASE>,
	               DASHBOARD_MODEL extends DashboardModel>
	
	        void initializeTestCases(BackendTasks tasks,
	        		                 Class<TEST_CASES_MODEL> testCasesModelClass) {

		tasks.task("initializeTestCases")

		     .waitsFor(model(testCasesModelClass))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 TEST_CASES_MODEL testCasesModel = inputs.get(model(testCasesModelClass));
		    	 
		    	 testCasesModel.generateFirstVersionIfNeeded();

		     });
	}

	@SuppressWarnings("unchecked")
	public static <CARDS_MODEL extends CardsModel,
	               TEST_CASE extends TestCase<CARDS_MODEL>,
		           TEST_CASES_MODEL extends TestCasesModel<CARDS_MODEL, TEST_CASE>>
	
	        void addTestCase(BackendTasks tasks,
	        		         Class<TEST_CASES_MODEL> testCasesModelClass) {

		tasks.task("addTestCaseDummy")

		     .thenExecutes(inputs -> {
		    	 
		    	 TEST_CASES_MODEL testCasesModel = inputs.get(model(testCasesModelClass));

		     });
	}
}
