package ca.ntro.app.tasks;

import java.util.Map;

import ca.ntro.core.task_graphs.task_graph.Task;
import ca.ntro.core.task_graphs.task_graph.TaskGraph;
import ca.ntro.core.task_graphs.task_graph.TaskContainer;
import ca.ntro.core.task_graphs.task_graph.TaskGroup;

public abstract class TaskGroupCreatorNtro<O extends Object, TASKS extends Tasks>

       extends        TaskCreatorNtro<O, TaskDescriptor<?>, TaskGroupCreator<O, TASKS>, TaskGroup>

       implements     TaskGroupCreator<O, TASKS> {
	

	public TaskGroupCreatorNtro(Map<String, Task> orphanTasks, 
			                    TaskGraph graph, 
			                    TaskContainer parent, 
			                    TaskGroup task) {

		super(orphanTasks, graph, parent, task);
	}


	protected abstract ContainerTasks<TASKS> newContainerTasks(Map<String, Task> orphanTasks, 
			                                                   TaskGraph graph,
			                                                   TaskGroup task);

	@Override
	public TaskGroupCreator<O, TASKS> andContains(SubTasksLambda<TASKS> subTasksLambda) {
		return contains(subTasksLambda);
	}

	@Override
	public TaskGroupCreator<O,TASKS> contains(SubTasksLambda<TASKS> subTasksLambda) {
		ContainerTasks<TASKS> subTasks = newContainerTasks(getOrphanTasks(), getGraph(), getTask());

		subTasksLambda.createSubTasks(subTasks.asTasks());
		
		return this;
	}


}
