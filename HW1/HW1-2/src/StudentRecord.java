/*--------------------------------------------------------------------------------------------------
 * Author:      Dan Cassidy and Dr. Zhang
 * Date:        2015-07-09
 * Assignment:  HW1-2
 * Source File: StudentRecord.java
 * Language:    Java
 * Course:      CSCI-C 490, Android Programming, MoWe 08:00
--------------------------------------------------------------------------------------------------*/

/**
 * A small record of grades for a student.
 * @author Dan Cassidy
 * @author Dr. Zhang
 */
public class StudentRecord 
{
	private int quiz1 = 0;
	private int quiz2 = 0;
	private int quiz3 = 0;
	private int midterm = 0;
	private int finalExam = 0;

	private double numericScore = 0.0D;
	private char letterGrade = '?';

	private final int A_GRADE = 90;
	private final int B_GRADE = 80;
	private final int C_GRADE = 70;
	private final int D_GRADE = 60;
	
	private final int NUMBER_OF_QUIZZES = 3;
	
	private final int QUIZ_MIN_SCORE = 0;
	private final int QUIZ_MAX_SCORE = 10;
	private final int MIDTERM_MIN_SCORE = 0;
	private final int MIDTERM_MAX_SCORE = 100;
	private final int FINAL_EXAM_MIN_SCORE = 0;
	private final int FINAL_EXAM_MAX_SCORE = 100;
	
	private final int QUIZ_WEIGHT = 25;
	private final int MIDTERM_WEIGHT = 35;
	private final int FINAL_EXAM_WEIGHT = 40;

	/**
	 * Constructor.
	 * @param quiz1 Student score for quiz 1.
	 * @param quiz2 Student score for quiz 2.
	 * @param quiz3 Student score for quiz 3.
	 * @param midterm Student score for the midterm.
	 * @param finalExam Student score for the final exam.
	 */
	public StudentRecord(int quiz1, int quiz2, int quiz3, int midterm, int finalExam)
	{
		// Use this object's own mutators to set its instance variables, allowing verification
		// to happen in a single location.
		this.setQuiz1(quiz1);
		this.setQuiz2(quiz2);
		this.setQuiz3(quiz3);
		this.setMidterm(midterm);
		this.setFinalExam(finalExam);
	}

	// BEGIN GETTERS AND SETTERS -->
	public int getQuiz1()
	{
		return this.quiz1;
	}
	
	public void setQuiz1(int score)
	{
		if (score >= QUIZ_MIN_SCORE && score <= QUIZ_MAX_SCORE)
			this.quiz1 = score;
	}
	
	public int getQuiz2()
	{
		return this.quiz1;
	}
	
	public void setQuiz2(int score)
	{
		if (score >= QUIZ_MIN_SCORE && score <= QUIZ_MAX_SCORE)
			this.quiz2 = score;
	}
	
	public int getQuiz3()
	{
		return this.quiz1;
	}
	
	public void setQuiz3(int score)
	{
		if (score >= QUIZ_MIN_SCORE && score <= QUIZ_MAX_SCORE)
			this.quiz3 = score;
	}
	
	public int getMidterm()
	{
		return this.midterm;
	}
	
	public void setMidterm(int score)
	{
		if (score >= MIDTERM_MIN_SCORE && score <= MIDTERM_MAX_SCORE)
			this.midterm = score;
	}
	
	public int getFinalExam()
	{
		return this.finalExam;
	}
	
	public void setFinalExam(int score)
	{
		if (score >= FINAL_EXAM_MIN_SCORE && score <= FINAL_EXAM_MAX_SCORE)
			this.finalExam = score;
	}
	// <-- END GETTERS AND SETTERS
	
	/**
	 * This method calculates the numericScore based on the scores of the quizzes and exams.
	 * @return Nothing.
	 */
	public void computeNumericScore() 
	{
		this.numericScore = 
			(double)(quiz1 + quiz2 + quiz3) / (NUMBER_OF_QUIZZES * QUIZ_MAX_SCORE) * QUIZ_WEIGHT +
			(double)midterm / MIDTERM_MAX_SCORE * MIDTERM_WEIGHT +
			(double)finalExam / FINAL_EXAM_MAX_SCORE * FINAL_EXAM_WEIGHT;
	}

	/**
	 * This method calculates the letterGrade based on the numberScore.
	 * @return Nothing.
	 */
	public void computeLetterGrade()
	{
		computeNumericScore();
		if (numericScore >= A_GRADE)
			letterGrade = 'A';
		else if (numericScore >= B_GRADE)
			letterGrade = 'B';
		else if (numericScore >= C_GRADE)
			letterGrade = 'C';
		else if (numericScore >= D_GRADE)
			letterGrade = 'D';
		else
			letterGrade = 'F';
	}

	/**
	 * This method compares two StudentRecord objects. It will return true only if two objects
	 * have same score for each of the quizzes and exams.
	 * @param other Another StudentRecord object that will be compared against for equality.
	 * @return boolean, showing whether the two student records are equal (true) or not (false).
	 */
	public boolean equals(StudentRecord other) 
	{
		if (other == null)
			return false;
		else
			return  (this.quiz1 == other.quiz1) &&
					(this.quiz2 == other.quiz2) &&
					(this.quiz3 == other.quiz3) &&
					(this.midterm == other.midterm) &&
					(this.finalExam == other.finalExam);
	}

	/**
	 * This method returns a string representation of the data in the calling object.
	 * @return A string representation of the StudentRecord object. 
	 */
	public String toString() 
	{
		this.computeLetterGrade();
		return 	"Quiz 1: " + this.quiz1 + ", " +
				"Quiz 2: " + this.quiz2 + ", " +
				"Quiz 3: " + this.quiz3 + ", " +
				"Midterm: " + this.midterm + ", " +
				"Final Exam: " + this.finalExam + ", " + 
				"Grade: " + this.letterGrade + " (" +
				this.numericScore + "%)";
	}
}// StudentRecord
