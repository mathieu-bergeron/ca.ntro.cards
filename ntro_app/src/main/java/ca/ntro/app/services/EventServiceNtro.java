package ca.ntro.app.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ca.ntro.app.frontend.events.Event;
import ca.ntro.app.frontend.events.EventNtro;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.task_graphs.task_graph.SimpleTask;

public class EventServiceNtro implements EventService {
	
	private Map<String, Set<SimpleTask>> eventHandlers = new HashMap<>();

	@Override
	public <E extends EventNtro> E newEvent(Class<E> eventClass) {
		E event = Ntro.factory().newInstance(eventClass);
		
		((EventNtro) event).setEventService(this);

		return event;
	}

	public <E extends Event> void triggerEvent(E event) {
		String handlerId = Ntro.reflection().simpleName(event.getClass());
		
		Set<SimpleTask> handlers = eventHandlers.get(handlerId);
		if(handlers != null) {
			for(SimpleTask handler : handlers) {
				handler.addResult(event);
			}
		}
	}

	@Override
	public <E extends EventNtro> void registerEventHandler(Class<E> eventClass, SimpleTask handler) {
		// FIXME
		String handlerId = handler.id();
		handlerId = handlerId.replace("event[", "");
		handlerId = handlerId.replace("]", "");

		Set<SimpleTask> handlers = eventHandlers.get(handlerId);
		if(handlers == null) {
			handlers = new HashSet<>();
			eventHandlers.put(handlerId, handlers);
		}

		handlers.add(handler);
	}

}
