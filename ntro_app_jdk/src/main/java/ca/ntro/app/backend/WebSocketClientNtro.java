package ca.ntro.app.backend;

import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import ca.ntro.app.NtroApp;
import ca.ntro.app.messages.DeliveryMode;
import ca.ntro.app.messages.Message;
import ca.ntro.app.messages.MessageFromServerHandler;
import ca.ntro.app.messages.MessageFromServerHandlerNull;
import ca.ntro.app.messages.MessageServer;
import ca.ntro.app.messages.ObservationFromServerHandler;
import ca.ntro.app.messages.ObservationFromServerHandlerNull;
import ca.ntro.app.messages.BroadcastMessageNtro;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.json.JsonObject;
import ca.ntro.core.reflection.object_graph.ObjectGraph;
import ca.ntro.core.reflection.observer.Observation;
import ca.ntro.core.reflection.observer.ObservationNtro;

public class WebSocketClientNtro extends WebSocketClient implements MessageServer {
	
	private MessageFromServerHandler messageHandler = new MessageFromServerHandlerNull();
	private ObservationFromServerHandler observationHandler = new ObservationFromServerHandlerNull();
	
	
	public WebSocketClientNtro(String serverName, int port) {
		super(connectionUri(serverName, port));

        Ntro.factory().registerNamedClass(ObservationNtro.class);
	}
	
	private static URI connectionUri(String serverName, int port) {
		URI uri = null;
		try {

			uri = new URI(connectionString(serverName, port));

		} catch (URISyntaxException e) {
			Ntro.throwException(e);
		}
		
		return uri;
	}
	
	private static String connectionString(String serverName, int port) {
		return String.format("ws://%s:%d", serverName, port);
		
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		System.out.println("\n\n[INFO] connected to " + getURI() + "\n\n");

		NtroApp.messageService().registerMessageServer(this, DeliveryMode.SEND_TO_SERVER);
	}

	@Override
	public void onMessage(String messageText) {
		try {
			JsonObject jsonObject = Ntro.json().fromJsonString(messageText);
			ObjectGraph objectGraph = Ntro.reflection().graphFromJsonObject(jsonObject);

			Object messageObject = Ntro.reflection().objectFromGraph(objectGraph);
			
			if(messageObject instanceof Message) {
				
				messageHandler.onMessage((Message) messageObject);

			}else if(messageObject instanceof Observation) {
				
				Observation<?> observation = (Observation<?>) messageObject;
				String observationName = Ntro.reflection().simpleName(observation.currentValue().getClass());
				
				observationHandler.onObservation(observationName, observation);

			}

		}catch(Throwable t) {
			System.out.println("\n\n[INFO] onMessage: " + messageText + "\n\n");
			Ntro.throwException(t);
		}
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		System.out.println("\n\n[FATAL] lost connection to " + getURI());
		NtroApp.exit(() -> {
			System.out.println("\n\n");
		});
	}

	@Override
	public void onError(Exception ex) {
		if(ex instanceof ConnectException) {

			System.out.println("\n\n[FATAL] failed to connect to " + getURI());
			NtroApp.exit(() -> {
				System.out.println("\n\n");
			});

		}else {
			Ntro.throwException(ex);
		}
	}

	@Override
	public void sendMessageToServer(Message message) {
		send(Ntro.reflection().toJsonObject(message).toJsonString(false));
	}

	@Override
	public void broadcastMessageToOtherClients(Message message) {
		BroadcastMessageNtro broadcastMessage = new BroadcastMessageNtro(message);
		send(Ntro.reflection().toJsonObject(broadcastMessage).toJsonString(false));
	}

	@Override
	public void pushObservationToClients(String observationName, Observation<?> observation) {
		// XXX: not supported here
	}

	@Override
	public void onMessageFromServer(MessageFromServerHandler handler) {
		this.messageHandler = handler;
	}

	@Override
	public void onObservationFromServer(ObservationFromServerHandler handler) {
		this.observationHandler = handler;
	}

}
