package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.graphs.hierarchical_dag.InternalHierarchicalDagWriter;

public interface InternalTaskGraphWriter<T  extends GenericTask<T,ST,ET,TG,G>,
                                            ST extends GenericSimpleTask<T,ST,ET,TG,G>,
                                            ET extends GenericExecutableTask<T,ST,ET,TG,G>,
                                            TG extends GenericTaskGroup<T,ST,ET,TG,G>,
                                            G  extends GenericTaskGraph<T,ST,ET,TG,G>> 

       extends InternalHierarchicalDagWriter<GenericTaskGraphNode<T,ST,ET,TG,G>,
	                                         GenericTaskGraphEdge<T,ST,ET,TG,G>> {

}
