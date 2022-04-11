package ca.ntro.core.task_graphs.generic_task_graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.generics.graph.SearchStrategy;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagNodeBuilder;
import ca.ntro.core.stream.Stream;

public abstract class GenericTaskNtro<T  extends GenericTask<T,ST,ET,TG,G>,
                                       ST extends GenericSimpleTask<T,ST,ET,TG,G>,
                                       ET extends GenericExecutableTask<T,ST,ET,TG,G>,
                                       TG extends GenericTaskGroup<T,ST,ET,TG,G>,
                                       G  extends GenericTaskGraph<T,ST,ET,TG,G>> 

       implements GenericTask<T,ST,ET,TG,G> {
	
	private String id;
	private GenericTaskGraphNtro<T,ST,ET,TG,G> graph;
	private Set<GenericTaskGraphNodeNtro<T,ST,ET,TG,G>> nodes = new HashSet<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public GenericTaskGraphNtro<T, ST, ET, TG, G> getGraph() {
		return graph;
	}

	public void setGraph(GenericTaskGraphNtro<T, ST, ET, TG, G> graph) {
		this.graph = graph;
	}

	public Set<GenericTaskGraphNodeNtro<T,ST,ET,TG,G>> getNodes() {
		return nodes;
	}

	public void setNodes(Set<GenericTaskGraphNodeNtro<T, ST, ET, TG, G>> nodes) {
		this.nodes = nodes;
	}
	
	public void addNode(GenericTaskGraphNodeNtro<T,ST,ET,TG,G> node) {
		getNodes().add(node);
	}

	public Stream<GenericTaskGraphNodeNtro<T,ST,ET,TG,G>> nodes() {
		return Stream.forSet(getNodes());
	}

	
	
	
	@Override
	public String id() {
		return getId();
	}

	@Override
	public G parentGraph() {
		return (G) getGraph();
	}

	@Override
	public boolean isTaskGroup() {
		return false;
	}

	@Override
	public boolean isSimpleTask() {
		return false;
	}

	@Override
	public TG asTaskGroup() {
		return (TG) this;
	}

	@Override
	public ST asSimpleTask() {
		return (ST) this;
	}

	@Override
	public void addPreviousTask(T previousTask) {
		nodes().forEach(toNode -> {

			String fromNodeId = previousTask.id();

			if(toNode.hasParent()) {
				fromNodeId = toNode.parent().id().toKey().toString() + "_" + fromNodeId;
			}

			HierarchicalDagNodeBuilder<GenericTaskGraphNode<T, ST, ET, TG, G>, GenericTaskGraphEdge<T, ST, ET, TG, G>> fromNodeBuilder = getGraph().getHdagBuilder().findNode(fromNodeId);
			
			if(fromNodeBuilder != null) {
				
				HierarchicalDagNodeBuilder<GenericTaskGraphNode<T, ST, ET, TG, G>, GenericTaskGraphEdge<T, ST, ET, TG, G>> toNodeBuilder = (HierarchicalDagNodeBuilder<GenericTaskGraphNode<T, ST, ET, TG, G>, GenericTaskGraphEdge<T, ST, ET, TG, G>>) toNode.getNodeStructure();
				fromNodeBuilder.addEdge("", toNodeBuilder);

			}
		});
	}

	@Override
	public void addNextTask(T nextTask) {
		nodes().forEach(fromNode -> {

			String toNodeId = nextTask.id();

			if(fromNode.hasParent()) {
				toNodeId = fromNode.parent().id().toKey().toString() + "_" + toNodeId;
			}

			HierarchicalDagNodeBuilder<GenericTaskGraphNode<T, ST, ET, TG, G>, GenericTaskGraphEdge<T, ST, ET, TG, G>> toNodeBuilder = getGraph().getHdagBuilder().findNode(toNodeId);
			
			if(toNodeBuilder != null) {
				HierarchicalDagNodeBuilder<GenericTaskGraphNode<T, ST, ET, TG, G>, GenericTaskGraphEdge<T, ST, ET, TG, G>> fromNodeBuilder = (HierarchicalDagNodeBuilder<GenericTaskGraphNode<T, ST, ET, TG, G>, GenericTaskGraphEdge<T, ST, ET, TG, G>>) fromNode.getNodeStructure();
				fromNodeBuilder.addEdge("", toNodeBuilder);
			}
		});
	}

	protected TaskGraphSearchOptionsNtro neighborSearchOptions(Direction direction) {

		TaskGraphSearchOptionsNtro neighborOptions = new TaskGraphSearchOptionsNtro();

		neighborOptions.internal().setSearchStrategy(SearchStrategy.DEPTH_FIRST_SEARCH);
		neighborOptions.internal().setDirections(new Direction[] {direction});
		neighborOptions.internal().setMaxDistance(1);
		neighborOptions.internal().setSortEdgesByName(false);

		return neighborOptions;
	}

	@Override
	public Stream<T> previousTasks() {
		return reachableTasks(neighborSearchOptions(Direction.BACKWARD));
	}

	@Override
	public Stream<T> nextTasks() {
		return reachableTasks(neighborSearchOptions(Direction.FORWARD));
	}

	@Override
	public Stream<T> parentTasks() {
		return reachableTasks(neighborSearchOptions(Direction.UP));
	}

	@Override
	public Stream<T> reachableTasks() {
		return reachableTasks(new TaskGraphSearchOptionsNtro());
	}

	@Override
	public Stream<T> reachableTasks(TaskGraphSearchOptions options) {
		Map<String, T> tasks = new HashMap<>();

		nodes().forEach(node -> {
			
			node.reachableNodes(options).forEach(visitedNode -> {
				
				T task = visitedNode.node().task();
				
				tasks.put(task.id(), task);
			});
		});
		
		return Stream.forMapValues(tasks);
	}
}
	
