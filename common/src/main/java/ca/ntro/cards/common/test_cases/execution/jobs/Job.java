package ca.ntro.cards.common.test_cases.execution.jobs;

import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.TestCase;
import ca.ntro.cards.common.test_cases.execution.TestCaseJobEngine;
import ca.ntro.cards.common.test_cases.execution.handlers.DoneHandler;
import ca.ntro.cards.common.test_cases.execution.signals.Signal;

public abstract class Job<EXECUTABLE_MODEL extends CommonExecutableModel,
                                  STUDENT_MODEL extends EXECUTABLE_MODEL,
                                  TEST_CASE extends TestCase>  

       extends Signal {
	
	private TEST_CASE testCase;
	private TestCaseJobEngine<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> executionEngine;
	private DoneHandler doneHandler;

	public TEST_CASE getTestCase() {
		return testCase;
	}

	public void setTestCase(TEST_CASE testCase) {
		this.testCase = testCase;
	}

	public TestCaseJobEngine<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> getExecutionEngine() {
		return executionEngine;
	}

	public void setExecutionEngine(TestCaseJobEngine<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> executionEngine) {
		this.executionEngine = executionEngine;
	}

	public DoneHandler getDoneHandler() {
		return doneHandler;
	}

	public void setDoneHandler(DoneHandler doneHandler) {
		this.doneHandler = doneHandler;
	}

	protected String id() {
		return getTestCase().getTestCaseId();
	}

	public abstract void run();

	public void addExecutionStep() {
		testCase.addExecutionStep();
	}

	public void failsWith(Throwable t) {
	}

}
