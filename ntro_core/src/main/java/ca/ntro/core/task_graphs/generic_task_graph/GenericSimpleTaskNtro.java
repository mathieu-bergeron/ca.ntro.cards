package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.task_graphs.task_graph_trace.TaskGraphTrace;
import ca.ntro.core.task_graphs.task_graph_trace.TaskGraphTraceNtro;
import ca.ntro.core.task_graphs.task_graph_trace.TaskTrace;
import ca.ntro.core.task_graphs.task_graph_trace.TaskTraceNtro;
import ca.ntro.core.task_graphs.task_graph_trace.TaskResults;
import ca.ntro.core.task_graphs.task_graph_trace.TaskResultsNtro;

public abstract class GenericSimpleTaskNtro<T  extends GenericTask<T,ST,ET,TG,G>,
                                            ST extends GenericSimpleTask<T,ST,ET,TG,G>,
                                            ET extends GenericExecutableTask<T,ST,ET,TG,G>,
                                            TG extends GenericTaskGroup<T,ST,ET,TG,G>,
                                            G  extends GenericTaskGraph<T,ST,ET,TG,G>> 

       extends GenericTaskNtro<T,ST,ET,TG,G>

       implements GenericSimpleTask<T,ST,ET,TG,G> {
	
	
	private SimpleTaskOptions<?> options;
	
	public SimpleTaskOptions<?> getOptions() {
		return options;
	}

	public void setOptions(SimpleTaskOptions<?> options) {
		this.options = options;
	}




	@Override
	public TaskResults newResults(TaskGraphTrace graphTrace) {
		TaskResultsNtro results = (TaskResultsNtro) Ntro.factory().newInstance(getOptions().getResultsClass());
		
		results.setGraphTrace((TaskGraphTraceNtro) graphTrace);
		results.setTask((GenericSimpleTaskNtro<?,?,?,?,?>) this);

		results.initialize();

		return results;
	}

	@Override
	public TaskTrace newTrace(TaskGraphTrace graphTrace) {
		TaskTraceNtro trace = (TaskTraceNtro) Ntro.factory().newInstance(getOptions().getTraceClass());
		
		trace.setGraphTrace((TaskGraphTraceNtro) graphTrace);
		trace.setTask((GenericSimpleTaskNtro<?,?,?,?,?>) this);
		
		trace.initialize();
		
		return trace;
	}
	
	

	@Override
	public void addResult(Object result) {
		getGraph().registerNewResult(this.id(), result);
	}
	
	
	@Override
	public boolean isSimpleTask() {
		return true;
	}

	@Override
	public boolean isExecutableTask() {
		return false;
	}

	@Override
	public ET asExecutableTask() {
		return (ET) this;
	}

}
	
