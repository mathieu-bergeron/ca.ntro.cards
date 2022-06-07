package ca.ntro.cards.common.test_cases.descriptor;

public class CommonAttemptDescriptor implements AbstractAttemptDescriptor {
	
	private int numberOfSteps;
	private int currentStep;
	private boolean isASolution;
	private boolean isLoaded;
	
	private CommonTestCaseDescriptor parentTestCase;

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
		return parentTestCase.isCurrentAttempt(this);
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
