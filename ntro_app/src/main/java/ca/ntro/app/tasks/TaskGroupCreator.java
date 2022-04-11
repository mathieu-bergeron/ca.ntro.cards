package ca.ntro.app.tasks;

import ca.ntro.core.task_graphs.task_graph.TaskGroup;

public interface TaskGroupCreator<O extends Object, TASKS extends Object>

       extends   TaskCreator<O, TaskGroup>,
                 WaitsFor<O,
                          TaskDescriptor<?>,
                          TaskGroupCreator<O, TASKS>,
                          TaskGroup> {

	TaskGroupCreator<?, TASKS> andContains(SubTasksLambda<TASKS> subTasksLambda);
	TaskGroupCreator<?, TASKS> contains(SubTasksLambda<TASKS> subTasksLambda);

}
