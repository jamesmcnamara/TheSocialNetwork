package GraphStructure;

import java.util.HashMap;



/**
 * Class for representing the graph structure of a social network
 * @author Michael Dipple
 * @author James McNamara
 * @version 2013-11-24
 */
public class Graph {

    private HashMap<Integer, Vertex> vertices;
    private HashMap<Integer, Edge> edges;

    /**Constructor*/
    public Graph()
    {
        vertices = new HashMap<Integer, Vertex>();
        edges = new HashMap<Integer, Edge>();
    }

    /**Constructor
     * creates a graph that is equal to the given graph
     * @param old <code>Graph</code>
     */
    public Graph(Graph old)
    {
        this.vertices = old.vertices;
        this.edges = old.edges;
    }

    /**EFFECT
     * Accessor Method
     * @return <code>HashMap</code>
     */
    public HashMap<Integer, Vertex> getVertices() {
        return vertices;
    }

    /**EFFECT
     * Accessor Method
     * @return <code>HashMap</code>
     */
    public HashMap<Integer, Edge> getEdges() {
        return edges;
    }

    /**MODIFIES
     * If the graph does not contain the given <code>Vertex</code>
     * add it to the vertices map
     * @param v <code>Vertices</code>
     */
    public void addVertex(Vertex v)
    {
        if (!vertices.containsKey(v.getID())) {
            vertices.put(v.hashCode(), v);
        }

    }

    /**MODIFIES
     * If the graph does not contain the given key, create a 
     * vertex with that key and add it to the vertices map
     * @param v <code>Vertices</code>
     */
    public void addVertex(Integer i) {
        if (!vertices.containsKey(i)) {
            Vertex v = new Vertex(i);
            vertices.put(v.getID(), v);
        }
    }

    /**MODIFIES
     * If the given edge does not exist, add it to this graph
     * @param e <code>Edge</code>
     */
    public void addEdge(Edge e)
    {
        Vertex v1 = e.getV1();
        Vertex v2 = e.getV2();

        v1.addNeighbor(v2);
        v2.addNeighbor(v1);

        edges.put(e.hashCode(), e);
    }

    /**MODIFIES
     * Consumes two integers corresponding to the ID's of two 
     * vertices in this graph, and creates or overwrites the
     * edge entry in the edges map
     * @param i1 <code>Integer</code> corresponding to a vertex ID
     * @param i2 <code>Integer</code> corresponding to a vertex ID
     */
    public void addEdge(Integer i1, Integer i2) {
        if (vertices.containsKey(i1) && vertices.containsKey(i2)) {

            Vertex v1 = vertices.get(i1);
            Vertex v2 = vertices.get(i2);

            v1.addNeighbor(v2);
            v2.addNeighbor(v1);

            Edge e = new Edge(v1, v2);
            edges.put(e.hashCode(), e);
        }
        else {
            throw new RuntimeException("Either " + i1 + " or " + 
                    i2 + " does not exist in the current graph");
        }
    }


    /**MODIFIES
     * If this graph contains the given vertex, remove it, and update
     * all of its neighbors lists to not include the given vertex 
     * @param v <code>Vertex</code> to remove
     */
    public void removeVertex(Vertex v)
    {
        if (vertices.containsKey(v.getID())) {
            for (Vertex neighbor : v.getNeighbors().values()) {
                neighbor.removeNeighbor(v);
            }
            vertices.remove(v.getID());
        }
    }

    /**EFFECT
     * Returns the vertex in this graph that has the given ID
     * @param ID <code>int</code> Vertex ID
     * @return <code>Vertex</code> with the given ID
     */
    public Vertex getVertex(int ID) {
        if (vertices.containsKey(ID)) {
            return vertices.get(ID);
        }
        else {
            throw new RuntimeException("Map does not contain key " + ID); 
        }
    }

    /**EFFECT
     * Returns a <code>String</code> representation of this graph
     * @return <code>String</code>
     */
    public String toString() {
        String s = "";
        for (Vertex v : vertices.values()) {
            s = s + "\nNode " + v.toString() + " has neighbors:";
            for (Vertex neighbor : v.getNeighbors().values()) {
                s = s + "\n\t" + neighbor;
            }
        }
        return s;
    }

    /**EFFECT
     * Determines if the given vertex is a member of this graph
     * @param v <code>Vertex</code>
     * @return <code>boolean</code>
     */
    public boolean contains(Vertex v) {
        return vertices.containsKey(v.getID());
    }



}
