package GraphAlgorithms;
import java.util.ArrayList;
import java.util.HashMap;

import GraphStructure.Graph;
import GraphStructure.Vertex;

/**
 * Static class for determining the largest interconnected community
 * @author James McNamara
 * @version 2013-11-24
 */
public final class kCommunity {
    
    /**EFFECT
     * Finds the largest community in the given <code>Graph</code> where
     * every member has at least k friends that each in turn have k
     * friends within the community
     * @param G the <code>Graph</code> to inspect 
     * @param k the number of friends each person must have
     * @return <code>ArrayList</code> of <code>Vertex</code>s that have 
     * k friends in the community
     */
	public static ArrayList<Vertex> Run(Graph G, int k)
	{
	    //The list of vertices to groom
		HashMap<Integer, Vertex> kComm = 
				new HashMap<Integer, Vertex> (G.getVertices());
		
		//for every node in the graph, we must check if they have enough friends
		//NOTE:a more efficient approach would be to iterate through the list
		//that we are grooming. However, Java's Iterator methods will not allow
		//you to edit a list while you iterate. Thus this will consider nodes
		//that we have already eliminated. A faster implementation would be 
		//possible if we wrote our own iterator class, or used another PL
		for (Vertex node : G.getVertices().values()) {
		    
		    //if the current node is still part of the kComm map and has less
		    //than k friends, we need to delete it from kComm, remove it from
		    //its neighbors friends, as well as checking the ripple effect 
			if (node.getFriendCount() < k && kComm.containsKey(node.getID())) {
				deleteNodeAndNeighbors(kComm, node, k);
			}
		}
		
		//Once we have removed all nodes that have less than k friends,
		//we have the largest kCommunity
		return new ArrayList<Vertex>(kComm.values());
	}
	
	/**MODIFIES
	 * Removes the given node from the kCommunity, then iterates over
	 * its neighbors, and removes the given node from their neighbors
	 * If any neighbor's friendCount drops below k in this process,
	 * pause the current loop, and recur with that node as the 
	 * vertex to removed
	 * @param kComm <code>HashMap</code> of vertices
	 * @param node <code>Vertex</code> to be removed
	 * @param k Minimum friend count
	 */
	public static void deleteNodeAndNeighbors(HashMap<Integer, Vertex> kComm,
			Vertex node, int k) {
		if (kComm.containsKey(node.getID())) {
			kComm.remove(node.getID());
			
			//Like above, we must create a copy to iterate over to get around 
			//Java's iterator locks
			HashMap<Integer, Vertex> neighbors = 
			        new HashMap<Integer, Vertex>(node.getNeighbors());
			
			//For every friend that the given vertex had, remove the 
			//given vertex from their friend list
			for (Vertex v : neighbors.values()) {
				v.removeNeighbor(node);
				
				//If the friend of the given node now has less than k friends
				//we need to remove them as well before continuing
				if (v.getFriendCount() < k) {
					deleteNodeAndNeighbors(kComm, v, k);
				}
			}
		}
	}
}
