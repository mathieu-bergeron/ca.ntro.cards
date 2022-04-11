package ca.ntro.core.services;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import ca.ntro.core.graph_writer.ClusterAlreadyAddedException;
import ca.ntro.core.graph_writer.ClusterNotFoundException;
import ca.ntro.core.graph_writer.EdgeSpec;
import ca.ntro.core.graph_writer.GraphItemSpec;
import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.graph_writer.GraphWriterException;
import ca.ntro.core.graph_writer.NodeAlreadyAddedException;
import ca.ntro.core.graph_writer.NodeNotFoundException;
import ca.ntro.core.graph_writer.NodeSpec;
import ca.ntro.core.graphs.generics.graph.GraphId;
import ca.ntro.core.graphs.generics.graph.GraphWriterOptions;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.path.Filepath;

import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Rank.RankDir;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Link;
import guru.nidi.graphviz.model.MutableAttributed;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;

import static guru.nidi.graphviz.model.Factory.*;
import static guru.nidi.graphviz.model.Compass.*;

public class GraphWriterJdk implements GraphWriter {
	
	private Filepath basepath;

	private MutableGraph graph;
	private Map<String, MutableGraph> clusters = new HashMap<>();
	private Map<String, String> parentCluster = new HashMap<>();
	private Map<String, MutableNode> clusterAnchorNodes = new HashMap<>();
	private Map<String, MutableNode> nodes = new HashMap<>();
	private GraphWriterOptions options;

	public GraphWriterJdk() {
	}

	@Override
	public void initialize(GraphId id, GraphWriterOptions options) {
		this.options = options;
		this.basepath = id.toFilepath();
		
		graph = mutGraph(basepath.filename()).setDirected(options.isDirected())
				.graphAttrs().add(Rank.dir(RankDir.LEFT_TO_RIGHT))
				.graphAttrs().add("compound", "true");
		
		if(options.ranksep() != null) {
			graph.graphAttrs().add("ranksep", options.ranksep());
		}

		if(options.nodesep() != null) {
			graph.graphAttrs().add("nodesep", options.nodesep());
		}

		if(options.overlap() != null) {
			graph.graphAttrs().add("overlap", options.overlap());
		}

		if(options.splines() != null) {
			graph.graphAttrs().add("splines", options.splines());
		}
		

		clusters = new HashMap<>();
		parentCluster = new HashMap<>();
		clusterAnchorNodes = new HashMap<>();
		nodes = new HashMap<>();
	}

	protected void prepareToWrite() {
		for(String clusterId : clusters.keySet()) {
			createClusterAnchorNodeIfNeeded(clusterId);
		}
	}
	
	private File createFile(String extension) {
		File file = new File("_storage/" + basepath.toRawPath() + extension);
		
		File parentFile = file.getParentFile();

		if(parentFile != null) {
			parentFile.mkdirs();
		}
		
		return file;
	}

	protected void writeLater(Format format, String extension) {
		try {

			Graphviz.fromGraph(graph).render(format).toFile(createFile(extension));

		} catch (IOException e) {

			Ntro.exceptionService().throwException(e);

		}
		
		/*
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {

				try {

					Graphviz.fromGraph(graph).render(format).toFile(createFile(extension));

				} catch (IOException e) {

					Ntro.exceptions().throwException(e);

				}
			}

		}, 1);*/
	}

	@Override
	public void writePng() {
		prepareToWrite();
	
		writeLater(Format.PNG, ".png");
	}

	@Override
	public void writeSvg() {
		prepareToWrite();

		writeLater(Format.SVG, ".svg");
	}


	@Override
	public void writeDot() {
		prepareToWrite();

		writeLater(Format.DOT, ".dot");
	}

	@Override
	public void addNode(NodeSpec nodeSpec) throws NodeAlreadyAddedException {
		MutableNode node = createNode(nodeSpec);
		graph.add(node);
	}

