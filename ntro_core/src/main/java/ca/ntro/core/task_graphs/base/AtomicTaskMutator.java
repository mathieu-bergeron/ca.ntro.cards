package ca.ntro.core.task_graphs.base;

public interface AtomicTaskMutator {

	void addResult(Object value);
	void clearResults();

	void notifyWaitingForResult();

}
