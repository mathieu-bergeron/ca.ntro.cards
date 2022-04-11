package ca.ntro.core.reflection.object_graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ntro.core.json.JsonObject;

public abstract class GenericObjectBuilderNtro<O> {

	private ObjectGraphNtro graph;
	private Map<String, Object> localHeap = new HashMap<>();


	public ObjectGraphNtro getGraph() {
		return graph;
	}

	public void setGraph(ObjectGraphNtro graph) {
		this.graph = graph;
	}

	public Map<String, Object> getLocalHeap() {
		return localHeap;
	}

	public void setLocalHeap(Map<String, Object> localHeap) {
		this.localHeap = localHeap;
	}

	public GenericObjectBuilderNtro() {
	}

	public GenericObjectBuilderNtro(ObjectGraphNtro graph) {
		setGraph(graph);
	}
	
	public void initialize() {
	}
	
	public O build() {
		ObjectNode startNode = getGraph().startNodes().findFirst(n -> true);

		return objectFromNode(startNode);
	}
	
	protected O objectFromNode(ObjectNode node) {
		String objectId = node.id().toKey().toString();

		if(getLocalHeap().containsKey(objectId)) {
			return (O) newObjectReference(objectId, getLocalHeap().get(objectId));
		}

		O object = emptyObjectFromNode(node);
		getLocalHeap().put(node.id().toKey().toString(), object);
		
		node.edges().forEach(edge -> {
			
			String attributeName = edge.type().name().toString();
			
			if(!attributeName.equals(JsonObject.REFERENCE_KEY)
					&& !attributeName.equals(JsonObject.TYPE_KEY)) {

				Object attributeValue = valueFromNode(edge.to());
				setAttribute(object, attributeName, attributeValue);
			}
		});
		
		return object;
	}

	protected Object valueFromNode(ObjectNode node) {
		String objectId = node.id().toKey().toString();
		if(getLocalHeap().containsKey(objectId)) {
			return newObjectReference(objectId, getLocalHeap().get(objectId));
		}
		
		if(isReferenceNode(node)) {
			return valueFromNode(getReferencedNode(node));
		}
		
		Object value = null;
		
		if(node.isUserDefinedObject()) {

			value = objectFromNode(node);
			
		} else if(node.isList()){
			
			value = buildList(node);

		}else if(node.isMap()) {

			value = buildMap(node);

		}else {

			value = node.object();

		}

		getLocalHeap().put(node.id().toKey().toString(), value);
		
		return value;
	}

	protected boolean isReferenceNode(ObjectNode node) {
		boolean isReferenceNode = false;
		
		if(node.isMap()
				&& node.asMap().containsKey(JsonObject.REFERENCE_KEY)) {
			
			isReferenceNode = true;
		}
		
		return isReferenceNode;
	}

	protected ObjectNode getReferencedNode(ObjectNode objectReferenceNode) {

		String referencedObjectId = (String) objectReferenceNode.asMap().get(JsonObject.REFERENCE_KEY);
		
		ObjectNode referencedNode = getGraph().findNode(referencedObjectId);

		return referencedNode;
	}
	
	
	protected abstract Object newObjectReference(String referencedObjetId, Object referencedObject);

	private List<Object> buildList(ObjectNode listNode){
		List<Object> list = new ArrayList<>();

		listNode.edges().forEach(edge -> {
			
			String indexName = edge.type().name().toString();
			int index = Integer.valueOf(indexName);
			Object item = valueFromNode(edge.to());
			
			list.add(index, item);
		});
		
		return list;
	}

	private Map<String,Object> buildMap(ObjectNode mapNode){
		Map<String,Object> map = new HashMap<>();

		mapNode.edges().forEach(edge -> {
			
			String key = edge.type().name().toString();
			Object value = valueFromNode(edge.to());
			
			map.put(key, value);
		});
		
		return map;
	}
	

	protected abstract O emptyObjectFromNode(ObjectNode node);
	protected abstract void setAttribute(O object, String attributeName, Object attributeValue);


}
