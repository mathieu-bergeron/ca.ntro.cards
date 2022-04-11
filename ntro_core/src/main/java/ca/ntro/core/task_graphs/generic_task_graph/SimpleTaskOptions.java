package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.task_graphs.task_graph_trace.TaskTrace;
import ca.ntro.core.task_graphs.task_graph_trace.TaskResults;

public interface SimpleTaskOptions<ST extends GenericSimpleTask<?,?,?,?,?>> {
	
	Class<ST> getTaskClass();
	Class<? extends TaskTrace> getTraceClass();
	Class<? extends TaskResults> getResultsClass();
	
	public static <ST extends GenericSimpleTask<?,?,?,?,?>> SimpleTaskOptionsNtro<ST> taskClass(Class<ST> taskClass){
		return new SimpleTaskOptionsNtro<ST>(taskClass);
	}

}
   