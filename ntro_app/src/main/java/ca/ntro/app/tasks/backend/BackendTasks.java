package ca.ntro.app.tasks.backend;

import ca.ntro.app.messages.Message;
import ca.ntro.app.messages.MessageNtro;
import ca.ntro.app.models.Model;
import ca.ntro.app.tasks.SimpleTaskCreator;
import ca.ntro.app.tasks.TaskGroupCreator;
import ca.ntro.app.tasks.Tasks;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.reflection.object_graph.revisions.Revisions;
import ca.ntro.core.reflection.observer.Observable;

public interface BackendTasks extends Tasks {

	SimpleTaskCreator<?> task(String taskId);
	<O> SimpleTaskCreator<O> task(BackendSimpleTaskDescriptor<O> descriptor);

	TaskGroupCreator<?, BackendTasks> taskGroup(String taskGroupId);
	<O> TaskGroupCreator<O, BackendTasks> taskGroup(BackendTaskGroupDescriptor<O> descriptor);

	public static <O> BackendSimpleTaskDescriptor<O> create(Class<O> _class) {
		return new BackendSimpleTaskDescriptorNtro<>(Ntro.reflection().simpleName(_class));
	}

	public static <O> BackendSimpleTaskDescriptor<O> created(Class<O> _class) {
		return new BackendSimpleTaskDescriptorNtro<O> (Ntro.reflection().simpleName(_class));
	}

	public static <MSG extends Message> BackendSimpleTaskDescriptor<MSG> message(Class<MSG> messageClass) {
		return new BackendSimpleTaskDescriptorNtro<>("message["+ Ntro.reflection().simpleName(messageClass) + "]");
	}

	public static <M extends Model> BackendSimpleTaskDescriptor<M> model(Class<M> modelClass) {
		return new BackendSimpleTaskDescriptorNtro<>("model["+ Ntro.reflection().simpleName(modelClass) + "]");
	}

	public static <R extends Observable> BackendSimpleTaskDescriptor<Revisions> revisions(Class<R> revisableClass) {
		return new BackendSimpleTaskDescriptorNtro<>("revisions["+ Ntro.reflection().simpleName(revisableClass) + "]");
	}

}
