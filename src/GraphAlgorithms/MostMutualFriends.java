package GraphAlgorithms;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import GraphStructure.Graph;
import GraphStructure.Vertex;

/**
 * Static class used for identifying the best friend recommendation
 * based on shortest paths
 * @author James McNamara
 * @version 2013-11-24
 */
public final class MostMutualFriends {

    /**EFFECT
     * Given a starting node and a graph that contains that vertex, this 
     * method will find the vertex in this graph which has the most mutual
     * friends with the given vertex, but is not friends with it
     * @param G the <code>Graph</code> which contains the vertex to analyze
     * @param start the <code>Vertex</code> in the given <code>Graph</code>
     * to start from in analysis
     * @return <code>Map.Entry</code> which contains the vertex with the most
     * mutual friends with the given vertex, and the count of shared friends
     */
    public static Map.Entry<Vertex, Integer> Run(Graph G, Vertex start)
    {
        Map<Vertex, Integer> oneHop = new HashMap<Vertex, Integer>();
        Map<Integer, Vertex> startsFriends = start.getNeighbors();

        int maxMutualCount = 0;
        Vertex maxMutualFriend = new Vertex(0);

        //Iterate through every one of the start nodes friends
        for (Vertex friend : startsFriends.values()) {

            //for each of the start node's friends, iterate through
            //each of THEIR friends
            for (Vertex fof : friend.getNeighbors().values()) {

                //Check that this friend of a friend is not friends with
                //the start node
                if (!startsFriends.containsValue(fof) && fof != start) {

                    if (oneHop.containsKey(fof)) {
                        int fofMutualFriends = oneHop.get(fof) + 1;

                        //Check if this friend of a friend has the most
                        //friends in common with the given node, and
                        //potentially update the max count, 
                        if (fofMutualFriends > maxMutualCount) {
                            maxMutualCount= fofMutualFriends;
                            maxMutualFriend = fof;
                        }

                        //Update the mutual friends count for this node 
                        oneHop.put(fof, fofMutualFriends);
                    }

                    //if our hashmap of recommendation candidates does not
                    //contain the current fof, add them, with a count of 
                    //one mutual friend

                    else {
                        oneHop.put(fof, 1);
                    }
                }
            }
        }
        
        //return the vertex with the highest count of mutual friends, and 
        //what that count actually is
        return new AbstractMap.SimpleEntry<Vertex, 
                Integer>(maxMutualFriend, maxMutualCount);



    }


}
