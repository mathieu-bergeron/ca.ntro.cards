package ca.ntro.cards.common.test_cases.descriptor;

import ca.ntro.cards.common.models.enums.Mode;

public class CommonTestCaseDescriptor implements AbstractTestCaseDescriptor {

	private static final long serialVersionUID = -6757195432385501062L;

	private static long nextId = 0;
	
	private String category = "_noCategory";
	private String testCaseId = "_noId";
	private int inputSize = 0;
	private int numberOfSteps = 0;
	private boolean loaded = false;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}

	public int getInputSize() {
		return inputSize;
	}

	public void setInputSize(int size) {
		this.inputSize = size;
	}

	public int getNumberOfSteps() {
		return numberOfSteps;
	}

	public void setNumberOfSteps(int numberOfSteps) {
		this.numberOfSteps = numberOfSteps;
	}

	public boolean getLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	@Override
	public String category() {
		return getCategory();
	}

	@Override
	public String testCaseId() {
		return getTestCaseId();
	}

	@Override
	public int inputSize() {
		return getInputSize();
	}

	public CommonTestCaseDescriptor testCaseId(String testCaseId) {
		setTestCaseId(testCaseId);
		return this;
	}

	public CommonTestCaseDescriptor random(int size) {
		setCategory("random");
		setInputSize(size);
		setTestCaseId(String.valueOf(++nextId));

		return this;
	}

	public CommonTestCaseDescriptor category(String category) {
		setCategory(category);

		return this;
	}

	@Override
	public int numberOfSteps(Mode mode) {
		return getNumberOfSteps();
	}

	@Override
	public int currentStep(Mode mode) {
		return 0;
	}

	@Override
	public boolean passed(Mode mode) {
		return false;
	}

	@Override
	public boolean loaded(Mode mode) {
		return getLoaded();
	}
}
