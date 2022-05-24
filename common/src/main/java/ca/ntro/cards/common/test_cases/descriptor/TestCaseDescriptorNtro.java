package ca.ntro.cards.common.test_cases.descriptor;

public class TestCaseDescriptorNtro implements TestCaseDescriptor {
	
	private static long nextId = 0;
	
	private String category = "_noCategory";
	private String testCaseId = "_noId";
	private int size = 0;

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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
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
	public int size() {
		return getSize();
	}

	public TestCaseDescriptorNtro testCaseId(String testCaseId) {
		setTestCaseId(testCaseId);
		return this;
	}

	public TestCaseDescriptorNtro random(int size) {
		setCategory("random");
		setSize(size);
		setTestCaseId(String.valueOf(++nextId));

		return this;
	}

	public TestCaseDescriptorNtro category(String category) {
		setCategory(category);

		return this;
	}

}
