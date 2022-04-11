package ca.ntro.core.reflection.object_graph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ca.ntro.core.edit_distance.EditDistance;
import ca.ntro.core.edit_distance.edits.Edit;
import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedNodeNtro;
import ca.ntro.core.graphs.generics.graph.WalkId;
import ca.ntro.core.graphs.generics.graph.WalkIdNtro;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.reflection.object_graph.revisions.DeleteNtro;
import ca.ntro.core.reflection.object_graph.revisions.InsertNtro;
import ca.ntro.core.reflection.object_graph.revisions.Revision;
import ca.ntro.core.reflection.object_graph.revisions.Revisions;
import ca.ntro.core.reflection.object_graph.revisions.RevisionsNtro;
import ca.ntro.core.reflection.object_graph.revisions.UpdateNtro;
import ca.ntro.core.stream.Stream;

public abstract class ObjectNodeNtro 

	   extends        GenericDirectedNodeNtro<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions>

	   implements     GenericObjectNode, 
	                  ObjectNode,
	                  ObjectNodeSimpleValue {
	
	private Object object;
	private LocalHeap localHeap;
	private ObjectGraphNtro graph;

	public ObjectGraphNtro getGraph(){
		return graph;
	}

	public void setGraph(ObjectGraphNtro graph) {
		this.graph = graph;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public LocalHeap getLocalHeap() {
		return localHeap;
	}

	public void setLocalHeap(LocalHeap localHeap) {
		this.localHeap = localHeap;
	}

	public Object getObject() {
		return object;
	}

	public ObjectNodeNtro(ObjectGraphNtro graph, LocalHeap localHeap, Object object, NodeId nodeId) {
		super(nodeId);
		setGraph(graph);
		setLocalHeap(localHeap);
		setObject(object);
	}



	@Override
	public String label() {
		StringBuilder builder = new StringBuilder();

		if(isUserDefinedObject()) {
			
			builder.append(Ntro.reflection().simpleName(type()));
		
		}else if(isList()) {

			builder.append("List");

		}else if(isMap()) {

			builder.append("Map");

		}else if(isSimpleValue()) {

			if(asSimpleValue().isString()) {
				
				builder.append('"');
				builder.append(getObject());
				builder.append('"');

			} else {

				builder.append(getObject());
			}
		}

		return builder.toString();
	}

	@Override
	public Object object() {
		return getObject();
	}

	@Override
	public Class<?> type() {
		Class<?> type = null;
		Object currentObject = getObject();

		if(currentObject != null) {

			type = currentObject.getClass();

		}else {

			type = Void.class;
		}
		
		return type;
	}

	@Override
	public boolean isList() {
		return Ntro.reflection().isList(getObject());
	}


	@Override
	public boolean isMap() {
		return Ntro.reflection().isMap(getObject());
	}


	@Override
	public boolean isUserDefinedObject() {
		return Ntro.reflection().isUserDefinedObject(getObject());
	}

	@Override
	public boolean isSimpleValue() {
		return Ntro.reflection().isSimpleValue(getObject());
	}

	@Override
	public ObjectNodeSimpleValue asSimpleValue() {
		return (ObjectNodeSimpleValue) this;
	}

	@Override
	public boolean isNull() {
		return Ntro.reflection().isNull(getObject());
	}

	@Override
	public boolean isBoolean() {
		return Ntro.reflection().isBoolean(getObject());
	}

	@Override
	public boolean isString() {
		return Ntro.reflection().isString(getObject());
	}

	@Override
	public boolean asBoolean() {
		return Ntro.reflection().asBoolean(getObject());
	}

	@Override
	public String asString() {
		return Ntro.reflection().asString(getObject());
	}

	@Override
	public List<Object> asList() {
		return Ntro.reflection().asList(getObject());
	}

	@Override
	public <I> List<I> asList(Class<I> itemClass) {
		return Ntro.reflection().asList(getObject(), itemClass);
	}

	@Override
	public <V> Map<String, V> asMap(Class<V> valueClass) {
		return Ntro.reflection().asMap(getObject(), valueClass);
	}

	@Override
	public Map<String, Object> asMap() {
		return Ntro.reflection().asMap(getObject());
	}

	@Override
	public Object asUserDefinedObject() {
		return Ntro.reflection().asUserDefinedObject(object());
	}

	@Override
	public <V> V asUserDefinedObject(Class<V> _class) {
		return Ntro.reflection().asUserDefinedObject(getObject(), _class);
	}

	@Override
	public boolean isNumber() {
		return Ntro.reflection().isNumber(getObject());
	}

	@Override
	public char asChar() {
		return Ntro.reflection().asChar(getObject());
	}

	@Override
	public int asInt() {
		return Ntro.reflection().asInt(getObject());
	}

	@Override
	public long asLong() {
		return Ntro.reflection().asLong(getObject());
	}

	@Override
	public float asFloat() {
		return Ntro.reflection().asFloat(getObject());
	}

	@Override
	public double asDouble() {
		return Ntro.reflection().asDouble(getObject());
	}

	@Override
	public Object value() {
		return getObject();
	}
	
	@Override 
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		if(isString()) {
			builder.append('"');
		}
		
		builder.append(asSimpleValue().asString());

		if(isString()) {
			builder.append('"');
		}
		
		
		return builder.toString();
		
	}

	@Override
	public boolean graphEquals(ObjectNode otherNode) {
		Set<String> visitedNodes = new HashSet<>();
		visitedNodes.add(id().toKey().toString());
		
		Set<String> otherVisitedNodes = new HashSet<>();
		otherVisitedNodes.add(otherNode.id().toKey().toString());
		
		return graphEquals(visitedNodes,
				           otherVisitedNodes,
				           (ObjectNodeNtro) otherNode);
	}

	public boolean graphEquals(Set<String> visitedNodes, 
			                   Set<String> otherVisitednodes, 
			                   ObjectNodeNtro otherNode) {

		boolean graphEquals = false;
		
		if(object() == null) {

			graphEquals = otherNode.object() == null;

		}else if(otherNode.object() == null) {
			
			graphEquals = object() == null;
			
		} else if(isSimpleValue()
				&& otherNode.isSimpleValue()) {

			graphEquals = object().equals(otherNode.object());

		}else {
			
			graphEquals = graphEqualsStep(visitedNodes, otherVisitednodes, otherNode) 
					&& otherNode.graphEqualsStep(otherVisitednodes, visitedNodes, this);
		}

		return graphEquals;
	}

	public boolean graphEqualsStep(Set<String> visitedNodes, 
			                       Set<String> otherVisitedNodes,
			                       ObjectNodeNtro otherNode) {

		return edges().ifAll(edge -> {
			String toId = edge.to().id().toKey().toString();
			if(visitedNodes.contains(toId)) {
				return true;
			}
			visitedNodes.add(toId);

			WalkId walk = new WalkIdNtro();
			walk.add(edge.type());
			
			return otherNode.walk(walk).ifSome(walkInProgress -> {
				if(!walkInProgress.remainingWalk().isEmpty()
						|| !walkInProgress.hasCurrentNode()) {
					return false;
				}
				
				ObjectNodeNtro to = (ObjectNodeNtro) edge.to();
				ObjectNodeNtro otherTo = (ObjectNodeNtro) walkInProgress.currentNode();
				
				return to.graphEquals(visitedNodes, otherVisitedNodes, otherTo);
			});
		});
	}

	@Override
	public Revisions revisionsTo(ObjectNode target) {
		Set<String> visitedNodes = new HashSet<>();
		visitedNodes.add(id().toKey().toString());
		
		Set<String> targetVisitedNodes = new HashSet<>();
		targetVisitedNodes.add(target.id().toKey().toString());
		
		return revisionsTo(visitedNodes,
				           targetVisitedNodes,
				           (ObjectNodeNtro) target);
	}

	public Revisions revisionsTo(Set<String> visitedNodes, 
			                                Set<String> targetVisitedNodes,
			                                ObjectNodeNtro target) {
		
		RevisionsNtro revisions = new RevisionsNtro();


		if(isNull()
				&& !target.isNull()) {

			revisions.addAll(target.asRevisions(targetVisitedNodes));

		}else if(!isNull()
				&& target.isNull()) {
			
			revisions.add(new UpdateNtro(id().toKey().toString(), null));

		}else if(!isNull()
				&& !target.isNull()
				&& !type().equals(target.type())) {

			revisions.addAll(target.asRevisions(targetVisitedNodes));

		}else if(!isNull()
				&& !target.isNull()
				&& type().equals(target.type())
				&& isSimpleValue()
				&& target.isSimpleValue()
				&& !object().equals(target.object())) {

			revisions.add(new UpdateNtro(id().toKey().toString(), target.object()));

		}else if(!isNull()
				&& !target.isNull()
				&& isList()
				&& target.isList()) {
			
			Stream<Edit> edits = EditDistance.editSequence(asList(), target.asList());
			edits.forEach(edit -> {
				
				String name = String.valueOf(edit.index());

				if(edit.isDelete()) {

					revisions.add(new DeleteNtro(name));

				}else if(edit.isUpdate()) {

					// TODO: revisions inside the new value
					revisions.add(new UpdateNtro(name, edit.asUpdate().value()));

				}else if(edit.isInsert()) {

					// TODO: revisions inside the new value
					revisions.add(new InsertNtro(name, edit.asInsert().value()));

				}
			});
			
		}else if(!isNull()
				&& !target.isNull()
				&& type().equals(target.type())) {
			
			revisions.addAll(revisionsToStep(visitedNodes, targetVisitedNodes, target));
			
		}

		return revisions;
	}

	public Revisions revisionsToStep(Set<String> visitedNodes, 
			                                    Set<String> targetVisitedNodes,
			                                    ObjectNodeNtro target) {

		RevisionsNtro revisions = new RevisionsNtro();

		edges().forEach(edge -> {
			String toId = edge.to().id().toKey().toString();
			if(visitedNodes.contains(toId)) {
				return;
			}
			visitedNodes.add(toId);

			WalkId walk = new WalkIdNtro();
			walk.add(edge.type());
			
			target.walk(walk).forEach(walkInProgress -> {
				if(!walkInProgress.remainingWalk().isEmpty()) {
					return;
				}
				
				if(walkInProgress.hasCurrentNode()) {

					ObjectNodeNtro to = (ObjectNodeNtro) edge.to();
					ObjectNodeNtro otherTo = (ObjectNodeNtro) walkInProgress.currentNode();
					
					revisions.addAll(to.revisionsTo(visitedNodes, targetVisitedNodes, otherTo));

				}else {

					revisions.add(new DeleteNtro(edge.name().toString()));
				}
			});
		});
		
		target.edges().forEach(edge -> {
			ObjectNodeNtro to = (ObjectNodeNtro) edge.to();
			String toId = to.id().toKey().toString();
			if(targetVisitedNodes.contains(toId)) {
				return;
			}
			targetVisitedNodes.add(toId);

			WalkId walk = new WalkIdNtro();
			walk.add(edge.type());
			
			walk(walk).forEach(walkInProgress -> {
				if(!walkInProgress.remainingWalk().isEmpty()) {
					return;
				}
				
				if(!walkInProgress.hasCurrentNode()) {

					revisions.add(new InsertNtro(edge.to().id().toKey().toString(), to.asRevisionValue()));
					revisions.addAll(to.asRevisions(targetVisitedNodes));
				}
			});
		});

		return revisions;
	
	}

	public Revisions asRevisions(Set<String> visitedNodes) {
		RevisionsNtro revisions = new RevisionsNtro();
		
		if(isUserDefinedObject()) {

			revisions.addAll(edgeRevisions(visitedNodes, to -> {
				return new UpdateNtro(to.id().toKey().toString(), to.asRevisionValue());
			}));

		}else {

			revisions.addAll(edgeRevisions(visitedNodes, to -> {
				return new InsertNtro(to.id().toKey().toString(), to.asRevisionValue());
			}));
		}

		return revisions;
	}

	private interface RevisionFactory {
		Revision createRevision(ObjectNodeNtro objectNodeNtro);
	}

	private Revisions edgeRevisions(Set<String> visitedNodes, RevisionFactory factory) {
		RevisionsNtro revisions = new RevisionsNtro();

		edges().forEach(edge -> {
			if(visitedNodes.contains(edge.to().id().toKey().toString())) {
				return;
			}
			visitedNodes.add(edge.to().id().toKey().toString());
			
			ObjectNodeNtro to = (ObjectNodeNtro) edge.to();

			revisions.add(factory.createRevision(to));

			revisions.addAll(to.asRevisions(visitedNodes));
		});

		return revisions;
	}
	
	public Object asRevisionValue() {
		Object result = null;
		
		if(isSimpleValue()) {

			result = object();

		}else if(isUserDefinedObject()) {

			result = Ntro.factory().newInstance(type());

		}else if(isMap()) {
			
			result = new HashMap<>();
			
		}else if(isList()) {
			
			result = new ArrayList<>();

		}

		return result;
	}

	@Override
	public void applyRevision(Revision revision) {
		String edgeName = revision.name();
		
		if(isUserDefinedObject()) {
			
			applyRevisionToUserDefinedObject(asUserDefinedObject(), edgeName, revision);
			
		}else if(isList()) {

			applyRevisionToList((List<Object>) asList(), Integer.valueOf(edgeName), revision);
			
		}else if(isMap()) {

			applyRevisionToMap((Map<String, Object>) asMap(), edgeName, revision);

		}
		
	}

	protected abstract void applyRevisionToUserDefinedObject(Object object, String attributeName, Revision revision);

	private void applyRevisionToList(List<Object> list, int index, Revision revision) {

		if(revision.isDelete()) {

			list.remove(index);

		}else if(revision.isUpdate()) {

			list.set(index, revision.asUpdate().value());
			
		}else if(revision.isInsert()) {

			list.add(index, revision.asInsert().value());
		}
	}

	private void applyRevisionToMap(Map<String, Object> map, String key, Revision revision) {

		if(revision.isDelete()) {

			map.remove(key);

		}else if(revision.isUpdate()) {

			map.put(key, revision.asUpdate().value());
			
		}else if(revision.isInsert()) {

			map.put(key, revision.asInsert().value());
		}
	}

	

}
