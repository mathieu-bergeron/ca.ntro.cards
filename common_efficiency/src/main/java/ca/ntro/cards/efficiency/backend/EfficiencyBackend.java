package ca.ntro.cards.efficiency.backend;

import ca.ntro.app.NtroApp;
import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.common.backend.CommonBackend;
import ca.ntro.cards.common.test_cases.CommonTestCase;
import ca.ntro.cards.common.test_cases.CommonTestCaseDatabase;
import ca.ntro.cards.efficiency.models.EfficiencyDashboardModel;
import ca.ntro.cards.efficiency.models.EfficiencyGraphsModel;
import ca.ntro.cards.efficiency.models.EfficiencySettingsModel;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;

public abstract class EfficiencyBackend <EXECUTABLE_MODEL extends ProcedureCardsModel,
                                         STUDENT_MODEL    extends EXECUTABLE_MODEL,
                                         CANVAS_MODEL     extends EfficiencyGraphsModel,
                                         TEST_CASE        extends CommonTestCase,
                                         TEST_CASES_MODEL extends CommonTestCaseDatabase,
                                         EXECUTION_TRACE  extends ProcedureExecutionTrace,
                                         DASHBOARD_MODEL  extends EfficiencyDashboardModel,
                                         SETTINGS_MODEL   extends EfficiencySettingsModel>

       extends        CommonBackend<EXECUTABLE_MODEL,
                                    STUDENT_MODEL,
                                    CANVAS_MODEL, 
                                    TEST_CASE, 
                                    TEST_CASES_MODEL, 
                                    EXECUTION_TRACE,
                                    DASHBOARD_MODEL, 
                                    SETTINGS_MODEL> {

	@Override
	public void earlyModelInitialization() {
		
		CANVAS_MODEL canvasModel = NtroApp.models().load(getCanvasModelClass());
		
		canvasModel.clear();
	}

	@Override
	protected void addSubTasksToModifyCanvasModel(BackendTasks subTasks) {
	}

	@Override
	protected void addSubTasksToModifyDashboardModel(BackendTasks subTasks) {
	}

	@Override
	protected void addSubTasksToModifySettingsModel(BackendTasks subTasks) {
	}


	@Override
	protected void createAdditionalTasks(BackendTasks tasks) {
	}

}
