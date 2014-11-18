package sportsScheduler;

import java.util.ArrayList;

public class Team {
	
	//team name and acronym
	String name;
	String acronym;
	
	//number codes for game restrictions;
	/*
	 * Possible Restriction Codes:
	 * 0 - no restrictions, can travel anywhere or be home
	 * 1 - home or close away (for weekday games)
	 * 2 - Able to play far away game
	 * 3 - home back-to-back
	 * 4 - away back-to-back
	 * 5 - bye week for school reasons
	 */
	int[] restrictions;
	
	//schedule games from lastYear;
	//Game[] lastYear;
	
	//List of home games to be played by the team
	ArrayList<Game> homeGames;
	
	//List of closer away games to be played by the team
	ArrayList<Game> closeAwayGames;
	
	//List of far away games to be played
	ArrayList<Game> farAwayGames;
	
	//inits just name of team and acronym
	public Team(String teamName, String ac){
		this.name = teamName;
		this.acronym = ac;
	}
	
	//inits all things for team from file
	public Team(String filePath){
		//inits from a file 
	}
	
	public void setRestrictions(int[] restrict){
		this.restrictions = restrict;
	}
	
	public void getGameTypes(){
		//puts games that a team must play in categories of home,
		//short away, and long away
		
		//Will read through the gamesList in the conference class
		//and find games involving the team and categorize them appropriately
	}
	
	
}
