/*--------------------------------------------------------------------------------------------------
 * Author:      Dan Cassidy
 * Date:        2015-07-24
 * Assignment:  HW6-1
 * Source File: MainActivity.java
 * Language:    Java
 * Course:      CSCI-C 490, Android Programming, MoWe 08:00
--------------------------------------------------------------------------------------------------*/
package dancassidy.bmicalculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Main activity class for the BMI Calculator program.
 *
 * @author Dan Cassidy
 */
public class MainActivity extends Activity {
    private EditText heightEditText;
    private EditText weightEditText;
    private TextView heightAbbreviationTextView;
    private TextView weightAbbreviationTextView;
    private TextView bmiResultTextView;
    private BMICalculator calculatorInstance;

    /**
     * Main method that runs on application start.
     *
     * @param savedInstanceState The saved instance state.
     */
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        heightEditText = (EditText) findViewById(R.id.heightEditText);
        weightEditText = (EditText) findViewById(R.id.weightEditText);
        heightAbbreviationTextView = (TextView) findViewById(R.id.heightAbbreviationTextView);
        weightAbbreviationTextView = (TextView) findViewById(R.id.weightAbbreviationTextView);
        bmiResultTextView = (TextView) findViewById(R.id.bmiResultTextView);
        RadioGroup unitsRadioGroup = (RadioGroup) findViewById(R.id.unitsRadioGroup);

        heightEditText.addTextChangedListener(heightEditTextWatcher);
        weightEditText.addTextChangedListener(weightEditTextWatcher);
        unitsRadioGroup.setOnCheckedChangeListener(unitsRadioGroupListener);

        calculatorInstance = new BMICalculator();

        updateBMI();
    }

    /**
     * Listener for the height field to detect when the input has changed.
     */
    private TextWatcher heightEditTextWatcher = new TextWatcher() {
        @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                calculatorInstance.setHeight(Double.parseDouble(s.toString()));
            }
            catch (NumberFormatException e) { }
            updateBMI();
        }

        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
        @Override public void afterTextChanged(Editable s) { }
    };

    /**
     * Listener for the weight field to detect when the input has changed.
     */
    private TextWatcher weightEditTextWatcher = new TextWatcher() {
        @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                calculatorInstance.setWeight(Double.parseDouble(s.toString()));
            }
            catch (NumberFormatException e) { }
            updateBMI();
        }

        @Override public void afterTextChanged(Editable s) { }
        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
    };

    /**
     * Listener for the RadioGroup to detect when the user has switched from metric to English units
     * and vice versa.
     */
    private RadioGroup.OnCheckedChangeListener unitsRadioGroupListener =
            new RadioGroup.OnCheckedChangeListener() {
        @Override public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.metricRadioButton) {
                calculatorInstance.setMetric(true);
                heightAbbreviationTextView.setText(R.string.meter_abbreviation);
                weightAbbreviationTextView.setText(R.string.kilogram_abbreviation);
            }
            else {
                calculatorInstance.setMetric(false);
                heightAbbreviationTextView.setText(R.string.inch_abbreviation);
                weightAbbreviationTextView.setText(R.string.pound_abbreviation);
            }
            heightEditText.setText(String.format("%.2f", calculatorInstance.getHeight()));
            weightEditText.setText(String.format("%.2f", calculatorInstance.getWeight()));
            updateBMI();
        }
    };

    /**
     * Helper method to update the activity's BMI display when needed.
     */
    private void updateBMI() {
        bmiResultTextView.setText(String.format("%.2f", calculatorInstance.getBMI()));
    }
}
