package services;

import ca.ntro.app.messages.Message;
import ca.ntro.app.services.MessageServiceNtro;
import ca.ntro.core.reflection.observer.Modified;
import ca.ntro.core.reflection.observer.Observation;
import ca.ntro.core.task_graphs.task_graph.SimpleTask;
import javafx.application.Platform;

public class MessageServiceFx extends MessageServiceNtro {

	@Override
	protected void addMessageToMessageHandlerTasks(SimpleTask handler, Message message) {
		Platform.runLater(() -> {
			handler.addResult(message);
		});
	}

	@Override
	protected void addObservationToObservationHandlerTask(SimpleTask handler, Observation<?> observation) {
		Platform.runLater(() -> {
			handler.addResult((Modified<?>) observation);
		});
	}

}
