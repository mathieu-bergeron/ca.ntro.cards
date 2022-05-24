package ca.ntro.cards.common.test_cases.execution;

import java.util.concurrent.locks.ReentrantLock;

import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.TestCase;
import ca.ntro.cards.common.test_cases.execution.jobs.TestCaseJob;

public class TestCaseJobThread<EXECUTABLE_MODEL extends CommonExecutableModel,
                            STUDENT_MODEL extends EXECUTABLE_MODEL,
                            TEST_CASE extends TestCase>  

       extends Thread {
	
	private TestCaseJob<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> job;
	private TestCaseJobEngine<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> executionEngine;

	private ReentrantLock nextJob = new ReentrantLock();
	
	public TestCaseJob<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> getJob() {
		return job;
	}

	public void setJob(TestCaseJob<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> job) {
		this.job = job;
	}

	public TestCaseJobEngine<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> getExecutionEngine() {
		return executionEngine;
	}

	public void setExecutionEngine(TestCaseJobEngine<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> executionEngine) {
		this.executionEngine = executionEngine;
	}
	

	@Override
	public void run() {
		while(!isInterrupted()) {
			
			try {
				
				//job.run();
				
				
			}catch(Throwable t){
				
				//job.failedWith(t);

			}
			
			
			
		}

	}

	public void addExecutionStep() {
		job.addExecutionStep();
	}
}
