package GraphStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;




public class Graph {

	private HashMap<Integer, Vertex> vertices;
	private ArrayList<Edge> edges;

	public Graph()
	{
		vertices = new HashMap<Integer, Vertex>();
		edges = new ArrayList<Edge>();
	}

	public Graph(Graph old)
	{
		this.vertices = old.vertices;
		this.edges = old.edges;
	}

	public HashMap<Integer, Vertex> getVertices() {
		return vertices;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public void addVertex(Vertex v)
	{
		vertices.put(v.hashCode(), v);
	}

	public void addVertex(Integer i) {
		Vertex v = new Vertex(i);
		vertices.put(i, v);
	}

	public void addEdge(Edge e)
	{
		Vertex v1 = vertices.get(e.getV1().hashCode());
		Vertex v2 = vertices.get(e.getV2().hashCode());

		v1.addNeighbor(v2);
		v2.addNeighbor(v1);

		edges.add(e);

	}

	public void addEdge(Integer i1, Integer i2) {
		if (vertices.containsKey(i1) && vertices.containsKey(i2)) {

			Vertex v1 = vertices.get(i1);
			Vertex v2 = vertices.get(i2);

			v1.addNeighbor(v2);
			v2.addNeighbor(v1);

			edges.add(new Edge(v1, v2));
		}
		else {
			throw new RuntimeException("Either " + i1 + " or " + 
					i2 + " does not exist in the current graph");
		}
	}


	public void removeVertex(Vertex v)
	{
		for (Vertex neighbor : v.getNeighbors().values()) {
			neighbor.removeNeighbor(v);
		}
		vertices.remove(v.getID());
	}
	
	public Vertex getVertex(int ID) {
		if (vertices.containsKey(ID)) {
			return vertices.get(ID);
		}
		else {
			throw new RuntimeException("Map does not contain key " + ID); 
		}
	}

	public String toString() {
	    String s = "";
	    for (Vertex v : vertices.values()) {
	        s = s + "\nNode " + v.toString() + " has neighbors:";
	        for (Vertex neighbor : v.getNeighbors().values()) {
	        	s = s + "\n\t" + neighbor;
	        }
	    }
	    return s;
	}
	
	public boolean contains(Vertex v) {
		return vertices.containsKey(v.getID());
	}
	
	

}
