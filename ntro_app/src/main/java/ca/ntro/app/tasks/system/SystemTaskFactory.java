package ca.ntro.app.tasks.system;

import ca.ntro.app.tasks.ContainerTasksNtro;
import ca.ntro.app.tasks.TaskFactoryNtro;

public class SystemTaskFactory 

       extends TaskFactoryNtro<SystemTasks> {
	
	private SystemTasksNtro systemTasks = new SystemTasksNtro(this);

	public SystemTasksNtro getSystemTasks() {
		return systemTasks;
	}

	public void setSystemTasks(SystemTasksNtro systemTasks) {
		this.systemTasks = systemTasks;
	}

	@Override
	protected String graphName() {
		return "system";
	}

	@Override
	protected ContainerTasksNtro<SystemTasks> newContainerTasksNtro() {
		return new SystemContainerTasksNtro();
	}

	@Override
	public SystemTasks asTasks() {
		return getSystemTasks();
	}

}
