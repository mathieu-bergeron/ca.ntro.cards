package ca.ntro.core.task_graphs.task_graph_trace;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskGraphNtro;

public class TaskGraphTraceNtro 

       implements TaskGraphTrace {
	
	private GenericTaskGraphNtro<?,?,?,?,?> graph;

	private TaskGraphTraceState state = TaskGraphTraceState.INACTIVE;

	private Map<String, TaskTraceNtro> traces = new HashMap<>();
	private Map<String, Set<TaskResults>> results = new HashMap<>();
	
	public synchronized TaskGraphTraceState getState() {
		return state;
	}

	public synchronized void setState(TaskGraphTraceState state) {
		this.state = state;
	}

	public Map<String, TaskTraceNtro> getTraces() {
		return traces;
	}

	public void setTraces(Map<String, TaskTraceNtro> traces) {
		this.traces = traces;
	}
	
	private Stream<TaskTraceNtro> traces(){
		return Stream.forMapValues(getTraces());
	}

	public GenericTaskGraphNtro<?, ?, ?, ?, ?> getGraph() {
		return graph;
	}

	public void setGraph(GenericTaskGraphNtro<?, ?, ?, ?, ?> graph) {
		this.graph = graph;
	}

	public Map<String, Set<TaskResults>> getResults() {
		return results;
	}

	public void setResults(Map<String, Set<TaskResults>> results) {
		this.results = results;
	}
	
	
	public void initialize() {
		getGraph().tasks().forEach(task -> {
			
			if(task.isSimpleTask()) {
				
				TaskTraceNtro trace = (TaskTraceNtro) task.asSimpleTask().newTrace(this);

				getTraces().put(task.id(), trace);
			}
		});
	}
	
	public void registerNewTaskResults(String taskId, TaskResults taskResults) {

		Set<TaskResults> resultsForTask = getResults().get(taskId);
		if(resultsForTask == null) {
			resultsForTask = new HashSet<>();
			getResults().put(taskId, resultsForTask);
		}

		resultsForTask.add(taskResults);
	}


	public void execute() {
		switch(getState()) {
		
			case INACTIVE:
				setState(TaskGraphTraceState.ACTIVE_AND_EXECUTING);
				executeLoop();
				break;

			case ACTIVE_AND_EXECUTING:
			case ACTIVE_BUT_DONE_FOR_NOW:
				break;
		}
	}
	
	private void executeLoop() {
		/* FIXME: we do not need to check every task every time
		 *        we should maintain a set of candidate tasks
		 *        
		 *        we know when to add tasks to the candidate set
		 *        (on registerNewResult and tasks that have that result as a
		 *         precondition)
		 * 
		 */
		
		
		while(getState() == TaskGraphTraceState.ACTIVE_AND_EXECUTING) {
			
			boolean stillExecuting = traces().reduceToResult(false, (accumulator, trace) -> {

				if(trace.canExecuteOneStep()) {
					
					trace.executeOneStep();
					
					accumulator = true;
				}

				return accumulator;

			}).value();
			
			if(!stillExecuting) {
				setState(TaskGraphTraceState.ACTIVE_BUT_DONE_FOR_NOW);
			}
		}
	}
	
	public void registerNewResult(String taskId, Object result) {

		registerNewResultImpl(taskId, result);
		
		executeAfterResult();
	}

	private void registerNewResultImpl(String taskId, Object result) {

		Set<TaskResults> resultsForTask = getResults().get(taskId);
		if(resultsForTask == null) {
			resultsForTask = new HashSet<>();
			getResults().put(taskId, resultsForTask);
		}
		
		for(TaskResults results : resultsForTask) {
			results.registerNewResult(result);
		}
	}

	private void executeAfterResult() {
		switch(getState()) {
		
			case ACTIVE_BUT_DONE_FOR_NOW:
				setState(TaskGraphTraceState.ACTIVE_AND_EXECUTING);
				executeLoop();
				break;

			case INACTIVE:
			case ACTIVE_AND_EXECUTING:
				break;
		}
	}






	@Override
	public boolean isWaitingForExternalResults() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void writeCurrentState(GraphWriter writer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeTrace(GraphWriter writer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isActive() {
		TaskGraphTraceState currentState = getState();
		
		return currentState == TaskGraphTraceState.ACTIVE_AND_EXECUTING
				|| currentState == TaskGraphTraceState.ACTIVE_BUT_DONE_FOR_NOW;
	}
}
