package ca.ntro.app.frontend.events;

public interface EventRegistrar {
	
	<E extends EventNtro> void registerEvent(Class<E> eventClass);

}
