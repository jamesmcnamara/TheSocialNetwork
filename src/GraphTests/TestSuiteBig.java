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
abstract class TestSuiteBig {
	
	public static void testGraph(Integer graphID, Graph G, PrintWriter out)
	{
		out.println("Testing G" + graphID);
		
		// test mutual friends on first vertex
		Map.Entry<Vertex, Integer> friendRec;
    	Vertex v = G.getVertex(1);
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
	        System.out.println("Reading in (big) graph...");
	        Graph G2001;
	     
	        	
	        G2001 = GraphReader.readGraphFromFile("test_network_2001.net");
	        System.out.println("G2001 read in");
	        try
	        {
	        PrintWriter out = new PrintWriter(new FileWriter("results_big.out") ) ;
	        long start = System.currentTimeMillis();
	        testGraph(2001,G2001,out);
	        long end = System.currentTimeMillis();
	        System.out.println("Running the test suite took: " + 
	        ((end-start)/1000) + " seconds");
	        out.close();
	        }
	        catch( IOException e )
	         { System.err.println( e ); }
	        
	   
	        
	    }

}
