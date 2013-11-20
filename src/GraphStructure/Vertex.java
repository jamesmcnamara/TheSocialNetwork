package GraphStructure;

import java.util.HashMap;

public class Vertex {

	private int id;
	/**
	#the adjacency list refers to vertices by their ID
	
	#this essentially means that any time you want to access a neighbor, you will
	#have to look it up in the graph again
	
	#feel free to change this if you would rather do something different
	*/
	private HashMap<Integer, Vertex> neighbors;
	
	
	public Vertex(int ID)
	{
		id = ID;
		neighbors = new HashMap<Integer, Vertex>();
	}
	
	public int hashCode() {
	    return this.id;
	}
	
	public int getID()
	{
		return id;
	}
	
	
	public void addNeighbor(Vertex v)
	{
		neighbors.put(v.hashCode(), v);
		
	}
	
	
	public void removeNeighbor(Vertex v)
	{
		neighbors.remove(v.hashCode());

	}
	
	public HashMap<Integer, Vertex> getNeighbors() {
	    return neighbors;
	}
	
	public String toString() {
	    return "" + this.id;
	}
	
	public boolean equals(Object o) {
	    if (!(o instanceof Vertex)) {
	        return false;
	    }
	    else {
	        return equals((Vertex) o);
	    }
	}
	
	public boolean equals (Vertex o) {
	    return o.id == this.id;
	}
}
