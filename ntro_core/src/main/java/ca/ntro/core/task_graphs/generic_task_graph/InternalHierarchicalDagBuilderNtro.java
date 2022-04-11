package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagBuilderNtro;

public class InternalHierarchicalDagBuilderNtro<T  extends GenericTask<T,ST,ET,TG,G>,
                                                ST extends GenericSimpleTask<T,ST,ET,TG,G>,
                                                ET extends GenericExecutableTask<T,ST,ET,TG,G>,
                                                TG extends GenericTaskGroup<T,ST,ET,TG,G>,
                                                G  extends GenericTaskGraph<T,ST,ET,TG,G>>  

       extends   HierarchicalDagBuilderNtro<GenericTaskGraphNode<T,ST,ET,TG,G>,
                                            GenericTaskGraphEdge<T,ST,ET,TG,G>> 

       implements InternalHierarchicalDagBuilder<T,ST,ET,TG,G>{

}
