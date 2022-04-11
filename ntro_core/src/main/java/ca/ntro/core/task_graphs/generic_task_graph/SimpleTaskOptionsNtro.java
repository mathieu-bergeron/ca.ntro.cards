package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.task_graphs.task_graph_trace.TaskTrace;
import ca.ntro.core.task_graphs.task_graph_trace.TaskTraceNtro;
import ca.ntro.core.task_graphs.task_graph_trace.TaskResults;
import ca.ntro.core.task_graphs.task_graph_trace.TaskResultsCondition;

public class SimpleTaskOptionsNtro<ST extends GenericSimpleTask<?,?,?,?,?>> 

       implements SimpleTaskOptions<ST> {
	
	private Class<ST> taskClass;
	private Class<? extends TaskTrace> traceClass = TaskTraceNtro.class;
	private Class<? extends TaskResults> resultsClass = TaskResultsCondition.class;


	public void setTaskClass(Class<ST> taskClass) {
		this.taskClass = taskClass;
	}

	public void setTraceClass(Class<? extends TaskTrace> traceClass) {
		this.traceClass = traceClass;
	}

	@Override
	public Class<ST> getTaskClass() {
		return taskClass;
	}

	@Override
	public Class<? extends TaskTrace> getTraceClass() {
		return traceClass;
	}

	public Class<? extends TaskResults> getResultsClass() {
		return resultsClass;
	}

	public void setResultsClass(Class<? extends TaskResults> resultsClass) {
		this.resultsClass = resultsClass;
	}

	public SimpleTaskOptionsNtro() {
	}

	public SimpleTaskOptionsNtro(Class<ST> taskClass) {
		setTaskClass(taskClass);
	}
	

	public SimpleTaskOptionsNtro<ST> traceClass(Class<? extends TaskTrace> traceClass) {
		setTraceClass(traceClass);

		return this;
	}

	public SimpleTaskOptionsNtro<ST> resultsClass(Class<? extends TaskResults> resultsClass) {
		setResultsClass(resultsClass);

		return this;
	}

}
