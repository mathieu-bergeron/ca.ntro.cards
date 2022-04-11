package ca.ntro.app.services;

import ca.ntro.app.frontend.events.EventNtro;
import ca.ntro.core.task_graphs.task_graph.SimpleTask;

public interface EventService {

	<E extends EventNtro> E newEvent(Class<E> eventClass);

	<E extends EventNtro> void registerEventHandler(Class<E> eventClass, SimpleTask eventHandler);

}
