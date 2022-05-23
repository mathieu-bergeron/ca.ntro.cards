package ca.ntro.cards.common;

import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.models.TestCasesModel;
import ca.ntro.cards.common.models.values.test_cases.TestCase;

public abstract class GenerateTestCases<EXECUTABLE_MODEL extends CommonExecutableModel,
                                        STUDENT_MODEL extends EXECUTABLE_MODEL,
                                        TEST_CASE extends TestCase<EXECUTABLE_MODEL>,
                                        TEST_CASES_MODEL extends TestCasesModel<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE>> {

	public void run() {
		System.out.println("\n\n[GENERATING TEST CASES]\n\n\n");
	}

}
