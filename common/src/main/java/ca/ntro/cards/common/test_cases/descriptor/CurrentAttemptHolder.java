package ca.ntro.cards.common.test_cases.descriptor;

import ca.ntro.cards.common.models.enums.Attempt;

public interface CurrentAttemptHolder {
	
	boolean isCurrentAttempt(String testCaseId, Attempt attempt);

}
