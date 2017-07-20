/*--------------------------------------------------------------------------------------------------
 * Author:      Dan Cassidy
 * Date:        2015-07-10
 * Assignment:  HW3-2
 * Source File: CalculateAverage.java
 * Language:    Java
 * Course:      CSCI-C 490, Android Programming, MoWe 08:00
--------------------------------------------------------------------------------------------------*/
import java.util.Scanner;

/**
 * A small class to calculate the average of a given number of integers.
 * 
 * @author Dan Cassidy
 */
public class CalculateAverage
{
    
    public static void main(String[] args)
    {
        int numberOfNumbers = 0;
        boolean valid = false;
        Scanner consoleInput = new Scanner(System.in);
        
        // Loop while input is not valid.
        while (!valid)
        {
            try
            {
                numberOfNumbers = readInt("Please enter the number of numbers to average: ");
                if (numberOfNumbers <= 0)
                    throw new Exception("Number must be greater than 0.");
                valid = true;
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        
        // Declare an array of the specified size and then ask for input for all elements.
        int[] numbers = new int[numberOfNumbers];
        for (int counter = 0; counter < numbers.length; counter++)
            numbers[counter] = readInt("Please input a number for entry " + (counter + 1) + ": ");
            
        System.out.println("Average of all entries: " + average(numbers));
    }
    
    /**
     * Computes the average (arithmetic mean) of an array of numbers. If <b>numbers</b> is null or
     * an empty array, 0 is returned.
     * 
     * @param numbers An array of integers, from which their average will be computed.
     * @return double, representing the average of the elements contained in <b>numbers</b>.
     */
    public static double average(int[] numbers)
    {
        // No need to throw an error, just return 0 if the argument is no good.
        if (numbers == null || numbers.length == 0)
            return 0;
            
        // Compute and return the average.
        double sum = 0;
        for (int number : numbers)
            sum += number;
        return sum / numbers.length;
    }
    
    /**
     * Reads an integer from the console.
     * 
     * @param prompt A String object containing the prompt text for a user entering a number.
     * @return int, holding the integer read from the console.
     */
    public static int readInt(String prompt)
    {
        int number = 0;
        boolean valid = false;
        Scanner consoleInput = new Scanner(System.in);
        
        // Loop while input is not valid.
        while (!valid)
        {
            try
            {
                System.out.print(prompt);
                number = Integer.parseInt(consoleInput.nextLine());
                valid = true;
            }
            catch (NumberFormatException ex)
            {
                System.out.println("Invalid input, please try again.");
            }
        }
        
        return number;
    }
}
