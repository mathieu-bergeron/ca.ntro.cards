package ca.ntro.cards.messages;

import ca.ntro.app.messages.MessageNtro;
import ca.ntro.cards.models.ProcedureDashboardModel;

public class MsgChangeCurrentTestCase extends MessageNtro {
	
	private String testCaseId;

	public String getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}

	public void applyTo(ProcedureDashboardModel dashboardModel) {
		dashboardModel.changeCurrentTestCase(testCaseId);
	}
	
	

}
