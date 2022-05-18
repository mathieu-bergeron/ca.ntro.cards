package ca.ntro.cards.demo.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.messages.MsgExecuteCodeOneStep;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

import java.util.concurrent.locks.ReentrantLock;

public class DemoManageThread {

	
	public static void unlockThread(BackendTasks tasks, ReentrantLock lock) {
		tasks.task("unlockThread")

		     .waitsFor(message(MsgExecuteCodeOneStep.class))
		     
		     .thenExecutes(inputs -> {

		    	 if(lock.isHeldByCurrentThread()) {
					 lock.unlock();
		    	 }

		     });
	}


}
