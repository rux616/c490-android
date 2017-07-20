//TODO add file comment
package com.chaoticcognitions.aenigma.presenters;

import android.os.Parcel;
import android.os.Parcelable;

import com.chaoticcognitions.aenigma.models.Machine;
import com.chaoticcognitions.aenigma.models.MachineType;
import com.chaoticcognitions.aenigma.models.Plugboard;
import com.chaoticcognitions.aenigma.models.Rotor;
import com.chaoticcognitions.aenigma.models.RotorType;

import static com.chaoticcognitions.aenigma.models.Machine.RotorPosition;

/**
 * TODO finish class comment
 *
 * @author Dan Cassidy
 */
public class EnigmaPresenter implements Parcelable {
    public static final Parcelable.Creator<EnigmaPresenter> CREATOR
            = new Parcelable.Creator<EnigmaPresenter>() {
        public EnigmaPresenter createFromParcel(Parcel in) {
            return new EnigmaPresenter(in);
        }

        public EnigmaPresenter[] newArray(int size) {
            return new EnigmaPresenter[size];
        }
    };
    
    private Machine theMachine;

    public EnigmaPresenter() {
//        theMachine = new Machine(MachineType.ENIGMA_M3);
//        theMachine.setReflector(RotorType.M3_UKW_B);
//        theMachine.setRotor(RotorType.M3_I, RotorPosition.LEFT);
//        theMachine.setRotor(RotorType.M3_II, RotorPosition.MIDDLE);
//        theMachine.setRotor(RotorType.M3_III, RotorPosition.RIGHT);
//        theMachine.setStator(RotorType.M3_ETW);
//        theMachine.setPosition('A', RotorPosition.LEFT);
//        theMachine.setPosition('A', RotorPosition.MIDDLE);
//        theMachine.setPosition('A', RotorPosition.RIGHT);

//        theMachine = new Machine(MachineType.ENIGMA_I);
//        theMachine.setStator(RotorType.I_ETW);
//        theMachine.setRotor(RotorType.I_II, RotorPosition.LEFT);
//        theMachine.setRotor(RotorType.I_I, RotorPosition.MIDDLE);
//        theMachine.setRotor(RotorType.I_III, RotorPosition.RIGHT);
//        theMachine.setReflector(RotorType.I_UKW_A);
//        theMachine.setPlugboardPairs("AMFINVPSTUWZ");
//        theMachine.setRingSetting((char) (24 + 'A' - 1), RotorPosition.LEFT);
//        theMachine.setRingSetting((char) (13 + 'A' - 1), RotorPosition.MIDDLE);
//        theMachine.setRingSetting((char) (22 + 'A' - 1), RotorPosition.RIGHT);
////        theMachine.setPosition((char) (6 + 'A' - 1), RotorPosition.LEFT);
////        theMachine.setPosition((char) (15 + 'A' - 1), RotorPosition.MIDDLE);
////        theMachine.setPosition((char) (12 + 'A' - 1), RotorPosition.RIGHT);
//        theMachine.setPosition('A', RotorPosition.LEFT);
//        theMachine.setPosition('B', RotorPosition.MIDDLE);
//        theMachine.setPosition('L', RotorPosition.RIGHT);

//        theMachine = new Machine(MachineType.ENIGMA_M4);
//        theMachine.setStator(RotorType.M4_ETW);
//        theMachine.setRotor(RotorType.M4_BETA, RotorPosition.GREEK);
//        theMachine.setRotor(RotorType.M4_V, RotorPosition.LEFT);
//        theMachine.setRotor(RotorType.M4_VI, RotorPosition.MIDDLE);
//        theMachine.setRotor(RotorType.M4_VIII, RotorPosition.RIGHT);
//        theMachine.setReflector(RotorType.M4_UKW_C);
//        theMachine.setPlugboardPairs("AEBFCMDQHUJNLXPRSZVW");
//        theMachine.setRingSetting('E', RotorPosition.GREEK);
//        theMachine.setRingSetting('P', RotorPosition.LEFT);
//        theMachine.setRingSetting('E', RotorPosition.MIDDLE);
//        theMachine.setRingSetting('L', RotorPosition.RIGHT);
////        theMachine.setPosition('N', RotorPosition.GREEK);
////        theMachine.setPosition('A', RotorPosition.LEFT);
////        theMachine.setPosition('E', RotorPosition.MIDDLE);
////        theMachine.setPosition('M', RotorPosition.RIGHT);
//        theMachine.setPosition('C', RotorPosition.GREEK);
//        theMachine.setPosition('D', RotorPosition.LEFT);
//        theMachine.setPosition('S', RotorPosition.MIDDLE);
//        theMachine.setPosition('Z', RotorPosition.RIGHT);

//        theMachine = new Machine(MachineType.ENIGMA_M4);
//        theMachine.setStator(RotorType.M4_ETW);
//        theMachine.setRotor(RotorType.M4_GAMMA, RotorPosition.GREEK);
//        theMachine.setRotor(RotorType.M4_IV, RotorPosition.LEFT);
//        theMachine.setRotor(RotorType.M4_III, RotorPosition.MIDDLE);
//        theMachine.setRotor(RotorType.M4_VIII, RotorPosition.RIGHT);
//        theMachine.setReflector(RotorType.M4_UKW_B);
//        theMachine.setPlugboardPairs("CHEJNVOUTYLGSZPKDIQB");
//        theMachine.setRingSetting('A', RotorPosition.GREEK);
//        theMachine.setRingSetting('A', RotorPosition.LEFT);
//        theMachine.setRingSetting('C', RotorPosition.MIDDLE);
//        theMachine.setRingSetting('U', RotorPosition.RIGHT);
//        theMachine.setPosition('V', RotorPosition.GREEK);
//        theMachine.setPosition('M', RotorPosition.LEFT);
//        theMachine.setPosition('G', RotorPosition.MIDDLE);
//        theMachine.setPosition('C', RotorPosition.RIGHT);

        theMachine = new Machine(MachineType.ENIGMA_M4);
        theMachine.setReflector(RotorType.M4_UKW_B);
        theMachine.setRotor(RotorType.M4_BETA, RotorPosition.GREEK);
        theMachine.setRotor(RotorType.M4_II, RotorPosition.LEFT);
        theMachine.setRotor(RotorType.M4_IV, RotorPosition.MIDDLE);
        theMachine.setRotor(RotorType.M4_I, RotorPosition.RIGHT);
        theMachine.setStator(RotorType.M4_ETW);
        theMachine.setPlugboardPairs("ATBLDFGJHMNWOPQYRZVX");
        theMachine.setRingSetting('A', RotorPosition.GREEK);
        theMachine.setRingSetting('A', RotorPosition.LEFT);
        theMachine.setRingSetting('A', RotorPosition.MIDDLE);
        theMachine.setRingSetting('V', RotorPosition.RIGHT);
        theMachine.setRotorVisiblePosition('V', RotorPosition.GREEK);
        theMachine.setRotorVisiblePosition('J', RotorPosition.LEFT);
        theMachine.setRotorVisiblePosition('N', RotorPosition.MIDDLE);
        theMachine.setRotorVisiblePosition('A', RotorPosition.RIGHT);

//        this.theMachine = new Machine();
    }

