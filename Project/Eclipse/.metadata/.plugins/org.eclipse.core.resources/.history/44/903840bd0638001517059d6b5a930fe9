package com.chaoticcognitions.aenigma.models.machines;

import com.chaoticcognitions.aenigma.models.plugboards.Plugboard;
import com.chaoticcognitions.aenigma.models.rotors.Rotor;
import com.chaoticcognitions.aenigma.models.rotors.RotorType;

/**
 * Created by Dan on 2015-07-28.
 */
public class Machine {
    private Plugboard plugboard = new Plugboard();
    private int numRotors = 5;
    private Rotor[] rotors = new Rotor[5];
    private Rotor reflector;

    public Machine() {
        plugboard.addPlugSettings('A', 'E');
        plugboard.addPlugSettings('B', 'F');
        plugboard.addPlugSettings('C', 'M');
        plugboard.addPlugSettings('D', 'Q');
        plugboard.addPlugSettings('H', 'U');
        plugboard.addPlugSettings('J', 'N');
        plugboard.addPlugSettings('L', 'X');
        plugboard.addPlugSettings('P', 'R');
        plugboard.addPlugSettings('S', 'Z');
        plugboard.addPlugSettings('V', 'W');

        rotors[0] = new Rotor(RotorType.M4_VIII);
        rotors[1] = new Rotor(RotorType.M4_VI);
        rotors[2] = new Rotor(RotorType.M4_V);
        rotors[3] = new Rotor(RotorType.M4_BETA);
        reflector = new Rotor(RotorType.M4_UKW_C);

        rotors[0].setRingSetting('L');
        rotors[1].setRingSetting('E');
        rotors[2].setRingSetting('P');
        rotors[3].setRingSetting('E');

        rotors[0].setVisiblePosition('Z');
        rotors[1].setVisiblePosition('S');
        rotors[2].setVisiblePosition('D');
        rotors[3].setVisiblePosition('C');
    }

    public char encode(char inputChar) {
        char intermediary = plugboard.encode(inputChar);
        intermediary = rotors[0].encode(intermediary);
        return plugboard.encode(intermediary);
    }

    public String getRotorString() {
        String temp = "";
        return temp;
    }
}
