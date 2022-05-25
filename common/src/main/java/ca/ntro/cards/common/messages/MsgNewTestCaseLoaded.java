package ca.ntro.cards.common.messages;

import ca.ntro.app.messages.MessageNtro;

public class MsgNewTestCaseLoaded extends MessageNtro {
	
	private String testCaseId;

	public String getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}
	
	
	
	

}
