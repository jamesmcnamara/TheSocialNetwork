package GraphStructure;

public class Edge {
	private Vertex v1;
	private Vertex v2;
	
	public Edge(Vertex V1, Vertex V2)
	{
		v1 = V1;
		v2 = V2;
		return;
	}
	
	public Edge(int ID1, int ID2)
	{
		Vertex V1 = new Vertex(ID1);
		Vertex V2 = new Vertex(ID2);
		v1 = V1;
		v2 = V2;
		return;
		
	}
	
	public Vertex getV1(){ return v1; }
	
	public Vertex getV2() { return v2; }
	

}
