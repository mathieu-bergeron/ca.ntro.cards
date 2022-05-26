package ca.ntro.cards.backend;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.common.backend.CommonBackend;
import ca.ntro.cards.common.messages.MsgNewTestCaseLoaded;
import ca.ntro.cards.messages.MsgExecutionStepBack;
import ca.ntro.cards.messages.MsgExecutionStepForward;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.cards.models.ProcedureDashboardModel;
import ca.ntro.cards.models.ProcedureSettingsModel;
import ca.ntro.cards.test_cases.ProcedureTestCase;
import ca.ntro.cards.test_cases.ProcedureTestCaseDatabase;
import ca.ntro.core.initialization.Ntro;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

import ca.ntro.app.NtroApp;
import ca.ntro.app.models.Watch;

public abstract class ProcedureBackend<EXECUTABLE_MODEL   extends ProcedureCardsModel,
                                       STUDENT_MODEL      extends EXECUTABLE_MODEL,
                                       CANVAS_MODEL       extends ProcedureCardsModel,
                                       TEST_CASE          extends ProcedureTestCase,
                                       TEST_CASE_DATABASE extends ProcedureTestCaseDatabase,
                                       DASHBOARD_MODEL    extends ProcedureDashboardModel,
                                       SETTINGS_MODEL     extends ProcedureSettingsModel>

                extends CommonBackend<EXECUTABLE_MODEL, 
                                      STUDENT_MODEL,
                                      CANVAS_MODEL,
                                      TEST_CASE, 
                                      TEST_CASE_DATABASE, 
                                      DASHBOARD_MODEL, 
                                      SETTINGS_MODEL> {


    @SuppressWarnings("unchecked")
	@Override
	public void initializeCanvasModel() {
		
		DASHBOARD_MODEL dashboardModel = NtroApp.models().load(getDashboardModelClass());
		CANVAS_MODEL canvasModel = NtroApp.models().load(getCanvasModelClass());
		
		dashboardModel.loadCurrentTestCase(getTestCaseDatabase());

		dashboardModel.updateCardsModel(getTestCaseDatabase(), canvasModel);

	}

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
		System.out.println("addSubTasks");
		
		tasks.task("addTestCase")
		
		     .waitsFor(message(MsgNewTestCaseLoaded.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 MsgNewTestCaseLoaded msgNewTestCaseLoaded = inputs.get(message(MsgNewTestCaseLoaded.class));
		    	 DASHBOARD_MODEL      dashboardModel       = inputs.get(model(getDashboardModelClass()));
		    	 
		    	 dashboardModel.addTestCaseId(msgNewTestCaseLoaded.getTestCaseId());
		    	 
		    	 System.out.println("addTestCase: " + msgNewTestCaseLoaded.getTestCaseId());
		    	 
		     });
		
	}

	protected void addSubTasksToModifySettingsModel(BackendTasks tasks) {
		
	}

	protected void addSubTasksToManageThread(BackendTasks tasks) {
		
	}

}
