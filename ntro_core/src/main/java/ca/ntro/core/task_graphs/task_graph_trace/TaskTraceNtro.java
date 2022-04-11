package ca.ntro.core.task_graphs.task_graph_trace;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ca.ntro.core.identifyers.Id;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.task_graphs.base.AtomicTaskMutator;
import ca.ntro.core.task_graphs.generic_task_graph.GenericExecutableTaskNtro;
import ca.ntro.core.task_graphs.generic_task_graph.GenericSimpleTaskNtro;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskNtro;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskGroupNtro;
import ca.ntro.core.task_graphs.handlers.ExecuteHandler;
import ca.ntro.core.values.ObjectMap;

public  class TaskTraceNtro 

       implements TaskTrace,
                  ObjectMap {
	
	private GenericSimpleTaskNtro<?,?,?,?,?> task;
	private TaskGraphTraceNtro graphTrace;
	private Map<String, TaskResults> preconditions = new HashMap<>();
	
	// FIXME
	boolean hasExecutedOnce = false;
	
	
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

	public Map<String, TaskResults> getPreconditions() {
		return preconditions;
	}

	public void setPreconditions(Map<String, TaskResults> preconditions) {
		this.preconditions = preconditions;
	}
	
	public void initialize() {
		
		Set<String> visitedTasks = new HashSet<>();
		visitedTasks.add(getTask().id());
		
		addPreconditionsBackwards(getTask(), visitedTasks);
		addPreconditionsUpwards(getTask(), visitedTasks);
	}
	
	private void addPreconditionsBackwards(GenericTaskNtro<?,?,?,?,?> currentTask, Set<String> visitedTasks) {

		currentTask.previousTasks().forEach(previousTask -> {
			detectCycle((GenericTaskNtro<?,?,?,?,?>) previousTask);

			if(visitedTasks.contains(previousTask.id())) {
				return;
			}

			visitedTasks.add(previousTask.id());
			
			addPreconditions((GenericTaskNtro<?,?,?,?,?>) previousTask, visitedTasks);
		});
	}

	private void addPreconditionsUpwards(GenericTaskNtro<?,?,?,?,?> currentTask, Set<String> visitedTasks) {

		currentTask.parentTasks().forEach(parentTask -> {
			detectCycle((GenericTaskNtro<?,?,?,?,?>) parentTask);

			if(visitedTasks.contains(parentTask.id())) {
				return;
			}

			visitedTasks.add(parentTask.id());
			
			addPreconditionsBackwards((GenericTaskNtro<?,?,?,?,?>) parentTask, visitedTasks);
			addPreconditionsUpwards((GenericTaskNtro<?,?,?,?,?>) parentTask, visitedTasks);
		});
	}
	
	private void addPreconditions(GenericTaskNtro<?,?,?,?,?> currentTask, Set<String> visitedTasks) {
		if(currentTask.isSimpleTask()) {
			
			TaskResults results = currentTask.asSimpleTask().newResults(getGraphTrace());
			
			getPreconditions().put(currentTask.id(), results);
			
			getGraphTrace().registerNewTaskResults(currentTask.id(), results);
		}

		addPreconditionsBackwards((GenericTaskNtro<?,?,?,?,?>) currentTask, visitedTasks);
		
		if(currentTask.isTaskGroup()) {
			addPreconditionsDownwards((GenericTaskGroupNtro<?,?,?,?,?>) currentTask.asTaskGroup(), visitedTasks);
		}
	}

	private void detectCycle(GenericTaskNtro<?, ?, ?, ?, ?> currentTask) {
		if(currentTask == getTask()) {
			Ntro.throwException("\n\n\t[FATAL] cyclic dependancy for task " + getTask().id() + "\n\t\tPlease correct graph.\n\n\n");
		}
	}
	
	private void addPreconditionsDownwards(GenericTaskGroupNtro<?,?,?,?,?> currentTask, Set<String> visitedTasks) {
		currentTask.tasks().forEach(subTask -> {

			detectCycle((GenericTaskNtro<?,?,?,?,?>) subTask);

			if(visitedTasks.contains(subTask.id())) {
				return;
			}

			visitedTasks.add(subTask.id());

			addPreconditions((GenericTaskNtro<?,?,?,?,?>) subTask, visitedTasks);
		});
	}
	

	Stream<TaskResults> preconditions(){
		return Stream.forMapValues(getPreconditions());
	}
	

	@Override
	public boolean canExecuteOneStep() {
		//System.out.println("canExecuteOneStep: " + getTask().getId());

		// FIXME
		return (preconditions().isEmpty() && !hasExecutedOnce)
				|| preconditions().ifSome(pre -> pre.hasNext());
	}

	@Override
	public void executeOneStep() {
		preconditions().forEach(pre -> pre.advanceToNext());
		
		if(preconditions().ifAll(pre -> pre.hasResult())) {
			
			if(getTask().isExecutableTask()) {
				executeTask((GenericExecutableTaskNtro<?,?,?,?,?>) getTask().asExecutableTask());
			}
			
			hasExecutedOnce = true;
			
			preconditions().forEach(pre -> pre.notifyResultWasUsed());
			
		}else {
			
			preconditions().forEach(pre -> pre.notifyResultCouldNotBeUsed());
		}
	}

	private void executeTask(GenericExecutableTaskNtro<?,?,?,?,?> executableTask) {
		ExecuteHandler executeHandler = executableTask.getExecuteHandler();
		
		if(executeHandler != null) {
			
			try {
			
				executeHandler.execute((ObjectMap) this, new AtomicTaskMutator() {

					@Override
					public void addResult(Object value) {
						getGraphTrace().registerNewResult(getTask().id(), value);
					}

					@Override
					public void clearResults() {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void notifyWaitingForResult() {
						// TODO Auto-generated method stub
						
					}
				});
			}
				
			catch(Throwable t) {
				// TODO
				Ntro.exceptionService().throwException(t);
			}
		}
	}

	@Override
	public boolean contains(Id id) {
		return contains(id.toKey().toString());
	}

	@Override
	public boolean contains(String id) {
		return get(id) != null;
	}

	@Override
	public <O> O get(Class<O> _class, Id id) {
		return get(_class, id.toKey().toString());
	}

	@Override
	public <O> O get(Class<O> _class, String id) {
		return (O) get(id);
	}

	@Override
	public Object get(Id id) {
		return get(id.toKey().toString());
	}

	@Override
	public Object get(String id) {
		Object result = null;
		
		TaskResults results = getPreconditions().get(id);
		
		if(results != null
				&& results.hasResult()) {
			result = results.result();
		}
		
		return result;
	}

	@Override
	public Stream<String> ids() {
		return Stream.forMapKeys(getPreconditions());
	}

	@Override
	public Stream<Object> objects() {
		return Stream.forMapValues(getPreconditions()).reduceToStream((results, visitor) -> {
			if(results.hasResult()) {
				visitor.visit(results.result());
			}
		}) ;
	}

}
