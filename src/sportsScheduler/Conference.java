package sportsScheduler;

import java.util.ArrayList;

public class Conference {
	//name, teams, and dates;
	String confName;
	Team[] teams;
	Date[] datesOfCompetition;
	
	//distances to and from each team.
	int[][] mileage;
	
	//list of all games that must be played
	ArrayList<GameWithMileage> gamesList;
	
	public Conference(Team[] teams, Date[] dates){
		//initialize a conference from arrays
	}
	
	public Conference(String confData){
		//initialize a conference from a text file
	}
	
	public void readMileage(String mileData){
		//read in mileage data
	}
	
	public void generateGamesList(){
		//generates all games that should be played and 
		//puts them in gamesList
	}
	
}
