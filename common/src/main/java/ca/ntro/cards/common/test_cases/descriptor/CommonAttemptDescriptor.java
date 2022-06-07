package ca.ntro.cards.common.test_cases.descriptor;

import ca.ntro.cards.common.models.enums.Attempt;

public class CommonAttemptDescriptor implements AbstractAttemptDescriptor {

	private static final long serialVersionUID = -1330131719442951296L;

	private Attempt attempt;
	private int numberOfSteps;
	private int currentStep;
	private boolean isASolution;
	private boolean isLoaded;
	
	private CommonTestCaseDescriptor parentTestCase;

	public Attempt getAttempt() {
		return attempt;
	}

	public void setAttempt(Attempt attempt) {
		this.attempt = attempt;
	}

	public CommonTestCaseDescriptor getParentTestCase() {
		return parentTestCase;
	}

	public void setParentTestCase(CommonTestCaseDescriptor parentTestCase) {
		this.parentTestCase = parentTestCase;
	}

	public int getNumberOfSteps() {
		return numberOfSteps;
	}

	public void setNumberOfSteps(int numberOfSteps) {
		this.numberOfSteps = numberOfSteps;
	}

	public int getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(int currentStep) {
		this.currentStep = currentStep;
	}


	public void setIsASolution(boolean isASolution) {
		this.isASolution = isASolution;
	}

	public void setIsLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;
	}

	public boolean getIsASolution() {
		return isASolution;
	}

	public boolean getIsLoaded() {
		return isLoaded;
	}

	@Override
	public int numberOfSteps() {
		return getNumberOfSteps();
	}

	@Override
	public int currentStep() {
		return getCurrentStep();
	}

	@Override
	public boolean isCurrentAttempt() {
		return parentTestCase.isCurrentAttempt(attempt);
	}

	@Override
	public boolean isASolution() {
		return getIsASolution();
	}

	@Override
	public boolean isLoaded() {
		return getIsLoaded();
	}


}
