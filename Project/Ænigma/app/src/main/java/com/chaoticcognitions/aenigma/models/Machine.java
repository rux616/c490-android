//TODO create file comment
package com.chaoticcognitions.aenigma.models;

import android.os.Parcel;
import android.os.Parcelable;

import static com.chaoticcognitions.aenigma.models.Rotor.SignalDirection;

/**
 * TODO finish class comment
 * @author Dan Cassidy
 */
public class Machine implements Parcelable {
    public enum RotorPosition {RIGHT, MIDDLE, LEFT, GREEK, REFLECTOR}

    //TODO comment field groupings
    private MachineType machineType;
    private int numberOfRotors;

    private RotorType[] possibleStators;
    private RotorType[] possibleRotors;
    private RotorType[] possibleGreekRotors;
    private RotorType[] possibleReflectors;

    private Rotor reflector;
    private Rotor[] rotors;
    private Rotor stator;

    private Plugboard plugboard;

    //region Constructors.
    //TODO create method comment
    public Machine() {
        this(MachineType.values()[0]);
    }

    //TODO create method comment
    public Machine(MachineType machineType) {
        this.setMachineType(machineType);

        plugboard = new Plugboard();
    }
    //endregion

    //region Getters and setters.
    public MachineType getMachineType() {
        return machineType;
    }

    public void setMachineType(MachineType machineType) {
        if (this.machineType == machineType)
            return;

        this.machineType = machineType;

        numberOfRotors = this.machineType.numberOfRotors();
        possibleStators = this.machineType.possibleStators();
        possibleRotors = this.machineType.possibleRotors();
        possibleGreekRotors = this.machineType.possibleGreekRotors();
        possibleReflectors = this.machineType.possibleReflectors();

        //TODO add copy mechanism to copy what settings it can from machine to machine
        Rotor[] oldRotors = rotors;
        rotors = new Rotor[numberOfRotors];

        if (oldRotors == null) {
            reflector = new Rotor(possibleReflectors[0]);
            if (isValidPosition(RotorPosition.GREEK))
                rotors[RotorPosition.GREEK.ordinal()] = new Rotor(possibleGreekRotors[0]);
            rotors[RotorPosition.LEFT.ordinal()] = new Rotor(possibleRotors[0]);
            rotors[RotorPosition.MIDDLE.ordinal()] = new Rotor(possibleRotors[1]);
            rotors[RotorPosition.RIGHT.ordinal()] = new Rotor(possibleRotors[2]);
            stator = new Rotor(possibleStators[0]);
        } else {
            reflector.setRotorType(possibleReflectors[0]);
            if (isValidPosition(RotorPosition.GREEK))
                if (oldRotors.length == 4) {
                    rotors[RotorPosition.GREEK.ordinal()] = oldRotors[RotorPosition.GREEK.ordinal()];
                    rotors[RotorPosition.GREEK.ordinal()].setRotorType(possibleGreekRotors[0]);
                } else
                    rotors[RotorPosition.GREEK.ordinal()] = new Rotor(possibleGreekRotors[0]);

            rotors[RotorPosition.LEFT.ordinal()] = oldRotors[RotorPosition.LEFT.ordinal()];
            rotors[RotorPosition.LEFT.ordinal()].setRotorType(possibleRotors[0]);
            rotors[RotorPosition.MIDDLE.ordinal()] = oldRotors[RotorPosition.MIDDLE.ordinal()];
            rotors[RotorPosition.MIDDLE.ordinal()].setRotorType(possibleRotors[1]);
            rotors[RotorPosition.RIGHT.ordinal()] = oldRotors[RotorPosition.RIGHT.ordinal()];
            rotors[RotorPosition.RIGHT.ordinal()].setRotorType(possibleRotors[2]);

            stator.setRotorType(possibleStators[0]);
        }
    }

    public void setStator(RotorType statorType) {
        if (!isValidStator(statorType))
            throw new IllegalArgumentException("Invalid stator type.");

        // Copy existing stator to preserve its settings.
        stator = new Rotor(stator);
        stator.setRotorType(statorType);
    }

