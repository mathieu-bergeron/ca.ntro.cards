package ca.ntro.cards.demo.backend;


import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.backend.CommonBackend;
import ca.ntro.cards.demo.backend.tasks.DemoModifyCardsModel;
import ca.ntro.cards.demo.messages.DemoMsgRegisterSimpleOperation;
import ca.ntro.cards.demo.models.NaiveSort;
import ca.ntro.cards.demo.models.DemoDashboardModel;
import ca.ntro.cards.demo.models.DemoSettingsModel;

public class   DemoBackend<STUDENT_MODEL extends NaiveSort> 


       extends CommonBackend<STUDENT_MODEL,
                             DemoDashboardModel,
                             DemoSettingsModel,
                             DemoMsgRegisterSimpleOperation<STUDENT_MODEL>> {
	
	

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

}
