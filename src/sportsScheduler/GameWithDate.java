package sportsScheduler;

public class GameWithDate extends Game{

	//the date of the game
	Date gameDay;
	
	public GameWithDate(Team home, Team away, Date gd){
		super(home, away);
		this.gameDay = gd;
	}
	
	public String toString(){
		String returnString = this.gameDay.toString() + " - " + this.awayTeam.name + " vs. " + this.homeTeam.name;
		return returnString;
	}
}
