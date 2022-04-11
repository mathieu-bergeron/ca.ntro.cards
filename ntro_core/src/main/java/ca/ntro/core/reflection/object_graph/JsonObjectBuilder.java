package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.json.JsonObject;

public class JsonObjectBuilder extends GenericObjectBuilderNtro<JsonObject> {
	
	
	public JsonObjectBuilder() {
		super();
	}

	public JsonObjectBuilder(ObjectGraphNtro graph) {
		super(graph);
	}

	public JsonObject emptyObjectFromNode(ObjectNode node) {
		JsonObject jsonObject = Ntro.json().newJsonObject();
		
		jsonObject.put(JsonObject.TYPE_KEY, Ntro.reflection().simpleName(node.type()));

		return jsonObject;
	}

	@Override
	protected void setAttribute(JsonObject object, String attributeName, Object attributeValue) {
		object.put(attributeName, attributeValue);
	}

	@Override
	protected Object newObjectReference(String referencedObjetId, Object referencedObject) {
		JsonObject objectReference = Ntro.json().newJsonObject();
		
		objectReference.put(JsonObject.REFERENCE_KEY, referencedObjetId);

		return objectReference;
	}
}
