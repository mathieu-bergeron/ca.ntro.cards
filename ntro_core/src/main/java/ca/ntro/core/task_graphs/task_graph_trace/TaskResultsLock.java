package ca.ntro.core.task_graphs.task_graph_trace;

public class TaskResultsLock 
       
       extends TaskResultsNtro {
	
	boolean hasNext = true;
	boolean hasResult = false;

	@Override
	public boolean hasResult() {
		return hasResult;
	}

	@Override
	public Object result() {
		return true;
	}

	@Override
	public void notifyResultWasUsed() {
	}

	@Override
	public void notifyResultCouldNotBeUsed() {
	}

	@Override
	public boolean hasNext() {
		return hasNext;
	}

	@Override
	public void advanceToNext() {
		hasNext = false;
		hasResult = true;
	}

	@Override
	public void registerNewResult(Object result) {
	}

	@Override
	public void initialize() {
	}

}
