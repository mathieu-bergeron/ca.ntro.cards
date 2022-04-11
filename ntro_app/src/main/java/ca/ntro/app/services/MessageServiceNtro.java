package ca.ntro.app.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ca.ntro.app.messages.DeliveryMode;
import ca.ntro.app.messages.Message;
import ca.ntro.app.messages.MessageNtro;
import ca.ntro.app.messages.MessageServer;
import ca.ntro.app.messages.MessageServerNull;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.reflection.observer.Modified;
import ca.ntro.core.reflection.observer.Observation;
import ca.ntro.core.task_graphs.task_graph.SimpleTask;

public class MessageServiceNtro implements MessageService {
	
	private Map<Class<? extends Message>, Set<SimpleTask>> messageHandlers = new HashMap<>();
	private Map<String, Set<SimpleTask>> observationHandlers = new HashMap<>();
	private MessageServer messageServer = new MessageServerNull();
	private DeliveryMode deliveryMode = DeliveryMode.DISPATCH_LOCALLY;

	@Override
	public <MSG extends MessageNtro> MSG newMessage(Class<MSG> messageClass) {
		MSG message = Ntro.factory().newInstance(messageClass);
		
		message.registerMessageService(this);

		return message;
	}

	@Override
	public <MSG extends Message> void registerMessageHandler(Class<MSG> messageClass, SimpleTask messageHandlerTask) {
		Set<SimpleTask> handlers = messageHandlers.get(messageClass);
		if(handlers == null) {
			handlers = new HashSet<>();
			messageHandlers.put(messageClass, handlers);
		}
		
		handlers.add(messageHandlerTask);
	}

	@Override
	public void registerObserverTask(String observableName, SimpleTask observationHandlerTask) {
		Set<SimpleTask> handlers = observationHandlers.get(observableName);
		if(handlers == null) {
			handlers = new HashSet<>();
			observationHandlers.put(observableName, handlers);
		}

		handlers.add(observationHandlerTask);
	}

	@Override
	public void sendMessageToServer(Message message) {
		messageServer.sendMessageToServer(message);
	}

	@Override
	public void broadcastMessageToOtherClients(Message message) {
		messageServer.broadcastMessageToOtherClients(message);
	}

	@Override
	public void receiveMessageFromServer(Message message) {
		Set<SimpleTask> handlers = messageHandlers.get(message.getClass());
		
		if(handlers != null) {

			for(SimpleTask handler : handlers) {

				addMessageToMessageHandlerTasks(handler, message);

			}
		}
	}

	protected void addMessageToMessageHandlerTasks(SimpleTask handler, Message message) {
		handler.addResult(message);
	}

	@Override
	public void receiveObservationFromServer(String observationName, Observation<?> observation) {
		Set<SimpleTask> handlers = observationHandlers.get(observationName);
		
		if(handlers != null) {
			
			for(SimpleTask handler : handlers) {

				addObservationToObservationHandlerTask(handler, observation);
			}
		}
	}

	protected void addObservationToObservationHandlerTask(SimpleTask handler, Observation<?> observation) {
		handler.addResult((Modified<?>) observation);
	}

	@Override
	public void pushObservationToClients(String observationName, Observation<?> observation) {
		messageServer.pushObservationToClients(observationName, observation);
	}

	public void deliverMessage(MessageNtro message) {
		switch(deliveryMode) {
			case DISPATCH_LOCALLY:
			default:
				// XXX: copy message to simulate that the
				//      message is sent through a channel
				MessageNtro copyOfMessage = (MessageNtro) Ntro.reflection().clone(message);
				copyOfMessage.registerMessageService(this);
				receiveMessageFromServer(copyOfMessage);
				break;

			case SEND_TO_SERVER:
				sendMessageToServer(message);
				break;
		}
	}

	@Override
	public void registerMessageServer(MessageServer messageServer, DeliveryMode deliveryMode) {
		this.messageServer = messageServer;
		this.deliveryMode = deliveryMode;
		
		this.messageServer.onMessageFromServer(message -> {
			receiveMessageFromServer(message);
		});
		
		this.messageServer.onObservationFromServer((observationName, observation) -> {
			receiveObservationFromServer(observationName, observation);
		});
	}
}
