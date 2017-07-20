/*--------------------------------------------------------------------------------------------------
 * Author:      Dan Cassidy
 * Date:        2015-07-14
 * Assignment:  HW2-1
 * Source File: Rational.java
 * Language:    Java
 * Course:      CSCI-C 490, Android Programming, MoWe 08:00
--------------------------------------------------------------------------------------------------*/

/**
 * Small class that represents rational numbers in accordance with homework assignment 2-1. By
 * default, the numerator is set to 0 and the denominator is set to 1.
 * 
 * @author Dan Cassidy
 */
public class Rational
{
	private int numerator = 0;
	private int denominator = 1;
	
	/**
	 * Default constructor.
	 */
	public Rational()
	{
		// Do nothing, numerator and denominator are already set to 0 and 1 respectively.
	}
	
	/**
	 * 1-parameter constructor. Takes a whole number and creates a new Rational object based upon
	 * that. This means that the denominator will be 1.
	 * 
	 * @param wholeNumber The number that will be stored in the numerator.
	 */
	public Rational(int wholeNumber)
	{
		this.setNumerator(wholeNumber);
		// Don't have to set denominator because it's 1 by default.
	}
	
	/**
	 * 2-parameter constructor. Takes two arguments that will be the numerator and denominator of
	 * the resultant Rational object.
	 * 
	 * @param numerator The number that will be the numerator.
	 * @param denominator The number that will be the denominator.
	 */
	public Rational(int numerator, int denominator)
	{
		this.setNumerator(numerator);
		this.setDenominator(denominator);
	}
	
	// BEGIN GETTERS AND SETTERS -->
	public int getDenominator()
	{
		return this.denominator;
	}
	
	/**
	 * @param denominator The number to set the denominator to.
	 * @throws IllegalArgumentException if <b>denominator</b> is 0.
	 */
	public void setDenominator(int denominator)
	{
		if (denominator == 0)
			throw new IllegalArgumentException("Cannot have a denominator of 0.");
		
		this.denominator = denominator;
		this.simplify();
	}
	
	public int getNumerator()
	{
		return this.numerator;
	}
	
	/**
	 * @param numerator The number to set the numerator to.
	 */
	public void setNumerator(int numerator)
	{
		this.numerator = numerator;
		this.simplify();
	}
	// <-- END GETTERS AND SETTERS
	
	/**
	 * Addition method that takes two Rational objects and adds them together.
	 * 
	 * @param r1 The first of two Rational objects that will be added together.
	 * @param r2 The second of two Rational objects that will be added together.
	 * @return Rational object, containing the result of the operation.
	 * @throws NullPointerException if <b>r1</b> or <b>r2</b> is null.
	 */
	public static Rational add(Rational r1, Rational r2)
	{
		if (r1 == null || r2 == null)
			throw new NullPointerException();
		
		if (r1.denominator == r2.denominator)
			return new Rational(r1.numerator + r2.numerator, r1.denominator);
		else
			return new Rational(r1.numerator * r2.denominator + r2.numerator * r1.denominator,
					r1.denominator * r2.denominator);
	}
	
	/**
	 * Subtraction method that takes two Rational objects and subtracts the second from the first.
	 * 
	 * @param r1 The first of two Rational objects. Will be subtracted from by the second.
	 * @param r2 The second of two Rational objects. Will subtract from the first.
	 * @return Rational object, containing the result of the operation.
	 * @throws NullPointerException if <b>r1</b> or <b>r2</b> is null.
	 */
	public static Rational subtract(Rational r1, Rational r2)
	{
		if (r1 == null || r2 == null)
			throw new NullPointerException();
		
		if (r1.denominator == r2.denominator)
			return new Rational(r1.numerator - r2.numerator, r1.denominator);
		else
			return new Rational(r1.numerator * r2.denominator - r2.numerator * r1.denominator,
					r1.denominator * r2.denominator);
	}
	
