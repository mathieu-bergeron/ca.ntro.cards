package ca.ntro.cards.common.test_cases.execution;

import ca.ntro.core.initialization.Ntro;

public class TestCaseThread extends Thread {
	
	private TestCaseTask task;

	public TestCaseTask getTask() {
		return task;
	}


	public void setTask(TestCaseTask task) {
		this.task = task;
	}


	@Override
	public void run() {
		while(true) {
			if(task != null) {
				
				task.run();
				
			}else {
				
				try {

					sleep(200);

				} catch (InterruptedException e) {
					Ntro.throwException(e);
				}
			}
		}
	}
}
