/**
 * Competition.java
 *
 * This class is used by Team.java.  It contains information about
 * a particular programming competition.
 */

public class Competition
{
    	private String competitionName;
    	private String winner;
    	private String runnerup;
   	 private int year;

  	 /**
	* Default constructor;  Initialize variables and year to 0.
 	*/
 	public Competition()
 	{
		competitionName = "No Competition Name";
		winner = "Unknown";
		runnerup = "Unknown";
		year = 0;
	}

   	/**
	* Constructor;  Initialize variables to input values.
	*
 	*/
 	public Competition(String competitionName, String winner, String runnerup, int year)
 	{
		this.competitionName = competitionName;
		this.winner = winner;
		this.runnerup = runnerup;
		this.year = year;
	}

   	/**
	* Return competition information as a string.
	*
	* @return String encapsulating all of the competition information.
 	*/
 	public String toString()
 	{
		return "Competition Name:" + competitionName + "\n" +
			   "Winner: " + winner + "\n" +
			   "Runner-Up: " + runnerup + "\n" +
			   "Year: " + year;
	}


// =====================================
// Various accessor and mutator methods
// =====================================
    public String getCompetitionName()
    {
        return competitionName;
    }

    public void setCompetitionName(String competitionName)
    {
        this.competitionName = competitionName;
    }

    public String getWinner()
    {
        return winner;
    }

    public void setWinner(String winner)
    {
        this.winner = winner;
    }

    public String getRunnerup()
    {
        return runnerup;
    }

    public void setRunnerup(String runnerup)
    {
        this.runnerup = runnerup;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }
} // Competition