	/**
	 * Multiplication method that takes two Rational objects and multiplies them together.
	 * 
	 * @param r1 The first of two Rational objects that will be multiplied together.
	 * @param r2 The second of two Rational objects that will be multiplied together.
	 * @return Rational object, containing the result of the operation.
	 * @throws NullPointerException if <b>r1</b> or <b>r2</b> is null.
	 */
	public static Rational multiply(Rational r1, Rational r2)
	{
		if (r1 == null || r2 == null)
			throw new NullPointerException();
		
		return new Rational(r1.numerator * r2.numerator, r1.denominator * r2.denominator);
	}
	
	/**
	 * Division method that takes two Rational objects and divides the first by the second.
	 * 
	 * @param r1 The first of two Rational objects. Will be divided by the second.
	 * @param r2 The second of two Rational objects. Will divide the first.
	 * @return Rational object, containing the result of the operation.
	 * @throws NullPointerException if <b>r1</b> or <b>r2</b> is null.
	 */
	public static Rational divide(Rational r1, Rational r2)
	{
		if (r1 == null || r2 == null)
			throw new NullPointerException();
		
		return new Rational(r1.numerator * r2.denominator, r1.denominator * r2.numerator);
	}
	
	/**
	 * Equals method that compares this object to another Rational object.
	 * @param other A Rational object that will be compared against to determine equality.
	 * @return boolean, representing whether this object is equal to the other object or not.
	 */
	public boolean equals(Rational other)
	{
		if (other == null)
			return false;
		
		return (this.numerator * other.denominator == other.numerator * this.denominator);
	}
	
	/**
	 * Override of the toString() method. Returns a String representation of the object.
	 * @return String containing either the numerator and denominator separated by a forward slash
	 * if denominator is not equal to 1, or just the numerator if the denominator is equal to 1.
	 */
	public String toString()
	{
		if (this.denominator == 1)
			return "" + this.numerator;
		
		return this.numerator + "/" + this.denominator;
	}
	
	/**
	 * Simplifies the current Rational object (this) by putting any negative signs in the numerator
	 * and dividing both the numerator and the denominator by their greatest common divisor.
	 */
	private void simplify()
	{
		if (this.denominator < 0)
		{
			this.numerator *= -1;
			this.denominator *= -1;
		}
		
		if (this.denominator != 1)
		{		
			int gcd = gcd(this.numerator < 0 ? this.numerator * -1 : this.numerator,
					this.denominator);
			this.numerator /= gcd;
			this.denominator /= gcd;
		}
	}
	
	/**
	 * Iterative binary greatest common divisor (GCD) algorithm (Stein's algorithm) from
	 * <a href="https://en.wikipedia.org/wiki/Binary_GCD_algorithm">Wikipedia</a>.
	 * 
	 * @param u The first of two non-negative numbers to find the GCD of. 
	 * @param v The second of two non-negative numbers to find the GCD of.
	 * @return An integer representing the greatest common divisor of <b>u</b> and <b>v</b>.
	 * @throws IllegalArgumentException if either <b>u</b> or <b>v</b> are negative.
	 */
	private int gcd(int u, int v)
	{
		if (u < 0 || v < 0)
			throw new IllegalArgumentException("Arguments must be non-negative.");
		
		// If either argument is 0, return the other argument.
		if (u == 0)
			return v;
		if (v == 0)
			return u;

		// Find the greatest power of 2 dividing both u and v.
		int shift;
		for (shift = 0; ((u | v) & 1) == 0; ++shift)
		{
			u >>= 1;
			v >>= 1;
		}

		// Make u odd.
		while ((u & 1) == 0)
			u >>= 1;

		do
		{
			// Remove all factors of 2 in v.
			while ((v & 1) == 0)
				v >>= 1;

			// Now u and v are both odd. Swap if necessary so u <= v, then set v = v - u.
			if (u > v)
			{
				int t = v;
				v = u;
				u = t;
			}
			v = v - u;
		} while (v != 0);

		// Restore common factors of 2.
		return u << shift;
	}
}
