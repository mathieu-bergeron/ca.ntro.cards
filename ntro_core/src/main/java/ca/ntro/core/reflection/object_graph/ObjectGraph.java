package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraph;
import ca.ntro.core.json.JsonObject;
import ca.ntro.core.reflection.object_graph.revisions.Revisions;

public interface ObjectGraph
      
       extends   GenericDirectedGraph<ObjectNode, 
                                      ReferenceEdge, 
                                      ObjectGraphSearchOptions,
                                      ObjectGraphWriterOptions> {
	
	ObjectNode startNode();

	ObjectNode findNode(Object o);
	
	JsonObject buildJsonObject();
	Object buildObject();
	
	boolean graphEquals(ObjectGraph other);

	Revisions revisionsTo(ObjectGraph target);
	void applyRevisions(Revisions revisions);

}
