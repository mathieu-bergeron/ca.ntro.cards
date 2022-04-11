package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagNodeNtro;

public class GenericTaskGraphNodeNtro<T  extends GenericTask<T,ST,ET,TG,G>,
                                       ST extends GenericSimpleTask<T,ST,ET,TG,G>,
                                       ET extends GenericExecutableTask<T,ST,ET,TG,G>,
                                       TG extends GenericTaskGroup<T,ST,ET,TG,G>,
                                       G  extends GenericTaskGraph<T,ST,ET,TG,G>>  

       extends   HierarchicalDagNodeNtro<GenericTaskGraphNode<T,ST,ET,TG,G>,
                                         GenericTaskGraphEdge<T,ST,ET,TG,G>> 

       implements GenericTaskGraphNode<T,ST,ET,TG,G> {
	
	
	private T task;

	public T getTask() {
		return task;
	}

	public void setTask(T task) {
		this.task = task;
	}

	@Override
	public T task() {
		return getTask();
	}
	


}
