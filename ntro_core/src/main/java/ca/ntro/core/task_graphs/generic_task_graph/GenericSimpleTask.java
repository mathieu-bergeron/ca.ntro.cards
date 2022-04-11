package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.task_graphs.task_graph_trace.TaskGraphTrace;
import ca.ntro.core.task_graphs.task_graph_trace.TaskTrace;
import ca.ntro.core.task_graphs.task_graph_trace.TaskResults;

public interface GenericSimpleTask<T  extends GenericTask<T,ST,ET,TG,G>,
                                   ST extends GenericSimpleTask<T,ST,ET,TG,G>,
                                   ET extends GenericExecutableTask<T,ST,ET,TG,G>,
                                   TG extends GenericTaskGroup<T,ST,ET,TG,G>,
                                   G  extends GenericTaskGraph<T,ST,ET,TG,G>>

       extends   GenericTask<T,ST,ET,TG,G> {
	
	boolean isExecutableTask();
	ET asExecutableTask();
	
	void addResult(Object result);
	
	TaskTrace newTrace(TaskGraphTrace graphTrace);
	TaskResults newResults(TaskGraphTrace graphTrace);

}