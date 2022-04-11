package ca.ntro.app.tasks.backend;

import java.util.Map;

import ca.ntro.app.tasks.ContainerTasks;
import ca.ntro.app.tasks.TaskGroupCreatorNtro;
import ca.ntro.app.tasks.TaskGroupDescriptor;
import ca.ntro.core.task_graphs.task_graph.Task;
import ca.ntro.core.task_graphs.task_graph.TaskGraph;
import ca.ntro.core.task_graphs.task_graph.TaskContainer;
import ca.ntro.core.task_graphs.task_graph.TaskGroup;

public class BackendTaskGroupCreatorNtro<O>

       extends TaskGroupCreatorNtro<O, BackendTasks> {

	public BackendTaskGroupCreatorNtro(Map<String, Task> orphanTasks, 
			                            TaskGraph graph, TaskContainer parent, 
			                            TaskGroup task) {

		super(orphanTasks, graph, parent, task);
	}


	@Override
	protected TaskGroupDescriptor<?> newTaskDescriptor(String id) {
		return new BackendTaskGroupDescriptorNtro<>(id);
	}


	@Override
	protected ContainerTasks<BackendTasks> newContainerTasks(Map<String, Task> orphanTasks, 
			                                                  TaskGraph graph, 
			                                                  TaskGroup task) {

		return new BackendContainerTasksNtro(orphanTasks, graph, task);
	}

}
