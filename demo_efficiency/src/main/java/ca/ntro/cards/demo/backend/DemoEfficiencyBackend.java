package ca.ntro.cards.demo.backend;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.demo.models.DemoEfficiencyDashboardModel;
import ca.ntro.cards.demo.models.DemoEfficiencySettingsModel;
import ca.ntro.cards.demo.models.DemoGraphsModel;
import ca.ntro.cards.demo.models.TriNaif;
import ca.ntro.cards.demo.models.values.DemoTestCase;
import ca.ntro.cards.demo.test_cases.DemoTestCaseDatabase;
import ca.ntro.cards.efficiency.backend.EfficiencyBackend;

public class DemoEfficiencyBackend<STUDENT_MODEL extends TriNaif>

       extends EfficiencyBackend<TriNaif, 
                                 STUDENT_MODEL,
                                 DemoGraphsModel, // canvas model
                                 DemoTestCase,
                                 DemoTestCaseDatabase,
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
