package ca.ntro.cards.common.models;

public interface TestCaseDescriptor {
	
	String category();
	String testCaseId();
	int size();

	public static TestCaseDescriptorNtro create() {
		TestCaseDescriptorNtro descriptor = new TestCaseDescriptorNtro();
		return descriptor;
	}
	

}
