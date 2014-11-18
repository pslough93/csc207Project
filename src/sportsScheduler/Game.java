package sportsScheduler;

public class Game {
	
	Team homeTeam;
	Team awayTeam;
	
	public Game(Team home, Team away){
		this.homeTeam = home;
		this.awayTeam = away;
	}
	
	public String toString(){
		String returnString = this.awayTeam.name + " at " + this.homeTeam.name;
		return returnString;
	}
}
