package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.graphs.generics.graph.GraphId;
import ca.ntro.core.graphs.generics.graph.VisitedEdge;
import ca.ntro.core.graphs.generics.graph.VisitedNode;
import ca.ntro.core.graphs.generics.graph.WalkId;
import ca.ntro.core.graphs.generics.graph.WalkInProgress;
import ca.ntro.core.json.JsonObject;
import ca.ntro.core.reflection.object_graph.revisions.Revisions;
import ca.ntro.core.stream.Stream;

public class ObjectGraphNull implements ObjectGraph {

	@Override
	public GraphId id() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String label() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(GraphWriter writer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(ObjectGraphWriterOptions options, GraphWriter writer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ObjectGraphSearchOptions defaultSearchOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectGraphWriterOptions defaultGraphWriterOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectNode findNode(String nodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectNode findNode(NodeId nodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<ObjectNode> startNodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<ObjectNode> nodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<ReferenceEdge> edges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<VisitedNode<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions>> visitNodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<VisitedNode<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions>> visitNodes(
			ObjectGraphSearchOptions options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<VisitedEdge<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions>> visitEdges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<VisitedEdge<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions>> visitEdges(
			ObjectGraphSearchOptions options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<WalkInProgress<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions>> walk(WalkId walk) {
		return null;
	}

	@Override
	public Stream<WalkInProgress<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions>> walk(WalkId walk,
			ObjectGraphSearchOptions options) {
		return null;
	}

	@Override
	public JsonObject buildJsonObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object buildObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean graphEquals(ObjectGraph other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ObjectNode findNode(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectNode startNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Revisions revisionsTo(ObjectGraph target) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void applyRevisions(Revisions revisions) {
		// TODO Auto-generated method stub
		
	}
}
