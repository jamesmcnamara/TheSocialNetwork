package GraphAlgorithms;
import java.util.ArrayList;

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
        //Create a deep clone of the given graph to avoid manipulating the inputs
        Graph g = new Graph(G);
        
        //Clone the keys for the graph so that we can iterate over it without
        //deleting from the iterator
        ArrayList<Integer> keys = new ArrayList<Integer>();
        for (Integer i : g.getVertices().keySet()) {
            keys.add(i);
        }
        
        //Vertices remaining to delete
        ArrayList<Vertex> toDelete = new ArrayList<Vertex>();

        //for every node in the graph, we must check if they have enough friends
        for (Integer i : keys) {
            Vertex node = g.getVertex(i);
            
            //If this node has less than k friends, add it to the list of 
            //vertices stacked for deletion
            if (node.getFriendCount() < k){
                toDelete.addAll(g.removeVertexAndMarkVerticesToDelete(k, node));
            }   
        }
        //while the toDelete stack is non-empty, pop the top element, remove it,
        //decrement its friend count, and add its friends who fell below k to
        //the stack
        while (!toDelete.isEmpty()) {
            toDelete.addAll(g.removeVertexAndMarkVerticesToDelete(k, 
            		toDelete.remove(toDelete.size()-1)));
        }
        //Once we have removed all nodes that have less than k friends,
        //we have the largest kCommunity
        return new ArrayList<Vertex>(g.getVertices().values());
    }
  }

