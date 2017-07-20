/*--------------------------------------------------------------------------------------------------
 * Author:      Dan Cassidy
 * Date:        2015-07-09
 * Assignment:  HW1-1
 * Source File: Fibonacci.java
 * Language:    Java
 * Course:      CSCI-C 490, Android Programming, MoWe 08:00
--------------------------------------------------------------------------------------------------*/

/**
 * Simple class dealing with Fibonacci numbers. Only handles up to the 93rd (F92) Fibonacci number
 * due to the fact the class only uses the long primitive type.
 * @author Dan Cassidy
 */
public class Fibonacci
{
	private final static byte F0 = 0;
	private final static byte F1 = 1;

	/**
	 * Calculates and displays a given number of Fibonacci numbers.
	 * @param sequenceLength The number of Fibonacci numbers to calculate and display.
	 */
	public static void calculateAndDisplay(byte sequenceLength)
	{
		// Declare the requisite numbers.
		long fibA = F1, fibB = F0, fibSum = 0;
		
		// Display the first couple numbers in the sequence, if needed.
		if (sequenceLength > 0)
			System.out.print(F0 + " ");
		if (sequenceLength > 1)
			System.out.print(F1 + " ");
		
		// Iteratively calculate and display the rest of the Fibonacci sequence.
		for (int i = 2; i < sequenceLength; i++)
		{
			fibSum = fibA + fibB;
			fibB = fibA;
			fibA = fibSum;
			System.out.print(fibSum + " ");
		}
	}
}
