package ca.ntro.app.messages;

public class BroadcastMessageNtro implements BroadcastMessage {
	
	private Message message;


	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public BroadcastMessageNtro() {

	}

	public BroadcastMessageNtro(Message message) {
		setMessage(message);
	}

	@Override
	public Message message() {
		return getMessage();
	}
}
