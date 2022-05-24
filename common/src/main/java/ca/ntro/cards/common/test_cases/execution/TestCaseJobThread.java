package ca.ntro.cards.common.test_cases.execution;

import ca.ntro.cards.common.CommonConstants;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.TestCase;
import ca.ntro.cards.common.test_cases.execution.jobs.TestCaseJob;
import ca.ntro.core.initialization.Ntro;

public class TestCaseJobThread<EXECUTABLE_MODEL extends CommonExecutableModel,
                            STUDENT_MODEL extends EXECUTABLE_MODEL,
                            TEST_CASE extends TestCase>  

       extends Thread {
	
	private boolean shouldRun = true;
	
	private TestCaseJob<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> task;

	public TestCaseJob<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> getTask() {
		return task;
	}

	public void setTask(TestCaseJob<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> task) {
		this.task = task;
	}


	@Override
	public void run() {
		while(shouldRun) {
			if(task != null) {
				
				task.run();
				
			}else {
				
				try {

					sleep(CommonConstants.EXECUTION_THREAD_SLEEP_TIME_MILISECONDS);

				} catch (InterruptedException e) {
					Ntro.throwException(e);
				}
			}
		}
	}

	public void shutdown() {
		this.shouldRun = false;
	}
}
