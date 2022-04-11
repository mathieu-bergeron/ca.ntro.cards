package ca.ntro.core.reflection;

import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.reflection.object_graph.LocalHeap;
import ca.ntro.core.reflection.object_graph.LocalHeapNtro;
import ca.ntro.core.reflection.object_graph.ObjectGraphNtro;
import ca.ntro.core.reflection.object_graph.ObjectNode;

public class LocalHeapJdk extends LocalHeapNtro {

	public LocalHeapJdk(ObjectGraphNtro graph) {
		super(graph);
	}

	@Override
	protected ObjectNode createNode(ObjectGraphNtro graph, LocalHeap localHeap, Object object, NodeId nodeId, boolean isStartNode) {
		
		return new ObjectNodeJdk(graph, localHeap, object, nodeId, isStartNode);
	}

}
