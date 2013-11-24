package GraphAlgorithms;
import GraphStructure.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class kCommunity {
	public static ArrayList<Vertex> Run(Graph G, int k)
	{
		Graph g = new Graph(G);
		HashMap<Integer, Vertex> kComm = 
				new HashMap<Integer, Vertex> (g.getVertices());
		
		for (Vertex node : g.getVertices().values()) {
			if (node.getFriendCount() < k) {
				deleteNodeAndNeighbors(kComm, node, k);
			}
		}
		return new ArrayList<Vertex>(kComm.values());
	}
	
	public static void deleteNodeAndNeighbors(HashMap<Integer, Vertex> kComm,
			Vertex node, int k) {
		if (kComm.containsKey(node.getID())) {
			kComm.remove(node.getID());
			HashMap<Integer, Vertex> neighbors = new HashMap<Integer, Vertex>(node.getNeighbors());
			for (Vertex v : neighbors.values()) {
				v.removeNeighbor(node);
				if (v.getFriendCount() < k) {
					deleteNodeAndNeighbors(kComm, v, k);
				}
			}
		}
	}


}
