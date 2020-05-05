package org.graphstream.graph;

import org.graphstream.graph.implementations.DefaultGraph;
import org.junit.Test;

public class PathTest {

	@Test(expected = IllegalStateException.class)
	public void setRoot_rootNodeMustBeNull() {
		Graph graph = createSimpleGraph();
		Path path = new Path();

		path.setRoot(graph.getNode("a"));

		// this has to fail, as root is already set
		path.setRoot(graph.getNode("b"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void add_nodeHeadMustBeInEdge() {
		Graph graph = createSimpleGraph();
		Path path = new Path();

		path.setRoot(graph.getNode("a"));

		// this has to fail as there is no edge between nodes "a" and "c"
		path.add(graph.getEdge("cd"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void add_whenAddingEdgeRootMustBeSet() {
		Graph graph = createSimpleGraph();
		Path path = new Path();

		// this has to fail as root of the path is not set
		path.add(graph.getEdge("ab"));
	}

	private Graph createSimpleGraph() {
		Graph graph = new DefaultGraph("test");
		graph.setStrict(false);
		graph.setAutoCreate(true);

		graph.addEdge("ab", "a", "b");
		graph.addEdge("bc", "b", "c");
		graph.addEdge("cd", "c", "d");

		return graph;
	}
}