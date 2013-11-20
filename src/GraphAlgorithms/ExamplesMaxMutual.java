package GraphAlgorithms;

import java.util.Map;

import GraphIO.GraphReader;
import GraphStructure.Graph;
import GraphStructure.Vertex;



public class ExamplesMaxMutual{
     
    public static void main(String[] args) {
        Graph g = GraphReader.readGraphFromFile("test_network_101.net");   
        Map.Entry<Vertex, Integer> maxMutual = MostMutualFriends.Run(g, g.getVertex(9));
//        for (Vertex v : g.getVertex(1).getNeighbors().values()) {
//            System.out.println(v);
//        }
        System.out.println("The vertex with the most mutual friends with this node is " 
                + maxMutual.getKey() + "\nThey have " + maxMutual.getValue() + " friends in common");
        
        
    }
}