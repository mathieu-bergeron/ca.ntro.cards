package ca.ntro.app.tasks.backend;

import java.util.Map;

import ca.ntro.app.tasks.ContainerTasksNtro;
import ca.ntro.app.tasks.SimpleTaskCreator;
import ca.ntro.app.tasks.TaskGroupCreator;
import ca.ntro.core.task_graphs.task_graph.ExecutableTask;
import ca.ntro.core.task_graphs.task_graph.Task;
import ca.ntro.core.task_graphs.task_graph.TaskGraph;
import ca.ntro.core.task_graphs.task_graph.TaskContainer;
import ca.ntro.core.task_graphs.task_graph.TaskGroup;

public class BackendContainerTasksNtro 

       extends ContainerTasksNtro<BackendTasks>

       implements BackendContainerTasks {
	
	private BackendTasksNtro backendTasks = new BackendTasksNtro(this);

	public BackendTasksNtro getBackendTasks() {
		return backendTasks;
	}

	public void setBackendTasks(BackendTasksNtro backendTasks) {
		this.backendTasks = backendTasks;
	}

	public BackendContainerTasksNtro() {
		super();
	}
	
	public BackendContainerTasksNtro(Map<String, Task> orphanTasks, 
			                          TaskGraph graph, 
			                          TaskGroup task) {

		super(orphanTasks, graph, task);
	}
	


	@Override
	protected <O> TaskGroupCreator<O, BackendTasks> newTaskGroupCreator(Map<String, Task> orphanTasks, 
			                                                             TaskGraph graph, 
			                                                             TaskContainer parent, 
			                                                             TaskGroup group) {

		return new BackendTaskGroupCreatorNtro(orphanTasks, graph, parent, group);
	}
	
	@Override
	protected <O> SimpleTaskCreator<O> newSimpleTaskCreator(Map<String, Task> orphanTasks, 
			                                                TaskGraph graph, 
			                                                TaskContainer parent, 
			                                                ExecutableTask task) {

		return new BackendSimpleTaskCreatorNtro<>(orphanTasks, graph, parent, task);
	}

	@Override
	public BackendTasks asTasks() {
		return getBackendTasks();
	}
}