	@Override
	public void addCluster(NodeSpec clusterSpec) throws ClusterAlreadyAddedException {
		MutableGraph cluster = createCluster(clusterSpec);

		MutableGraph clusterContainer = mutGraph(clusterSpec.id() + "__container");
		clusterContainer.setCluster(true);
		clusterContainer.graphAttrs().add("margin", 40);
		clusterContainer.graphAttrs().add("style", "invis");
		
		clusterContainer.add(cluster);
		
		graph.add(clusterContainer);
	}

	@Override
	public void addSubCluster(NodeSpec clusterSpec, NodeSpec subClusterSpec) throws ClusterNotFoundException, ClusterAlreadyAddedException {
		MutableGraph cluster = findCluster(clusterSpec);
		MutableGraph subCluster = createCluster(subClusterSpec);
		
		parentCluster.put(subClusterSpec.id(), clusterSpec.id());
		
		cluster.add(subCluster);
	}

	@Override
	public void addSubNode(NodeSpec clusterSpec, NodeSpec nodeSpec) throws ClusterNotFoundException, NodeAlreadyAddedException {
		MutableGraph cluster = findCluster(clusterSpec);
		MutableNode subNode = createNode(nodeSpec);

		parentCluster.put(nodeSpec.id(), clusterSpec.id());
		
		registerPossibleAnchorNode(clusterSpec.id(), subNode);

		cluster.add(subNode);
	}
	
	private void registerPossibleAnchorNode(String clusterId, MutableNode subNode) {

		if(!clusterAnchorNodes.containsKey(clusterId)) {
			clusterAnchorNodes.put(clusterId, subNode);
		}
		
		String parentId = parentCluster.get(clusterId);

		if(parentId != null) {
			registerPossibleAnchorNode(parentId, subNode);
		}
	}
	
	

	@Override
	public void addEdge(EdgeSpec edgeSpec) throws GraphWriterException {
		checkThatNodeExists(edgeSpec.from());
		checkThatNodeExists(edgeSpec.to());

		// XXX: all edges are written to the top-level graph
		MutableNode fromNode = null;
		if(edgeSpec.from().isCluster()) {
			
			fromNode = mutNode(findOrCreateClusterAnchorNode(edgeSpec.from()).name());
			
		}else {

			fromNode = mutNode(edgeSpec.from().id());
		}
		
		MutableNode toNode = null; 
		if(edgeSpec.to().isCluster()) {

			toNode = mutNode(findOrCreateClusterAnchorNode(edgeSpec.to()).name());
			
		}else {
			
			toNode = mutNode(edgeSpec.to().id());
		}
		
		Link link;
		
		if(edgeSpec.hasToPort()
				&& !edgeSpec.hasFromPort()) {

			link = Link.to(toNode.port(edgeSpec.toPort()));

		} else if(edgeSpec.hasToPort()
				&& edgeSpec.hasFromPort()) {

			link = between(port(edgeSpec.fromPort(), CENTER), toNode.port(edgeSpec.toPort()));
			link.attrs().add("tailclip","false");
			link.attrs().add("arrowtail","dot");
			link.attrs().add("dir","both");

		}else {
			
			link = Link.to(toNode);
		}

		link.attrs().add("label", edgeSpec.label());
		
		if(edgeSpec.from().isCluster()) {
			link.attrs().add("ltail","cluster_" + edgeSpec.from().id());
		}
		
		if(edgeSpec.to().isCluster()) {
			link.attrs().add("lhead","cluster_" + edgeSpec.to().id());
		}
		
		if(edgeSpec.from().isCluster()
				&& edgeSpec.to().isCluster()) {
			
			link.attrs().add("penwidth","3");
			//link.attrs().add("style","dashed");
			
			
		}

		fromNode.links().add(link);
		graph.add(fromNode);
	}

	
	private void checkThatClusterDoesNotAlreadyExists(NodeSpec clusterSpec) throws ClusterAlreadyAddedException {
		if(clusters.containsKey(clusterSpec.id())) {
			throw new ClusterAlreadyAddedException("Cluster already added: " + clusterSpec.id());
		}
	}

