/*--------------------------------------------------------------------------------------------------
 * Author:      Dan Cassidy
 * Date:        2015-07-20
 * Assignment:  HW4-3
 * Source File: BlankTrimmerController.java
 * Language:    Java
 * Course:      CSCI-C 490, Android Programming, MoWe 08:00
--------------------------------------------------------------------------------------------------*/
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.*;

import javax.swing.JOptionPane;

/**
 * Controller for the Blank Trimmer program.
 * 
 * @author Dan Cassidy
 */
public class BlankTrimmerController
{
    BlankTrimmerView theView = new BlankTrimmerView();
    
    /**
     * No-parameter constructor.
     */
    public BlankTrimmerController()
    {
        // Add action listener to the trim button in the view.
        theView.addTrimButtonActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                String message = "";
                int messageType = 0;
                
                try
                {
                    // Try to trim the file.
                    BlankTrimmer.trim(theView.getFilePath());
                    message = "Blanks trimmed successfully.";
                    messageType = JOptionPane.INFORMATION_MESSAGE;
                }
                catch (InvalidPathException ex)
                {
                    // Path couldn't be parsed. Not a valid path or a bad character or something.
                    message = "Invalid file path.\n" + ex.getReason();
                    messageType = JOptionPane.ERROR_MESSAGE;
                }
                catch (NoSuchFileException ex)
                {
                    // File couldn't be found.
                    message = "File does not exist.";
                    messageType = JOptionPane.ERROR_MESSAGE;
                }
                catch (SecurityException ex)
                {
                    // A file couldn't be read or couldn't be written to.
                    message = "Access denied.\n" + ex.getMessage();
                    messageType = JOptionPane.ERROR_MESSAGE;
                }
                catch (IOException ex)
                {
                    // Generic I/O exception.
                    message = "I/O exception:\n" + ex.getMessage();
                    messageType = JOptionPane.ERROR_MESSAGE;
                }
                catch (Exception ex)
                {
                    // Some other exception that wasn't explicitly planned for.
                    message = "Unknown error:\n" + ex.getMessage();
                    messageType = JOptionPane.ERROR_MESSAGE;
                    ex.printStackTrace();
                }
                finally
                {
                    // Always display a message as to how the operation went.
                    theView.showMessage(message, messageType);
                }
            }
        });
    }
    
    /**
     * Show the view.
     */
    public void showView()
    {
        theView.setVisible(true);
    }
}
