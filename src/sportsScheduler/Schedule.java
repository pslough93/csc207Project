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
		 * while count of tries < maxTries
		 *    for each date in dates of competition{
		 * 	  	find team with highest priority restriction on date
		 * 	  		schedule game for them by randomly selecting game from their gameList
		 * 			remove game from team arrayList
		 * 			remove game from conference arrayList
		 * 			put new game on schedule
		 *              find next team with highest priority restriction on date and repeat
		 *    tries++
		 *
		 * if we find that all arrayLists are empty, it means we are done.
		 * 
		 * (Approximate) Asymptotic Analysis:
		 *    Within the "find team with..." block, all accessing and removing from arraylists will be O(n).
		 *    This will be repeated for half of the number of Teams, so the algorithm will be O(n^2). 
		 *    This again gets repeated for the number of dates d and the number of tries t.
		 *    So the "running time" will be O(t*d*n^2), or O(n^2), but with a very big coefficient. 
		 */
	}
	
	public String scheduleToString(){
		//makes schedule into a string
		String s1 = "not implemented";
		return s1;
	}
	
}