	private MutableGraph createCluster(NodeSpec clusterSpec) throws ClusterAlreadyAddedException {
		checkThatClusterDoesNotAlreadyExists(clusterSpec);
		
		MutableGraph cluster = mutGraph(clusterSpec.id());
		cluster.setCluster(true);
		cluster.graphAttrs().add(Rank.dir(RankDir.LEFT_TO_RIGHT));
		cluster.setDirected(this.options.isDirected());
		
		adjustNodeAttributes(cluster.graphAttrs(), clusterSpec);
		
		cluster.graphAttrs().add("penwidth", 3.0);
		cluster.graphAttrs().add("fontsize", 18.0);
		
		clusters.put(clusterSpec.id(), cluster);
		
		return cluster;
	}

	private void createClusterAnchorNodeIfNeeded(String clusterId) {
		findOrCreateClusterAnchorNode(clusterId);
	}
	
	private MutableNode findOrCreateClusterAnchorNode(String clusterId) {
		MutableNode anchorNode = clusterAnchorNodes.get(clusterId);

		if(anchorNode == null) {

			anchorNode = mutNode(anchorNodeId(clusterId));
			anchorNode.attrs().add("shape", "none");
			anchorNode.attrs().add("style", "invis");
			anchorNode.attrs().add("label", "");
			
			clusterAnchorNodes.put(clusterId, anchorNode);

			MutableGraph cluster = clusters.get(clusterId);
			cluster.add(anchorNode);
		}

		return anchorNode;
	}

	
	private MutableNode findOrCreateClusterAnchorNode(NodeSpec clusterSpec) throws GraphWriterException {
		checkThatNodeExists(clusterSpec);
		
		return findOrCreateClusterAnchorNode(clusterSpec.id());
	}
	
	
	
	private void checkThatClusterExists(NodeSpec clusterSpec) throws ClusterNotFoundException {
		if(!clusters.containsKey(clusterSpec.id())) {
			throw new ClusterNotFoundException("Cluster not found: " + clusterSpec.id());
		}
	}

	private MutableGraph findCluster(NodeSpec clusterSpec) throws ClusterNotFoundException {
		checkThatClusterExists(clusterSpec);

		return clusters.get(clusterSpec.id());
	}
	
	private String anchorNodeId(NodeSpec clusterSpec) {
		return anchorNodeId(clusterSpec.id());
	}

	private String anchorNodeId(String clusterId) {
		return "__" + clusterId + "__";
	}

	private void checkThatNodeExists(NodeSpec nodeSpec) throws GraphWriterException {
		if(nodeSpec.isCluster()) {

			if(!clusters.containsKey(nodeSpec.id())) {
				throw new ClusterNotFoundException("Cluster not found: " + nodeSpec.id());
			}

		}else {
			
			if(!nodes.containsKey(nodeSpec.id())) {
				throw new NodeNotFoundException("Node not found: " + nodeSpec.id());
			}
		}
	}

	private void checkThatNodeDoesNotAlreadyExists(NodeSpec nodeSpec) throws NodeAlreadyAddedException {
		if(nodes.containsKey(nodeSpec.id())) {
			throw new NodeAlreadyAddedException("Node already added: " + nodeSpec.id());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void adjustGraphItemAttributes(MutableAttributed attrs, GraphItemSpec spec) {
		attrs.add(Label.of(spec.label()));
	}
	
	@SuppressWarnings("rawtypes")
	private void adjustNodeAttributes(MutableAttributed attrs, NodeSpec nodeSpec) {
		
		adjustGraphItemAttributes(attrs, nodeSpec);

		attrs.add("style", "filled");
		if(nodeSpec.color() != null) {
			attrs.add("fillcolor", nodeSpec.color());
		}else {
			attrs.add("fillcolor", "white");
		}

		if(nodeSpec.shape() != null) {
			attrs.add("shape", nodeSpec.shape());
		}

		if(nodeSpec.margin() != null) {
			attrs.add("margin", nodeSpec.margin());
		}
	}

	private MutableNode createNode(NodeSpec nodeSpec) throws NodeAlreadyAddedException {
		checkThatNodeDoesNotAlreadyExists(nodeSpec);
		
		
		MutableNode node = mutNode(nodeSpec.id());

		adjustNodeAttributes(node.attrs(), nodeSpec);
		
		nodes.put(nodeSpec.id(), node);
		
		
		return node;
	}
}
