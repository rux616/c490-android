
public class StudentRecord 
{

	private int quiz1;
	private int quiz2;
	private int quiz3;
	private int midterm;
	private int finalExam;

	private double numericScore;
	private char letterGrade;

	private final int A_GRADE = 90;
	private final int B_GRADE = 80;
	private final int C_GRADE = 70;
	private final int D_GRADE = 60;

	public StudentRecord (int quiz1, int quiz2, int quiz3, 
		int midterm, int finalExam)
	{
		this.quiz1 = quiz1;
		this.quiz2 = quiz2;
		this.quiz3 = quiz3;
		this.midterm = midterm;
		this.finalExam = finalExam;
	}


	//*******************************
	// accessor and mutator methods 	
	// to be completed by students
	//*******************************

	// This method calculates the numericScore based on 
	// the scores of the quizzes and exams.
	public void computeNumericScore() 
	{
		//*****************************
		// to be completed by students
		//*****************************
	}

	// This method calculates the letterGrade based on
	// the numberScore.
	public void computeLetterGrade()
	{
		computeNumericScore();

		//*****************************
		// to be completed by students
		//*****************************

	}

	// This method compares two StudentRecord objects.
	// It will return true only if two objects have  
	// same score for each of the quizzes and exams.
	public boolean equals(StudentRecord other) 
	{
		//*****************************
		// to be completed by students
		//*****************************
	}

	// This method returns a string representation of 
	// the data in the calling object. 
	public String toString() 
	{
		//*****************************
		// to be completed by students
		//*****************************
	}

}// StudentRecord
