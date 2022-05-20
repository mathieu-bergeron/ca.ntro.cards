package ca.ntro.cards.backend.tasks;

import java.util.concurrent.locks.ReentrantLock;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.backend.ModelThread;
import ca.ntro.cards.backend.model_history.ModelHistory;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.cards.models.DashboardModel;
import ca.ntro.cards.models.TestCasesModel;
import ca.ntro.cards.models.values.TestCase;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

public class InitializeModels {

	@SuppressWarnings("rawtypes")
	public static <CARDS_MODEL extends ProcedureCardsModel,
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <CARDS_MODEL extends ProcedureCardsModel,
	               DASHBOARD_MODEL extends DashboardModel>
	
	        void initializeCards(BackendTasks tasks,
	        		             Class<CARDS_MODEL> cardsModelClass,
	        		             ModelHistory<CARDS_MODEL> modelHistory, 
	        		             ReentrantLock lock,
	        		             ModelThread<CARDS_MODEL> modelThread) {

		tasks.task("initializeCards")

		     .waitsFor(model(cardsModelClass))

		     .waitsFor("initializeTestCases")
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 CARDS_MODEL cardsModel = inputs.get(model(cardsModelClass));

		    	 cardsModel.createFirstVersionIfNeeded();
				 cardsModel.registerLock(lock);
				 cardsModel.registerModelHistory(modelHistory);
		    	 
		    	 modelHistory.pushCopyOf((CARDS_MODEL) cardsModel);

				 modelThread.setModel(cardsModel);
				 modelThread.start();

		     });
	}

	@SuppressWarnings("rawtypes")
	public static <CARDS_MODEL extends ProcedureCardsModel,
	               DASHBOARD_MODEL extends DashboardModel>
	
	        void initializeDashboard(BackendTasks tasks,
	        		                 Class<DASHBOARD_MODEL> dashboardModelClass,
	        		                 ModelHistory<CARDS_MODEL> modelHistory) {

		tasks.task("initializeDashboard")

		     .waitsFor(model(dashboardModelClass))

		     .waitsFor("initializeCards")
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 DASHBOARD_MODEL dashboardModel = inputs.get(model(dashboardModelClass));
		    	 
		    	 modelHistory.updateDashboard(dashboardModel);
		    	 
		     });
	}

}
