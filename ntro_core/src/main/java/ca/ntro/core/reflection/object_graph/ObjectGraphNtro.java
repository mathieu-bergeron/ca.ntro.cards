package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphNtro;
import ca.ntro.core.graphs.generics.graph.GraphId;
import ca.ntro.core.json.JsonObject;
import ca.ntro.core.path.ValuePath;
import ca.ntro.core.reflection.object_graph.revisions.Revisions;
import ca.ntro.core.graphs.generics.graph.GenericInternalGraphWriter;

public abstract class ObjectGraphNtro

       extends        GenericDirectedGraphNtro<ObjectNode, 
                                               ReferenceEdge,
                                               ObjectGraphSearchOptions,
                                               ObjectGraphWriterOptions> 

       implements     ObjectGraph {
	
	private ObjectBuilderNtro objectBuilder;
	private JsonObjectBuilder jsonObjectBuilder;

	public ObjectBuilderNtro getObjectBuilder() {
		return objectBuilder;
	}

	public void setObjectBuilder(ObjectBuilderNtro objectBuilder) {
		this.objectBuilder = objectBuilder;
	}

	public JsonObjectBuilder getJsonObjectBuilder() {
		return jsonObjectBuilder;
	}

	public void setJsonObjectBuilder(JsonObjectBuilder jsonObjectBuilder) {
		this.jsonObjectBuilder = jsonObjectBuilder;
	}

	public ObjectGraphNtro(Object o) {
		super();

		initialize(o, o.getClass().getSimpleName());
	}

	public ObjectGraphNtro(Object o, String graphName) {
		super();

		initialize(o, graphName);
	}

	private void initialize(Object o, String graphName) {

		ObjectGraphStructureNtro graphStructure = newObjectGraphStructureInstance();
		graphStructure.setStartObject(o);
		graphStructure.setGraph(this);
		
		setId(GraphId.fromGraphName(graphName));

		setGraphStructure(graphStructure);
		
		setObjectBuilder(newObjectBuilder(this));
		setJsonObjectBuilder(new JsonObjectBuilder(this));
	}

	protected abstract ObjectGraphStructureNtro newObjectGraphStructureInstance();


	@Override
	protected GenericInternalGraphWriter<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions, ObjectGraphWriterOptions> newInternalGraphWriterInstance() {
		return new InternalObjectGraphWriterNtro();
	}

	@Override
	protected ObjectGraphSearchOptions newDefaultSearchOptionsInstance() {
		return new ObjectGraphSearchOptionsNtro();
	}

	@Override
	protected ObjectGraphWriterOptions newDefaultGraphWriterOptionsInstance() {
		return new ObjectGraphWriterOptionsNtro();
	}
	
	protected abstract ObjectBuilderNtro newObjectBuilder(ObjectGraphNtro graph);

	@Override
	public JsonObject buildJsonObject() {
		return getJsonObjectBuilder().build();
	}

	@Override
	public Object buildObject() {
		return getObjectBuilder().build();
	}

	@Override
	public boolean graphEquals(ObjectGraph other) {
		ObjectNode startNode = startNode();
		ObjectNode otherStartNode = other.startNode();
		
		return startNode.graphEquals(otherStartNode);
	}

	@Override
	public ObjectNode findNode(Object o) {
		ObjectNode node = ((ObjectGraphStructureNtro) getGraphStructure()).getLocalHeap().findNodeInHeap(o);
		
		if(node == null) {
			node = nodes().findFirst(n -> n.object() == o);
		}
		
		return node;
	}

	@Override
	public ObjectNode startNode() {
		return startNodes().findFirst(n -> true);
	}

	@Override
	public Revisions revisionsTo(ObjectGraph target) {
		ObjectNode startNode = startNode();
		ObjectNode targetStartNode = target.startNode();
		
		return startNode.revisionsTo(targetStartNode);
	}

	@Override
	public void applyRevisions(Revisions revisions) {
		revisions.forEach(revision -> {
			
			/*
			
			ValuePath valuePath = revision.valuePath();
			ValuePath targetNodePath = valuePath.subPath(0, valuePath.nameCount()-1);

			ObjectNode node = findNode(targetNodePath.toRawPath());

			if(node != null) {
				node.applyRevision(revision);
			}
			
			*/

		});
	}

}
