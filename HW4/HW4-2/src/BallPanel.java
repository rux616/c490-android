
// JPanel that creates a ball when the mouse is pressed. Ball bounces
// around the JPanel.
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JPanel;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class BallPanel extends JPanel
{
    private final int       MAX_X         = 200;                 // horizontal edge of JPanel
    private final int       MAX_Y         = 200;                 // vertical edge of JPanel
    private final int       MAX_BALLS     = 20;                  // maximum number of balls
    private Ball[]          balls         = new Ball[MAX_BALLS]; // array to hold the balls
    private int             ballCount     = 0;                   // current number of balls
    private ExecutorService threadExecutor;                      // for running Ball runnable
    private JFrame          parent;                              // parent window of JPanel
    
    public BallPanel(JFrame window)
    {
        parent = window; // set parent window of JPanel
        
        // create ExecutorService for running ball runnable
        threadExecutor = Executors.newCachedThreadPool();
        
        // let BallPanel be its own MouseListener
        addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent event)
            {
                createBall(event); // delegate call to ball starter
            } // end method mousePressed
        } // end anonymous inner class
        ); // end call to addMouseListener
    } // end BallPanel constructor
    
    // create a ball and set it in motion if no ball exists
    private void createBall(MouseEvent event)
    {
        // if ( blueBall == null ) // if no ball exists
        if (ballCount < MAX_BALLS)
        {
            int x = event.getX(); // get x position of mouse press
            int y = event.getY(); // get y position of mouse press
            balls[ballCount] = new Ball(parent, x, y); // create new ball
            threadExecutor.execute(balls[ballCount++]); // set ball in motion and increment count
        } // end if
    } // end method createBall
    
    // return minimum size of animation
    public Dimension getMinimumSize()
    {
        return getPreferredSize();
    } // end method getMinimumSize
    
    // return preferred size of animation
    public Dimension getPreferredSize()
    {
        return new Dimension(MAX_X, MAX_Y);
    } // end method getPreferredSize
    
    // draw ball at current position
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        // Loop through all the existing balls.
        for (int ballIndex = 0; ballIndex < ballCount; ballIndex++)
        {
            g.setColor(balls[ballIndex].getColor()); // Sets color.
            
            // draw ball
            g.fillOval(balls[ballIndex].getX(), balls[ballIndex].getY(), 10, 10);
        } // end if
    } // end method paintComponent
} // end class BallPanel