package ca.ntro.core.task_graphs.task_graph_trace;

public interface TaskResults {

	boolean hasResult();
	Object result();

	void notifyResultWasUsed();
	void notifyResultCouldNotBeUsed();
	
	boolean hasNext();
	void advanceToNext();

	void registerNewResult(Object result);

}
