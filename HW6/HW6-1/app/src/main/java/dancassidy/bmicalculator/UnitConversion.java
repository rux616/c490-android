/*--------------------------------------------------------------------------------------------------
 * Author:      Dan Cassidy
 * Date:        2015-07-24
 * Assignment:  HW6-1
 * Source File: UnitConversion.java
 * Language:    Java
 * Course:      CSCI-C 490, Android Programming, MoWe 08:00
--------------------------------------------------------------------------------------------------*/
package dancassidy.bmicalculator;

/**
 * Small utility class to convert between different units.
 *
 * @author Dan Cassidy
 */
public class UnitConversion {
    // Conversion factors.
    private static final double POUND_KILOGRAM_CONVERSION_FACTOR = 0.45359237;
    private static final double INCH_METER_CONVERSION_FACTOR = 0.0254;

    /**
     * Converts kilograms to pounds.
     *
     * @param weightInKilograms The weight in kilograms.
     * @return The weight in pounds.
     */
    public static double weightKilogramsToPounds(double weightInKilograms) {
        return weightInKilograms / POUND_KILOGRAM_CONVERSION_FACTOR;
    }

    /**
     * Converts pounds to kilograms.
     *
     * @param weightInPounds The weight in pounds.
     * @return The weight in kilograms.
     */
    public static double weightPoundsToKilograms(double weightInPounds) {
        return weightInPounds * POUND_KILOGRAM_CONVERSION_FACTOR;
    }

    /**
     * Converts meters to inches.
     *
     * @param lengthInMeters The length in meters.
     * @return The length in inches.
     */
    public static double lengthMetersToInches(double lengthInMeters) {
        return lengthInMeters / INCH_METER_CONVERSION_FACTOR;
    }

    /**
     * Converts inches to meters.
     *
     * @param lengthInInches The length in inches.
     * @return The length in meters.
     */
    public static double lengthInchesToMeters(double lengthInInches) {
        return lengthInInches * INCH_METER_CONVERSION_FACTOR;
    }
}
