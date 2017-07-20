//TODO create file comment
package com.chaoticcognitions.aenigma.views;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.chaoticcognitions.aenigma.R;
import com.chaoticcognitions.aenigma.models.Machine;
import com.chaoticcognitions.aenigma.models.MachineType;
import com.chaoticcognitions.aenigma.models.RotorType;
import com.chaoticcognitions.aenigma.presenters.EnigmaPresenter;

//TODO create class comment
public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner machineTypeSelectionSpinner;

    private Spinner reflectorSelectionSpinner;

    private TextView greekRotorLabel;
    private Spinner greekRotorSelectionSpinner;
    private Spinner greekRingSettingSpinner;
    private Spinner greekPositionSelectionSpinner;

    private Spinner leftRotorSelectionSpinner;
    private Spinner leftRingSettingSpinner;
    private Spinner leftPositionSelectionSpinner;

    private Spinner middleRotorSelectionSpinner;
    private Spinner middleRingSettingSpinner;
    private Spinner middlePositionSelectionSpinner;

    private Spinner rightRotorSelectionSpinner;
    private Spinner rightRingSettingSpinner;
    private Spinner rightPositionSelectionSpinner;

    ArrayAdapter<MachineType> machineTypeSelectionArrayAdapter;
    ArrayAdapter<RotorType> reflectorSelectionArrayAdapter;
    ArrayAdapter<RotorType> greekRotorSelectionArrayAdapter;
    ArrayAdapter<RotorType> leftRotorSelectionArrayAdapter;
    ArrayAdapter<RotorType> middleRotorSelectionArrayAdapter;
    ArrayAdapter<RotorType> rightRotorSelectionArrayAdapter;

    private EditText plugboardPairsEditText;

    EnigmaPresenter presenter;

    //TODO create method comment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent = getIntent();
        presenter = intent.getParcelableExtra("presenter");


        // Save the views to class variables.
        machineTypeSelectionSpinner = (Spinner) findViewById(R.id.machineTypeSelectionSpinner);

        reflectorSelectionSpinner = (Spinner) findViewById(R.id.reflectorSelectionSpinner);

        greekRotorLabel = (TextView) findViewById(R.id.greekRotorLabelTextView);
        greekRotorSelectionSpinner = (Spinner) findViewById(R.id.greekRotorSelectionSpinner);
        greekRingSettingSpinner = (Spinner) findViewById(R.id.greekRingSettingSpinner);
        greekPositionSelectionSpinner = (Spinner) findViewById(R.id.greekPositionSelectionSpinner);

        leftRotorSelectionSpinner = (Spinner) findViewById(R.id.leftRotorSelectionSpinner);
        leftRingSettingSpinner = (Spinner) findViewById(R.id.leftRingSettingSpinner);
        leftPositionSelectionSpinner = (Spinner) findViewById(R.id.leftPositionSelectionSpinner);

        middleRotorSelectionSpinner = (Spinner) findViewById(R.id.middleRotorSelectionSpinner);
        middleRingSettingSpinner = (Spinner) findViewById(R.id.middleRingSettingSpinner);
        middlePositionSelectionSpinner = (Spinner) findViewById(R.id.middlePositionSelectionSpinner);

        rightRotorSelectionSpinner = (Spinner) findViewById(R.id.rightRotorSelectionSpinner);
        rightRingSettingSpinner = (Spinner) findViewById(R.id.rightRingSettingSpinner);
        rightPositionSelectionSpinner = (Spinner) findViewById(R.id.rightPositionSelectionSpinner);

        plugboardPairsEditText = (EditText) findViewById(R.id.plugboardPairsEditText);


        // Set up and save the array adapters for the non-changing spinners.
        // Machine Type
        machineTypeSelectionArrayAdapter = new ArrayAdapter<>(this, R.layout.simple_spinner_item, MachineType.values());
        machineTypeSelectionArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        machineTypeSelectionSpinner.setAdapter(machineTypeSelectionArrayAdapter);

        // Greek Rotor
        ArrayAdapter<CharSequence> greekRingSettingArrayAdapter = ArrayAdapter.createFromResource(this, R.array.rotor_alphanumeric, R.layout.simple_spinner_item);
        greekRingSettingArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        greekRingSettingSpinner.setAdapter(greekRingSettingArrayAdapter);

        ArrayAdapter<CharSequence> greekPositionSelectionArrayAdapter = ArrayAdapter.createFromResource(this, R.array.rotor_alphanumeric, R.layout.simple_spinner_item);
        greekPositionSelectionArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        greekPositionSelectionSpinner.setAdapter(greekPositionSelectionArrayAdapter);

        // Left Rotor
        ArrayAdapter<CharSequence> leftRingSettingArrayAdapter = ArrayAdapter.createFromResource(this, R.array.rotor_alphanumeric, R.layout.simple_spinner_item);
        leftRingSettingArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        leftRingSettingSpinner.setAdapter(leftRingSettingArrayAdapter);

        ArrayAdapter<CharSequence> leftPositionSelectionArrayAdapter = ArrayAdapter.createFromResource(this, R.array.rotor_alphanumeric, R.layout.simple_spinner_item);
        leftPositionSelectionArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        leftPositionSelectionSpinner.setAdapter(leftPositionSelectionArrayAdapter);

        // Middle Rotor
        ArrayAdapter<CharSequence> middleRingSettingArrayAdapter = ArrayAdapter.createFromResource(this, R.array.rotor_alphanumeric, R.layout.simple_spinner_item);
        middleRingSettingArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        middleRingSettingSpinner.setAdapter(middleRingSettingArrayAdapter);

        ArrayAdapter<CharSequence> middlePositionSelectionArrayAdapter = ArrayAdapter.createFromResource(this, R.array.rotor_alphanumeric, R.layout.simple_spinner_item);
        middlePositionSelectionArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        middlePositionSelectionSpinner.setAdapter(middlePositionSelectionArrayAdapter);

        // Right Rotor
        ArrayAdapter<CharSequence> rightRingSettingArrayAdapter = ArrayAdapter.createFromResource(this, R.array.rotor_alphanumeric, R.layout.simple_spinner_item);
        rightRingSettingArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        rightRingSettingSpinner.setAdapter(rightRingSettingArrayAdapter);

        ArrayAdapter<CharSequence> rightPositionSelectionArrayAdapter = ArrayAdapter.createFromResource(this, R.array.rotor_alphanumeric, R.layout.simple_spinner_item);
        rightPositionSelectionArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        rightPositionSelectionSpinner.setAdapter(rightPositionSelectionArrayAdapter);


        // Update the array adapters for the spinners.
        updateArrayAdapters();


        // Set the onItemSelected listeners for the spinners.
        machineTypeSelectionSpinner.setOnItemSelectedListener(this);

        reflectorSelectionSpinner.setOnItemSelectedListener(this);

        greekRotorSelectionSpinner.setOnItemSelectedListener(this);
        greekRingSettingSpinner.setOnItemSelectedListener(this);
        greekPositionSelectionSpinner.setOnItemSelectedListener(this);

        leftRotorSelectionSpinner.setOnItemSelectedListener(this);
        leftRingSettingSpinner.setOnItemSelectedListener(this);
        leftPositionSelectionSpinner.setOnItemSelectedListener(this);

        middleRotorSelectionSpinner.setOnItemSelectedListener(this);
        middleRingSettingSpinner.setOnItemSelectedListener(this);
        middlePositionSelectionSpinner.setOnItemSelectedListener(this);

        rightRotorSelectionSpinner.setOnItemSelectedListener(this);
        rightRingSettingSpinner.setOnItemSelectedListener(this);
        rightPositionSelectionSpinner.setOnItemSelectedListener(this);


        // Update the view.
        updateView();
    }

    //TODO create method comment
    private void updateView() {
        if (presenter.getMachineType().numberOfRotors() == 4) {
            greekRotorLabel.setVisibility(View.VISIBLE);
            greekRotorSelectionSpinner.setVisibility(View.VISIBLE);
            greekRingSettingSpinner.setVisibility(View.VISIBLE);
            greekPositionSelectionSpinner.setVisibility(View.VISIBLE);
        } else {
            greekRotorLabel.setVisibility(View.INVISIBLE);
            greekRotorSelectionSpinner.setVisibility(View.INVISIBLE);
            greekRingSettingSpinner.setVisibility(View.INVISIBLE);
            greekPositionSelectionSpinner.setVisibility(View.INVISIBLE);
        }

        machineTypeSelectionSpinner.setSelection(presenter.getMachineType().ordinal());

        reflectorSelectionSpinner.setSelection(presenter.getReflectorSpinnerPosition());

        greekRotorSelectionSpinner.setSelection(presenter.getGreekRotorSpinnerPosition());
        greekRingSettingSpinner.setSelection(presenter.getGreekRotorRingSettingSpinnerPosition());
        greekPositionSelectionSpinner.setSelection(presenter.getGreekRotorPositionSpinnerPosition());

        leftRotorSelectionSpinner.setSelection(presenter.getRotorSpinnerPosition(Machine.RotorPosition.LEFT));
        leftRingSettingSpinner.setSelection(presenter.getRotorRingSettingSpinnerPosition(Machine.RotorPosition.LEFT));
        leftPositionSelectionSpinner.setSelection(presenter.getRotorPositionSpinnerPosition(Machine.RotorPosition.LEFT));

        middleRotorSelectionSpinner.setSelection(presenter.getRotorSpinnerPosition(Machine.RotorPosition.MIDDLE));
        middleRingSettingSpinner.setSelection(presenter.getRotorRingSettingSpinnerPosition(Machine.RotorPosition.MIDDLE));
        middlePositionSelectionSpinner.setSelection(presenter.getRotorPositionSpinnerPosition(Machine.RotorPosition.MIDDLE));

        rightRotorSelectionSpinner.setSelection(presenter.getRotorSpinnerPosition(Machine.RotorPosition.RIGHT));
        rightRingSettingSpinner.setSelection(presenter.getRotorRingSettingSpinnerPosition(Machine.RotorPosition.RIGHT));
        rightPositionSelectionSpinner.setSelection(presenter.getRotorPositionSpinnerPosition(Machine.RotorPosition.RIGHT));

        plugboardPairsEditText.setText(presenter.getPlugboardDump());
    }

    //TODO create method comment
    private void updateArrayAdapters() {
        // Set up and save the array adapters for the spinners with changing data.
        // Reflector
        reflectorSelectionArrayAdapter = new ArrayAdapter<>(this, R.layout.simple_spinner_item, presenter.getMachineType().possibleReflectors());
        reflectorSelectionArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        reflectorSelectionSpinner.setAdapter(reflectorSelectionArrayAdapter);

        // Greek Rotor
        greekRotorSelectionArrayAdapter = new ArrayAdapter<>(this, R.layout.simple_spinner_item, presenter.getMachineType().possibleGreekRotors());
        greekRotorSelectionArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        greekRotorSelectionSpinner.setAdapter(greekRotorSelectionArrayAdapter);

        // Left Rotor
        leftRotorSelectionArrayAdapter = new ArrayAdapter<>(this, R.layout.simple_spinner_item, presenter.getMachineType().possibleRotors());
        leftRotorSelectionArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        leftRotorSelectionSpinner.setAdapter(leftRotorSelectionArrayAdapter);

        // Middle Rotor
        middleRotorSelectionArrayAdapter = new ArrayAdapter<>(this, R.layout.simple_spinner_item, presenter.getMachineType().possibleRotors());
        middleRotorSelectionArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        middleRotorSelectionSpinner.setAdapter(middleRotorSelectionArrayAdapter);

        // Right Rotor
        rightRotorSelectionArrayAdapter = new ArrayAdapter<>(this, R.layout.simple_spinner_item, presenter.getMachineType().possibleRotors());
        rightRotorSelectionArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        rightRotorSelectionSpinner.setAdapter(rightRotorSelectionArrayAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getIntent());
        intent.putExtra("presenter", presenter);
        setResult(RESULT_OK, intent);

        super.onBackPressed();
    }

    //region Spinner Item Selected Implementation
    //TODO create method comment
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        final char CHAR_OFFSET = 'A';
        
        switch (parent.getId()) {
            case R.id.machineTypeSelectionSpinner:
                presenter.setMachineType((MachineType) (parent.getSelectedItem()));
                updateArrayAdapters();
                updateView();
                break;

            case R.id.reflectorSelectionSpinner:
                presenter.setReflector((RotorType) (parent.getSelectedItem()));
                break;

            case R.id.greekRotorSelectionSpinner:
                presenter.setGreekRotor((RotorType) (parent.getSelectedItem()));
                break;

            case R.id.greekRingSettingSpinner:
                presenter.setGreekRingSetting((char) (pos + CHAR_OFFSET));
                break;

            case R.id.greekPositionSelectionSpinner:
                presenter.setGreekPosition((char) (pos + CHAR_OFFSET));
                break;

            case R.id.leftRotorSelectionSpinner:
                presenter.setLeftRotor((RotorType) (parent.getSelectedItem()));
                break;

            case R.id.leftRingSettingSpinner:
                presenter.setLeftRingSetting((char) (pos + CHAR_OFFSET));
                break;

            case R.id.leftPositionSelectionSpinner:
                presenter.setLeftPosition((char) (pos + CHAR_OFFSET));
                break;

            case R.id.middleRotorSelectionSpinner:
                presenter.setMiddleRotor((RotorType) (parent.getSelectedItem()));
                break;

            case R.id.middleRingSettingSpinner:
                presenter.setMiddleRingSetting((char) (pos + CHAR_OFFSET));
                break;

            case R.id.middlePositionSelectionSpinner:
                presenter.setMiddlePosition((char) (pos + CHAR_OFFSET));
                break;

            case R.id.rightRotorSelectionSpinner:
                presenter.setRightRotor((RotorType) (parent.getSelectedItem()));
                break;

            case R.id.rightRingSettingSpinner:
                presenter.setRightRingSetting((char) (pos + CHAR_OFFSET));
                break;

            case R.id.rightPositionSelectionSpinner:
                presenter.setRightPosition((char) (pos + CHAR_OFFSET));
                break;
        }
    }

    //TODO create method comment
    public void onNothingSelected(AdapterView<?> parent) {
        //TODO handle
        Log.i("SettingsActivity", "In onNothingSelected method.");
    }
    //endregion Spinner Item Selected Implementation

    //region Settings Menu
    // This region contains methods related to setting up and handling the settings action menu.
    //TODO create method comment
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    //TODO create method comment
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reset:
                presenter.reset();
                updateView();
                return true;

            case android.R.id.home:
                Intent intent = new Intent(getIntent());
                intent.setClass(this, MainActivity.class);
                intent.putExtra("presenter", presenter);
                NavUtils.navigateUpTo(this, intent);
                return true;

            /* Disabling "Help" and "About" items due to time constraints.
            case R.id.action_help:
                // TODO: Handle "Help" menu item.
                return true;

            case R.id.action_about:
                // TODO: Handle "About" menu item.
                return true;
            */

            default:
                return super.onContextItemSelected(item);
        }
    }
    //endregion Settings Menu
}
