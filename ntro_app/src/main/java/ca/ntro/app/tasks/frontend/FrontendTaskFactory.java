package ca.ntro.app.tasks.frontend;

import ca.ntro.app.tasks.ContainerTasksNtro;
import ca.ntro.app.tasks.TaskFactoryNtro;

public class FrontendTaskFactory 

       extends TaskFactoryNtro<FrontendTasks> {
	
	private FrontendTasksNtro frontendTasks = new FrontendTasksNtro(this);

	public FrontendTasksNtro getFrontendTasks() {
		return frontendTasks;
	}

	public void setFrontendTasks(FrontendTasksNtro frontendTasks) {
		this.frontendTasks = frontendTasks;
	}

	@Override
	protected String graphName() {
		return "frontend";
	}

	@Override
	protected ContainerTasksNtro<FrontendTasks> newContainerTasksNtro() {
		return new FrontendContainerTasksNtro();
	}

	@Override
	public FrontendTasks asTasks() {
		return getFrontendTasks();
	}


}
