package ca.ntro.cards.common.test_cases.execution.jobs;

import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.TestCase;

public class TestCaseJob<EXECUTABLE_MODEL extends CommonExecutableModel,
                          STUDENT_MODEL extends EXECUTABLE_MODEL,
                          TEST_CASE extends TestCase>  {
	
	private TEST_CASE testCase;

	public TEST_CASE getTestCase() {
		return testCase;
	}

	public void setTestCase(TEST_CASE testCase) {
		this.testCase = testCase;
}

	public void run() {

	}


}
