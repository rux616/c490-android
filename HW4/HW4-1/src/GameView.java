/*--------------------------------------------------------------------------------------------------
 * Author:      Dan Cassidy
 * Date:        2015-07-18
 * Assignment:  HW4-1
 * Source File: GameView.java
 * Language:    Java
 * Course:      CSCI-C 490, Android Programming, MoWe 08:00
--------------------------------------------------------------------------------------------------*/
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * View for the Tic-Tac-Toe game. Handles the visual representation.
 * 
 * @author Dan Cassidy
 */
@SuppressWarnings("serial")
public class GameView extends JFrame
{
    private JLabel statusLabel;
    private JButton[][] board;
    private JLabel winningConditionsLabel;
    private JButton resetButton = new JButton("Reset");
    
    /**
     * 2-parameter constructor.
     * 
     * @param numRows The number of rows of buttons the game board will have.
     * @param numColumns The number of columns of buttons the game board will have.
     */
    public GameView(int numRows, int numColumns)
    {
        // General window options and layout.
        super("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(numColumns * 50, (numRows + 2) * 50);
        setLayout(new BorderLayout());
        
        // NORTH. Create and add the game status label.
        statusLabel = new JLabel();
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(statusLabel, BorderLayout.NORTH);
        
        // CENTER. Create and add the buttons for the board.
        JPanel boardPanel = new JPanel(new GridLayout(numRows, numColumns));
        board = new JButton[numRows][numColumns];
        for (int row = 0; row < numRows; row++)
            for (int column = 0; column < numColumns; column++)
            {
                board[row][column] = new JButton();
                board[row][column].setActionCommand("" + (row * numColumns + column));
                boardPanel.add(board[row][column]);
            }
        add(boardPanel, BorderLayout.CENTER);
        
        // SOUTH. Create and add a label detailing the winning conditions and a reset button.
        winningConditionsLabel = new JLabel();
        winningConditionsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel resetButtonPanel = new JPanel();
        resetButtonPanel.setLayout(new FlowLayout());
        resetButtonPanel.add(resetButton);
        JPanel bottomPanel = new JPanel(new GridLayout(2,1));
        bottomPanel.add(winningConditionsLabel);
        bottomPanel.add(resetButtonPanel);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Add an action listener to all of the board buttons.
     * 
     * @param listener The ActionListener to add to the buttons.
     */
    public void addBoardButtonActionListener(ActionListener listener)
    {
        for (JButton[] buttonRow : board)
            for (JButton button : buttonRow)
                button.addActionListener(listener);
    }
    
    /**
     * Add an action listener to the reset button.
     * 
     * @param listener The ActionListener to add.
     */
    public void addResetButtonActionListener(ActionListener listener)
    {
        resetButton.addActionListener(listener);
    }
    
    /**
     * Resets the status text and the board buttons to default.
     */
    public void reset()
    {
        statusLabel.setText("");
        setBoardEnabled(true);
        
        for (JButton[] buttonRow : board)
            for (JButton button : buttonRow)
                button.setText("");
    }
    
    /**
     * Interface method to set the text of a given board button.
     * 
     * @param row The board row of the button.
     * @param column The board column of the button.
     * @param text The String to set the text to.
     */
    public void setBoardButtonText(int row, int column, String text)
    {
        board[row][column].setText(text);
    }
    
    /**
     * Enables (or disables) the button board.
     * 
     * @param b true to enable the button board, otherwise false.
     */
    public void setBoardEnabled(boolean b)
    {
        for (JButton[] buttonRow : board)
            for (JButton button : buttonRow)
                button.setEnabled(b);
    }
    
    /**
     * Interface method to update the status label.
     * 
     * @param text The String to set the text to.
     */
    public void setStatusLabelText(String text)
    {
        statusLabel.setText(text);
    }
    
    /**
     * Interface method to update the winning conditions label.
     * 
     * @param text The String to set the text to.
     */
    public void setWinningConditionsLabelText(String text)
    {
        winningConditionsLabel.setText(text);
    }
}
