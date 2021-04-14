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

public class FrameDisplay extends JFrame
{
    int WIDTH = (900);
    int HEIGHT = (900);
    
     ReadInputFromTextFile newRead = new ReadInputFromTextFile();
    
   
     Graph graphy = new Graph(newRead.numberOfVertices);
    
    
    
    public FrameDisplay()
    {
        setTitle("Graph Display");
        setSize(WIDTH, HEIGHT);
        

        GraphDisplay panel = new GraphDisplay(newRead.numberOfVertices);
        add(panel);
    }
}
