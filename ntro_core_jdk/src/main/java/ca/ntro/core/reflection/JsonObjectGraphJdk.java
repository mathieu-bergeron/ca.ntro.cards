package ca.ntro.core.reflection;

import ca.ntro.core.json.JsonObjectGraphNtro;
import ca.ntro.core.reflection.object_graph.ObjectBuilderNtro;
import ca.ntro.core.reflection.object_graph.ObjectGraphNtro;
import ca.ntro.core.reflection.object_graph.ObjectNode;

public class JsonObjectGraphJdk extends JsonObjectGraphNtro {

	public JsonObjectGraphJdk(Object o) {
		super(o);
	}

	public JsonObjectGraphJdk(Object o, String graphName) {
		super(o, graphName);
	}

	@Override
	protected ObjectBuilderNtro newObjectBuilder(ObjectGraphNtro graph) {
		return new ObjectBuilderJdk(graph);
	}



}
