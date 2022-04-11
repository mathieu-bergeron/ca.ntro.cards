package ca.ntro.core.reflection;

import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.graphs.generics.graph.GenericNodeStructure;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.reflection.object_graph.GenericObjectNode;
import ca.ntro.core.reflection.object_graph.LocalHeap;
import ca.ntro.core.reflection.object_graph.ObjectGraphNtro;
import ca.ntro.core.reflection.object_graph.ObjectGraphSearchOptions;
import ca.ntro.core.reflection.object_graph.ObjectGraphSearchOptionsNtro;
import ca.ntro.core.reflection.object_graph.ObjectNodeNtro;
import ca.ntro.core.reflection.object_graph.ObjectNode;
import ca.ntro.core.reflection.object_graph.ReferenceEdge;
import ca.ntro.core.reflection.object_graph.revisions.Revision;
import ca.ntro.core.reflection.object_graph.revisions.Revisions;

public class ObjectNodeJdk extends ObjectNodeNtro {
	
	
	private ObjectNodeStructureJdk nodeStructure;

	public ObjectNodeStructureJdk getNodeStructure() {
		return nodeStructure;
	}

	public void setNodeStructure(ObjectNodeStructureJdk nodeStructure) {
		this.nodeStructure = nodeStructure;
	}


	public ObjectNodeJdk(ObjectGraphNtro graph, LocalHeap localHeap, Object object, NodeId nodeId, boolean isStartNode) {
		super(graph, localHeap, object, nodeId);
		
		setNodeStructure(new ObjectNodeStructureJdk((ObjectNodeNtro) this, (ObjectGraphNtro) getGraph(), isStartNode));
	}


	@Override
	protected ObjectGraphSearchOptions defaultSearchOptions() {
		return new ObjectGraphSearchOptionsNtro();
	}

	@Override
	protected GenericNodeStructure<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions> nodeStructure() {
		return getNodeStructure();
	}

	@Override
	protected void applyRevisionToUserDefinedObject(Object object, String attributeName, Revision revision) {
		
		if(revision.isUpdate()) {

			String setterName = ReflectionUtils.setterNameFromAttributeName(attributeName);

			ObjectBuilderJdk.invokeSetter(object, setterName, revision.asUpdate().value());
			
		}else {

			Ntro.throwException("[FATAL] only update is supported on user-defined object");
		}

		
	}





}
