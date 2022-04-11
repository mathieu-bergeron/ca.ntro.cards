package ca.ntro.app.tasks.system;

import java.util.Map;

import ca.ntro.app.tasks.ContainerTasks;
import ca.ntro.app.tasks.TaskGroupCreatorNtro;
import ca.ntro.app.tasks.TaskGroupDescriptor;
import ca.ntro.core.task_graphs.task_graph.Task;
import ca.ntro.core.task_graphs.task_graph.TaskGraph;
import ca.ntro.core.task_graphs.task_graph.TaskContainer;
import ca.ntro.core.task_graphs.task_graph.TaskGroup;

public class SystemTaskGroupCreatorNtro<O>

       extends TaskGroupCreatorNtro<O, SystemTasks> {

	public SystemTaskGroupCreatorNtro(Map<String, Task> orphanTasks, 
			                            TaskGraph graph, TaskContainer parent, 
			                            TaskGroup task) {

		super(orphanTasks, graph, parent, task);
	}


	@Override
	protected TaskGroupDescriptor<?> newTaskDescriptor(String id) {
		return new SystemTaskGroupDescriptorNtro<>(id);
	}


	@Override
	protected ContainerTasks<SystemTasks> newContainerTasks(Map<String, Task> orphanTasks, 
			                                                  TaskGraph graph, 
			                                                  TaskGroup task) {

		return new SystemContainerTasksNtro(orphanTasks, graph, task);
	}

}
