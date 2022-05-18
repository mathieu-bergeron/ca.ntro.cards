package ca.ntro.cards.demo.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.demo.messages.MsgUnlockThread;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

public class DemoManageThread {
	
	public static void unlockThread(BackendTasks tasks) {
		tasks.task("unlockThread")

		     .waitsFor(message(MsgUnlockThread.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 System.out.println("unlock thread");
		    	 
		     });
	}

}
