package ca.ntro.app.tasks.frontend;

import ca.ntro.app.tasks.ContainerTasks;
import ca.ntro.app.tasks.SimpleTaskCreator;
import ca.ntro.app.tasks.TaskGroupCreator;

public class FrontendTasksNtro 

       implements FrontendTasks {
	
	private ContainerTasks<FrontendTasks> containerTasks;

	public ContainerTasks<FrontendTasks> getContainerTasks() {
		return containerTasks;
	}

	public void setContainerTasks(ContainerTasks<FrontendTasks> containerTasks) {
		this.containerTasks = containerTasks;
	}

	public FrontendTasksNtro() {
	}

	public FrontendTasksNtro(ContainerTasks<FrontendTasks> containerTasks) {
		setContainerTasks(containerTasks);
	}
	
	@Override
	public SimpleTaskCreator<?> task(String taskId) {
		return getContainerTasks().task(taskId);
	}

	@Override
	public <O> SimpleTaskCreator<O> task(FrontendSimpleTaskDescriptor<O> descriptor) {
		return getContainerTasks().task(descriptor);
	}

	@Override
	public TaskGroupCreator<?, FrontendTasks> taskGroup(String taskGroupId) {
		return getContainerTasks().taskGroup(taskGroupId);
	}

	@Override
	public <O> TaskGroupCreator<O, FrontendTasks> taskGroup(FrontendTaskGroupDescriptor<O> descriptor) {
		return getContainerTasks().taskGroup(descriptor);
	}

}
