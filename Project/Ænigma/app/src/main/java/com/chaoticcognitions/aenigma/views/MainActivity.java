//TODO create file comment
package com.chaoticcognitions.aenigma.views;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chaoticcognitions.aenigma.R;
import com.chaoticcognitions.aenigma.presenters.EnigmaPresenter;

/**
 * TODO finish class comment
 */
public class MainActivity extends AppCompatActivity{
    private TextView machineTypeTextView;

    private TextView[] rotorLabels;
    private TextView[] rotorPositions;
    
    private TextView inputTextView;
    private ScrollView inputScrollView;
    private TextView outputTextView;
    private ScrollView outputScrollView;

    private int charsEncoded = 0;

    private EnigmaPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        machineTypeTextView = (TextView) findViewById(R.id.machineTypeTextView);

        rotorLabels = new TextView[]{
                (TextView) findViewById(R.id.rightRotorLabelTextView),
                (TextView) findViewById(R.id.middleRotorLabelTextView),
                (TextView) findViewById(R.id.leftRotorLabelTextView),
                (TextView) findViewById(R.id.greekRotorLabelTextView)};
        rotorPositions = new TextView[]{
                (TextView) findViewById(R.id.rightRotorPositionTextView),
                (TextView) findViewById(R.id.middleRotorPositionTextView),
                (TextView) findViewById(R.id.leftRotorPositionTextView),
                (TextView) findViewById(R.id.greekRotorPositionTextView)};

        inputTextView = (TextView) findViewById(R.id.inputTextView);
        inputScrollView = (ScrollView) findViewById(R.id.inputScrollView);
        outputTextView = (TextView) findViewById(R.id.outputTextView);
        outputScrollView = (ScrollView) findViewById(R.id.outputScrollView);

        Button[] buttons = new Button[]{
                (Button) findViewById(R.id.aButton), (Button) findViewById(R.id.bButton),
                (Button) findViewById(R.id.cButton), (Button) findViewById(R.id.dButton),
                (Button) findViewById(R.id.eButton), (Button) findViewById(R.id.fButton),
                (Button) findViewById(R.id.gButton), (Button) findViewById(R.id.hButton),
                (Button) findViewById(R.id.iButton), (Button) findViewById(R.id.jButton),
                (Button) findViewById(R.id.kButton), (Button) findViewById(R.id.lButton),
                (Button) findViewById(R.id.mButton), (Button) findViewById(R.id.nButton),
                (Button) findViewById(R.id.oButton), (Button) findViewById(R.id.pButton),
                (Button) findViewById(R.id.qButton), (Button) findViewById(R.id.rButton),
                (Button) findViewById(R.id.sButton), (Button) findViewById(R.id.tButton),
                (Button) findViewById(R.id.uButton), (Button) findViewById(R.id.vButton),
                (Button) findViewById(R.id.wButton), (Button) findViewById(R.id.xButton),
                (Button) findViewById(R.id.yButton), (Button) findViewById(R.id.zButton)};

        presenter = new EnigmaPresenter();

        for (Button button : buttons)
            button.setOnClickListener(buttonListener);

        unpackIntent(getIntent());
    }

    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i("buttonListener", "button '" + ((Button) v).getText() + "' clicked");
            addInputOutputText(((Button) v).getText());
        }
    };

    public void rotorPanelOnClick(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra("presenter", presenter);
        intent.putExtra("inputText", inputTextView.getText());
        intent.putExtra("outputText", outputTextView.getText());
        intent.putExtra("charsEncoded", charsEncoded);
        startActivityForResult(intent, 0);
    }

    private void unpackIntent(Intent data) {
        EnigmaPresenter tempPresenter = data.getParcelableExtra("presenter");
        if (tempPresenter != null)
            presenter = tempPresenter;

        CharSequence tempCharSequence = data.getCharSequenceExtra("inputText");
        if (tempCharSequence != null)
            inputTextView.setText(data.getCharSequenceExtra("inputText"));

        tempCharSequence = data.getCharSequenceExtra("outputText");
        if (tempCharSequence != null)
            outputTextView.setText(data.getCharSequenceExtra("outputText"));

        charsEncoded = data.getIntExtra("charsEncoded", 0);

        updateMachineType();
        updateRotorPanel();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK)
            unpackIntent(data);
    }

    private void updateMachineType() {
        machineTypeTextView.setText(presenter.getMachineName());
    }

    private void addInputOutputText(CharSequence s) {
        if (s == null || s.length() == 0)
            return;

        final int CHAR_GROUP_SIZE = 5;

        for (char inputChar : s.toString().toCharArray()) {
            char toAdd = presenter.encode(inputChar);
            if (toAdd != '\0') {
                if (charsEncoded % CHAR_GROUP_SIZE == 0 && inputTextView.getText().length() > 0) {
                    inputTextView.append(" ");
                    outputTextView.append(" ");
                }
                inputTextView.append(String.valueOf(inputChar));
                outputTextView.append(String.valueOf(toAdd));
                charsEncoded++;
                scrollInputOutputToBottom();
                updateRotorPanel();
            }
        }
    }

    private void scrollInputOutputToBottom() {
        inputScrollView.post(new Runnable() {
            public void run() {
                inputScrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
        outputScrollView.post(new Runnable() {
            public void run() {
                outputScrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }

    public void updateRotorPanel() {
        String[] rotorNames = presenter.getRotorNames();
        String[] rotorVisibleCharacters = presenter.getRotorPositions();

        // Check if a 4th displayable is there and show label and position text views if so,
        // otherwise hide the label and position text views.
        if (rotorNames.length == 4) {
            rotorLabels[3].setVisibility(View.VISIBLE);
            rotorPositions[3].setVisibility(View.VISIBLE);
        } else {
            rotorLabels[3].setVisibility(View.GONE);
            rotorPositions[3].setVisibility(View.GONE);
        }

        for (int index = 0; index < rotorNames.length; index++) {
            rotorLabels[index].setText(rotorNames[index]);
            rotorPositions[index].setText(rotorVisibleCharacters[index]);
        }
    }

    //region Main menu related methods.
    //TODO create method comment
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //TODO create method comment
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        switch (item.getItemId()) {
            case R.id.action_clear:
                inputTextView.setText("");
                outputTextView.setText("");
                charsEncoded = 0;
                return true;

            case R.id.action_paste_input:
                // Makes sure the clipboard has something in it, and that something is plaintext
                // before calling addInputOutputText() on it.
                if (clipboard.hasPrimaryClip() && clipboard.getPrimaryClipDescription()
                        .hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN))
                    addInputOutputText(clipboard.getPrimaryClip().getItemAt(0).getText());
                return true;

            case R.id.action_copy_output:
                ClipData clip = ClipData.newPlainText(getString(R.string.clipboard_copy_label), outputTextView.getText());
                clipboard.setPrimaryClip(clip);
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
    //endregion
}
