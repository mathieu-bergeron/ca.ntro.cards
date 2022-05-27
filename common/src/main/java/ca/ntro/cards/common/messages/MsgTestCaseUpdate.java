package ca.ntro.cards.common.messages;

import ca.ntro.app.messages.MessageNtro;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.test_cases.descriptor.TestCaseDescriptor;

public class MsgTestCaseUpdate extends MessageNtro {
	
	private TestCaseDescriptor testCaseDescriptor;

	public TestCaseDescriptor getTestCaseDescriptor() {
		return testCaseDescriptor;
	}

	public void setTestCaseDescriptor(TestCaseDescriptor testCaseDescriptor) {
		this.testCaseDescriptor = testCaseDescriptor;
	}

	public void applyTo(CommonDashboardModel dashboardModel) {
		dashboardModel.addOrUpdateTestCase(testCaseDescriptor);
	}
	
	

}
