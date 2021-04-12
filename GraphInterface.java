/*
 *
 * @author Aiman Kayad PID: 5975108
 * @author Kevin Hernandez PID: 5634615
 * 
 * Section: U01
 * 
 * Sources credited that provided me with guidance:
 * - Professor Hernandez
 * 
 * 
 */

public interface GraphInterface 
{
     public void addEdge(int v, int w, int weight);    
     public void removeEdge(int v, int w);    
     public int[] findAdjacencyVertices(int v);    
     public String toString();
}
