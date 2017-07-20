/*--------------------------------------------------------------------------------------------------
 * Author:      Dan Cassidy and Dr. Zhang
 * Date:        2015-07-11
 * Assignment:  HW3-3
 * Source File: ModifiedPanelDemo.java
 * Language:    Java
 * Course:      CSCI-C 490, Android Programming, MoWe 08:00
--------------------------------------------------------------------------------------------------*/
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModifiedPanelDemo extends JFrame implements ActionListener
{
    public static final int WIDTH  = 300;
    public static final int HEIGHT = 200;
    
    private JPanel pinkPanel;
    private JPanel greenPanel;
    private JPanel yellowPanel;
    private JPanel redPanel;
    private JPanel whitePanel;
    private JPanel bluePanel;
    
    public static void main(String[] args)
    {
        ModifiedPanelDemo gui = new ModifiedPanelDemo();
        gui.setVisible(true);
    }
    
    /**
     * Default constructor. Handles the setup of all the GUI elements.
     */
    public ModifiedPanelDemo()
    {
        super("Panel Demonstration");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Set up the top row of buttons.
        JPanel buttonPanel1 = new JPanel();
        buttonPanel1.setBackground(Color.LIGHT_GRAY);
        buttonPanel1.setLayout(new FlowLayout());
        
        JButton pinkButton = new JButton("Pink");
        pinkButton.setBackground(Color.PINK);
        pinkButton.addActionListener(this);
        buttonPanel1.add(pinkButton);
        
        JButton greenButton = new JButton("Green");
        greenButton.setBackground(Color.GREEN);
        greenButton.addActionListener(this);
        buttonPanel1.add(greenButton);
        
        JButton yellowButton = new JButton("Yellow");
        yellowButton.setBackground(Color.YELLOW);
        yellowButton.addActionListener(this);
        buttonPanel1.add(yellowButton);
        
        add(buttonPanel1, BorderLayout.NORTH);
        
        // Set up the main color panel.
        JPanel biggerPanel = new JPanel();
        biggerPanel.setLayout(new GridLayout(2, 3));
        
        // Begin new row in main color panel.
        redPanel = new JPanel();
        redPanel.setBackground(Color.LIGHT_GRAY);
        biggerPanel.add(redPanel);
        
        whitePanel = new JPanel();
        whitePanel.setBackground(Color.LIGHT_GRAY);
        biggerPanel.add(whitePanel);
        
        bluePanel = new JPanel();
        bluePanel.setBackground(Color.LIGHT_GRAY);
        biggerPanel.add(bluePanel);
        
        // Begin new row in main color panel.
        pinkPanel = new JPanel();
        pinkPanel.setBackground(Color.LIGHT_GRAY);
        biggerPanel.add(pinkPanel);
        
        greenPanel = new JPanel();
        greenPanel.setBackground(Color.LIGHT_GRAY);
        biggerPanel.add(greenPanel);
        
        yellowPanel = new JPanel();
        yellowPanel.setBackground(Color.LIGHT_GRAY);
        biggerPanel.add(yellowPanel);
        
        add(biggerPanel, BorderLayout.CENTER);
        
        // Set up the bottom row of buttons.
        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.setBackground(Color.LIGHT_GRAY);
        buttonPanel2.setLayout(new FlowLayout());
        
        JButton redButton = new JButton("Red");
        redButton.setBackground(Color.RED);
        redButton.addActionListener(this);
        buttonPanel2.add(redButton);
        
        JButton whiteButton = new JButton("White");
        whiteButton.setBackground(Color.WHITE);
        whiteButton.addActionListener(this);
        buttonPanel2.add(whiteButton);
        
        JButton blueButton = new JButton("Blue");
        blueButton.setBackground(Color.BLUE);
        blueButton.addActionListener(this);
        buttonPanel2.add(blueButton);
        
        add(buttonPanel2, BorderLayout.SOUTH);
    }
    
    /**
     * Handles events generated by the buttons.
     * 
     * @param e Specifies the generated event.
     */
    public void actionPerformed(ActionEvent e)
    {
        String buttonString = e.getActionCommand();
        
        if (buttonString.equals("Pink"))
            pinkPanel.setBackground(Color.PINK);
        else if (buttonString.equals("Green"))
            greenPanel.setBackground(Color.GREEN);
        else if (buttonString.equals("Yellow"))
            yellowPanel.setBackground(Color.YELLOW);
        else if (buttonString.equals("Red"))
            redPanel.setBackground(Color.RED);
        else if (buttonString.equals("White"))
            whitePanel.setBackground(Color.WHITE);
        else if (buttonString.equals("Blue"))
            bluePanel.setBackground(Color.BLUE);
        else
            System.out.println("Unexpected error.");
    }
}
