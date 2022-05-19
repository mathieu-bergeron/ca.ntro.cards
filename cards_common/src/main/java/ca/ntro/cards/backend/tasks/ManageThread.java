package ca.ntro.cards.backend.tasks;

import ca.ntro.app.tasks.SubTasksLambda;
import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.messages.MsgExecuteCodeOneStep;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

import java.util.concurrent.locks.ReentrantLock;

public class ManageThread {

	public static void createTasks(BackendTasks tasks, 
			                       ReentrantLock lock, 
			                       SubTasksLambda<BackendTasks> subTasksLambda) {

		tasks.taskGroup("ManageThread")
		
		     .contains(subTasks -> {
		    	 
		    	 unlockThread(subTasks, lock);
		    	 
		    	 subTasksLambda.createSubTasks(subTasks);

		     });
		
	}
	
	private static void unlockThread(BackendTasks tasks, ReentrantLock lock) {

		tasks.task("unlockThread")

		     .waitsFor(message(MsgExecuteCodeOneStep.class))
		     
		     .thenExecutes(inputs -> {

		    	 if(lock.isHeldByCurrentThread()) {
					 lock.unlock();
		    	 }

		     });
	}



}