    public Rotor getReflector() {
        return new Rotor(reflector);
    }

    public void setReflector(RotorType reflectorType) {
        if (!isValidReflector(reflectorType))
            throw new IllegalArgumentException("Invalid reflector type.");

        // Copy existing reflector to preserve its settings.
        reflector = new Rotor(reflector);
        reflector.setRotorType(reflectorType);
    }

    public Rotor getRotor(RotorPosition position) throws IllegalArgumentException {
        if (!isValidPosition(position))
            throw new IllegalArgumentException("Invalid rotor position.");
        return new Rotor(rotors[position.ordinal()]);
    }

    public String[] getRotorNames() {
        String[] toReturn = new String[rotors.length];
        for (int index = 0; index < toReturn.length; index++)
            toReturn[index] = rotors[index].getRotorType().toString();

        return toReturn;
    }

    public void setRotor(RotorType rotorType, RotorPosition position) {
        if (!isValidRotorPosition(rotorType, position))
            throw new IllegalArgumentException("Invalid rotor type or position.");

        // Copy existing rotor to preserve its settings.
        rotors[position.ordinal()] = new Rotor(rotors[position.ordinal()]);
        rotors[position.ordinal()].setRotorType(rotorType);
    }

    public Plugboard getPlugboard() {
        return new Plugboard(plugboard);
    }

    public void setPlugboardPairs(String plugPairs) {
        for (int index = 0; index < plugPairs.length() && index + 1 < plugPairs.length(); index += 2)
            plugboard.addPlugPair(plugPairs.charAt(index), plugPairs.charAt(index + 1));
    }

    public void setRingSetting(char ringSetting, RotorPosition position) {
        if (!isValidPosition(position))
            throw new IllegalArgumentException("Invalid position.");
        if (position == RotorPosition.REFLECTOR)
            reflector.setRingSetting(ringSetting);
        else
            rotors[position.ordinal()].setRingSetting(ringSetting);
    }

    public void setRotorVisiblePosition(char visiblePosition, RotorPosition position) {
        if (!isValidPosition(position))
            throw new IllegalArgumentException("Invalid position.");
        if (position == RotorPosition.REFLECTOR)
            reflector.setPosition(visiblePosition);
        else
            rotors[position.ordinal()].setPosition(visiblePosition);
    }

    public String[] getRotorVisiblePositions() {
        String[] toReturn = new String[rotors.length];
        for (int index = 0; index < toReturn.length; index++)
            toReturn[index] = rotors[index].getPositionString();

        return toReturn;
    }
    //endregion Getters and setters.

    //TODO create method comment
    public char encode(char inputChar) {
        // Only encode the character if it is between A and Z.
        if (inputChar >= 'A' && inputChar <= 'Z') {
            // Step the rotors.
            doStep();

            // Pass through the plugboard from right to left.
            inputChar = plugboard.encode(inputChar);

            // Pass through the stator from right to left.
            inputChar = stator.encode(inputChar, SignalDirection.RIGHT_TO_LEFT);

            // Pass through the rotor array from right to left.
            for (Rotor rotor : rotors)
                inputChar = rotor.encode(inputChar, SignalDirection.RIGHT_TO_LEFT);

            // Pass through the reflector from right to "left". (The reflector simply reflects the
            // signal back to the rotor array, causing it to go from left to right.)
            inputChar = reflector.encode(inputChar, SignalDirection.RIGHT_TO_LEFT);

            // Pass through the rotor array from left to right.
            for (int index = numberOfRotors - 1; index >= 0; index--)
                inputChar = rotors[index].encode(inputChar, SignalDirection.LEFT_TO_RIGHT);

            // Pass through the stator from left to right.
            inputChar = stator.encode(inputChar, SignalDirection.LEFT_TO_RIGHT);

            // Pass through the plugboard from left to right.
            inputChar = plugboard.encode(inputChar);

            // Return the encoded character.
            return inputChar;
        } else
            return '\0';

    }

