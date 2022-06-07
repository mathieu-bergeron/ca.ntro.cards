package ca.ntro.cards.common.test_cases.descriptor;

import java.io.Serializable;

import ca.ntro.app.models.Value;

public interface AbstractAttemptDescriptor extends Value, Serializable {

	int numberOfSteps();

	int currentStep();
	
	boolean isCurrentAttempt();

	boolean isASolution();
	
	boolean isLoaded();

}
