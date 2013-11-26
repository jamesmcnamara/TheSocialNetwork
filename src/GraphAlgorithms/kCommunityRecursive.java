package GraphAlgorithms;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;

import GraphStructure.Graph;
import GraphStructure.Vertex;

/**
 * Static class for determining the largest interconnected community
 * @author James McNamara
 * @version 2013-11-24
 */
public final class kCommunityRecursive {

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
        //Create a deep clone of the given graph to avoid manipulating the inputs
        Graph g = new Graph(G);

        //The list of vertices to groom
        HashMap<Integer, Vertex> kComm = new HashMap<Integer, Vertex> (g.getVertices());

        //for every node in the graph, we must check if they have enough friends
        Iterator<Vertex> iter = kComm.values().iterator();
        while(iter.hasNext()) {
            try { 
                Vertex node = iter.next();
                
                //if the current node is still part of the kComm map and has less
                //than k friends, we need to delete it from kComm, remove it from
                //its neighbors friends, as well as checking the ripple effect 
                if (node.getFriendCount() < k && kComm.containsKey(node.getID())){
                    iter.remove();
                    deleteNodeAndNeighbors(kComm, node, k);
                }   
                //If the last element of the list is removed, the next() call will
                //trigger a concurrent modification exception, and we know that 
                //there is no kCommunity
            } catch (ConcurrentModificationException e) {
                return new ArrayList<Vertex>();
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
        
        //For every friend that the given vertex had, remove the 
        //given vertex from their friend list
        Iterator<Vertex> neighborsIter = node.getNeighbors().values().iterator();
        while (neighborsIter.hasNext()) {
            try {
                Vertex v = neighborsIter.next();
                v.removeNeighbor(node);

                //If the friend of the given node now has less than k friends
                //we need to remove them as well before continuing
                if (v.getFriendCount() < k) {
                    neighborsIter.remove();
                    deleteNodeAndNeighbors(kComm, v, k);
                }

            }
            //if the last element of kComm is removed, the iterator
            //will trigger a Concurrent Modification Exception,
            //and we know that their is no kCommunity
            catch (ConcurrentModificationException e) {
                kComm.clear();
                return;
            }
        }
    }
}

