package ca.ntro.cards.backend;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.common.backend.ExecutableModelThread;
import ca.ntro.cards.common.backend.CommonBackend;
import ca.ntro.cards.common.test_cases.TestCase;
import ca.ntro.cards.common.test_cases.TestCasesModel;
import ca.ntro.cards.common.test_cases.descriptor.TestCaseDescriptor;
import ca.ntro.cards.common.test_cases.execution_trace.ExecutionTraceFull;
import ca.ntro.cards.messages.MsgExecutionStepBack;
import ca.ntro.cards.messages.MsgExecutionStepForward;
import ca.ntro.cards.messages.MsgFlipCard;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.cards.models.ProcedureDashboardModel;
import ca.ntro.cards.models.ProcedureSettingsModel;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

import java.util.concurrent.locks.ReentrantLock;

public abstract class ProcedureBackend<EXECUTABLE_MODEL extends ProcedureCardsModel,
                                       STUDENT_MODEL    extends EXECUTABLE_MODEL,
                                       CANVAS_MODEL     extends ProcedureCardsModel,
                                       TEST_CASE        extends TestCase,
                                       TEST_CASES_MODEL extends TestCasesModel,
                                       DASHBOARD_MODEL  extends ProcedureDashboardModel,
                                       SETTINGS_MODEL   extends ProcedureSettingsModel>

                extends CommonBackend<EXECUTABLE_MODEL, 
                                      STUDENT_MODEL,
                                      CANVAS_MODEL,
                                      TEST_CASE, 
                                      TEST_CASES_MODEL, 
                                      DASHBOARD_MODEL, 
                                      SETTINGS_MODEL> {

	private ReentrantLock lock = new ReentrantLock();
	private ExecutableModelThread<EXECUTABLE_MODEL> modelThread = new ExecutableModelThread<>();
	private ExecutionTraceFull<EXECUTABLE_MODEL> modelHistory = new ExecutionTraceFull<>();

	@Override
	protected void initializeCanvasModel(CANVAS_MODEL canvasModel) {
		
		@SuppressWarnings("unchecked")
		EXECUTABLE_MODEL executableModel = (EXECUTABLE_MODEL) canvasModel;

		executableModel.generateTestCase(TestCaseDescriptor.create().testCaseId("ex01"));
		executableModel.registerLock(lock);
		executableModel.registerModelHistory(modelHistory);

		modelHistory.pushCopyOf(executableModel);

		modelThread.setModel(executableModel);
		modelThread.start();

	}


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
		    	 
		    	 getModelHistory().stepForward();
		    	 cardsModel.copyDataFrom(getModelHistory().currentModel());

		     });
	}

	private void executionStepBack(BackendTasks tasks) {
		tasks.task("executionStepBack")
		
		     .waitsFor(message(MsgExecutionStepBack.class))

		     .thenExecutes(inputs -> {
		    	 
		    	 EXECUTABLE_MODEL cardsModel = inputs.get(model(getExecutableModelClass()));
		    	 
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
