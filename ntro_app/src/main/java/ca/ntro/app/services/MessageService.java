package ca.ntro.app.services;

import ca.ntro.app.messages.DeliveryMode;
import ca.ntro.app.messages.Message;
import ca.ntro.app.messages.MessageNtro;
import ca.ntro.app.messages.MessageServer;
import ca.ntro.core.reflection.observer.Observation;
import ca.ntro.core.task_graphs.task_graph.SimpleTask;

public interface MessageService {
	
	void registerMessageServer(MessageServer server, DeliveryMode deliveryMode);

	<MSG extends MessageNtro> MSG newMessage(Class<MSG> messageClass);

	<MSG extends Message> void registerMessageHandler(Class<MSG> messageClass, SimpleTask messageHandlerTask);

	void registerObserverTask(String revisionsName, SimpleTask revisionsHandlerTask);
	
	void sendMessageToServer(Message message);
	void broadcastMessageToOtherClients(Message message);

	void receiveMessageFromServer(Message message);

	void pushObservationToClients(String observationName, Observation<?> observation);
	void receiveObservationFromServer(String observationName, Observation<?> observation);

}
