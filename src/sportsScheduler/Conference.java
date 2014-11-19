package sportsScheduler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
	
	public Conference(String confData, String dateData, int numTeams, int numDates) throws FileNotFoundException{
		//initialize a conference from a text file
	  Scanner sc1 = new Scanner(new File(confData));
	  this.teams = new Team[numTeams];
	  for(int i = 0; i < numTeams; i++){
	    String name = sc1.nextLine();
	    String ac = sc1.nextLine();
	    this.teams[i] = new Team(name, ac);
	  }
	  
	  Scanner sc2 = new Scanner(new File(dateData));
	  this.datesOfCompetition = new Date[numDates];
	  for(int j = 0; j < numDates; j++){
	    String dateString = sc2.nextLine();
	    this.datesOfCompetition[j] = new Date(dateString);
	  }
	}
	
	public void readMileage(String mileData) throws FileNotFoundException{
		//read in mileage data
	  Scanner sc = new Scanner(new File(mileData));
	  int teamCount = this.teams.length;
	  this.mileage = new int[teamCount][teamCount];
	  
	  sc.nextLine();
	  for(int i = 0; i < teamCount; i++){
	    sc.next();
	    for(int j = 0; j < teamCount; j++){
	      this.mileage[i][j] = sc.nextInt();
	    }
	  }
	}
	
	public void generateGamesList(){
		//generates all games that should be played and 
		//puts them in gamesList
	}
	
}
