package sportsScheduler;

/**
 * A class for games with a mileage parameter
 * @author Patrick Slough, Noah Schlager, Samee Zahid
 *
 */
public class GameWithMileage extends Game {
	
	//distance away team must travel
	int mileage;
	
	//init game
	public GameWithMileage(Team home, Team away, int miles){
		super(home, away);
		this.mileage = miles;
	}//GameWithMileage(Team, Team, int)
}//class GameWithMileage.java
