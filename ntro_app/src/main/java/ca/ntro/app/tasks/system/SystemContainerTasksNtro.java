package ca.ntro.app.tasks.system;

import java.util.Map;

import ca.ntro.app.tasks.ContainerTasksNtro;
import ca.ntro.app.tasks.SimpleTaskCreator;
import ca.ntro.app.tasks.TaskGroupCreator;
import ca.ntro.core.task_graphs.task_graph.ExecutableTask;
import ca.ntro.core.task_graphs.task_graph.Task;
import ca.ntro.core.task_graphs.task_graph.TaskGraph;
import ca.ntro.core.task_graphs.task_graph.TaskContainer;
import ca.ntro.core.task_graphs.task_graph.TaskGroup;

public class SystemContainerTasksNtro 

       extends ContainerTasksNtro<SystemTasks>

       implements SystemContainerTasks {
	
	private SystemTasksNtro systemTasks = new SystemTasksNtro(this);

	public SystemTasksNtro getSystemTasks() {
		return systemTasks;
	}

	public void setSystemTasks(SystemTasksNtro systemTasks) {
		this.systemTasks = systemTasks;
	}

	public SystemContainerTasksNtro() {
		super();
	}
	
	public SystemContainerTasksNtro(Map<String, Task> orphanTasks, 
			                          TaskGraph graph, 
			                          TaskGroup task) {

		super(orphanTasks, graph, task);
	}
	


	@Override
	protected <O> TaskGroupCreator<O, SystemTasks> newTaskGroupCreator(Map<String, Task> orphanTasks, 
			                                                             TaskGraph graph, 
			                                                             TaskContainer parent, 
			                                                             TaskGroup group) {

		return new SystemTaskGroupCreatorNtro(orphanTasks, graph, parent, group);
	}
	
	@Override
	protected <O> SimpleTaskCreator<O> newSimpleTaskCreator(Map<String, Task> orphanTasks, 
			                                                TaskGraph graph, 
			                                                TaskContainer parent, 
			                                                ExecutableTask task) {

		return new SystemSimpleTaskCreatorNtro<>(orphanTasks, graph, parent, task);
	}

	@Override
	public SystemTasks asTasks() {
		return getSystemTasks();
	}
}
