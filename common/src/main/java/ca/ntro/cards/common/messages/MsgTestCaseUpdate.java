package ca.ntro.cards.common.messages;

import ca.ntro.app.messages.MessageNtro;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.test_cases.descriptor.AbstractTestCaseDescriptor;

public class MsgTestCaseUpdate extends MessageNtro {
	
	private AbstractTestCaseDescriptor testCaseDescriptor;

	public AbstractTestCaseDescriptor getTestCaseDescriptor() {
		return testCaseDescriptor;
	}

	public void setTestCaseDescriptor(AbstractTestCaseDescriptor testCaseDescriptor) {
		this.testCaseDescriptor = testCaseDescriptor;
	}

	public void applyTo(CommonDashboardModel dashboardModel) {
		dashboardModel.addOrUpdateTestCase(testCaseDescriptor);
	}
	
	

}
