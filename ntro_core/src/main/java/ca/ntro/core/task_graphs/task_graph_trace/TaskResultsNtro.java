package ca.ntro.core.task_graphs.task_graph_trace;

import ca.ntro.core.task_graphs.generic_task_graph.GenericSimpleTaskNtro;

public abstract class TaskResultsNtro implements TaskResults {

	private GenericSimpleTaskNtro<?,?,?,?,?> task;
	private TaskGraphTraceNtro graphTrace;

	public GenericSimpleTaskNtro<?, ?, ?, ?, ?> getTask() {
		return task;
	}

	public void setTask(GenericSimpleTaskNtro<?, ?, ?, ?, ?> task) {
		this.task = task;
	}

	public TaskGraphTraceNtro getGraphTrace() {
		return graphTrace;
	}

	public void setGraphTrace(TaskGraphTraceNtro graphTrace) {
		this.graphTrace = graphTrace;
	}
	
	public abstract void initialize();

}
