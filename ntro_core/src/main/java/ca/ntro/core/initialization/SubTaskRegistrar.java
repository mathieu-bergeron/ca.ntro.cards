package ca.ntro.core.initialization;

import ca.ntro.core.task_graphs.base.TaskId;
import ca.ntro.core.task_graphs.task_graph.Task;

public interface SubTaskRegistrar {
	
	void addSubTask(TaskId taskId, Task subTask);

}
