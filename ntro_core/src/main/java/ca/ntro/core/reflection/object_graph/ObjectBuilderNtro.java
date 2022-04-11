package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.initialization.Ntro;

public abstract class ObjectBuilderNtro extends GenericObjectBuilderNtro<Object> {


	public ObjectBuilderNtro() {
	}
	

	public ObjectBuilderNtro(ObjectGraphNtro graph) {
		super(graph);
	}

	public Object emptyObjectFromNode(ObjectNode node) {

		Class<?> _class = node.type();
		
		Object object = Ntro.factory().newInstance(_class);
		
		return object;
	}

	@Override
	protected Object newObjectReference(String referencedObjetId, Object referencedObject) {
		return referencedObject;
	}

}
