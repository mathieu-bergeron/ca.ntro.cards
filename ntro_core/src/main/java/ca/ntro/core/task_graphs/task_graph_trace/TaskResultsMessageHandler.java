package ca.ntro.core.task_graphs.task_graph_trace;

public class TaskResultsMessageHandler 
       
       extends TaskResultsQueue {

	@Override
	public void notifyResultWasUsed() {
		consumeResult();
	}

	@Override
	public void notifyResultCouldNotBeUsed() {
	}

}
