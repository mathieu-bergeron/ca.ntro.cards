package ca.ntro.cards.demo.backend;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.demo.models.DemoEfficiencyDashboardModel;
import ca.ntro.cards.demo.models.DemoEfficiencySettingsModel;
import ca.ntro.cards.demo.models.DemoGraphsModel;
import ca.ntro.cards.demo.models.DemoTestCasesModel;
import ca.ntro.cards.demo.models.TriNaif;
import ca.ntro.cards.demo.models.values.DemoTestCase;
import ca.ntro.cards.efficiency.backend.EfficiencyBackend;

public class DemoEfficiencyBackend 

       extends EfficiencyBackend<TriNaif,         // executable model
                                 DemoGraphsModel, // canvas model
                                 DemoTestCase,
                                 DemoTestCasesModel,
                                 DemoEfficiencyDashboardModel,
                                 DemoEfficiencySettingsModel> {


	@Override
	protected void initializeCanvasModel(BackendTasks tasks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addSubTasksToModifyTestCasesModel(BackendTasks subTasks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addSubTasksToModifyCardsModel(BackendTasks subTasks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addSubTasksToModifyDashboardModel(BackendTasks subTasks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addSubTasksToModifySettingsModel(BackendTasks subTasks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addSubTasksToManageThread(BackendTasks subTasks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void createAdditionalTasks(BackendTasks tasks) {
		// TODO Auto-generated method stub
		
	}
}
