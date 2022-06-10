package ca.ntro.cards.common.test_cases.execution;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.CommonTestCase;
import ca.ntro.cards.common.test_cases.execution.jobs.Job;
import ca.ntro.cards.common.test_cases.execution.signals.ExitSignal;
import ca.ntro.cards.common.test_cases.execution.signals.Signal;
import ca.ntro.core.initialization.Ntro;

public class TestCaseJobThread<EXECUTABLE_MODEL extends CommonExecutableModel,
                            STUDENT_MODEL extends EXECUTABLE_MODEL,
                            TEST_CASE extends CommonTestCase>  

       extends Thread {
	
	
	private BlockingQueue<Signal> signals = new LinkedBlockingQueue<>();
	private Job currentJob = null;

	private TestCaseJobEngine<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> executionEngine;
	
	public void registerExecutionEngine(TestCaseJobEngine<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> executionEngine) {
		this.executionEngine = executionEngine;
	}

	@Override
	public void run() {
		while(true) {
			
			executionEngine.notifyThreadIsIdle(getId());
			
			Signal signal = waitForNextSignal();

			if(signal instanceof ExitSignal) {
				
				executionEngine.notifyThreadIsTerminated(getId());
				return;
				
			}else if(signal instanceof Job) {

				executionEngine.notifyThreadIsRunning(getId());
				currentJob = (Job) signal;
				
				try {

					currentJob.run();

				}catch(Throwable t) {
					
					currentJob.failsWith(t);
					t.printStackTrace();

				}finally {
					
					currentJob = null;

				}

			}else {
				
				Ntro.throwException(String.format("[FATAL] unknown signal %s in thread %s", signal, getId()));
				
			}
		}
	}
	

	private Signal waitForNextSignal() {
		Signal signal = null;

		try {

			signal = signals.take();

		} catch (InterruptedException e) {
			
			Ntro.throwException(e);

		}

		return signal;
	}

	public void addExecutionStep() {
		if(currentJob != null) {
			currentJob.addExecutionStep();
		}
	}

	public synchronized void pushSignal(Signal signal) {
		signals.add(signal);
	}
}
