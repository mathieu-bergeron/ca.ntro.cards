package ca.ntro.app.system;

import ca.ntro.app.tasks.system.SystemTaskFactory;

public class SystemTasksNtro {

	private SystemTaskFactory taskFactory = new SystemTaskFactory();

	public SystemTaskFactory getTaskFactory() {
		return taskFactory;
	}

	public void setTaskFactory(SystemTaskFactory taskFactory) {
		this.taskFactory = taskFactory;
	}
	
	public void prepareToExecuteTasks() {

		getTaskFactory().prepareToExecuteTasks();
	}

	public void executeTasks() {
		
		getTaskFactory().executeTasks();

	}

	public void writeGraph() {
		getTaskFactory().writeGraph();
	}

}
