package ca.ntro.app.messages;

import ca.ntro.core.reflection.observer.Observation;

public class MessageServerNull implements MessageServer {

	@Override
	public void sendMessageToServer(Message message) {
	}

	@Override
	public void broadcastMessageToOtherClients(Message message) {
	}

	@Override
	public void pushObservationToClients(String revisionsName, Observation<?> observation) {
	}

	@Override
	public void onMessageFromServer(MessageFromServerHandler handler) {
	}

	@Override
	public void onObservationFromServer(ObservationFromServerHandler handler) {
	}

}
