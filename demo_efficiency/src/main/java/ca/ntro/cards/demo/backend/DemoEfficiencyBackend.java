package ca.ntro.cards.demo.backend;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.demo.models.DemoEfficiencyDashboardModel;
import ca.ntro.cards.demo.models.DemoEfficiencySettingsModel;
import ca.ntro.cards.demo.models.DemoGraphsModel;
import ca.ntro.cards.demo.models.DemoCardsModel;
import ca.ntro.cards.demo.models.values.DemoTestCase;
import ca.ntro.cards.demo.test_cases.DemoTestCaseDatabase;
import ca.ntro.cards.efficiency.backend.EfficiencyBackend;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;

public class DemoEfficiencyBackend<STUDENT_MODEL extends DemoCardsModel>

       extends EfficiencyBackend<DemoCardsModel, 
                                 STUDENT_MODEL,
                                 DemoGraphsModel, // canvas model
                                 DemoTestCase,
                                 DemoTestCaseDatabase,
                                 ProcedureExecutionTrace,
                                 DemoEfficiencyDashboardModel,
                                 DemoEfficiencySettingsModel> {



	@Override
	protected void addSubTasksToModifyTestCasesModel(BackendTasks subTasks) {
		
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
