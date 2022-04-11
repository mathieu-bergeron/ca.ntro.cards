package ca.ntro.app.messages;

import ca.ntro.app.services.MessageServiceNtro;

public class MessageNtro implements Message {
	
	private MessageServiceNtro messageService;

	public void registerMessageService(MessageServiceNtro messageService) {
		this.messageService = messageService;
	}

	public void send() {
		messageService.deliverMessage(this);
	}

	public void broadcast() {
		messageService.broadcastMessageToOtherClients(this);
	}
}
