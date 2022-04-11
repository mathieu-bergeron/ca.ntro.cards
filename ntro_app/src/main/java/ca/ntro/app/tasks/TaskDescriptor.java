package ca.ntro.app.tasks;

import ca.ntro.core.task_graphs.task_graph.Task;
import ca.ntro.core.task_graphs.task_graph.TaskContainer;

public interface TaskDescriptor<O> {
	
	String id();
	
	boolean hasType();
	Class<O> type();

	Task newTask(TaskContainer taskContainer);

}
