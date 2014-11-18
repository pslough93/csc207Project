package sportsScheduler;

public class Game {

	//the two teams playing the game
	Team homeTeam;
	Team awayTeam;
	
	//the date of the game
	Date gameDay;
	
	public Game(Team home, Team away, Date gd){
		this.homeTeam = home;
		this.awayTeam = away;
		this.gameDay = gd;
	}
	
	public String toString(){
		String returnString = this.gameDay.toString() + " - " + this.awayTeam.name + " vs. " + this.homeTeam.name;
		return returnString;
	}
}
