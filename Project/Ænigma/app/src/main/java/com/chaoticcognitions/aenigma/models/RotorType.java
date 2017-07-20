/*--------------------------------------------------------------------------------------------------
 * Author:      Dan Cassidy
 * Date:        2015-08-11
 * Assignment:  Project
 * Source File: RotorType.java
 * Language:    Java
 * Course:      CSCI-C 490, Android Programming, MoWe 08:00
--------------------------------------------------------------------------------------------------*/
package com.chaoticcognitions.aenigma.models;

/**
 * Enum to store the relevant information about the different types of rotors in a single place.
 *
 * @author Dan Cassidy
 */
public enum RotorType {
    // Enigma I - German Army and Air Force (Wehrmacht, Luftwaffe)
    // Stator
    I_ETW,
    // Rotors
    I_I, I_II, I_III, I_IV, I_V,
    // Reflectors
    I_UKW_A, I_UKW_B, I_UKW_C,

    // Enigma M3 - German Navy (Kriegsmarine)
    // Stator
    M3_ETW,
    // Rotors
    M3_I, M3_II, M3_III, M3_IV, M3_V, M3_VI, M3_VII, M3_VIII,
    // Reflectors
    M3_UKW_B, M3_UKW_C,

    // Enigma M4 - U-Boot Enigma
    // Stator
    M4_ETW,
    // Rotors
    M4_I, M4_II, M4_III, M4_IV, M4_V, M4_VI, M4_VII, M4_VIII, M4_BETA, M4_GAMMA,
    // Reflectors
    M4_UKW_B, M4_UKW_C;

