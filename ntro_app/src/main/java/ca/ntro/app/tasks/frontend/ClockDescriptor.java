package ca.ntro.app.tasks.frontend;

import ca.ntro.core.clock.Tick;
import ca.ntro.core.task_graphs.task_graph.Task;
import ca.ntro.core.task_graphs.task_graph.TaskGraph;

public interface ClockDescriptor {
	
	FrontendSimpleTaskDescriptor<Tick> nextTick();


}
