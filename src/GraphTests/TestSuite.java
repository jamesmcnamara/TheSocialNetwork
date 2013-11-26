package GraphTests;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import GraphAlgorithms.MostMutualFriends;
import GraphAlgorithms.kCommunity;
import GraphIO.GraphReader;
import GraphStructure.Graph;
import GraphStructure.Vertex;
public class TestSuite {
	
	public static void testGraph(Integer graphID, Graph G, PrintWriter out)
	{
	    System.out.println("Testing G" + graphID);
	    out.println("Testing G" + graphID);
		
		// test mutual friends on first vertex
		Map.Entry<Vertex, Integer> friendRec;
    	Vertex v = new Vertex(1);
    	friendRec = MostMutualFriends.Run(G, v);
    	out.println("node " + v.getID() + " should add node " + friendRec.getKey().getID() + " - friends in common is " + friendRec.getValue());
    	// test again on result from first
    	// this won't always return Vertex(1), but it typically does, and should on our provided networks
    	Vertex v2 = friendRec.getKey();
    	friendRec = MostMutualFriends.Run(G, v2);
    	out.println("node " + v2.getID() + " should add node " + friendRec.getKey().getID() + " - friends in common is " + friendRec.getValue());
		
    	
    	// test kCommunity
    	for(int i = 1; ; i*=2)
        {
        ArrayList<Vertex> kComm = new ArrayList<Vertex>();
        kComm = kCommunity.Run(G,i);
        if(kComm.size() == 0)
        {
        	out.println("There is no k-community of size " + i);
        	out.println("Finished testing of G" + graphID);
        	return;
        }
        out.println("Largest " + i + "-community has size " + kComm.size());
        
        for(Vertex K : kComm)
        {
        	out.print(" " + K.getID());
        	// K.printAdjacentList();
        }
    	out.println("");
        }

	}
	 public static void main(String[] args) {
		 	//int testBig = 0;
	        System.out.println("Reading in graphs...");
	        Graph G9, G21, G101, G501;
	        G9 = GraphReader.readGraphFromFile("test_network_9.net");
	        System.out.println("G9 read in");
	        G21 = GraphReader.readGraphFromFile("test_network_21.net");
	        System.out.println("G21 read in");
	        G101 = GraphReader.readGraphFromFile("test_network_101.net");
	        System.out.println("G101 read in");
	        G501 = GraphReader.readGraphFromFile("test_network_501.net");
        	
	        System.out.println("G501 read in");
	        System.out.println("All graphs read in");
	        try
	        {
	        PrintWriter out = new PrintWriter(new FileWriter("results.out") ) ;
	        testGraph(9,G9,out);
	        System.out.println(G9);
	        testGraph(21,G21,out);
	        System.out.println(G21);
	        testGraph(101,G101,out);
	        System.out.println(G101);
	        testGraph(501,G501,out);
	        out.close();
	        }
	        catch( IOException e )
	         { System.err.println( e ); }
	        
	      
	        
	    }

}