    /**
     * Get the wiring for the rotor based on its type. For instance, 'A' is wired to the first
     * letter of this string, 'B' is wired to the second letter, and so on.
     */
    public String wiring() {
        switch (this) {
            // Stators
            case I_ETW:
            case M3_ETW:
            case M4_ETW:
                return "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

            // Rotors
            case I_I:
            case M3_I:
            case M4_I:
                return "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
            case I_II:
            case M3_II:
            case M4_II:
                return "AJDKSIRUXBLHWTMCQGZNPYFVOE";
            case I_III:
            case M3_III:
            case M4_III:
                return "BDFHJLCPRTXVZNYEIWGAKMUSQO";
            case I_IV:
            case M3_IV:
            case M4_IV:
                return "ESOVPZJAYQUIRHXLNFTGKDCMWB";
            case I_V:
            case M3_V:
            case M4_V:
                return "VZBRGITYUPSDNHLXAWMJQOFECK";
            case M3_VI:
            case M4_VI:
                return "JPGVOUMFYQBENHZRDKASXLICTW";
            case M3_VII:
            case M4_VII:
                return "NZJHGRCXMYSWBOUFAIVLPEKQDT";
            case M3_VIII:
            case M4_VIII:
                return "FKQHTLXOCBJSPDZRAMEWNIUYGV";
            case M4_BETA:
                return "LEYJVCNIXWPBQMDRTAKZGFUHOS";
            case M4_GAMMA:
                return "FSOKANUERHMBTIYCWLQPZXVGJD";

            // Reflectors
            case I_UKW_A:
                return "EJMZALYXVBWFCRQUONTSPIKHGD";
            case I_UKW_B:
            case M3_UKW_B:
                return "YRUHQSLDPXNGOKMIEBFZCWVJAT";
            case I_UKW_C:
            case M3_UKW_C:
                return "FVPJIAOYEDRZXWGCTKUQSBNMHL";
            case M4_UKW_B:
                return "ENKQAUYWJICOPBLMDXZVFTHRGS";
            case M4_UKW_C:
                return "RDOBJNTKVEHMLFCWZAXGYIPSUQ";

            default:
                return "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        }
    }

    /**
     * Get the reverse wiring for the different types of rotors.
     */
    public String reverseWiring() {
        String wiring = this.wiring();
        char[] reverseWiring = new char[wiring.length()];

        final char CHAR_OFFSET = 'A';
        for (int index = 0; index < wiring.length(); index++)
            reverseWiring[wiring.charAt(index) - CHAR_OFFSET] = (char) (index + CHAR_OFFSET);

        return new String(reverseWiring);
    }

    /**
     * Get the turnover characters for the different types of rotors.
     */
    public String turnoverChars() {
        switch (this) {
            // Stators
            case I_ETW:
            case M3_ETW:
            case M4_ETW:
                return "";

            // Rotors
            case I_I:
            case M3_I:
            case M4_I:
                return "Q";
            case I_II:
            case M3_II:
            case M4_II:
                return "E";
            case I_III:
            case M3_III:
            case M4_III:
                return "V";
            case I_IV:
            case M3_IV:
            case M4_IV:
                return "J";
            case I_V:
            case M3_V:
            case M4_V:
                return "Z";
            case M3_VI:
            case M4_VI:
            case M3_VII:
            case M4_VII:
            case M3_VIII:
            case M4_VIII:
                return "ZM";
            case M4_BETA:
            case M4_GAMMA:
                return "";

            // Reflectors
            case I_UKW_A:
            case I_UKW_B:
            case I_UKW_C:
            case M3_UKW_B:
            case M3_UKW_C:
            case M4_UKW_B:
            case M4_UKW_C:
                return "";

            default:
                return "";
        }
    }

    /**
     * Get whether the rotor steps or not, based on the type.
     */
    public boolean isSteppingRotor() {
        switch (this) {
            // Stators
            case I_ETW:
            case M3_ETW:
            case M4_ETW:
                return false;

            // Rotors
            case I_I:
            case I_II:
            case I_III:
            case I_IV:
            case I_V:
            case M3_I:
            case M3_II:
            case M3_III:
            case M3_IV:
            case M3_V:
            case M3_VI:
            case M3_VII:
            case M3_VIII:
            case M4_I:
            case M4_II:
            case M4_III:
            case M4_IV:
            case M4_V:
            case M4_VI:
            case M4_VII:
            case M4_VIII:
                return true;
            case M4_BETA:
            case M4_GAMMA:
                return false;

            // Reflectors
            case I_UKW_A:
            case I_UKW_B:
            case I_UKW_C:
            case M3_UKW_B:
            case M3_UKW_C:
            case M4_UKW_B:
            case M4_UKW_C:
                return false;

            default:
                return false;
        }
    }

    /**
     * Get whether the rotor is marked with numbers or not, based on the type.
     */
    public boolean isMarkedWithNumbers() {
        switch (this) {
            // Stators
            case I_ETW:
            case M3_ETW:
            case M4_ETW:
                return false;

            // Rotors
            case I_I:
            case I_II:
            case I_III:
            case I_IV:
            case I_V:
                return true;
            case M3_I:
            case M3_II:
            case M3_III:
            case M3_IV:
            case M3_V:
            case M3_VI:
            case M3_VII:
            case M3_VIII:
            case M4_I:
            case M4_II:
            case M4_III:
            case M4_IV:
            case M4_V:
            case M4_VI:
            case M4_VII:
            case M4_VIII:
            case M4_BETA:
            case M4_GAMMA:
                return false;

            // Reflectors
            case I_UKW_A:
            case I_UKW_B:
            case I_UKW_C:
            case M3_UKW_B:
            case M3_UKW_C:
            case M4_UKW_B:
            case M4_UKW_C:
                return false;

            default:
                return false;
        }
    }

    /**
     * Get whether the rotor is a stator or not.
     */
    public boolean isStator() {
        switch (this) {
            // Stators
            case I_ETW:
            case M3_ETW:
            case M4_ETW:
                return true;

            // Rotors
            case I_I:
            case I_II:
            case I_III:
            case I_IV:
            case I_V:
            case M3_I:
            case M3_II:
            case M3_III:
            case M3_IV:
            case M3_V:
            case M3_VI:
            case M3_VII:
            case M3_VIII:
            case M4_I:
            case M4_II:
            case M4_III:
            case M4_IV:
            case M4_V:
            case M4_VI:
            case M4_VII:
            case M4_VIII:
            case M4_BETA:
            case M4_GAMMA:
                return false;

            // Reflectors
            case I_UKW_A:
            case I_UKW_B:
            case I_UKW_C:
            case M3_UKW_B:
            case M3_UKW_C:
            case M4_UKW_B:
            case M4_UKW_C:
                return false;

            default:
                return false;
        }
    }

    /**
     * Get whether the rotor is an actual rotor or not.
     */
    public boolean isRotor() {
        switch (this) {
            // Stators
            case I_ETW:
            case M3_ETW:
            case M4_ETW:
                return false;

            // Rotors
            case I_I:
            case I_II:
            case I_III:
            case I_IV:
            case I_V:
            case M3_I:
            case M3_II:
            case M3_III:
            case M3_IV:
            case M3_V:
            case M3_VI:
            case M3_VII:
            case M3_VIII:
            case M4_I:
            case M4_II:
            case M4_III:
            case M4_IV:
            case M4_V:
            case M4_VI:
            case M4_VII:
            case M4_VIII:
            case M4_BETA:
            case M4_GAMMA:
                return true;

            // Reflectors
            case I_UKW_A:
            case I_UKW_B:
            case I_UKW_C:
            case M3_UKW_B:
            case M3_UKW_C:
            case M4_UKW_B:
            case M4_UKW_C:
                return false;

            default:
                return false;
        }
    }

    /**
     * Get whether the rotor is a reflector or not.
     */
    public boolean isReflector() {
        switch (this) {
            // Stators
            case I_ETW:
            case M3_ETW:
            case M4_ETW:
                return false;

            // Rotors
            case I_I:
            case I_II:
            case I_III:
            case I_IV:
            case I_V:
            case M3_I:
            case M3_II:
            case M3_III:
            case M3_IV:
            case M3_V:
            case M3_VI:
            case M3_VII:
            case M3_VIII:
            case M4_I:
            case M4_II:
            case M4_III:
            case M4_IV:
            case M4_V:
            case M4_VI:
            case M4_VII:
            case M4_VIII:
            case M4_BETA:
            case M4_GAMMA:
                return false;

            // Reflectors
            case I_UKW_A:
            case I_UKW_B:
            case I_UKW_C:
            case M3_UKW_B:
            case M3_UKW_C:
            case M4_UKW_B:
            case M4_UKW_C:
                return true;

            default:
                return false;
        }
    }

    /**
     * Returns the string representation of the rotor type.
     */
    @Override
    public String toString() {
        switch (this) {
            // Stators
            case I_ETW:
            case M3_ETW:
            case M4_ETW:
                return "ETW";

            // Rotors
            case I_I:
            case M3_I:
            case M4_I:
                return "I";
            case I_II:
            case M3_II:
            case M4_II:
                return "II";
            case I_III:
            case M3_III:
            case M4_III:
                return "III";
            case I_IV:
            case M3_IV:
            case M4_IV:
                return "IV";
            case I_V:
            case M3_V:
            case M4_V:
                return "V";
            case M3_VI:
            case M4_VI:
                return "VI";
            case M3_VII:
            case M4_VII:
                return "VII";
            case M3_VIII:
            case M4_VIII:
                return "VII";
            case M4_BETA:
                return "β";
            case M4_GAMMA:
                return "γ";

            // Reflectors
            case I_UKW_A:
                return "UKW-A";
            case I_UKW_B:
            case M3_UKW_B:
            case M4_UKW_B:
                return "UKW-B";
            case I_UKW_C:
            case M3_UKW_C:
            case M4_UKW_C:
                return "UKW-C";

            default:
                return "";
        }
    }
}
