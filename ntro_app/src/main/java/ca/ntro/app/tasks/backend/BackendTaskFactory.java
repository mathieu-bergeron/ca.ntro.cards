package ca.ntro.app.tasks.backend;

import ca.ntro.app.tasks.ContainerTasksNtro;
import ca.ntro.app.tasks.TaskFactoryNtro;

public class BackendTaskFactory 

       extends TaskFactoryNtro<BackendTasks> {
	
	private BackendTasksNtro backendTasks = new BackendTasksNtro(this);

	public BackendTasksNtro getBackendTasks() {
		return backendTasks;
	}

	public void setBackendTasks(BackendTasksNtro frontendTasks) {
		this.backendTasks = frontendTasks;
	}

	@Override
	protected String graphName() {
		return "backend";
	}

	@Override
	protected ContainerTasksNtro<BackendTasks> newContainerTasksNtro() {
		return new BackendContainerTasksNtro();
	}

	@Override
	public BackendTasks asTasks() {
		return getBackendTasks();
	}


}
