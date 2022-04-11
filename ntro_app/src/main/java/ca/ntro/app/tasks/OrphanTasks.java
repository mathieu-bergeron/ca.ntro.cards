package ca.ntro.app.tasks;

import ca.ntro.core.task_graphs.generic_task_graph.SimpleTaskOptions;

public interface OrphanTasks {

	SimpleTaskCreator<?> orphanTask(String taskId, SimpleTaskOptions<?> options);
	<O> SimpleTaskCreator<O> orphanTask(SimpleTaskDescriptor<O> descriptor, SimpleTaskOptions options);

}
