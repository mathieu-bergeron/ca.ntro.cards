package ca.ntro.core.reflection.object_graph;

import java.util.List;
import java.util.Map;

import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.EdgeType;
import ca.ntro.core.graphs.common.EdgeTypeNtro;
import ca.ntro.core.path.Path;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

public abstract class GenericObjectNodeStructureNtro<ON extends ObjectNodeNtro> 

       implements     ObjectNodeStructure {

	private ON node;
	private ObjectGraphNtro graph;
	private boolean isStartNode;

	public boolean getIsStartNode() {
		return isStartNode;
	}

	public void setIsStartNode(boolean isStartNode) {
		this.isStartNode = isStartNode;
	}

	public GenericObjectNodeStructureNtro(ON node, ObjectGraphNtro graph, boolean isStartNode) {
		setNode(node);
		setGraph(graph);
		setIsStartNode(isStartNode);
	}

	public ON getNode() {
		return node;
	}

	public void setNode(ON node) {
		this.node = node;
	}

	public ObjectGraphNtro getGraph() {
		return graph;
	}

	public void setGraph(ObjectGraphNtro graph) {
		this.graph = graph;
	}

	@Override
	public Stream<EdgeType> edgeTypes(Direction direction) {
		return new StreamNtro<EdgeType>() {
			@Override
			public void forEach_(Visitor<EdgeType> visitor) throws Throwable {
				if(direction != Direction.FORWARD) {
					return;
				}

				if(node().isUserDefinedObject()){
					
					_visitEdgeTypesForUserDefinedObject(visitor, node().asUserDefinedObject());

				} else if(node().isList()) {
					
					_visitEdgeTypesForList(visitor, node().asList());
					
				} else if(node().isMap()) {

					_visitEdgeTypesForMap(visitor, (Map<String,?>) node().asMap());

				}
			}
		};
	}

	protected void _visitEdgeTypesForList(Visitor<EdgeType> visitor, List<?> list) throws Throwable {
		for(int i = 0; i < list.size(); i++) {
			visitor.visit(new EdgeTypeNtro(Direction.FORWARD, String.valueOf(i)));
		}
	}

	protected void _visitEdgeTypesForMap(Visitor<EdgeType> visitor, Map<String, ?> map) throws Throwable {
		for(String key : map.keySet()) {
			visitor.visit(new EdgeTypeNtro(Direction.FORWARD, key));
		}
		
	}

	protected abstract void _visitEdgeTypesForUserDefinedObject(Visitor<EdgeType> visitor, Object object) throws Throwable;

	@Override
	public Stream<ReferenceEdge> edges(EdgeType edgeType) {
		return new StreamNtro<ReferenceEdge>(){

			@Override
			public void forEach_(Visitor<ReferenceEdge> visitor) throws Throwable {
				if(edgeType.direction() != Direction.FORWARD) {
					return;
				}

				if(node().isList()) {
					
					_visitEdgesByTypeForList(edgeType, node().asList(), visitor);

					
				} else if(node().isMap()) {

					_visitEdgesByTypeForMap(edgeType, node().asMap(), visitor);

					
				}else if(node().isUserDefinedObject()){

					_visitEdgesByTypeForUserDefinedObject(edgeType, node().asUserDefinedObject(), visitor);
				}
				
			}
		};
	}

	protected void _visitEdgesByTypeForList(EdgeType edgeType, 
			                              List<?> list, 
			                              Visitor<ReferenceEdge> visitor) throws Throwable {

		String attributeName = edgeType.name().toString();
		
		Integer index = Integer.parseInt(attributeName);

		Object attributeValue = list.get(index);
		
		_visitAttributeEdge(attributeName, attributeValue, visitor);
	}

	protected void _visitAttributeEdge(String attributeName, Object attributeValue, Visitor<ReferenceEdge> visitor) throws Throwable {

		Path attributePath = Path.fromRawPath(this.node().id().toKey().toString());
		attributePath.addName(attributeName);
		
		ObjectGraphStructureNtro graphStructure = (ObjectGraphStructureNtro) getGraph().graphStructure();
		
		ObjectNode toNode = graphStructure.getLocalHeap().findOrCreateNode(getGraph(), attributePath, attributeValue, false);
		ReferenceEdge edge = new ReferenceEdgeNtro(this.node(), attributeName, toNode);
		
		visitor.visit(edge);
	}

	protected void _visitEdgesByTypeForMap(EdgeType edgeType, 
			                             Map<String, ?> map, 
			                             Visitor<ReferenceEdge> visitor) throws Throwable {

		String attributeName = edgeType.name().toString();
		
		Object attributeValue = map.get(attributeName);
		
		_visitAttributeEdge(attributeName, attributeValue, visitor);
		
	}

	protected abstract void _visitEdgesByTypeForUserDefinedObject(EdgeType edgeType, 
			                                                      Object object, 
			                                                      Visitor<ReferenceEdge> visitor) throws Throwable;

	@Override
	public boolean isStartNode() {
		return getIsStartNode();
	}


	@Override
	public ObjectNode node() {
		return getNode();
	}

	@Override
	public ObjectGraph parentGraph() {
		return getGraph();
	}

}
