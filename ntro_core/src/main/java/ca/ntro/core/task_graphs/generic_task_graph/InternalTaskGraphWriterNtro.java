package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.graph_writer.NodeSpecNtro;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagWriterOptions;
import ca.ntro.core.graphs.hierarchical_dag.InternalHierarchicalDagWriterNtro;

public class InternalTaskGraphWriterNtro<T  extends GenericTask<T,ST,ET,TG,G>,
                                          ST extends GenericSimpleTask<T,ST,ET,TG,G>,
                                          ET extends GenericExecutableTask<T,ST,ET,TG,G>,
                                          TG extends GenericTaskGroup<T,ST,ET,TG,G>,
                                          G  extends GenericTaskGraph<T,ST,ET,TG,G>> 

       extends InternalHierarchicalDagWriterNtro<GenericTaskGraphNode<T,ST,ET,TG,G>,
	                                             GenericTaskGraphEdge<T,ST,ET,TG,G>> 

       implements InternalTaskGraphWriter<T,ST,ET,TG,G> {

	@Override
	protected void adjustNodeSpecAttributes(GenericTaskGraphNode<T,ST,ET,TG,G> node, 
			                                HierarchicalDagWriterOptions options,
			                                NodeSpecNtro nodeSpec) {

		super.adjustNodeSpecAttributes(node, options, nodeSpec);
		
		
		if(node.task().isTaskGroup()) {

			nodeSpec.setColor("gray");

		}else {

			nodeSpec.setColor("white");

		}

		nodeSpec.setLabel(node.task().id());

	}

}
