import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


/*
 *
 * @author Aiman Kayad PID: 5975108
 * @author Kevin Hernandez PID: 5634615
 * 
 * Section: U01
 * 
 * Sources credited that provided me with guidance:
 * - Professor Hernandez
 * - https://stackoverflow.com/users/3182664/marco13
 * - https://stackoverflow.com/users/230513/trashgod
 */


public class Graph implements GraphInterface
{
    int verticesNumber;
    static int[][] matrix; //adjacency matrix
    
   
    
    public Graph()
    {
        verticesNumber = 5;
        matrix = new int[verticesNumber][verticesNumber];
    }
    
    public Graph(int n)
    {
        verticesNumber = n;
        matrix = new int[verticesNumber][verticesNumber];
    }
    
     public int verticesNumber() 
    {
       return verticesNumber;
    }
    
    public void addEdge(int v, int w, int weight)
    {
        matrix[v][w] = weight;
        matrix[w][v] = weight;
    }
    
    public void removeEdge(int v, int w)
    {
        matrix[v][w] = 0;
        matrix[w][v] = 0;        
    }
    
    /**
     * Finds vertices adjacent to a given vertex.
     * 
     * @param v given vertex
     * @return list of vertices adjacent to v stored in an array;
     *          size of array = number of adjacent vertices
     */
    public int[] findAdjacencyVertices(int v)
    {
        int[] vert = new int[verticesNumber];
        int total = 0;
        
        for (int i=0; i<verticesNumber; i++)
        {
            if (matrix[v][i] != 0)
            {
                vert[total] = i;
                total++;
            }
        }
        
        return Arrays.copyOf(vert, total);        
    }
    
    public String toString()
    {
        String s = "";
        
        for (int i=0; i<verticesNumber; i++)
        {
            for (int j=0; j<2; j++)
            {
                s += matrix[i][j] + " ";
            }
            s += "\n";
        }
        
        return s;
    }
    
    /**
     * Calculates the shortest paths from a given vertex to
     * all vertices.
     * 
     * @param p paths (p[i] contains previous vertex in the
     * shortest path from v)
     * 
     * @param d distances (d[i] contains the shortest distance
     * from v)
     * 
     * @param v given vertex
     */
    public void allShortestPaths(int[] p, int[] d, int v) 
    {
        boolean[] visited = new boolean[verticesNumber];
        
        for (int i = 0; i < verticesNumber; i++) {
            visited[i] = false; // not yet visited
            p[i] = -1; // previous vertex in unknown
            d[i] = Integer.MAX_VALUE; //d[i] = INFINITY
        }
        
        d[v] = 0;
        
        for (int i = 0; i < verticesNumber - 1; i++) 
        {
            int w = minDistance(visited, d);
            visited[w] = true;
            
            int[] adj = findAdjacencyVertices(w);
            for(int u : adj) 
            {
                if (!visited[u]) 
                 {
                    if (d[w] + matrix[w][u] < d[u]) 
                    {
                        d[u] = d[w] + matrix[w][u];
                        p[u] = w;
                    }
                  }
            }
        }
    }
    
    private int minDistance(boolean[] visited, int[] distance) {
        int index = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < verticesNumber; i++) {
             if (!visited[i]) {
                 if (distance[i] <= min) {
                     min = distance[i];
                     index = i;
                 }
             }
        }
        
