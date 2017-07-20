// TODO create file comment
package com.chaoticcognitions.aenigma.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Class to handle the functionality of the Enigma machine rotors.
 *
 * @author Dan Cassidy
 */
public class Rotor implements Parcelable {
    public enum SignalDirection {RIGHT_TO_LEFT, LEFT_TO_RIGHT}

    private static final char CHAR_OFFSET = 'A';
    private static final int CHAR_SET_SIZE = 26;

    // Basic rotor settings. They are stored inside the Rotor class itself to avoid having an
    // excessive amount of calls to the various methods of the RotorType enum.
    private RotorType rotorType;
    private String wiring;
    private String reverseWiring;
    private String turnoverChars;
    private boolean isSteppingRotor;
    private boolean isMarkedWithNumbers;

    // User-changeable fields.
    private char position = 'A';
    private char ringSetting = 'A';

    // Automatic field to deal with turnover and stepping.
    private boolean isAtTurnoverPosition = false;
    private boolean justStepped = false; //TODO needed?
    private boolean steppingBuffer = false; //TODO needed?

    /**
     * 1-parameter constructor.
     *
     * @param rotorType The type of rotor to construct.
     */
    public Rotor(RotorType rotorType) {
        this.setRotorType(rotorType);
    }

    /**
     * Copy constructor.
     *
     * @param rotor The rotor to be copied.
     */
    public Rotor(Rotor rotor) {
        this(rotor.rotorType);

        position = rotor.position;
        ringSetting = rotor.ringSetting;

        checkTurnover();
    }

    // BEGIN GETTERS AND SETTERS -->
    public RotorType getRotorType() {
        return rotorType;
    }

    public void setRotorType(RotorType rotorType) {
        this.rotorType = rotorType;

        wiring = this.rotorType.wiring();
        reverseWiring = this.rotorType.reverseWiring();
        turnoverChars = this.rotorType.turnoverChars();
        isSteppingRotor = this.rotorType.isSteppingRotor();
        isMarkedWithNumbers = this.rotorType.isMarkedWithNumbers();

        checkTurnover();
    }

    public char getPosition() {
        return position;
    }

    public String getPositionString() {
        if (isMarkedWithNumbers) {
            int visibleNumber = position - 'A' + 1;
            return (visibleNumber < 10 ? "0" + visibleNumber : "" + visibleNumber);
        } else
            return Character.toString(position);
    }

    public void setPosition(char position) throws IllegalArgumentException {
        if (!isValidChar(position))
            throw new IllegalArgumentException("Invalid position setting.");

        this.position = position;
        checkTurnover();
    }

    public char getRingSetting() {
        return ringSetting;
    }

    public void setRingSetting(char ringSetting) throws IllegalArgumentException {
        if (!isValidChar(ringSetting))
            throw new IllegalArgumentException("Invalid ring setting.");
        this.ringSetting = ringSetting;
    }

    public boolean justStepped() {
        return justStepped;
    }

    public boolean isAtTurnoverPosition() {
        return isAtTurnoverPosition;
    }
    // <-- END GETTERS AND SETTERS

    /**
     * Encodes a character where the input is on the main (right) side of the rotor.
     *
     * @param inputChar The character to encode.
     * @return The encoded character.
     */
    public char encode(char inputChar, SignalDirection direction) throws IllegalArgumentException {
        if (!isValidChar(inputChar))
            throw new IllegalArgumentException("Invalid");

        // Helper code for stepping. //TODO determine if actually needed
        if (steppingBuffer)
            steppingBuffer = false;
        else
            justStepped = false;

        // Determine the current offset.
        int offset = ringSetting - position;
        Log.i("Rotor", "Rotor " + rotorType + " [encode(" + direction.toString().charAt(0) +
                ")] current offset: " + offset + ".");

        // Remove the offset and normalize the result to get the true input character and handle any
        // rollover. (E.g. - A character beyond 'Z' will get rolled over from the end of the rotor
        // back to the beginning.)
        inputChar = normalize((char) (inputChar - offset));
        Log.i("Rotor", "Rotor " + rotorType + " [encode(" + direction.toString().charAt(0) +
                ")] true input character: " + inputChar + ".");

        // Get the corresponding character that the true input character is wired to on the rotor.
        char outputChar;
        if (direction == SignalDirection.RIGHT_TO_LEFT)
            outputChar = wiring.charAt(inputChar - CHAR_OFFSET);
        else
            outputChar = reverseWiring.charAt(inputChar - CHAR_OFFSET);
        Log.i("Rotor", "Rotor " + rotorType + " [encode(" + direction.toString().charAt(0) +
                ")] true output character: " + outputChar + ".");

        // Add the offset to the true output character, then normalize it to handle any rollover and
        // get the final output character.
        outputChar = normalize((char) (outputChar + offset));
        Log.i("Rotor", "Rotor " + rotorType + " [encode(" + direction.toString().charAt(0) +
                ")] final output character: " + outputChar + ".");

        // Return the final output character.
        return outputChar;
    }

    /**
     * Steps the rotor.
     */
    public void doStep() {
        // If this rotor does not step, just return, otherwise proceed with stepping.
        if (!isSteppingRotor)
            return;

        // TODO: Verify rotor stepping method. <-- Tentatively good.
        Log.i("Rotor", "Rotor " + rotorType + " stepping from '" + position + "' to '" +
                (position == 'Z' ? 'A' : (char) (position + 1)) + "'.");

        // Step the rotor and normalize (handle rollover) if needed.
        position = normalize(++position);

        // Set some flags to advertise the fact this rotor just stepped. TODO needed?
        justStepped = true;
        steppingBuffer = true;

        // Check to see if this rotor is at a turnover position.
        checkTurnover();
    }

    /**
     * Determines whether the rotor is at a turnover position or not.
     */
    private void checkTurnover() {
        isAtTurnoverPosition = (turnoverChars.indexOf(position) != -1);
    }

    /**
     * Determines whether the argument is a valid character.
     *
     * @param charToValidate The character to validate.
     * @return boolean, representing whether the argument is a valid character (true) or not
     * (false).
     */
    private boolean isValidChar(char charToValidate) {
        return (wiring.indexOf(charToValidate) != -1);
    }

    /**
     * Normalize the given character to within the rotor's character set. In other words, handle
     * the rollover from the end of the character set to the beginning, or from the beginning of the
     * character set to the end.
     *
     * @param charToNormalize The character to normalize.
     * @return char, containing the normalized character.
     */
    private char normalize(char charToNormalize) {
        if (charToNormalize < CHAR_OFFSET)
            charToNormalize += CHAR_SET_SIZE;
        else if (charToNormalize >= CHAR_OFFSET + CHAR_SET_SIZE)
            charToNormalize -= CHAR_SET_SIZE;
        return charToNormalize;
    }

    //region Parcelable Implementation
    // This region implements the methods for the Parcelable interface so this object can be passed
    // between activities.
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeSerializable(rotorType);
        out.writeInt(position);
        out.writeInt(ringSetting);
    }

    public static final Parcelable.Creator<Rotor> CREATOR
            = new Parcelable.Creator<Rotor>() {
        public Rotor createFromParcel(Parcel in) {
            return new Rotor(in);
        }

        public Rotor[] newArray(int size) {
            return new Rotor[size];
        }
    };

    private Rotor(Parcel in) {
        this.setRotorType((RotorType) in.readSerializable());
        this.setPosition((char) in.readInt());
        this.setRingSetting((char) in.readInt());
    }
    //endregion Parcelable Implementation
}
