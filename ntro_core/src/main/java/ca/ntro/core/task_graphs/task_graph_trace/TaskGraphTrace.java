package ca.ntro.core.task_graphs.task_graph_trace;

import ca.ntro.core.graph_writer.GraphWriter;

public interface TaskGraphTrace {
	
	void execute();

	boolean isActive();
	boolean isWaitingForExternalResults();

	void writeCurrentState(GraphWriter writer);
	void writeTrace(GraphWriter writer);

}
