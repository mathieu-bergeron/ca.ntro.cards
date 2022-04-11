package ca.ntro.app.tasks;

import ca.ntro.core.task_graphs.task_graph.Task;

public interface TaskCreator<O extends Object, T extends Task> {

	T getTask();

}
