package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.path.Path;

public interface LocalHeap {

	ObjectNode findOrCreateNode(ObjectGraphNtro graph, Path attributePath, Object object, boolean isStartNode);
	ObjectNode findNodeInHeap(Object o);

	ObjectNode findNodeById(String nodeId);

}
