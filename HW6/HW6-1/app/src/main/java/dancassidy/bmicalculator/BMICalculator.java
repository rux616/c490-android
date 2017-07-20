/*--------------------------------------------------------------------------------------------------
 * Author:      Dan Cassidy
 * Date:        2015-07-24
 * Assignment:  HW6-1
 * Source File: BMICalculator.java
 * Language:    Java
 * Course:      CSCI-C 490, Android Programming, MoWe 08:00
--------------------------------------------------------------------------------------------------*/
package dancassidy.bmicalculator;

/**
 * A class to calculate a person's body mass index (BMI).
 *
 * @author Dan Cassidy
 */
public class BMICalculator {
    private double weight = 0;
    private double height = 0;
    private double bmi = 0;
    boolean metric = true;

    // BEGIN GETTERS AND SETTERS -->
    public double getBMI() {
        return this.bmi;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        if (height >= 0) {
            this.height = height;
            calculateBMI();
        }
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        if (weight >= 0) {
            this.weight = weight;
            calculateBMI();
        }
    }

    public boolean getMetric() {
        return metric;
    }

    public void setMetric(boolean metric) {
        if (this.metric ^ metric) {
            if (this.metric) {
                this.height = UnitConversion.lengthMetersToInches(this.height);
                this.weight = UnitConversion.weightKilogramsToPounds(this.weight);
            } else {
                this.height = UnitConversion.lengthInchesToMeters(this.height);
                this.weight = UnitConversion.weightPoundsToKilograms(this.weight);
            }
            this.metric = metric;
            calculateBMI();
        }
    }
    // <-- END GETTERS AND SETTERS

    /**
     * Helper method to calculate the BMI.
     */
    private void calculateBMI() {
        if (this.height == 0)
            this.bmi = 0;
        else if (this.metric)
            this.bmi = this.weight / (this.height * this.height);
        else
            this.bmi = (this.weight * 703) / (this.height * this.height);
    }
}
