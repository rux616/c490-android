/*--------------------------------------------------------------------------------------------------
 * Author:      Dan Cassidy
 * Date:        2015-08-11
 * Assignment:  Project
 * Source File: MachineType.java
 * Language:    Java
 * Course:      CSCI-C 490, Android Programming, MoWe 08:00
--------------------------------------------------------------------------------------------------*/
package com.chaoticcognitions.aenigma.models;

/**
 * Enum to store the relevant information about the different types of Enigma machines in a single
 * place.
 *
 * @author Dan Cassidy
 */
public enum MachineType {
    ENIGMA_I,
    ENIGMA_M3,
    ENIGMA_M4;

    /**
     * Gets a list of possible stators for the given machine type.
     */
    public RotorType[] possibleStators() {
        switch (this) {
            case ENIGMA_I:
                return new RotorType[]{RotorType.I_ETW};
            case ENIGMA_M3:
                return new RotorType[]{RotorType.M3_ETW};
            case ENIGMA_M4:
                return new RotorType[]{RotorType.M4_ETW};

            default:
                return new RotorType[]{};
        }
    }

    /**
     * Gets a list of possible rotors for the given machine type.
     */
    public RotorType[] possibleRotors() {
        switch (this) {
            case ENIGMA_I:
                return new RotorType[]{RotorType.I_I, RotorType.I_II, RotorType.I_III,
                        RotorType.I_IV, RotorType.I_V};
            case ENIGMA_M3:
                return new RotorType[]{RotorType.M3_I, RotorType.M3_II, RotorType.M3_III,
                        RotorType.M3_IV, RotorType.M3_V, RotorType.M3_VI, RotorType.M3_VII,
                        RotorType.M3_VIII};
            case ENIGMA_M4:
                return new RotorType[]{RotorType.M4_I, RotorType.M4_II, RotorType.M4_III,
                        RotorType.M4_IV, RotorType.M4_V, RotorType.M4_VI, RotorType.M4_VII,
                        RotorType.M4_VIII};

            default:
                return new RotorType[]{};
        }
    }

    /**
     * Gets a list of possible greek rotors for the given machine type. (Only applicable to the M4.)
     */
    public RotorType[] possibleGreekRotors() {
        switch (this) {
            case ENIGMA_I:
            case ENIGMA_M3:
                return new RotorType[]{};
            case ENIGMA_M4:
                return new RotorType[]{RotorType.M4_BETA, RotorType.M4_GAMMA};

            default:
                return new RotorType[]{};
        }
    }

    /**
     * Gets a list of possible reflectors for the given machine type.
     */
    public RotorType[] possibleReflectors() {
        switch (this) {
            case ENIGMA_I:
                return new RotorType[]{RotorType.I_UKW_A, RotorType.I_UKW_B, RotorType.I_UKW_C};
            case ENIGMA_M3:
                return new RotorType[]{RotorType.M3_UKW_B, RotorType.M3_UKW_C};
            case ENIGMA_M4:
                return new RotorType[]{RotorType.M4_UKW_B, RotorType.M4_UKW_C};

            default:
                return new RotorType[]{};
        }
    }

    /**
     * Gets the number of rotors a machine has, based on its type. Note that this is only describing
     * the number of actual rotors and does not include the stator or reflector.
     */
    public int numberOfRotors() {
        switch (this) {
            case ENIGMA_I:
            case ENIGMA_M3:
                return 3;
            case ENIGMA_M4:
                return 4;

            default:
                return 3;
        }
    }

    /**
     * Returns the string representation of the machine type.
     */
    @Override
    public String toString() {
        switch (this) {
            case ENIGMA_I:
                return "Enigma I";
            case ENIGMA_M3:
                return "Enigma M3";
            case ENIGMA_M4:
                return "Enigma M4";

            default:
                return "Unknown";
        }
    }
}
