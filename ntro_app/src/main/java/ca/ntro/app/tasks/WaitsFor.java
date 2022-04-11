package ca.ntro.app.tasks;

import ca.ntro.core.task_graphs.task_graph.Task;

public interface WaitsFor<O extends Object, 
                          TD extends TaskDescriptor<?>,
                          TC extends TaskCreator<O,T>,
                          T extends  Task> {

	TC waitsFor(String taskId);
	TC waitsFor(TD descriptor);

}
