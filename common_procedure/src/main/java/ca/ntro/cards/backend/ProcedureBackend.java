package ca.ntro.cards.backend;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.common.backend.CommonBackend;
import ca.ntro.cards.common.messages.MsgRefreshDashboard;
import ca.ntro.cards.common.messages.MsgTestCaseUpdate;
import ca.ntro.cards.messages.MsgExecutionStepBack;
import ca.ntro.cards.messages.MsgExecutionStepForward;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.cards.models.ProcedureDashboardModel;
import ca.ntro.cards.models.ProcedureSettingsModel;
import ca.ntro.cards.test_cases.ProcedureTestCase;
import ca.ntro.cards.test_cases.ProcedureTestCaseDatabase;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;
import ca.ntro.core.initialization.Ntro;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

import ca.ntro.app.NtroApp;
import ca.ntro.app.models.Watch;

public abstract class ProcedureBackend<EXECUTABLE_MODEL   extends ProcedureCardsModel,
                                       STUDENT_MODEL      extends EXECUTABLE_MODEL,
                                       CANVAS_MODEL       extends ProcedureCardsModel,
                                       TEST_CASE          extends ProcedureTestCase,
                                       TEST_CASE_DATABASE extends ProcedureTestCaseDatabase,
                                       EXECUTION_TRACE    extends ProcedureExecutionTrace,
                                       DASHBOARD_MODEL    extends ProcedureDashboardModel,
                                       SETTINGS_MODEL     extends ProcedureSettingsModel>

                extends CommonBackend<EXECUTABLE_MODEL, 
                                      STUDENT_MODEL,
                                      CANVAS_MODEL,
                                      TEST_CASE, 
                                      TEST_CASE_DATABASE, 
                                      EXECUTION_TRACE,
                                      DASHBOARD_MODEL, 
                                      SETTINGS_MODEL> {


    @SuppressWarnings("unchecked")
	@Override
	public void initializeCanvasModel() {
		
		DASHBOARD_MODEL dashboardModel = NtroApp.models().load(getDashboardModelClass());


		dashboardModel.loadCurrentTestCase(getTestCaseDatabase());

		getTestCaseDatabase().updateDashboardModel(dashboardModel);

		CANVAS_MODEL canvasModel = NtroApp.models().load(getCanvasModelClass());

		dashboardModel.updateCardsModel(getTestCaseDatabase(), canvasModel);
		
		NtroApp.models().save(dashboardModel);
		NtroApp.models().save(canvasModel);

	}

	protected void addSubTasksToModifyTestCasesModel(BackendTasks tasks) {
		
		
	}

	protected void addSubTasksToModifyCanvasModel(BackendTasks tasks) {


	}



	protected void addSubTasksToModifyDashboardModel(BackendTasks tasks) {
		tasks.task("updateTestCase")
		
		     .waitsFor(message(MsgTestCaseUpdate.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 MsgTestCaseUpdate msgNewTestCaseLoaded = inputs.get(message(MsgTestCaseUpdate.class));
		    	 DASHBOARD_MODEL   dashboardModel       = inputs.get(model(getDashboardModelClass()));
		    	 
		    	 msgNewTestCaseLoaded.applyTo(dashboardModel);
		     });

		tasks.task("refreshDashboard")
		
		     .waitsFor(message(MsgRefreshDashboard.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 DASHBOARD_MODEL   dashboardModel       = inputs.get(model(getDashboardModelClass()));

		    	 getTestCaseDatabase().copyNewTestCasesTo(dashboardModel);

		     });
	}

	@Override
	protected void addSubTasksToModifySettingsModel(BackendTasks subTasks) {
	}

	@Override
	protected void createAdditionalTasks(BackendTasks tasks) {
		tasks.taskGroup("AccessTestCaseDatabase")

		     .waitsFor(model(getCanvasModelClass()))

		     .waitsFor(model(getDashboardModelClass()))
		     
		     .andContains(subTasks -> {

				executionStepBack(subTasks);

				executionStepForward(subTasks);
		    	 
		    	 
		     });
	}

	protected abstract void addSubTasksToAccessTestCaseDatabase(BackendTasks subTasks);

	private void executionStepBack(BackendTasks tasks) {
		tasks.task("executionStepBack")
		
		     .waitsFor(message(MsgExecutionStepBack.class))

		     .thenExecutes(inputs -> {
		    	 
		    	 DASHBOARD_MODEL dashboardModel = inputs.get(model(getDashboardModelClass()));
		    	 CANVAS_MODEL cardsModel = inputs.get(model(getCanvasModelClass()));

		    	 testCaseDatabase().stepBackward();
		    	 testCaseDatabase().updateCardsModel(cardsModel);
		    	 testCaseDatabase().updateDashboardModel(dashboardModel);

		     });
	}

	private void executionStepForward(BackendTasks tasks) {
		tasks.task("executionStepForward")
		
		     .waitsFor(message(MsgExecutionStepForward.class))

		     .thenExecutes(inputs -> {
		    	 
		    	 DASHBOARD_MODEL dashboardModel = inputs.get(model(getDashboardModelClass()));
		    	 CANVAS_MODEL cardsModel = inputs.get(model(getCanvasModelClass()));
		    	 
		    	 testCaseDatabase().stepForward();
		    	 testCaseDatabase().updateCardsModel(cardsModel);
		    	 testCaseDatabase().updateDashboardModel(dashboardModel);
		     });
	}

}