    private EnigmaPresenter(Parcel in) {
        theMachine = in.readParcelable(Machine.class.getClassLoader());
    }

    public char encode(char inputChar) {
        return theMachine.encode(inputChar);
    }

    public String[] getRotorNames() {
        return theMachine.getRotorNames();
    }

    public Rotor getReflector() {
        return theMachine.getReflector();
    }

    public void setReflector(RotorType reflectorType) {
        theMachine.setReflector(reflectorType);
    }

    public Rotor getGreekRotor() {
        try {
            return theMachine.getRotor(Machine.RotorPosition.GREEK);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public void setGreekRotor(RotorType rotorType) {
        theMachine.setRotor(rotorType, RotorPosition.GREEK);
    }

    public int getReflectorSpinnerPosition() {
        RotorType reflectorType = theMachine.getReflector().getRotorType();
        RotorType[] possibleReflectors = theMachine.getMachineType().possibleReflectors();
        for (int index = 0; index < possibleReflectors.length; index++)
            if (reflectorType == possibleReflectors[index])
                return index;
        return 0;
    }

    public int getGreekRotorSpinnerPosition() {
        try {
            RotorType greekRotorType = theMachine.getRotor(Machine.RotorPosition.GREEK).getRotorType();
            RotorType[] possibleGreekRotors = theMachine.getMachineType().possibleGreekRotors();
            for (int index = 0; index < possibleGreekRotors.length; index++)
                if (greekRotorType == possibleGreekRotors[index])
                    return index;
            return 0;
        } catch (IllegalArgumentException e) {
            return 0;
        }
    }

    public int getRotorSpinnerPosition(Machine.RotorPosition position) {
        try {
            RotorType rotorType = theMachine.getRotor(position).getRotorType();
            RotorType[] possibleRotors = theMachine.getMachineType().possibleRotors();
            for (int index = 0; index < possibleRotors.length; index++)
                if (rotorType == possibleRotors[index])
                    return index;
            return 0;
        } catch (IllegalArgumentException e) {
            return 0;
        }
    }

    public int getGreekRotorRingSettingSpinnerPosition() {
        try {
            return theMachine.getRotor(Machine.RotorPosition.GREEK).getRingSetting() - 'A';
        } catch (IllegalArgumentException e) {
            return 0;
        }
    }

    public int getRotorRingSettingSpinnerPosition(Machine.RotorPosition position) {
        return theMachine.getRotor(position).getRingSetting() - 'A';
    }

    public int getGreekRotorPositionSpinnerPosition() {
        try {
            return theMachine.getRotor(Machine.RotorPosition.GREEK).getPosition() - 'A';
        } catch (IllegalArgumentException e) {
            return 0;
        }
    }

    public int getRotorPositionSpinnerPosition(Machine.RotorPosition position) {
        return theMachine.getRotor(position).getPosition() - 'A';
    }

    public Rotor getLeftRotor() {
        return theMachine.getRotor(Machine.RotorPosition.LEFT);
    }

    public void setLeftRotor(RotorType rotorType) {
        theMachine.setRotor(rotorType, RotorPosition.LEFT);
    }

    public Rotor getRightMiddle() {
        return theMachine.getRotor(Machine.RotorPosition.MIDDLE);
    }

    public Rotor getRightRotor() {
        return theMachine.getRotor(Machine.RotorPosition.RIGHT);
    }

    public void setRightRotor(RotorType rotorType) {
        theMachine.setRotor(rotorType, RotorPosition.RIGHT);
    }

    public String getPlugboardDump() {
        return theMachine.getPlugboard().getPlugPairDump();
    }

    public String[] getRotorPositions() {
        return theMachine.getRotorVisiblePositions();
    }

    public String getMachineName() {
        return theMachine.getMachineType().toString();
    }

    public MachineType getMachineType() {
        return theMachine.getMachineType();
    }

    public void setMachineType(MachineType machineType) {
        theMachine.setMachineType(machineType);
    }

    public void reset() {
        theMachine = new Machine();
    }

    public void setMiddleRotor(RotorType rotorType) {
        theMachine.setRotor(rotorType, RotorPosition.MIDDLE);
    }

    public void setGreekRingSetting(char ringSetting) {
        try {
            theMachine.setRingSetting(ringSetting, RotorPosition.GREEK);
        } catch (IllegalArgumentException e) {
            // Do nothing, just eat the exception.
        }
    }
    
    public void setGreekPosition(char position) {
        try {
            theMachine.setRotorVisiblePosition(position, RotorPosition.GREEK);
        } catch (IllegalArgumentException e) {
            // Do nothing, just eat the exception.
        }
    }

    public void setLeftRingSetting(char ringSetting) {
        theMachine.setRingSetting(ringSetting, RotorPosition.LEFT);
    }

    public void setLeftPosition(char position) {
        theMachine.setRotorVisiblePosition(position, RotorPosition.LEFT);
    }

    public void setMiddleRingSetting(char ringSetting) {
        theMachine.setRingSetting(ringSetting, RotorPosition.MIDDLE);
    }

    public void setMiddlePosition(char position) {
        theMachine.setRotorVisiblePosition(position, RotorPosition.MIDDLE);
    }

    public void setRightRingSetting(char ringSetting) {
        theMachine.setRingSetting(ringSetting, RotorPosition.RIGHT);
    }

    public void setRightPosition(char position) {
        theMachine.setRotorVisiblePosition(position, RotorPosition.RIGHT);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(theMachine, 0);
    }
}
