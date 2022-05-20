package ca.ntro.cards.common.backend.tasks;

import java.util.concurrent.locks.ReentrantLock;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.common.backend.ModelThread;
import ca.ntro.cards.common.backend.model_history.ModelHistory;
import ca.ntro.cards.common.models.CommonCanvasModel;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonTestCasesModel;
import ca.ntro.cards.common.models.values.CommonTestCase;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

public class InitializeModels {

	@SuppressWarnings("rawtypes")
	public static <CARDS_MODEL      extends CommonCanvasModel,
	               TEST_CASE        extends CommonTestCase<CARDS_MODEL>,
		           TEST_CASES_MODEL extends CommonTestCasesModel<CARDS_MODEL, TEST_CASE>,
	               DASHBOARD_MODEL  extends CommonDashboardModel>
	
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
	public static <CARDS_MODEL extends CommonCanvasModel,
	               DASHBOARD_MODEL extends CommonDashboardModel>
	
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
	public static <CARDS_MODEL extends CommonCanvasModel,
	               DASHBOARD_MODEL extends CommonDashboardModel>
	
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
