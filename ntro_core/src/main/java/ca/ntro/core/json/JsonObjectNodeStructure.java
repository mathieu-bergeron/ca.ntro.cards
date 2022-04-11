package ca.ntro.core.json;

import java.util.Map;

import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.EdgeType;
import ca.ntro.core.graphs.common.EdgeTypeNtro;
import ca.ntro.core.reflection.object_graph.GenericObjectNodeStructureNtro;
import ca.ntro.core.reflection.object_graph.ObjectNodeStructure;
import ca.ntro.core.reflection.object_graph.ReferenceEdge;
import ca.ntro.core.stream.Visitor;

public class JsonObjectNodeStructure 

       extends GenericObjectNodeStructureNtro<JsonObjectNodeNtro>

       implements ObjectNodeStructure {
	

	public JsonObjectNodeStructure(JsonObjectNodeNtro objectNode, JsonObjectGraphNtro graph, boolean isStartNode) {
		super(objectNode, graph, isStartNode);
	}

	@Override
	protected void _visitEdgeTypesForUserDefinedObject(Visitor<EdgeType> visitor, 
			                                           Object object) throws Throwable {
		
		Map<String, Object> jsonObject = (Map<String,Object>) object;
		
		for(String attributeName : jsonObject.keySet()) {

			if(!attributeName.equals(JsonObject.REFERENCE_KEY)
					&& !attributeName.equals(JsonObject.TYPE_KEY)) {

				visitor.visit(new EdgeTypeNtro(Direction.FORWARD, attributeName));
			}
		}
	}

	@Override
	protected void _visitEdgesByTypeForUserDefinedObject(EdgeType edgeType, 
			                                             Object object, 
			                                             Visitor<ReferenceEdge> visitor) throws Throwable {

		Map<String, Object> jsonObject = (Map<String,Object>) object;
		
		String attributeName = edgeType.name().toString();
		
		Object attributeValue = jsonObject.get(attributeName);

		_visitAttributeEdge(attributeName, attributeValue, visitor);

	}

}
