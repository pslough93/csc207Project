package sportsScheduler;

public class Conference {
	//name, teams, and dates;
	String confName;
	Team[] teams;
	Date[] datesOfCompetition;
	
	//distances to and from each team.
	int[][] mileage;
	
	
	public Conference(Team[] teams, Date[] dates){
		//initialize a conference from arrays
	}
	
	public Conference(String confData){
		//initialize a conference from a text file
	}
	
	public void readMileage(String mileData){
		//read in mileage data
	}
	
}
