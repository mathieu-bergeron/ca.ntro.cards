package ca.ntro.app.tasks.frontend;

import ca.ntro.app.frontend.View;
import ca.ntro.app.frontend.ViewLoader;
import ca.ntro.app.frontend.events.EventNtro;
import ca.ntro.app.messages.Message;
import ca.ntro.app.services.Window;
import ca.ntro.app.session.Session;
import ca.ntro.app.tasks.SimpleTaskCreator;
import ca.ntro.app.tasks.TaskGroupCreator;
import ca.ntro.app.tasks.Tasks;
import ca.ntro.core.clock.Tick;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.reflection.object_graph.revisions.Revisions;
import ca.ntro.core.reflection.observer.Modified;
import ca.ntro.core.reflection.observer.Observable;

public interface FrontendTasks extends Tasks {

	SimpleTaskCreator<?> task(String taskId);
	<O> SimpleTaskCreator<O> task(FrontendSimpleTaskDescriptor<O> descriptor);

	TaskGroupCreator<?, FrontendTasks> taskGroup(String taskGroupId);
	<O> TaskGroupCreator<O, FrontendTasks> taskGroup(FrontendTaskGroupDescriptor<O> descriptor);
	
	

	public static FrontendSimpleTaskDescriptor<Window> window() {
		return new FrontendSimpleTaskDescriptorNtro<>("window");
	}

	public static <O> FrontendSimpleTaskDescriptor<O> create(Class<O> _class) {
		return new FrontendSimpleTaskDescriptorNtro<>(Ntro.reflection().simpleName(_class));
	}

	public static <O> FrontendSimpleTaskDescriptor<O> created(Class<O> _class) {
		return new FrontendSimpleTaskDescriptorNtro<O> (Ntro.reflection().simpleName(_class));
	}

	public static <V extends View<?>> FrontendSimpleTaskDescriptor<ViewLoader<V>> viewLoader(Class<V> viewClass) {
		return new FrontendSimpleTaskDescriptorNtro<>("viewLoader["+ Ntro.reflection().simpleName(viewClass) + "]");
	}

	public static <EVT extends EventNtro> FrontendSimpleTaskDescriptor<EVT> event(Class<EVT> eventClass) {
		return new FrontendSimpleTaskDescriptorNtro<>("event["+ Ntro.reflection().simpleName(eventClass) + "]");
	}

	public static <R extends Observable> FrontendSimpleTaskDescriptor<Revisions> snapshot(Class<R> revisableClass) {
		return new FrontendSimpleTaskDescriptorNtro<>("snapshot["+ Ntro.reflection().simpleName(revisableClass) + "]");
	}

	public static <S extends Session> FrontendSimpleTaskDescriptor<Session> session(Class<S> sessionClass) {
		return new FrontendSimpleTaskDescriptorNtro<>("session["+ Ntro.reflection().simpleName(sessionClass) + "]");
	}

	public static <R extends Observable> FrontendSimpleTaskDescriptor<Modified<R>> modified(Class<R> revisableClass) {
		return new FrontendSimpleTaskDescriptorNtro<>("modified["+ Ntro.reflection().simpleName(revisableClass) + "]");
	}

	public static <MSG extends Message> FrontendSimpleTaskDescriptor<MSG> message(Class<MSG> messageClass) {
		return new FrontendSimpleTaskDescriptorNtro<>("message["+ Ntro.reflection().simpleName(messageClass) + "]");
	}

	public static ClockDescriptor clock() {
		return new ClockDescriptorNtro();
	}

}
