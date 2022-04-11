package ca.ntro.core.task_graphs.generic_task_graph;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagNodeBuilder;
import ca.ntro.core.stream.Stream;

public abstract class GenericTaskGroupNtro<T  extends GenericTask<T,ST,ET,TG,G>,
                                           ST extends GenericSimpleTask<T,ST,ET,TG,G>,
                                           ET extends GenericExecutableTask<T,ST,ET,TG,G>,
                                           TG extends GenericTaskGroup<T,ST,ET,TG,G>,
                                           G  extends GenericTaskGraph<T,ST,ET,TG,G>> 

       extends        GenericTaskNtro<T,ST,ET,TG,G>

       implements     GenericTaskGroup<T,ST,ET,TG,G> {
	
	private Map<String, T> tasks = new HashMap<>();

	public Map<String, T> getTasks() {
		return tasks;
	}

	public void setTasks(Map<String, T> tasks) {
		this.tasks = tasks;
	}

	@Override
	public T findTask(String id) {
		return getTasks().get(id);
	}

	@Override
	public ST newTask(String id) {
		return newTask(id, getGraph().getDefaultSimpleTaskOptions());
	}

	@Override
	public <TT extends ST> TT newTask(String id, SimpleTaskOptions<TT> options) {
		GenericTaskNtro<T,ST,ET,TG,G> newTask = getGraph().newGenericTaskInstance(id, options.getTaskClass());
		
		addGenericSubTask(id, newTask);
		
		((GenericSimpleTaskNtro<T,ST,ET,TG,G>) newTask).setOptions(options);
		
		return (TT) newTask;
	}

	private void addGenericSubTask(String id, GenericTaskNtro<T, ST, ET, TG, G> newTask) {
		nodes().forEach(node -> {
			
			HierarchicalDagNodeBuilder<GenericTaskGraphNode<T, ST, ET, TG, G>, GenericTaskGraphEdge<T, ST, ET, TG, G>> currentNode = (HierarchicalDagNodeBuilder<GenericTaskGraphNode<T, ST, ET, TG, G>, GenericTaskGraphEdge<T, ST, ET, TG, G>> ) node.getNodeStructure();
			HierarchicalDagNodeBuilder<GenericTaskGraphNode<T, ST, ET, TG, G>, GenericTaskGraphEdge<T, ST, ET, TG, G>> newNode = getGraph().getHdagBuilder().addNode(node.id().toKey().toString() + "_" + id);

			currentNode.addSubNode(newNode);
			
			GenericTaskGraphNodeNtro<T,ST,ET,TG,G> newNodeNtro = (GenericTaskGraphNodeNtro<T,ST,ET,TG,G>) newNode.node();

			newNodeNtro.setTask((T) newTask);

			newTask.addNode(newNodeNtro);
		
			getTasks().put(id, (T) newTask);
			
			getGraph().getTasks().put(id, (T) newTask);
		});
	}

	@Override
	public void addTask(ST simpleTask) {
		addGenericSubTask(simpleTask.id(), (GenericTaskNtro<T,ST,ET,TG,G>) simpleTask);
	}

	@Override
	public TG newGroup(String id) {
		return newGroup(id, getGraph().getDefaultTaskGroupOptions());
	}

	@Override
	public <GG extends TG> GG newGroup(String id, TaskGroupOptions<GG> options) {
		GenericTaskNtro<T,ST,ET,TG,G> newTask = getGraph().newGenericTaskInstance(id, options.taskGroupClass());
		
		addGenericSubTask(id, newTask);

		return (GG) newTask;
	}

	@Override
	public void addGroup(TG taskGroup) {
		addGenericSubTask(taskGroup.id(), (GenericTaskNtro<T,ST,ET,TG,G>) taskGroup);
	}

	@Override
	public boolean isTaskGroup() {
		return true;
	}

	@Override
	public Stream<T> tasks() {
		return Stream.forMapValues(getTasks());
	}

	@Override
	public Stream<T> startTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<T> endTasks() {
		// TODO Auto-generated method stub
		return null;
	}
}
