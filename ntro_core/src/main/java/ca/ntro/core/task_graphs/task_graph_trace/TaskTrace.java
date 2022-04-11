package ca.ntro.core.task_graphs.task_graph_trace;

public interface TaskTrace {
	
	boolean canExecuteOneStep();
	void executeOneStep();

}
