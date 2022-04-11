package ca.ntro.core.task_graphs.handlers;

import ca.ntro.core.task_graphs.base.AtomicTaskMutator;
import ca.ntro.core.values.ObjectMap;

public interface ExecuteHandler {
	
	void execute(ObjectMap currentResults, AtomicTaskMutator notifyer) throws Throwable;

}
