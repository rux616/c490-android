/*--------------------------------------------------------------------------------------------------
 * Author:      Dan Cassidy
 * Date:        2015-07-25
 * Assignment:  HW6-2
 * Source File: MainActivity.java
 * Language:    Java
 * Course:      CSCI-C 490, Android Programming, MoWe 08:00
--------------------------------------------------------------------------------------------------*/
package dancassidy.targetheartratecalculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Main activity class for Target Heart Rate Calculator program.
 *
 * @author Dan Cassidy
 */
public class MainActivity extends Activity {
    private TextView targetHeartRateRangeTextView;

    /**
     * Main method that runs on application start.
     *
     * @param savedInstanceState The saced instance state.
     */
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText ageEditText = (EditText) findViewById(R.id.ageEditText);
        ageEditText.addTextChangedListener(ageEditTextWatcher);

        targetHeartRateRangeTextView =
                (TextView) findViewById(R.id.targetHeartRateRangeResultTextView);
    }

    /**
     * Listener for the age field to detect and handle changes.
     */
    private TextWatcher ageEditTextWatcher = new TextWatcher() {
        @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
            final int MAX_HEART_RATE = 220;
            final float LOWER_THR_MULT = 0.5f;
            final float UPPER_THR_MULT = 0.85f;

            try {
                int age = Integer.parseInt(s.toString());
                if (age > 220)
                    age = 220;

                int lowerTHR = Math.round((MAX_HEART_RATE - age) * LOWER_THR_MULT);
                int upperTHR = Math.round((MAX_HEART_RATE - age) * UPPER_THR_MULT);
                targetHeartRateRangeTextView.setText(Integer.toString(lowerTHR) + " to " +
                        Integer.toString(upperTHR));
            }
            catch (NumberFormatException e) {
                targetHeartRateRangeTextView.setText("");
            }
        }

        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
        @Override public void afterTextChanged(Editable s) { }
    };
}
