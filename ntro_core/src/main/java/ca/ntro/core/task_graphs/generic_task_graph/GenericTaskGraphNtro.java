package ca.ntro.core.task_graphs.generic_task_graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.task_graphs.task_graph_trace.TaskGraphTrace;
import ca.ntro.core.task_graphs.task_graph_trace.TaskGraphTraceNtro;

public abstract class GenericTaskGraphNtro<T  extends GenericTask<T,ST,ET,TG,G>,
                                            ST extends GenericSimpleTask<T,ST,ET,TG,G>,
                                            ET extends GenericExecutableTask<T,ST,ET,TG,G>,
                                            TG extends GenericTaskGroup<T,ST,ET,TG,G>,
                                            G  extends GenericTaskGraph<T,ST,ET,TG,G>> 

       implements     GenericTaskGraph<T,ST,ET,TG,G> {
	
	
	
	private InternalHierarchicalDagBuilderNtro<T,ST,ET,TG,G> hdagBuilder = new InternalHierarchicalDagBuilderNtro<>();
	private InternalTaskGraphWriterNtro<T,ST,ET,TG,G> internalWriter = new InternalTaskGraphWriterNtro<>();
	
	private SimpleTaskOptions<? extends ST> defaultSimpleTaskOptions;
	private TaskGroupOptions<? extends TG> defaultTaskGroupOptions;
	
	
	private Map<String, T> tasks = new HashMap<>();
	private Set<TaskGraphTraceNtro> traces = new HashSet<>();

	public InternalHierarchicalDagBuilderNtro<T, ST, ET, TG, G> getHdagBuilder() {
		return hdagBuilder;
	}

	public void setHdagBuilder(InternalHierarchicalDagBuilderNtro<T, ST, ET, TG, G> hdagBuilder) {
		this.hdagBuilder = hdagBuilder;
	}

	public InternalTaskGraphWriterNtro<T, ST, ET, TG, G> getInternalWriter() {
		return internalWriter;
	}

	public void setInternalWriter(InternalTaskGraphWriterNtro<T, ST, ET, TG, G> internalWriter) {
		this.internalWriter = internalWriter;
	}

	public SimpleTaskOptions<? extends ST> getDefaultSimpleTaskOptions() {
		return defaultSimpleTaskOptions;
	}

	public void setDefaultSimpleTaskOptions(SimpleTaskOptions<? extends ST> defaultSimpleTaskOptions) {
		this.defaultSimpleTaskOptions = defaultSimpleTaskOptions;
	}

	public TaskGroupOptions<? extends TG> getDefaultTaskGroupOptions() {
		return defaultTaskGroupOptions;
	}

	public void setDefaultTaskGroupOptions(TaskGroupOptions<? extends TG> defaultTaskGroupOptions) {
		this.defaultTaskGroupOptions = defaultTaskGroupOptions;
	}

	public Map<String, T> getTasks() {
		return tasks;
	}

	public void setTasks(Map<String, T> tasks) {
		this.tasks = tasks;
	}

	public Set<TaskGraphTraceNtro> getTraces() {
		return traces;
	}

	public void setTraces(Set<TaskGraphTraceNtro> traces) {
		this.traces = traces;
	}
	
	
	

	public void initialize() {
		getHdagBuilder().setNodeFactory(() -> {
			return new GenericTaskGraphNodeNtro<>();
		});
		
		getHdagBuilder().setEdgeFactory(() -> {
			return new GenericTaskGraphEdgeNtro<>();
		});
		
		getHdagBuilder().initialize();
	}
	
	
	

	@Override
	public ST newTask(String id) {
		return newTask(id, getDefaultSimpleTaskOptions());
	}

	@Override
	public <TT extends ST> TT newTask(String id, SimpleTaskOptions<TT> options) {
		TT newTask = newTaskInstance(id, options);
		
		addGenericTask(id, (GenericTaskNtro<T,ST,ET,TG,G>) newTask);

		return (TT) newTask;
	}

	public <TT extends ST> TT newTaskInstance(String id, SimpleTaskOptions<TT> options) {
		GenericSimpleTaskNtro<T,ST,ET,TG,G> newTask =  (GenericSimpleTaskNtro<T, ST, ET, TG, G>) newGenericTaskInstance(id, options.getTaskClass());
		
		newTask.setOptions(options);

		return (TT) newTask;
	}

	public GenericTaskNtro<T,ST,ET,TG,G> newGenericTaskInstance(String id, Class<?> taskClass){
		GenericTaskNtro<T,ST,ET,TG,G> newTask = null;

		if(findTask(id) != null) {
			
			Ntro.exceptionService().throwException(new TaskAlreadyExistsException("Task already exists: " + id));
			
		}else {

			newTask = (GenericTaskNtro<T,ST,ET,TG,G>) Ntro.factory().newInstance(taskClass);
			newTask.setGraph(this);
			newTask.setId(id);

		}

		return newTask;
	}

	private GenericTaskNtro<T,ST,ET,TG,G> newGenericTask(String id, Class<?> taskClass){
		GenericTaskNtro<T,ST,ET,TG,G> newTask = newGenericTaskInstance(id, taskClass);
		
		addGenericTask(id, newTask);

		return newTask;
	}
	
	private void addGenericTask(String id, GenericTaskNtro<T,ST,ET,TG,G> task){
		GenericTaskGraphNodeNtro<T,ST,ET,TG,G> node = (GenericTaskGraphNodeNtro<T,ST,ET,TG,G>) getHdagBuilder().addNode(id).node();
			
		node.setTask((T) task);

		task.addNode(node);

		getTasks().put(id, (T) task);
	}

	@Override
	public void addTask(ST simpleTask) {
		addGenericTask(simpleTask.id(), (GenericTaskNtro<T,ST,ET,TG,G>) simpleTask);
	}

	@Override
	public TG newGroup(String id) {
		return newGroup(id, getDefaultTaskGroupOptions());
	}

	@Override
	public <GG extends TG> GG newGroup(String id, TaskGroupOptions<GG> options) {
		return (GG) newGenericTask(id, options.taskGroupClass());
	}

	@Override
	public void addGroup(TG taskGroup) {
		addGenericTask(taskGroup.id(), (GenericTaskNtro<T,ST,ET,TG,G>) taskGroup);
	}

	@Override
	public Stream<T> startTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<T> tasks() {
		return Stream.forMapValues(getTasks());
	}

	@Override
	public T findTask(String id) {
		return getTasks().get(id);
	}

	@Override
	public void setGraphName(String graphName) {
		getHdagBuilder().setGraphName(graphName);
	}

	@Override
	public void write(GraphWriter writer) {
		write(writer, new GenericTaskGraphWriterOptionsNtro<>());
	}

	@Override
	public void write(GraphWriter writer, GenericTaskGraphWriterOptions<T, ST, ET, TG, G> options) {
		getInternalWriter().write(getHdagBuilder().getGraph(), options, writer);
	}

	@Override
	public TaskGraphTrace newTrace() {
		TaskGraphTraceNtro trace = new TaskGraphTraceNtro();
		
		trace.setGraph(this);

		trace.initialize();
		
		getTraces().add(trace);

		return trace;
	}

	@Override
	public Stream<T> endTasks() {
		// TODO Auto-generated method stub
		return null;
	}


	public void registerNewResult(String taskId, Object result) {
		for(TaskGraphTraceNtro trace : getTraces()) {
			trace.registerNewResult(taskId, result);
		}
	}

}
