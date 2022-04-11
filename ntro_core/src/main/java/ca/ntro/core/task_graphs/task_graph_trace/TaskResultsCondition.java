package ca.ntro.core.task_graphs.task_graph_trace;


public class TaskResultsCondition 
       
       extends TaskResultsNtro {
	
	private Object result = null;
	private boolean hasNext = false;

	@Override
	public void initialize() {
	}

	@Override
	public boolean hasResult() {
		return result != null;
	}

	@Override
	public Object result() {
		return result;
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
	}

	@Override
	public void registerNewResult(Object result) {
		hasNext = true;
		this.result = result;
	}



}
