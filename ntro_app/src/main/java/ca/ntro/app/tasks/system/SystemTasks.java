package ca.ntro.app.tasks.system;

import ca.ntro.app.messages.Message;
import ca.ntro.app.messages.MessageNtro;
import ca.ntro.app.models.Model;
import ca.ntro.app.tasks.SimpleTaskCreator;
import ca.ntro.app.tasks.TaskGroupCreator;
import ca.ntro.app.tasks.Tasks;
import ca.ntro.core.initialization.Ntro;

public interface SystemTasks extends Tasks {

	SimpleTaskCreator<?> task(String taskId);
	<O> SimpleTaskCreator<O> task(SystemSimpleTaskDescriptor<O> descriptor);

	TaskGroupCreator<?, SystemTasks> taskGroup(String taskGroupId);
	<O> TaskGroupCreator<O, SystemTasks> taskGroup(SystemTaskGroupDescriptor<O> descriptor);

	public static <O> SystemSimpleTaskDescriptor<O> create(Class<O> _class) {
		return new SystemSimpleTaskDescriptorNtro<>(Ntro.reflection().simpleName(_class));
	}

	public static <O> SystemSimpleTaskDescriptor<O> created(Class<O> _class) {
		return new SystemSimpleTaskDescriptorNtro<O> (Ntro.reflection().simpleName(_class));
	}

	public static <MSG extends Message> SystemSimpleTaskDescriptor<MSG> message(Class<MSG> messageClass) {
		return new SystemSimpleTaskDescriptorNtro<>("message["+ Ntro.reflection().simpleName(messageClass) + "]");
	}

	public static <M extends Model> SystemSimpleTaskDescriptor<M> model(Class<M> modelClass) {
		return new SystemSimpleTaskDescriptorNtro<>("model["+ Ntro.reflection().simpleName(modelClass) + "]");
	}

}
