package ca.ntro.core.task_graphs.task_graph_trace;

public enum TaskGraphTraceState {

	INACTIVE,
	ACTIVE_AND_EXECUTING,
	ACTIVE_BUT_DONE_FOR_NOW;

	// FIXME: this is a flag
	//        when we are ACTIVE_BUT_DONE_FOR_NOW, we can check if waitingForExternalResults
	//ACTIVE_BUT_WAITING_FOR_EXTERNAL_RESULTS,

}
