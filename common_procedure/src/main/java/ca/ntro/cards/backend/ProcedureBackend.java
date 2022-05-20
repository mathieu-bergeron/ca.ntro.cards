package ca.ntro.cards.backend;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.common.backend.CommonBackend;
import ca.ntro.cards.messages.MsgExecutionStepBack;
import ca.ntro.cards.messages.MsgExecutionStepForward;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.cards.models.ProcedureDashboardModel;
import ca.ntro.cards.models.ProcedureSettingsModel;
import ca.ntro.cards.models.ProcedureTestCasesModel;
import ca.ntro.cards.models.values.ProcedureTestCase;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

public abstract class ProcedureBackend<CARDS_MODEL      extends ProcedureCardsModel,
                                       TEST_CASE        extends ProcedureTestCase<CARDS_MODEL>,
                                       TEST_CASES_MODEL extends ProcedureTestCasesModel<CARDS_MODEL, TEST_CASE>,
                                       DASHBOARD_MODEL  extends ProcedureDashboardModel,
                                       SETTINGS_MODEL   extends ProcedureSettingsModel>

                extends CommonBackend<CARDS_MODEL, TEST_CASE, TEST_CASES_MODEL, DASHBOARD_MODEL, SETTINGS_MODEL> {

	protected void addSubTasksToModifyTestCasesModel(BackendTasks tasks) {
		
		
	}

	protected void addSubTasksToModifyCardsModel(BackendTasks tasks) {

		executionStepBack(tasks);

		executionStepForward(tasks);

	}

	private void executionStepForward(BackendTasks tasks) {
		tasks.task("executionStepForward")
		
		     .waitsFor(message(MsgExecutionStepForward.class))

		     .thenExecutes(inputs -> {
		    	 
		    	 CARDS_MODEL cardsModel = inputs.get(model(getCardsModelClass()));
		    	 
		    	 getModelHistory().stepForward();
		    	 cardsModel.copyDataFrom(getModelHistory().currentModel());

		     });
	}

	private void executionStepBack(BackendTasks tasks) {
		tasks.task("executionStepBack")
		
		     .waitsFor(message(MsgExecutionStepBack.class))

		     .thenExecutes(inputs -> {
		    	 
		    	 CARDS_MODEL cardsModel = inputs.get(model(getCardsModelClass()));
		    	 
		    	 getModelHistory().stepBackward();
		    	 cardsModel.copyDataFrom(getModelHistory().currentModel());

		     });
	}

	protected void addSubTasksToModifyDashboardModel(BackendTasks tasks) {
		
	}

	protected void addSubTasksToModifySettingsModel(BackendTasks tasks) {
		
	}

	protected void addSubTasksToManageThread(BackendTasks tasks) {
		
	}

}
