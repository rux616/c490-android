/*--------------------------------------------------------------------------------------------------
 * Author:      Dan Cassidy
 * Date:        2015-07-25
 * Assignment:  HW6-3
 * Source File: MainActivity.java
 * Language:    Java
 * Course:      CSCI-C 490, Android Programming, MoWe 08:00
--------------------------------------------------------------------------------------------------*/
package dancassidy.craps;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Main activity class for the Craps game.
 *
 * @author Dan Cassidy
 */
public class MainActivity extends Activity {
    private static int[] dieResourceIDs = new int[] {R.drawable.die1, R.drawable.die2,
            R.drawable.die3, R.drawable.die4, R.drawable.die5, R.drawable.die6};

    private ImageView pointDie1;
    private ImageView pointDie2;
    private ImageView auxDie1;
    private ImageView auxDie2;
    private TextView pointTextView;
    private TextView statusTextView;
    private Button playButton;
    private Button rollButton;

    private Craps gameInstance;

    /**
     * Main method that runs on application start.
     *
     * @param savedInstanceState The saved instance state.
     */
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pointDie1 = (ImageView) findViewById(R.id.pointDie1ImageView);
        pointDie2 = (ImageView) findViewById(R.id.pointDie2ImageView);
        auxDie1 = (ImageView) findViewById(R.id.auxDie1ImageView);
        auxDie2 = (ImageView) findViewById(R.id.auxDie2ImageView);
        pointTextView = (TextView) findViewById(R.id.pointTextView);
        statusTextView = (TextView) findViewById(R.id.statusTextView);
        playButton = (Button) findViewById(R.id.playButton);
        rollButton = (Button) findViewById(R.id.rollButton);

        playButton.setOnClickListener(buttonClickListener);
        rollButton.setOnClickListener(buttonClickListener);

        gameInstance = new Craps();
    }

    /**
     * Listener for the Play and Roll buttons to detect clicks.
     */
    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override public void onClick(View v) {
            if (v == playButton) {
                gameInstance.reset();
                viewReset();
            }

            gameInstance.roll();

            if (v == playButton) {
                pointDie1.setImageResource(dieResourceIDs[gameInstance.getPointRoll1() - 1]);
                pointDie1.setVisibility(View.VISIBLE);
                pointDie2.setImageResource(dieResourceIDs[gameInstance.getPointRoll2() - 1]);
                pointDie2.setVisibility(View.VISIBLE);
            }
            else {
                auxDie1.setImageResource(dieResourceIDs[gameInstance.getAuxRoll1() - 1]);
                auxDie1.setVisibility(View.VISIBLE);
                auxDie2.setImageResource(dieResourceIDs[gameInstance.getAuxRoll2() - 1]);
                auxDie2.setVisibility(View.VISIBLE);
            }

            switch (gameInstance.getGameStatus()) {
                case WIN:
                case LOSE:
                    playButton.setEnabled(true);
                    rollButton.setEnabled(false);
                    statusTextView.setText(gameInstance.getGameStatus() == Craps.Status.WIN ?
                            R.string.player_win : R.string.player_lose);
                    break;

                case ONGOING:
                    playButton.setEnabled(false);
                    rollButton.setEnabled(true);
                    pointTextView.setText(getString(R.string.point) + ": " +
                            Integer.toString(gameInstance.getPoint()));
                    pointTextView.setVisibility(View.VISIBLE);
                    statusTextView.setText(R.string.player_roll);
                    break;
            }

            statusTextView.setVisibility(View.VISIBLE);
        }
    };

    /**
     * Reset the visibility of components to default.
     */
    private void viewReset() {
        pointDie1.setVisibility(View.INVISIBLE);
        pointDie2.setVisibility(View.INVISIBLE);
        auxDie1.setVisibility(View.INVISIBLE);
        auxDie2.setVisibility(View.INVISIBLE);
        pointTextView.setVisibility(View.INVISIBLE);
        statusTextView.setVisibility(View.INVISIBLE);
    }
}
