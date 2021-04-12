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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import javax.swing.*;

/**
 * Illustrates reading graph from text file.
 * 
 * @author Prof. Hernandez
 */

public class ReadInputFromTextFile
{
    
   static int numberOfVertices = 0;
   static int s = 0;
   static int t = 0;
   
   static int p[];
   static int d[];
   
   static int resultArray[];
   
    static int[][] matrix;
    
    static int[] pathValues;
    
    // static int p[];
    
    static ArrayList<Integer> pointsX = new ArrayList<Integer>();
    ArrayList<Integer> pointsY = new ArrayList<Integer>();
    
    public static void main(String[] args)
    {
        new ReadInputFromTextFile();
        
        FrameDisplay frame = new FrameDisplay();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        
       Graph graphy = new Graph(numberOfVertices);
        
        
        
         
        
       // System.out.println(graphy.verticesNumber);
        
        /*
        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = 0; j < 2; j++) {
                 graphy.addEdge(i, j, matrix[i][j]);
            }
        }
        */
        
        
        
       // System.out.println();
        
      System.out.println("Graph class read: \n" + graphy.toString());
        
     
       
    }
    
    public ReadInputFromTextFile()
    {
        readGraph();
    }
    
    /**
     * Reads graph from text file (entries as given in COP4534 3rd assignment)
     * and prints it
     */
    public void readGraph()
    {
        File input = new File("/Users/aimankayad/Desktop/TSPgraph.txt");

        Scanner in = null;
        try
        {
            in = new Scanner(input);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found!");
        }

        // int numberOfVertices = 0;
        
        // int mat_i_j=0;
        
        while (in.hasNextLine())
        {
            numberOfVertices = in.nextInt();
            System.out.println("Vertices: " + numberOfVertices);
             
            matrix = new int[numberOfVertices][numberOfVertices];
           
           
            for(int i = 0; i < numberOfVertices; i++)
            {
              for(int j = 0; j < 2; j++)
                {
                    matrix[i][j] = in.nextInt();
                    
                   
                    System.out.print(matrix[i][j] + " ");
                    
                }
                
               System.out.println();
            }
            
        }
        
        System.out.println("Closing Scanner...");
        in.close();
        System.out.println("Scanner Closed");
        
       
        
    }
    
        
    
    public static int[] shortestPath() 
    {
        return resultArray;
    }
    
    public static int verticesNumber() 
    {
       return numberOfVertices;
    }
    
    public static int sValue() 
    {
        return s;
    }
    
    public static int tValue() 
    {
        return t;
    }
       
    
}
