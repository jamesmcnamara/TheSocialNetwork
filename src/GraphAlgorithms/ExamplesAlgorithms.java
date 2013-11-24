package GraphAlgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import GraphIO.GraphReader;
import GraphStructure.Graph;
import GraphStructure.Vertex;

/**
 * Tester class for the algorithms
 * @author James McNamara
 * @version 2013-11-24
 */
public class ExamplesAlgorithms {

    Graph nine = GraphReader.readGraphFromFile("test_network_9.net");
    Graph twentyOne = GraphReader.readGraphFromFile("test_network_21.net");
    Graph oneOhOne= GraphReader.readGraphFromFile("test_network_101.net");

    /**EFFECT
     * Test the most mutual friends algorithm on the example graph with 
     * nine nodes
     * When run on node 1, the results should be:
     * Node with Most Mutual Friends: 6
     * Friends in Common: 4
     */
    @Test
    public void testMMF9() {
        Map.Entry<Vertex, Integer> nineMaxMutual = 
                MostMutualFriends.Run(nine, nine.getVertex(1));

        Assert.assertTrue(nineMaxMutual.getKey().getID()==6);
        Assert.assertTrue(nineMaxMutual.getValue()==4);
    }

    /**EFFECT
     * Test the most mutual friends algorithm on the example graph with 
     * twenty one nodes
     * When run on node 1, the results should be:
     * Node with Most Mutual Friends: 12
     * Friends in Common: 10
     */
    @Test
    public void testMMF21() {
        Map.Entry<Vertex, Integer> twentyOneMaxMutual = 
                MostMutualFriends.Run(twentyOne, twentyOne.getVertex(1));

        Assert.assertTrue(twentyOneMaxMutual.getKey().getID()==12);
        Assert.assertTrue(twentyOneMaxMutual.getValue()==10);
    }

    /**EFFECT
     * Test the most mutual friends algorithm on the example graph with 
     * one hundred and one nodes
     * When run on node 1, the results should be:
     * Node with Most Mutual Friends: 6
     * Friends in Common: 4
     */
    @Test
    public void testMMF101() {
        Map.Entry<Vertex, Integer> oneOhOneMaxMutual = 
                MostMutualFriends.Run(oneOhOne, oneOhOne.getVertex(1));

        Assert.assertTrue(oneOhOneMaxMutual.getKey().getID()==52);
        Assert.assertTrue(oneOhOneMaxMutual.getValue()==50);
    }

    /**EFFECT
     * Tests the kCommunity algorithm on the sample graph with 
     * nine nodes
     * When a kCommunity of size 3 is queried, a list of 6 nodes
     * should be returned
     * When a kCommunity of size 4 is queried, a list of 0 nodes
     * should be returned
     */
    @Test
    public void testKComm9() {
        ArrayList<Vertex> kComm3 = kCommunity.Run(nine, 3);
        ArrayList<Vertex> kComm4 = kCommunity.Run(nine, 4);

        Assert.assertTrue(kComm3.size()==6);
        Assert.assertTrue(kComm4.size()==0);

        //The ID's of the vertices which should be in the theoretical
        //kCommunity
        ArrayList<Integer> ids = 
                new ArrayList<Integer>(Arrays.asList(1, 3, 4, 5, 6, 7));

        //The list of vertices themselves
        ArrayList<Vertex> kCommTester = new ArrayList<Vertex>();

        //For each of the IDs of vertices which should be in the kCommunity
        //look up the vertex in the graph, and add it to the vertex list
        for (Integer i : ids) {
            kCommTester.add(nine.getVertex(i));
        }

        //test that the list returned by the algorithm matches the 
        //expected output
        Assert.assertTrue(kComm3.equals(kCommTester));
    }

    /**EFFECT
     * Tests the kCommunity algorithm on the sample graph with 
     * twenty one nodes
     * When a kCommunity of size 6 is queried, a list of 12 nodes
     * should be returned
     * When a kCommunity of size 7 is queried, a list of 0 nodes
     * should be returned
     */
    @Test
    public void testKComm21() {
        ArrayList<Vertex> kComm6 = kCommunity.Run(twentyOne, 6);
        ArrayList<Vertex> kComm7 = kCommunity.Run(twentyOne, 7);

        Assert.assertTrue(kComm6.size()==12);
        Assert.assertTrue(kComm7.size()==0);

        //The ID's of the vertices which should be in the theoretical
        //kCommunity
        ArrayList<Integer> ids = 
                new ArrayList<Integer>(Arrays.asList(1, 6, 7, 8, 9, 10, 11, 
                        12, 13, 14, 15, 16));

        //The list of vertices themselves
        ArrayList<Vertex> kCommTester = new ArrayList<Vertex>();

        //For each of the IDs of vertices which should be in the kCommunity
        //look up the vertex in the graph, and add it to the vertex list
        for (Integer i : ids) {
            kCommTester.add(twentyOne.getVertex(i));
        }

        //test that the list returned by the algorithm matches the 
        //expected output
        Assert.assertTrue(kComm6.equals(kCommTester));
    }

    /**EFFECT
     * Tests the kCommunity algorithm on the sample graph with 
     * one hundred and one nodes
     * When a kCommunity of size 26 is queried, a list of 52 nodes
     * should be returned
     * When a kCommunity of size 27 is queried, a list of 0 nodes
     * should be returned
     */
    @Test
    public void testKComm101() {
        ArrayList<Vertex> kComm26 = kCommunity.Run(oneOhOne, 26);
        ArrayList<Vertex> kComm27 = kCommunity.Run(oneOhOne, 27);

        Assert.assertTrue(kComm26.size()==52);
        Assert.assertTrue(kComm27.size()==0);

        //The ID's of the vertices which should be in the theoretical
        //kCommunity
        ArrayList<Integer> ids = 
                new ArrayList<Integer>(Arrays.asList(1, 27, 26, 29, 
                        28, 31, 30, 34, 35, 32, 33, 38, 39, 36, 37, 42, 
                        43, 40, 41, 46, 47, 44, 45, 51, 50, 49, 48, 55, 
                        54, 53, 52, 59, 58, 57, 56, 63, 62, 61, 60, 68, 
                        69, 70, 71, 64, 65, 66, 67, 76, 72, 73, 74, 75));

        //The list of vertices themselves
        ArrayList<Vertex> kCommTester = new ArrayList<Vertex>();

        //For each of the IDs of vertices which should be in the kCommunity
        //look up the vertex in the graph, and add it to the vertex list
        for (Integer i : ids) {
            kCommTester.add(oneOhOne.getVertex(i));
        }

        //test that the list returned by the algorithm matches the 
        //expected output
        Assert.assertTrue(kComm26.equals(kCommTester));


    }

}
