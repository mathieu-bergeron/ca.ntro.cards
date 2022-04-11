package ca.ntro.core.json;

import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.graphs.generics.graph.WalkId;
import ca.ntro.core.path.Path;
import ca.ntro.core.reflection.object_graph.ObjectGraph;
import ca.ntro.core.reflection.object_graph.ObjectGraphNtro;
import ca.ntro.core.reflection.object_graph.ObjectGraphStructureNtro;
import ca.ntro.core.reflection.object_graph.ObjectNode;

public abstract class   JsonObjectGraphNtro

       extends ObjectGraphNtro
       
       implements ObjectGraph {

	public JsonObjectGraphNtro(Object o) {
		super(o);
	}

	public JsonObjectGraphNtro(Object o, String graphName) {
		super(o, graphName);
	}

	@Override
	protected ObjectGraphStructureNtro newObjectGraphStructureInstance() {
		return new JsonObjectGraphStructure();
	}

	@Override
	public ObjectNode findNode(String rawPathToNode) {

		ObjectNode node = ((JsonObjectGraphStructure) getGraphStructure()).getLocalHeap().findNodeById(rawPathToNode);
		
		if(node == null) {

			WalkId walkId = WalkId.fromPath(Path.fromRawPath(rawPathToNode));

			var lastOfWalk = walk(walkId).findFirst(walkInProgress -> {

						if(walkInProgress.remainingWalk().isEmpty()
								&& walkInProgress.hasCurrentNode()) {
							
							return true;
						}
						
						return false;
			});

			if(lastOfWalk != null) {
				node = lastOfWalk.currentNode();
			}
		}

		return node;
	}

	@Override
	public ObjectNode findNode(NodeId nodeId) {
		return findNode(nodeId.toKey().toString());
	}

}
