package GraphStructure;

/**
 * Class for representing edges in the Graph 
 * @author Michael Dipple
 */
public class Edge {
    private Vertex v1;
    private Vertex v2;
    private int hashCode;

    /**Constructor:
     * @param V1 <code>Vertex</code>
     * @param V2 <code>Vertex</code>
     */
    public Edge(Vertex V1, Vertex V2)
    {
        v1 = V1;
        v2 = V2;
        hashCodeGenerator();
    }

    /**Constructor
     * @param ID1 <code>int</code>
     * @param ID2 <code>int</code>
     */
    public Edge(int ID1, int ID2)
    {
        v1 = new Vertex(ID1);
        v2 = new Vertex(ID2);
        hashCodeGenerator();

    }
    /**MODIFIES
     * Sets this edges hashCode to be the concatenation
     * of the ID's of the two vertices it contains
     */
    private void hashCodeGenerator() {
        int minVID = Math.min(v1.hashCode(), v2.hashCode());
        int maxVID = Math.max(v1.hashCode(), v2.hashCode());
        int c = (int) Math.log10(maxVID) + 1;
        hashCode = minVID * (int)Math.pow(10, c) + maxVID;
    }
    
    /**EFFECT
     * Returns this edge's hashCode
     * @param <code>int</code>
     */
    public int hashCode() {
        return hashCode;
    }

    /** Accessor Method
     * @return <code>Vertex</code>
     */
    public Vertex getV1(){ 
        return v1; 
    }

    /** Accessor Method
     * @return <code>Vertex</code>
     */
    public Vertex getV2() { 
        return v2; 
    }


}
