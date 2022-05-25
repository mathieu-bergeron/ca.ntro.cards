package ca.ntro.cards.backend;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.common.backend.CommonBackend;
import ca.ntro.cards.common.test_cases.TestCase;
import ca.ntro.cards.common.test_cases.TestCasesDatabase;
import ca.ntro.cards.messages.MsgExecutionStepBack;
import ca.ntro.cards.messages.MsgExecutionStepForward;
import ca.ntro.cards.messages.MsgFlipCard;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.cards.models.ProcedureDashboardModel;
import ca.ntro.cards.models.ProcedureSettingsModel;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

public abstract class ProcedureBackend<EXECUTABLE_MODEL extends ProcedureCardsModel,
                                       STUDENT_MODEL    extends EXECUTABLE_MODEL,
                                       CANVAS_MODEL     extends ProcedureCardsModel,
                                       TEST_CASE        extends TestCase,
                                       TEST_CASES_MODEL extends TestCasesDatabase,
                                       DASHBOARD_MODEL  extends ProcedureDashboardModel,
                                       SETTINGS_MODEL   extends ProcedureSettingsModel>

                extends CommonBackend<EXECUTABLE_MODEL, 
                                      STUDENT_MODEL,
                                      CANVAS_MODEL,
                                      TEST_CASE, 
                                      TEST_CASES_MODEL, 
                                      DASHBOARD_MODEL, 
                                      SETTINGS_MODEL> {

	protected void addSubTasksToModifyTestCasesModel(BackendTasks tasks) {
		
		
	}

	protected void addSubTasksToModifyCardsModel(BackendTasks tasks) {

		executionStepBack(tasks);

		executionStepForward(tasks);

		flipCard(tasks);

	}

	private void flipCard(BackendTasks tasks) {
		tasks.task("flipCard")

		     .waitsFor(message(MsgFlipCard.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 EXECUTABLE_MODEL cardsModel  = inputs.get(model(getExecutableModelClass()));
		    	 MsgFlipCard msgFlipCard = inputs.get(message(MsgFlipCard.class));
		    	 
		    	 msgFlipCard.applyTo(cardsModel);

		     });
	}

	private void executionStepForward(BackendTasks tasks) {
		tasks.task("executionStepForward")
		
		     .waitsFor(message(MsgExecutionStepForward.class))

		     .thenExecutes(inputs -> {
		    	 
		    	 EXECUTABLE_MODEL cardsModel = inputs.get(model(getExecutableModelClass()));
		    	 
		    	 testCasesModel().stepForward();
		    	 cardsModel.copyDataFrom(testCasesModel().currentModel());

		     });
	}

	private void executionStepBack(BackendTasks tasks) {
		tasks.task("executionStepBack")
		
		     .waitsFor(message(MsgExecutionStepBack.class))

		     .thenExecutes(inputs -> {
		    	 
		    	 EXECUTABLE_MODEL cardsModel = inputs.get(model(getExecutableModelClass()));

		    	 testCasesModel().stepBackward();
		    	 cardsModel.copyDataFrom(testCasesModel().currentModel());

		     });
	}

	protected void addSubTasksToModifyDashboardModel(BackendTasks tasks) {
		
	}

	protected void addSubTasksToModifySettingsModel(BackendTasks tasks) {
		
	}

	protected void addSubTasksToManageThread(BackendTasks tasks) {
		
	}

}
