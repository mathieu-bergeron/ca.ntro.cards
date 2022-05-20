package ca.ntro.cards.common.backend.tasks;

import ca.ntro.app.tasks.SubTasksLambda;
import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.common.messages.MsgLockThread;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

import java.util.concurrent.locks.ReentrantLock;

public class ManageThread {

	public static void createTasks(BackendTasks tasks, 
			                       ReentrantLock lock, 
			                       SubTasksLambda<BackendTasks> subTasksLambda) {

		tasks.taskGroup("ManageThread")
		
		     .contains(subTasks -> {
		    	 
		    	 lockThread(subTasks, lock);
		    	 
		    	 subTasksLambda.createSubTasks(subTasks);

		     });
		
	}
	
	private static void lockThread(BackendTasks tasks, ReentrantLock lock) {

		tasks.task("lockThread")

		     .waitsFor(message(MsgLockThread.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 lock.lock();

		     });
	}



}
