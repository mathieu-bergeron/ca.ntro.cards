package ca.ntro.core.reflection;

import ca.ntro.core.reflection.object_graph.LocalHeap;
import ca.ntro.core.reflection.object_graph.ObjectGraphNtro;
import ca.ntro.core.reflection.object_graph.ObjectGraphStructureNtro;

public class ObjectGraphStructureJdk extends ObjectGraphStructureNtro {

	public ObjectGraphStructureJdk() {
		super();
	}

	public ObjectGraphStructureJdk(Object o, ObjectGraphNtro graph) {
		super(o, graph);
	}

	@Override
	protected LocalHeap newLocalHeapInstance(ObjectGraphNtro graph) {
		return new LocalHeapJdk(graph);
	}


}
