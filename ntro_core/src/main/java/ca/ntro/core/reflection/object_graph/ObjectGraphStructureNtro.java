package ca.ntro.core.reflection.object_graph;


import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.path.Path;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

public abstract class ObjectGraphStructureNtro implements ObjectGraphStructure {

	private ObjectGraphNtro graph;
	private Object startObject;
	private LocalHeap localHeap;

	public ObjectGraphStructureNtro(Object o, ObjectGraphNtro graph) {
		setGraph(graph);
		setStartObject(o);
		localHeap = newLocalHeapInstance(graph);
	}

	public ObjectGraphStructureNtro() {
	}

	public Object getStartObject() {
		return startObject;
	}

	public void setStartObject(Object startObject) {
		this.startObject = startObject;
	}

	public LocalHeap getLocalHeap() {
		return localHeap;
	}

	public void setLocalHeap(LocalHeap localHeap) {
		this.localHeap = localHeap;
	}

	public ObjectGraphNtro getGraph() {
		return graph;
	}

	public void setGraph(ObjectGraphNtro graph) {
		this.graph = graph;
		localHeap = newLocalHeapInstance(graph);
	}

	protected abstract LocalHeap newLocalHeapInstance(ObjectGraphNtro graph);

	@Override
	public Stream<ObjectNode> startNodes() {
		return new StreamNtro<ObjectNode>() {

			@Override
			public void forEach_(Visitor<ObjectNode> visitor) throws Throwable {

				ObjectNode startNode = getLocalHeap().findOrCreateNode(getGraph(), Path.emptyPath(), getStartObject(), true); 

				visitor.visit(startNode);
			}
		};
	}

	@Override
	public String label() {
		return Ntro.reflection().simpleName(getStartObject().getClass());
	}

	public boolean isStartNode(ObjectNode objectNode) {
		return objectNode.object() == startObject;
	}

}
