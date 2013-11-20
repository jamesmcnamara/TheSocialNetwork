package GraphIO;
import GraphStructure.*;
import java.io.*;
import java.util.*;
public final class GraphReader {
	
	public static Graph readGraphFromFile(String fileName)
	{
		Graph G = new Graph();
		
		try
        {
            FileReader fin = new FileReader( fileName );
            BufferedReader graphFile = new BufferedReader( fin );
			String line;
            // Read the number of vertices 
			
			line = graphFile.readLine( );
			
			StringTokenizer st = new StringTokenizer( line );
            int numVertices = Integer.parseInt(st.nextToken() );
			
			for(int i = 1; i <= numVertices; i++)
			{
				G.addVertex(i);
			}
            while( ( line = graphFile.readLine( ) ) != null )
            {
                st = new StringTokenizer( line );

                try
                {
                    if( st.countTokens( ) != 2 )
                    {
                        System.err.println( "Skipping ill-formatted line " + line );
                        continue;
                    }
                    int    v1    = Integer.parseInt( st.nextToken( ) );
                    int    v2    = Integer.parseInt( st.nextToken( ) );
                    
                    G.addEdge( v1, v2);
                }
                catch( NumberFormatException e )
                  { 
                	System.err.println( "Skipping ill-formatted line " + line ); 
                	}
             }
         
		
		
        }
		 catch( IOException e )
         { System.err.println( e ); }
		return G;
	
	}
}