        return index;
    }
    
    
    /**
     * Returns shortest path between given source and target vertices.
     * 
     * @param p paths (p[i] contains previous vertex in the shortest path
     *   from source vertex)
     */
    public int[] getPath(int s, int t, int[] p) 
    {
        
        int[] shortestPath = new int[p.length]; 
        
        int current = t;
        int total = 0;
        while (current != s) 
        {
            shortestPath[total] = current;
            current = p[current];
            total++;
        }
       shortestPath[total++] = s;
       shortestPath = Arrays.copyOf(shortestPath, total);
       
       //reverses array
       for(int i = 0; i < total/2; i++) 
       {
           int temp = shortestPath[i];
           shortestPath[i] = shortestPath[total - 1 - i];
           shortestPath[total - 1 - i] = temp;
       }
       
       return shortestPath;
    }
    
    
    
    /**
     * Finds a shortest path route that visits every vertex exactly once and
     * returns to the starting point.
     * Uses exhaustive search.
     * 
     * @param shortestRoute array with a shortest path (return value)
     * 
     * @return shortest distance
     */
    public int TSP_exhaustiveSearch(int[] shortestRoute)
    {
        //initialize shortestRoute
        for (int i = 0; i < verticesNumber; i++)
        {
            shortestRoute[i] = i;
        }
        
        int[] a = new int[verticesNumber];
        TSP_exhaustiveSearch(shortestRoute, a, 0);
        
        return totalDistance(shortestRoute);
    }
    
    
    /**
     * Calculates distance of given route.
     * 
     * @param a route
     * 
     * @return distance of route
     */
    int totalDistance(int[] a)
    {
        int n = verticesNumber;
        
        //add weights of all edges in the path
        int totalWeight = 0;
        for (int i = 0; i < n; i++)
        {
            int weight = matrix[a[i]] [a[(i+1)%n]];
            totalWeight += weight;
        }
        return totalWeight;
    }
    
    
    /**
     * Recursive algorithm.
     * 
     * @param a array partially filled with permutation
     * @param k index of current element in permutation
     */
    private void TSP_exhaustiveSearch(int[] shortestRoute, int[] a, int k)
    {
        if (k == a.length)
        {
            if (totalDistance(a) < totalDistance(shortestRoute))
            {
                System.arraycopy(a, 0, shortestRoute, 0, verticesNumber);
            }
            //System.out.print(totalDistance(a) + " ");
            //printArray(a);
            
            
        }
        else
        {
            ArrayList<Integer> Sk = constructCandidateSet(a, k);
            for (int s : Sk)
            {
                a[k] = s;
                TSP_exhaustiveSearch(shortestRoute, a, k+1);
            }
        }
    }
    
    
    /**
     * Construct candidate set (set will contain elements not used 
     * in locations [0, k-1] of array a).
     */
    private ArrayList<Integer> constructCandidateSet(int[] a, int k)
    {
        ArrayList<Integer> candidates = new ArrayList<>();
        boolean[] b = new boolean[a.length];
        
        for (int i = 0; i < k; i++)
        {
            b[a[i]] = true;
        }
        
        for (int i = 0; i < a.length; i++)
        {
            if (!b[i]) candidates.add(i);
        }
        
        return candidates;
        
    }
    
    
    /**
     * Finds a shortest route that visits every vertex exactly once and returns
     * to the starting point.
     * Uses local search, so optimal solution is not obtained, in general.
     * 
     * @param shortestRoute array with a shortest path (return value)
     * 
     * @return shortest distance
     * 
     */
    public int TSP_localSearch(int[] shortestRoute)
    {
        int bestDistance;
        
        //generate initial solution as a random permutation
        int[] a = new int[verticesNumber];
        randomPermutation(a);
        
        //shortestRoute = initial solution
        System.arraycopy(a, 0, shortestRoute, 0, verticesNumber);
        bestDistance = totalDistance(shortestRoute);
        
        boolean betterSolutionFound;
       
        /*
          Loop will continue as long as there is a neighbor that improves
          best dsitance obtained so far.
        */
        do 
        {
          betterSolutionFound = false;
          PermutationNeighborhood pn = new PermutationNeighborhood(shortestRoute);
          while (pn.hasNext())
          {
              a = pn.next();
              int currentDistance = totalDistance(a);
              if (currentDistance < bestDistance)
              {
                  //shortestRoute = current solution
                  System.arraycopy(a, 0, shortestRoute, 0, verticesNumber);
                  bestDistance = currentDistance;
                  betterSolutionFound = true;
              }
          }
        }  while (betterSolutionFound);
          
          return bestDistance;
    }
    
    
    
    /**
     * Finds a shortest route that visits every vertex
     * exactly once and returns to the starting point.
     * Uses random sampling, so optimal solution is not
     * obtained, in general.
     * 
     * @param shortestRoute array with a shortest path (return value)
     * 
     * @return shortest distance
     */
    public int TSP_randomSampling(int[] shortestRoute)
    {
        int numberOfSamples = 10; 
        int bestDistance = Integer.MAX_VALUE;
        
        for (int i = 0; i < numberOfSamples; i++)
        {
            int[] a = new int[verticesNumber];
            randomPermutation(a);
            int currentDistance = totalDistance(a);
            if (currentDistance < bestDistance)
            {
                bestDistance = currentDistance;
                System.arraycopy(a, 0, shortestRoute, 0, verticesNumber);
            }
        }
        
        return bestDistance;
    }
    
    
    /**
     * Given an array, generates random permutation of values in [0, n-1],
     * where n is size of given array; random permutation will be stored
     * in the array. Uses Fisher-Yates shuffle algorithm.
     * 
     * @param a output array
     */
    public void randomPermutation(int[] a)
    {
        for (int i = 0; i < a.length; i++)
        {
            a[i] = i;
        }
        
        Random rnd  = new Random();
        for (int i = a.length - 1; i > 0; i--)
        {
            //geenrates a random index in [0, i]
            int randomLocation = rnd.nextInt(i + 1);
        
        
        if (randomLocation != i) 
        {
            //swap a[i] and a[randomLocation]
            int temp = a[i];
            a[i] = a[randomLocation];
            a[randomLocation] = temp;
        }
      }
    }
    
    
    
    /**
     * Prints array a.
     */
    private void printArray(int[] a)
    {
        for (int v : a) 
        {
            System.out.println(v + " ");
        }
        
        System.out.println();
    }
       
    
}

