package ca.ntro.cards.demo.backend;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.backend.CommonBackend;
import ca.ntro.cards.demo.backend.tasks.DemoManageThread;
import ca.ntro.cards.demo.backend.tasks.DemoModifyCardsModel;
import ca.ntro.cards.demo.messages.DemoMsgRegisterSimpleOperation;
import ca.ntro.cards.demo.models.DemoCardsModel;
import ca.ntro.cards.demo.models.DemoDashboardModel;
import ca.ntro.cards.demo.models.DemoSettingsModel;
import ca.ntro.core.initialization.Ntro;

public class   DemoBackend<STUDENT_MODEL extends DemoCardsModel> 


       extends CommonBackend<STUDENT_MODEL,
                             DemoDashboardModel,
                             DemoSettingsModel,
                             DemoMsgRegisterSimpleOperation<STUDENT_MODEL>> {
	
	
	private StudentThread<STUDENT_MODEL> studentThread = new StudentThread();

	@Override
	protected void addSubTasksToModifyCardsModel(BackendTasks subTasks) {

		 DemoModifyCardsModel.updateList(subTasks, getCardsModelClass());
		 
		 STUDENT_MODEL studentModel = Ntro.factory().newInstance(getCardsModelClass());
		 studentModel.createFirstVersion();
		 studentThread.setModel(studentModel);
		 
		 DemoManageThread.unlockThread(subTasks);
		 
		 studentThread.start();

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
