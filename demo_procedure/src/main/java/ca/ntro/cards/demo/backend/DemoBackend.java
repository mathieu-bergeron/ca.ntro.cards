package ca.ntro.cards.demo.backend;


import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.backend.ProcedureBackend;
import ca.ntro.cards.common.backend.CommonBackend;
import ca.ntro.cards.demo.backend.tasks.DemoModifyCardsModel;
import ca.ntro.cards.demo.models.TriNaif;
import ca.ntro.cards.demo.models.values.DemoTestCase;
import ca.ntro.cards.demo.models.DemoProcedureDashboardModel;
import ca.ntro.cards.demo.models.DemoProcedureSettingsModel;
import ca.ntro.cards.demo.models.DemoTestCasesModel;

public class   DemoBackend


       extends ProcedureBackend<TriNaif,
                                DemoTestCase,
                                DemoTestCasesModel,
                                DemoProcedureDashboardModel,
                                DemoProcedureSettingsModel> {
	
	

	@Override
	protected void addSubTasksToModifyCardsModel(BackendTasks subTasks) {

		 DemoModifyCardsModel.updateList(subTasks, getCardsModelClass());

	}

	@Override
	protected void addSubTasksToModifySettingsModel(BackendTasks subTasks) {

	}

	@Override
	protected void addSubTasksToModifyDashboardModel(BackendTasks subTasks) {

	}

	@Override
	protected void createAdditionalTasks(BackendTasks tasks) {
		
	}

	@Override
	protected void addSubTasksToManageThread(BackendTasks subTasks) {
		
	}

	@Override
	protected void addSubTasksToModifyTestCasesModel(BackendTasks subTasks) {

	}


}