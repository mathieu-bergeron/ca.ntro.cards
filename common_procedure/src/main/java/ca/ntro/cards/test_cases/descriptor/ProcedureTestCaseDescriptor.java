package ca.ntro.cards.test_cases.descriptor;

import ca.ntro.cards.common.models.enums.Attempt;
import ca.ntro.cards.common.test_cases.descriptor.CommonTestCaseDescriptor;
import ca.ntro.cards.frontend.views.fragments.ProcedureTestCaseFragment;

public class ProcedureTestCaseDescriptor<TEST_CASE_FRAGMENT extends ProcedureTestCaseFragment> extends CommonTestCaseDescriptor {

	public void displayOn(TEST_CASE_FRAGMENT testCaseFragment) {

		testCaseFragment.memorizeTestCaseId(getTestCaseId());
		testCaseFragment.displayTestCaseId(getTestCaseId());
		testCaseFragment.displayInputSize(String.valueOf(getInputSize()));
		
		testCaseFragment.displayManual(attempt(Attempt.MANUAL));
		testCaseFragment.displayCode(attempt(Attempt.CODE));
		testCaseFragment.displaySolution(attempt(Attempt.SOLUTION));

	}


}
