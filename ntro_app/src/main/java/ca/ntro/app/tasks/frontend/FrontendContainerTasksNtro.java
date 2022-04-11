package ca.ntro.app.tasks.frontend;

import java.util.Map;

import ca.ntro.app.tasks.ContainerTasksNtro;
import ca.ntro.app.tasks.SimpleTaskCreator;
import ca.ntro.app.tasks.TaskGroupCreator;
import ca.ntro.core.task_graphs.task_graph.Task;
import ca.ntro.core.task_graphs.task_graph.TaskGraph;
import ca.ntro.core.task_graphs.task_graph.TaskContainer;
import ca.ntro.core.task_graphs.task_graph.TaskGroup;

public class FrontendContainerTasksNtro 

       extends ContainerTasksNtro<FrontendTasks>

       implements FrontendContainerTasks {
	
	private FrontendTasksNtro frontendTasks = new FrontendTasksNtro(this);


	public FrontendTasksNtro getFrontendTasks() {
		return frontendTasks;
	}

	public void setFrontendTasks(FrontendTasksNtro frontendTasks) {
		this.frontendTasks = frontendTasks;
	}
	
	public FrontendContainerTasksNtro() {
		super();
	}
	
	public FrontendContainerTasksNtro(Map<String, Task> orphanTasks, 
			                          TaskGraph graph, 
			                          TaskGroup task) {

		super(orphanTasks, graph, task);
	}
	


	@Override
	protected <O> TaskGroupCreator<O, FrontendTasks> newTaskGroupCreator(Map<String, Task> orphanTasks, 
			                                                             TaskGraph graph, 
			                                                             TaskContainer parent, 
			                                                             TaskGroup group) {

		return new FrontendTaskGroupCreatorNtro(orphanTasks, graph, parent, group);
	}

	@Override
	public FrontendTasks asTasks() {
		return getFrontendTasks();
	}
}
