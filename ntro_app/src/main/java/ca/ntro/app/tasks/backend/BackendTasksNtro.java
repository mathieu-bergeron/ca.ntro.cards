package ca.ntro.app.tasks.backend;

import ca.ntro.app.tasks.ContainerTasks;
import ca.ntro.app.tasks.SimpleTaskCreator;
import ca.ntro.app.tasks.TaskGroupCreator;

public class BackendTasksNtro implements BackendTasks {

	private ContainerTasks<BackendTasks> containerTasks;

	public ContainerTasks<BackendTasks> getContainerTasks() {
		return containerTasks;
	}

	public void setContainerTasks(ContainerTasks<BackendTasks> containerTasks) {
		this.containerTasks = containerTasks;
	}

	public BackendTasksNtro() {
	}

	public BackendTasksNtro(ContainerTasks<BackendTasks> containerTasks) {
		setContainerTasks(containerTasks);
	}

	@Override
	public SimpleTaskCreator<?> task(String taskId) {
		return getContainerTasks().task(taskId);
	}

	@Override
	public <O> SimpleTaskCreator<O> task(BackendSimpleTaskDescriptor<O> descriptor) {
		return getContainerTasks().task(descriptor);
	}

	@Override
	public TaskGroupCreator<?, BackendTasks> taskGroup(String taskGroupId) {
		return getContainerTasks().taskGroup(taskGroupId);
	}

	@Override
	public <O> TaskGroupCreator<O, BackendTasks> taskGroup(BackendTaskGroupDescriptor<O> descriptor) {
		return getContainerTasks().taskGroup(descriptor);
	}

}
