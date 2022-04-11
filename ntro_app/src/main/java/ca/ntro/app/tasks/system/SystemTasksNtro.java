package ca.ntro.app.tasks.system;

import ca.ntro.app.tasks.ContainerTasks;
import ca.ntro.app.tasks.SimpleTaskCreator;
import ca.ntro.app.tasks.TaskGroupCreator;

public class SystemTasksNtro implements SystemTasks {

	private ContainerTasks<SystemTasks> containerTasks;

	public ContainerTasks<SystemTasks> getContainerTasks() {
		return containerTasks;
	}

	public void setContainerTasks(ContainerTasks<SystemTasks> containerTasks) {
		this.containerTasks = containerTasks;
	}

	public SystemTasksNtro() {
	}

	public SystemTasksNtro(ContainerTasks<SystemTasks> containerTasks) {
		setContainerTasks(containerTasks);
	}

	@Override
	public SimpleTaskCreator<?> task(String taskId) {
		return getContainerTasks().task(taskId);
	}

	@Override
	public <O> SimpleTaskCreator<O> task(SystemSimpleTaskDescriptor<O> descriptor) {
		return getContainerTasks().task(descriptor);
	}

	@Override
	public TaskGroupCreator<?, SystemTasks> taskGroup(String taskGroupId) {
		return getContainerTasks().taskGroup(taskGroupId);
	}

	@Override
	public <O> TaskGroupCreator<O, SystemTasks> taskGroup(SystemTaskGroupDescriptor<O> descriptor) {
		return getContainerTasks().taskGroup(descriptor);
	}

}
