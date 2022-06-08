package ca.ntro.cards.messages;

import ca.ntro.app.messages.MessageNtro;
import ca.ntro.cards.common.models.enums.Attempt;
import ca.ntro.cards.models.ProcedureDashboardModel;

public class MsgChangeTestCaseAttempt extends MessageNtro {
	
	private String testCaseId;
	private Attempt attempt;

	public String getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}

	public Attempt getAttempt() {
		return attempt;
	}

	public void setAttempt(Attempt attempt) {
		this.attempt = attempt;
	}

	public void applyTo(ProcedureDashboardModel dashboardModel) {
		dashboardModel.changeTestCaseAttempt(testCaseId, attempt);
	}
	

}
