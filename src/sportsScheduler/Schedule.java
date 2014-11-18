package sportsScheduler;

public class Schedule {

	int numGames;
	
	Game[][] Schedule;
	
	public Schedule(int games, Conference conf){
		//initialize from games and conf
	}
	
	public void generateSchedule(){
		//algorithm to generate schedules
		
		/*
		 * Possible Outline:
		 * 
		 * for each date in dates of competition{
		 * 		find team with highest priority restriction on date
		 * 			schedule game for them by randomly selecting game from their gameList
		 * 			remove game from team arrayList
		 * 			remove game from conference arrayList
		 * 			put new game on schedule
		 *
		 */
	}
	
	public String scheduleToString(){
		//makes schedule into a string
		String s1 = "not implemented";
		return s1;
	}
	
}
