package com.chaoticcognitions.aenigma.models.rotors;

/**
 * Superclass to handle the functionality of the Enigma machine rotors.
 *
 * @author Dan Cassidy
 */
public class Rotor {
    private static final int CHAR_SET_SIZE = 26;

    private RotorType rotorType;

    private final String wiring;
    private final String reverseWiring;
    private final String turnoverChars;
    private final boolean isSteppingRotor;
    private final boolean isMarkedWithNumbers;

    private char ringSetting = 'A';
    private char visiblePosition = 'A';

    private boolean stepNext = false;
    private boolean justStepped = false;

    public Rotor(RotorType rotorType) {
        this.rotorType = rotorType;
        wiring = rotorType.getWiring();
        reverseWiring = rotorType.getReverseWiring();
        turnoverChars = rotorType.getTurnoverChars();
        isSteppingRotor = rotorType.isSteppingRotor();
        isMarkedWithNumbers = rotorType.isMarkedWithNumbers();
    }

    // BEGIN GETTERS AND SETTERS -->
    public RotorType getRotorType() {
        return rotorType;
    }
    public String getVisiblePosition() {
        if (isMarkedWithNumbers) {
            int visibleNumber = visiblePosition - 'A' + 1;
            return (visibleNumber < 10 ? "0" + visibleNumber : "" + visibleNumber);
        } else
            return Character.toString(visiblePosition);
    }

    public void setVisiblePosition(char visiblePosition) {
        // TODO: Verify setVisiblePosition method.
        if (visiblePosition < 'A' || visiblePosition > 'Z')
            throw new IllegalArgumentException("Invalid position setting.");
        this.visiblePosition = visiblePosition;
    }

    public char getRingSetting() {
        return ringSetting;
    }

    public void setRingSetting(char ringSetting) {
        // TODO: Verify setRingSetting method.
        if (ringSetting < 'A' || ringSetting > 'Z')
            throw new IllegalArgumentException("Invalid ring setting.");
        this.ringSetting = ringSetting;
    }

    public char encode(char inputChar) {
        // TODO: Finish rotor encoding method.
        return wiring.charAt((inputChar + visiblePosition + ringSetting - (3 * 'A')) % CHAR_SET_SIZE);
    }

    public char encodeReverse(char inputChar) {
        // TODO: Finish rotor reverse encoding method.
        return reverseWiring.charAt((inputChar + visiblePosition + ringSetting - (3 * 'A')) % CHAR_SET_SIZE);
    }

    // Mimics the stepping mechanism of the Enigma machine, double stepping included.
    public void doStep() {
        if (isSteppingRotor) {
            // TODO: Verify rotor stepping method.
            visiblePosition++;
            if (visiblePosition > 'Z')
                visiblePosition -= CHAR_SET_SIZE;
        }
    }
}
