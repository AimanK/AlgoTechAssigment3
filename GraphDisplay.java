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
    

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphDisplay extends JPanel
{
    
    private static final int SIZE = 256;
    private int a = SIZE / 2;
    private int b = a;
    private int r = 4 * SIZE / 5;
    private int n;
    
    //ArrayList<Integer> pointsX = new ArrayList<Integer>();
   // ArrayList<Integer> pointsY = new ArrayList<Integer>();
    
   
    
    ReadInputFromTextFile newRead = new ReadInputFromTextFile();
    
   
    Graph graphy = new Graph(newRead.numberOfVertices);
    
    
    
    
    
    public GraphDisplay(int n) 
    {
        this.setPreferredSize(new Dimension(SIZE, SIZE));
        this.n = n;
    }
           
    public void paint(Graphics g)
    {    
        int leftX = 100;
        int topY = 100;
        int width = 50;
        int height = 50;
        int labelX = 17;
        int labelY = 31;
        int gridWidth = 150;
        
       
       a = getWidth() / 2;
       b = getHeight() / 2;
       int m =  Math.min(a, b);
       r = 4 * m / 5;
       int r2 = Math.abs(m - r) / 2;
       
        /*   
       // Looping through the vertices in order to find the points of each vertex and add to list
          for (int i = 0; i < graphy.matrix.length; i++)
       {
           for (int j = 0; j < graphy.matrix.length; j++)
           {
           // Define where a specific vertex will fall on a specific coordinate
           // within the frame.
           
           int x = (int) newRead.pointsX.get(i);
           int y = (int) newRead.pointsY.get(j);

             // Store the coordinates of the points:
            // pointsX.add(x);
             //pointsY.add(y);
             
       }
       }
          */
          
          
    /* Create a nested for loop to see if there is an edge between vertices,
     *  If there is, set color and create an edge.
     */
    for (int i = 0; i < newRead.numberOfVertices; i++)
    {
        for (int j = 0; j < newRead.numberOfVertices; j++)
        {   /*
            if (graphy.matrix[i][j] > 0) 
            */
            
        {
            // Fetching the coordinates of the vertices from the ArrayList
            int xi = newRead.pointsX.get(i);
            int yi = newRead.pointsY.get(i);
            
            int xj = newRead.pointsX.get(j);
            int yj = newRead.pointsY.get(j);
            
            g.setColor(Color.BLACK);
            g.drawLine(xi,yi,xj,yj); // (3,3 , 4,4)
            g.drawString(String.valueOf(Math.round(Math.hypot(yj-yi, xj-xi))), (Math.round(xi + xj) / 2) , (Math.round(yi + yj) / 2) );
                            //(Math.round(xi + yj) / 2) , (Math.round(yi + yj) / 2)
                            //(Math.round(xi + yj) / 2) , (Math.round(xj + yi) / 2)
           
           
        }
          
        }
        
    }
    
    /*
     * This was our attempt to highlight the edges on the graph according to the
     * shortest path algorithm results from s to t.
    /*
    for (int i = 0; i < newRead.resultArray.length; i++) 
    {
        if (i == newRead.resultArray.length-1) 
        {
            break;
        }
        
        int pos1 = newRead.resultArray[i];
        
        int x1 = pointsX.get(pos1);
        int y1 = pointsY.get(pos1);
        
        int pos2 = newRead.resultArray[i++];
        
        int x2 = pointsX.get(pos2);
        int y2 = pointsY.get(pos2);
        
        
        g.setColor(Color.RED);
        g.drawLine(x1,y1,x2,y2);
        
        
    }
    */
    
    
       
       // This creates the vertices on the graph and labels them properly.
       for (int i = 0; i < graphy.matrix.length; i++)
       {
             String str = Integer.toHexString(i);
            //Define where a specific vertex will fall on a x, y coordinate
            //inside the container.
           //double t = 2 * Math.PI * i / n;
          
           int x = newRead.pointsX.get(i);
           int y = newRead.pointsY.get(i);


            //Setting the properties of each vertex color, border, and number
             g.setColor(Color.GREEN);
             g.fillOval(x, y, r2, r2);
             g.setColor(Color.BLACK);
             g.drawOval(x, y, r2, r2  );
             g.drawString(String.valueOf(i), x + 10, y + 20);
       }
       
       
       
    }
}
