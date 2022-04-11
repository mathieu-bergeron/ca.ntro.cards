package ca.ntro.app.messages;

import ca.ntro.core.reflection.observer.Observation;

public interface MessageServer {

	void sendMessageToServer(Message message);

	void broadcastMessageToOtherClients(Message message);

	void pushObservationToClients(String observationName, Observation<?> observation);
	
	void onMessageFromServer(MessageFromServerHandler handler);

	void onObservationFromServer(ObservationFromServerHandler handler);

}
