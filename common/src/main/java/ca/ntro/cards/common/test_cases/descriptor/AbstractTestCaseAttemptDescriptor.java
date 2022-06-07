package ca.ntro.cards.common.test_cases.descriptor;

import java.io.Serializable;

import ca.ntro.app.models.Value;

public interface AbstractTestCaseAttemptDescriptor extends Value, Serializable {

	int numberOfSteps();

	int currentStep();
	
	boolean isCurrent();
	
	boolean isASolution();
	
	boolean isLoaded();

}
