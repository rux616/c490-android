/*--------------------------------------------------------------------------------------------------
 * Author:      Dan Cassidy
 * Date:        2015-07-20
 * Assignment:  HW4-1
 * Source File: Program.java
 * Language:    Java
 * Course:      CSCI-C 490, Android Programming, MoWe 08:00
--------------------------------------------------------------------------------------------------*/
import javax.swing.UIManager;

/**
 * Main class for the Blank Trimmer program. Uses a modified MVC pattern as the BlankTrimmer class
 * is just a utility class and doesn't require instantiation.
 * 
 * @author Dan Cassidy
 */
public class Program
{
    /**
     * Main entry point for the program.
     * 
     * @param args Command line arguments. <i>Not used</i>.
     */
    public static void main(String[] args)
    {
        // Use the system's look and feel if possible.
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex)
        {
            System.err.println("Something went wrong trying to set the system look and feel.");
            System.err.println("Using the default.");
        }
        
        // Create new controller and show the GUI. 
        BlankTrimmerController control = new BlankTrimmerController();
        control.showView();
    }
}
