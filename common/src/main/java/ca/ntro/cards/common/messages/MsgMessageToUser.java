package ca.ntro.cards.common.messages;

import ca.ntro.app.messages.MessageNtro;

public class MsgMessageToUser extends MessageNtro {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
