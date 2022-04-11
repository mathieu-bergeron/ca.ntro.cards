package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagWriterOptionsNtro;

public class GenericTaskGraphWriterOptionsNtro<T  extends GenericTask<T,ST,ET,TG,G>,
                                                ST extends GenericSimpleTask<T,ST,ET,TG,G>,
                                                ET extends GenericExecutableTask<T,ST,ET,TG,G>,
                                                TG extends GenericTaskGroup<T,ST,ET,TG,G>,
                                                G  extends GenericTaskGraph<T,ST,ET,TG,G>> 

       extends   HierarchicalDagWriterOptionsNtro
       
       implements GenericTaskGraphWriterOptions<T,ST,ET,TG,G> {

	@Override
	public TG rootTaskGroup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int maxDepth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String overlap() {
		return "false";
	}


}
