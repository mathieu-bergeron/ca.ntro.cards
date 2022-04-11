package ca.ntro.app.tasks.frontend;

import java.util.Map;

import ca.ntro.app.tasks.ContainerTasks;
import ca.ntro.app.tasks.TaskGroupCreatorNtro;
import ca.ntro.app.tasks.TaskGroupDescriptor;
import ca.ntro.core.task_graphs.task_graph.Task;
import ca.ntro.core.task_graphs.task_graph.TaskGraph;
import ca.ntro.core.task_graphs.task_graph.TaskContainer;
import ca.ntro.core.task_graphs.task_graph.TaskGroup;

public class FrontendTaskGroupCreatorNtro<O>

       extends TaskGroupCreatorNtro<O, FrontendTasks> {

	public FrontendTaskGroupCreatorNtro(Map<String, Task> orphanTasks, 
			                            TaskGraph graph, TaskContainer parent, 
			                            TaskGroup task) {

		super(orphanTasks, graph, parent, task);
	}


	@Override
	protected TaskGroupDescriptor<?> newTaskDescriptor(String id) {
		return new FrontendTaskGroupDescriptorNtro<>(id);
	}


	@Override
	protected ContainerTasks<FrontendTasks> newContainerTasks(Map<String, Task> orphanTasks, 
			                                                  TaskGraph graph, 
			                                                  TaskGroup task) {

		return new FrontendContainerTasksNtro(orphanTasks, graph, task);
	}

}
