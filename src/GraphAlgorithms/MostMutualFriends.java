package GraphAlgorithms;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import GraphStructure.Graph;
import GraphStructure.Vertex;

public final class MostMutualFriends {
	public static Map.Entry<Vertex, Integer> Run(Graph G, Vertex start)
	{
		Map<Vertex, Integer> oneHop = new HashMap<Vertex, Integer>();
		Map<Integer, Vertex> startsFriends = start.getNeighbors();
		
		int maxMutualCount = 0;
		Vertex maxMutualFriend = new Vertex(0);
		
		for (Vertex friend : startsFriends.values()) {
		    for (Vertex fof : friend.getNeighbors().values()) {
		        if (!startsFriends.containsValue(fof) && fof != start) {
                    if (oneHop.containsKey(fof)) {
                        int fofMutualFriends = oneHop.get(fof) + 1;
                        if (fofMutualFriends > maxMutualCount) {
                            maxMutualCount= fofMutualFriends;
                            maxMutualFriend = fof;
                        }
                        oneHop.put(fof, fofMutualFriends);
                    }
                    else {
                        oneHop.put(fof, 1);
                    }
                }
		    }
		}
		
		return new AbstractMap.SimpleEntry<Vertex, 
		        Integer>(maxMutualFriend, maxMutualCount);
		
		
		
	}
	

}
