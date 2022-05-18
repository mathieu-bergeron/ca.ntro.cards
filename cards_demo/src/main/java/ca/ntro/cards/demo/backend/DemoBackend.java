package ca.ntro.cards.demo.backend;

import java.util.concurrent.locks.ReentrantLock;

import ca.ntro.app.NtroApp;
import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.backend.CommonBackend;
import ca.ntro.cards.demo.backend.tasks.DemoManageThread;
import ca.ntro.cards.demo.backend.tasks.DemoModifyCardsModel;
import ca.ntro.cards.demo.messages.DemoMsgRegisterSimpleOperation;
import ca.ntro.cards.demo.models.DemoCardsModel;
import ca.ntro.cards.demo.models.DemoDashboardModel;
import ca.ntro.cards.demo.models.DemoSettingsModel;
import ca.ntro.cards.messages.MsgRegisterSimpleOperation;
import ca.ntro.core.initialization.Ntro;

public class   DemoBackend<STUDENT_MODEL extends DemoCardsModel> 


       extends CommonBackend<STUDENT_MODEL,
                             DemoDashboardModel,
                             DemoSettingsModel,
                             DemoMsgRegisterSimpleOperation<STUDENT_MODEL>> {
	
	
	private ReentrantLock lock = new ReentrantLock();
	private StudentThread<STUDENT_MODEL> studentThread = new StudentThread<>();

	@Override
	protected void addSubTasksToModifyCardsModel(BackendTasks subTasks) {

		 DemoModifyCardsModel.updateList(subTasks, getCardsModelClass());

		 
		 STUDENT_MODEL studentModel = Ntro.factory().newInstance(getCardsModelClass());
		 studentModel.createFirstVersion();
		 studentModel.registerLock(lock);
		 
		 MsgRegisterSimpleOperation msgRegisterSimpleOperation = NtroApp.newMessage(getMsgRegisterSimpleOperationClass());
		 msgRegisterSimpleOperation.setCardsModel(studentModel);

		 studentModel.registerMsgRegisterSimpleOperation(msgRegisterSimpleOperation);

		 studentThread.setModel(studentModel);

		 DemoManageThread.unlockThread(subTasks, lock);

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
	public void execute() {
		studentThread.start();
	}

}
