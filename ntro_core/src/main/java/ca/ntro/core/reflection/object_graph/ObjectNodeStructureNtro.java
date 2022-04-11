package ca.ntro.core.reflection.object_graph;


import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.EdgeType;
import ca.ntro.core.graphs.common.EdgeTypeNtro;
import ca.ntro.core.reflection.MethodNameReducer;
import ca.ntro.core.reflection.ReflectionUtils;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.Visitor;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class ObjectNodeStructureNtro 

       extends        GenericObjectNodeStructureNtro<ObjectNodeNtro>

       implements     ObjectNodeStructure {
	

	public ObjectNodeStructureNtro(ObjectNodeNtro node, ObjectGraphNtro graph, boolean isStartNode) {
		super(node, graph, isStartNode);
	}

	protected abstract <R> void _reduceMethodNames(Object object, ResultNtro<R> result, MethodNameReducer<R> reducer);
	protected abstract Stream<String> methodNames(Object object);
	protected abstract Object invokeGetter(Object object, String getterName) throws Throwable;

	
	protected void _visitEdgeTypesForUserDefinedObject(Visitor<EdgeType> visitor, Object object) throws Throwable {
		methodNames(object).forEach_(methodName -> {

			if(ReflectionUtils.isGetterName(methodName) 
					&& ReflectionUtils.isUserDefinedMethod(object, methodName)) {
				
				String attributeName = ReflectionUtils.attributeNameFromGetterName(methodName);

				visitor.visit(new EdgeTypeNtro(Direction.FORWARD, attributeName));
			}
		});
	}


	protected void _visitEdgesByTypeForUserDefinedObject(EdgeType edgeType, 
			                                     Object object, 
			                                     Visitor<ReferenceEdge> visitor) throws Throwable {

		String attributeName = edgeType.name().toString();
		
		String getterName = ReflectionUtils.getterNameFromAttributeName(attributeName);

		Object attributeValue;

		attributeValue = invokeGetter(object, getterName);

		_visitAttributeEdge(attributeName, attributeValue, visitor);
	}


	
}
