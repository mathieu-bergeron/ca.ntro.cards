package ca.ntro.core.json;

import ca.ntro.core.reflection.object_graph.LocalHeap;
import ca.ntro.core.reflection.object_graph.ObjectGraphNtro;
import ca.ntro.core.reflection.object_graph.ObjectGraphStructureNtro;

public class   JsonObjectGraphStructure 

       extends ObjectGraphStructureNtro {


	@Override
	protected LocalHeap newLocalHeapInstance(ObjectGraphNtro graph) {
		return new LocalJsonHeap(graph);
	}

}
