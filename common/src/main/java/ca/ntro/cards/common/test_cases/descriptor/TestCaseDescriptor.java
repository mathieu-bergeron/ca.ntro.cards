package ca.ntro.cards.common.test_cases.descriptor;

import java.io.Serializable;

import ca.ntro.app.models.Value;
import ca.ntro.cards.common.models.enums.Mode;

public interface TestCaseDescriptor extends Value, Serializable {
	
	String category();

	String testCaseId();

	int inputSize();

	int numberOfSteps(Mode mode);

	int currentStep(Mode mode);
	
	boolean passed(Mode mode);

	public static TestCaseDescriptorNtro create() {
		TestCaseDescriptorNtro descriptor = new TestCaseDescriptorNtro();
		return descriptor;
	}

}
