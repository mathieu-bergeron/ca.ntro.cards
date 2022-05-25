package ca.ntro.cards.common.messages;

import ca.ntro.app.messages.MessageNtro;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.test_cases.TestCase;

public class MsgNewTestCaseLoaded extends MessageNtro {
	
	private TestCase testCase;

	public TestCase getTestCase() {
		return testCase;
	}

	public void setTestCase(TestCase testCase) {
		this.testCase = testCase;
	}
	
	public void applyTo(CommonDashboardModel dashboardModel) {

		dashboardModel.addTestCase(testCase);

	}
	
	
	

}
