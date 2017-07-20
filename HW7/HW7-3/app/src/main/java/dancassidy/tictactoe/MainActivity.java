/*--------------------------------------------------------------------------------------------------
 * Author:      Dan Cassidy
 * Date:        2015-07-27
 * Assignment:  HW7-3
 * Source File: MainActivity.java
 * Language:    Java
 * Course:      CSCI-C 490, Android Programming, MoWe 08:00
--------------------------------------------------------------------------------------------------*/
package dancassidy.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Main activity class for the TicTacToe game.
 *
 * @author Dan Cassidy
 */
public class MainActivity extends Activity {
    TicTacToe theGame;
    TextView statusTextView;
    Button[] board;
    Button resetButton;

    /**
     * Main method that runs on application start.
     *
     * @param savedInstanceState The saved instance state.
     */
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        theGame = new TicTacToe();
        statusTextView = (TextView) findViewById(R.id.statusTextView);
        board = new Button[]{
                (Button) findViewById(R.id.xoButton1),
                (Button) findViewById(R.id.xoButton2),
                (Button) findViewById(R.id.xoButton3),
                (Button) findViewById(R.id.xoButton4),
                (Button) findViewById(R.id.xoButton5),
                (Button) findViewById(R.id.xoButton6),
                (Button) findViewById(R.id.xoButton7),
                (Button) findViewById(R.id.xoButton8),
                (Button) findViewById(R.id.xoButton9)};
        resetButton = (Button) findViewById(R.id.resetButton);

        // Set anonymous listeners for all the board buttons.
        for (Button theButton : board)
            theButton.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int row = Integer.parseInt(v.getTag().toString()) / theGame.getColumns();
                    int column = Integer.parseInt(v.getTag().toString()) % theGame.getColumns();

                    theGame.playMove(row, column);
                    ((Button) v).setText(theGame.getSpaceStringID(row, column));
                    statusTextView.setText(theGame.getStatusStringID());
                    if (theGame.getStatus() != TicTacToe.Status.IN_PROGRESS)
                        for (Button theButton : board)
                            theButton.setEnabled(false);
                }
            });

        // Set anonymous listener for the reset button.
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                theGame.reset();
                MainActivity.this.reset();
            }
        });
    }

    /**
     * Resets the status text and the board buttons to default.
     */
    private void reset() {
        statusTextView.setText(R.string.status_x_turn);
        for (Button theButton : board) {
            theButton.setEnabled(true);
            theButton.setText(R.string.blank);
        }
    }
}
