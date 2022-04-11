package ca.ntro.core.task_graphs.task_graph_trace;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class TaskResultsQueue 
       
       extends TaskResultsNtro {
	
	private Object result = null;
	private List<Object> queue = new LinkedList<>();
	

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
	public abstract void notifyResultWasUsed();

	@Override
	public abstract void notifyResultCouldNotBeUsed();
	

	@Override
	public boolean hasNext() {
		return !queue.isEmpty();
	}
	
	@Override
	public void advanceToNext() {
		if(!hasResult() && hasNext()) {
			result = queue.get(0);
			queue = queue.subList(1, queue.size());
		}
	}

	protected void consumeResult() {
		result = null;
	}
	
	@Override
	public void registerNewResult(Object result) {
		queue.add(result);
	}
}
