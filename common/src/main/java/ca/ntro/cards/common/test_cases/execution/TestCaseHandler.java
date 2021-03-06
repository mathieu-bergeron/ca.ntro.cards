package ca.ntro.cards.common.test_cases.execution;

import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.CommonTestCase;

public interface TestCaseHandler<EXECUTABLE_MODEL extends CommonExecutableModel,
                                   STUDENT_MODEL extends EXECUTABLE_MODEL,
                                   TEST_CASE extends CommonTestCase>  {
	
	void handle(TEST_CASE testCase);

}