    //TODO create method comment
    private void doStep() {
        // Add some variables to increase readability.
        int left = RotorPosition.LEFT.ordinal();
        int middle = RotorPosition.MIDDLE.ordinal();
        int right = RotorPosition.RIGHT.ordinal();

        //TODO see if this method can be optimized at all
        if (rotors[right].isAtTurnoverPosition()) {
            // Normal stepping.
            if (rotors[middle].isAtTurnoverPosition()) {
                if (rotors[left].isAtTurnoverPosition()) {
                    reflector.doStep();
                }
                rotors[left].doStep();
            }
            rotors[middle].doStep();
        } else {
            // Handle the double stepping anomaly.
            if (rotors[middle].isAtTurnoverPosition() && rotors[middle].justStepped()) {
                rotors[left].doStep();
                rotors[middle].doStep();
            }
        }
        rotors[right].doStep();
    }

    //TODO create method comment
    @Override public String toString() {
        String toReturn = "";
        for (Rotor rotor : rotors)
            toReturn = rotor.getPositionString() + (toReturn.isEmpty() ? "" : " ") + toReturn;
        return toReturn;
    }

    //TODO create method comment
    private boolean isValidStator(RotorType statorToValidate) {
        for (RotorType stator : possibleStators)
            if (statorToValidate == stator)
                return true;

        return false;
    }

    //TODO create method comment
    private boolean isValidRotor(RotorType rotorToValidate) {
        for (RotorType rotor : possibleRotors)
            if (rotorToValidate == rotor)
                return true;

        return false;
    }

    //TODO create method comment
    private boolean isValidGreekRotor(RotorType greekRotorToValidate) {
        for (RotorType rotor : possibleGreekRotors)
            if (greekRotorToValidate == rotor)
                return true;

        return false;
    }

    //TODO create method comment
    private boolean isValidReflector(RotorType reflectorToValidate) {
        for (RotorType reflector : possibleReflectors)
            if (reflectorToValidate == reflector)
                return true;

        return false;
    }

    //TODO create method comment
    private boolean isValidPosition(RotorPosition positionToValidate) {
        return !((positionToValidate == RotorPosition.GREEK && numberOfRotors != 4)
                || positionToValidate == RotorPosition.REFLECTOR);
    }

    /**
     * Validates whether a specific rotor type is valid in a specific rotor position.
     *
     * @param positionToValidate The position of the rotor.
     * @param rotorType The type of rotor.
     * @return boolean, specifying whether the rotor and position are valid (true) or not (false).
     */
    private boolean isValidRotorPosition(RotorType rotorType, RotorPosition positionToValidate) {
        return (isValidGreekRotor(rotorType) && positionToValidate == RotorPosition.GREEK) ||
                (isValidRotor(rotorType) && isValidPosition(positionToValidate) &&
                        positionToValidate != RotorPosition.GREEK);
    }

    //TODO create method comment
    private boolean isReady() {
        for (Rotor rotor : rotors)
            if (rotor == null)
                return false;
        return (stator != null && reflector != null && plugboard != null);
    }

    //region Parcelable Implementation
    // This region implements the methods for the Parcelable interface so this object can be passed
    // between activities.
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeSerializable(machineType);
        out.writeParcelable(reflector, 0);
        for (Rotor rotor : rotors)
            out.writeParcelable(rotor, 0);
        out.writeParcelable(stator, 0);
        out.writeParcelable(plugboard, 0);
    }

    public static final Parcelable.Creator<Machine> CREATOR
            = new Parcelable.Creator<Machine>() {
        public Machine createFromParcel(Parcel in) {
            return new Machine(in);
        }

        public Machine[] newArray(int size) {
            return new Machine[size];
        }
    };

    private Machine(Parcel in) {
        this.setMachineType((MachineType) in.readSerializable());
        reflector = in.readParcelable(Rotor.class.getClassLoader());
        for (int index = 0; index < rotors.length; index++)
            rotors[index] = in.readParcelable(Rotor.class.getClassLoader());
        stator = in.readParcelable(Rotor.class.getClassLoader());
        plugboard = in.readParcelable(Plugboard.class.getClassLoader());
    }
    //endregion Parcelable Implementation
}
