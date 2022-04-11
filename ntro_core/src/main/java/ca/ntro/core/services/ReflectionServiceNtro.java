package ca.ntro.core.services;

import java.util.List;
import java.util.Map;

import ca.ntro.core.graphs.generics.graph.GraphId;
import ca.ntro.core.json.JsonObject;
import ca.ntro.core.json.JsonObjectGraphNtro;
import ca.ntro.core.reflection.object_graph.ObjectGraph;
import ca.ntro.core.reflection.object_graph.revisions.Revisions;
import ca.ntro.core.stream.Stream;

public abstract class ReflectionServiceNtro implements ReflectionService {

	
	@Override
	public Object objectFromGraph(ObjectGraph objectGraph) {
		return objectGraph.buildObject();
	}

	@Override
	public JsonObject jsonObjectFromGraph(ObjectGraph objectGraph) {
		return objectGraph.buildJsonObject();
	}

	@Override
	public JsonObject toJsonObject(Object o) {
		ObjectGraph graph = graphFromObject(o);
		return jsonObjectFromGraph(graph);
	}

	@Override
	public Object fromJsonObject(JsonObject jsonObject) {
		ObjectGraph graph = graphFromJsonObject(jsonObject);
		return objectFromGraph(graph);
	}

	@Override
	public boolean isList(Object object) {
		return object instanceof List;
	}

	@Override
	public boolean isMap(Object object) {
		return object instanceof Map;
	}

	@Override
	public boolean isUserDefinedObject(Object object) {
		return !isList(object)
				&& !isMap(object)
				&& !isSimpleValue(object);
	}

	@Override
	public boolean isSimpleValue(Object object) {
		return isNull(object)
				|| isBoolean(object)
				|| isNumber(object)
				|| isString(object);
	}

	@Override
	public boolean isNull(Object object) {
		return object == null;
	}

	@Override
	public boolean isBoolean(Object object) {
		return object != null
				&& (object instanceof Boolean 
						|| object.getClass().equals(boolean.class));
	}

	@Override
	public boolean isNumber(Object object) {
		return  object != null
				&& (object instanceof Character 
						|| object instanceof Integer 
						|| object instanceof Long 
						|| object instanceof Float 
						|| object instanceof Double
						|| object.getClass().equals(char.class)
						|| object.getClass().equals(int.class)
						|| object.getClass().equals(long.class)
						|| object.getClass().equals(float.class)
						|| object.getClass().equals(double.class));
	}

	@Override
	public boolean isString(Object object) {
		return object != null
				&& object instanceof String;
	}

	@Override
	public boolean asBoolean(Object object) {
		if(object == null) return false;

		if(object.equals(true)) return true;
		
		return false;
	}

	@Override
	public String asString(Object object) {
		if(object == null) return "null";

		return object.toString();
	}


	@Override
	public Object asUserDefinedObject(Object object) {
		return object;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <V> V asUserDefinedObject(Object object, Class<V> _class) {
		return (V) object;
	}

	@Override
	public List<Object> asList(Object object) {
		return (List<Object>) object;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <I> List<I> asList(Object object, Class<I> itemClass) {
		return (List<I>) object;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> asMap(Object object) {
		return (Map<String,Object>) object;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <V> Map<String, V> asMap(Object object, Class<V> valueClass) {
		return (Map<String,V>) object;
	}

	@Override
	public Object clone(Object o) {
		ObjectGraph graph = graphFromObject(o);

		return graph.buildObject();
	}

	@Override
	public boolean graphEquals(Object a, Object b) {
		ObjectGraph graphA = graphFromObject(a);
		ObjectGraph graphB = graphFromObject(b);
		
		return graphA.graphEquals(graphB);
	}

	@Override
	public boolean graphEquals(Object a, JsonObject b) {
		ObjectGraph graphA = graphFromObject(a);
		ObjectGraph graphB = graphFromJsonObject(b);
		
		return graphA.graphEquals(graphB);
	}

	@Override
	public boolean graphEquals(JsonObject a, Object b) {
		ObjectGraph graphA = graphFromJsonObject(a);
		ObjectGraph graphB = graphFromObject(b);
		
		return graphA.graphEquals(graphB);
	}

	@Override
	public boolean graphEquals(JsonObject a, JsonObject b) {
		ObjectGraph graphA = graphFromJsonObject(a);
		ObjectGraph graphB = graphFromJsonObject(b);
		
		return graphA.graphEquals(graphB);
	}

	@Override
	public Revisions revisionsFromTo(Object source, Object target) {
		ObjectGraph sourceGraph = graphFromObject(source);
		ObjectGraph targetGraph = graphFromObject(target);

		return sourceGraph.revisionsTo(targetGraph);
	}
}
