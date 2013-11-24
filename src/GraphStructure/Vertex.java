package GraphStructure;

import java.util.HashMap;

/**
 * Class for representing nodes or vertices in a graph
 * @author Michael Dipple
 * @author James McNamara
 * @version 2013-11-24
 */
public class Vertex {

	private int id;
	private int friendCount;
	private HashMap<Integer, Vertex> neighbors;
	
	/**Constructor
	 * @param ID <code>int</code>
	 */
	public Vertex(int ID)
	{
		id = ID;
		friendCount = 0;
		neighbors = new HashMap<Integer, Vertex>();
	}
	
	/**EFFECT
	 * Overrides the hashCode method from the object
	 * class
	 * @return <code>int</code>
	 */
	public int hashCode() {
	    return this.id;
	}
	
	/**EFFECT
	 * Accessor Method
	 * @return <code>int</code>
	 */
	public int getID()
	{
		return id;
	}
	
	/**EFFECT
     * Accessor Method
     * @return <code>int</code>
     */
	public int getFriendCount() {
		return friendCount;
	}
	
	/**EFFECT
	 * Determines whether the given vertex is a neighbor of
	 * this vertex
	 * @param v <code>Vertex</code>
	 * @return <code>boolean</code>
	 */
	public boolean hasNeighbor(Vertex v) {
	    return neighbors.containsKey(v.getID());
	}
	
	/**MODIFIES
	 * Adds the given vertex to this vertex's neighbors
	 * and increments their friendCount
	 * @param v <code>Vertex</code> to be added
	 */
	public void addNeighbor(Vertex v)
	{
		if (!hasNeighbor(v)) {
		    neighbors.put(v.hashCode(), v);
	        friendCount++;   
		}	
	}
	
	/**MODIFIES
     * Removes the given vertex to this vertex's neighbors
     * and decrements their friendCount iff this node 
     * recognizes the given vertex as a neighbor
     * @param v <code>Vertex</code> to be removed
     */
	public void removeNeighbor(Vertex v)
	{
		if (hasNeighbor(v)) {
			neighbors.remove(v.getID());
			friendCount--;
		}
	}
	
	/**EFFECT
     * Accessor Method
     * @return <code>HashMap</code>
     */
	public HashMap<Integer, Vertex> getNeighbors() {
	    return neighbors;
	}
	
	/**EFFECT
	 * Overrides the toString method of the object class
	 * and returns a <code>String</code> representation 
	 * of the current object
	 * @return <code>String</code>
	 */
	public String toString() {
	    return "" + this.id;
	}
	
	/**EFFECT
	 * Determines whether this node is equal to the
	 * given vertex
	 * @param o <code>Vertex</code> to compare
	 */
	public boolean equals (Vertex o) {
	    return o.id == this.id;
	}
}
