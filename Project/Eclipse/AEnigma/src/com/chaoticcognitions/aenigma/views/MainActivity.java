package com.chaoticcognitions.aenigma.views;

import java.util.Scanner;

import com.chaoticcognitions.aenigma.models.rotors.Rotor;
import com.chaoticcognitions.aenigma.models.rotors.RotorType;

/**
 * Created by Dan on 2015-07-25.
 */
public class MainActivity {
	public static void main(String[] args) {
		Rotor testRotor = new Rotor(RotorType.M4_I);
		testRotor.setVisiblePosition('A');
		testRotor.setRingSetting('B');
		
		System.out.println("Wiring (F): " + testRotor.getRotorType().getWiring());
		System.out.println("Wiring (R): " + testRotor.getRotorType().getReverseWiring());
		System.out.println();
		
		Scanner keyboardInput = new Scanner(System.in);
		char testChar;
		
		do {
			System.out.println("Rotor Type: " + testRotor.getRotorType());
			System.out.println("Visible Char: " + testRotor.getVisiblePosition());
			System.out.println("Ring Setting: " + testRotor.getRingSetting());
			
			System.out.print("To Encode: ");
			testChar = keyboardInput.next().charAt(0);
			if (testChar != '0') {
				System.out.println("Output (F): " + testRotor.encode(testChar));
				System.out.println("Output (R): " + testRotor.encodeReverse(testChar));
				testRotor.doStep();
			}
			
			System.out.println();
		} while (testChar != '0');
	}
}
