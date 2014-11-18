package sportsScheduler;

public class Team {
	
	//team name and acronym
	String name;
	String acronym;
	
	//number codes for game restrictions;
	int[] restrictions;
	
	//schedule games from lastYear;
	Game[] lastYear;
	
	public Team(String teamName){
		this.name = teamName;
		String[] acronymParts = teamName.split(" ");
		String ac = "";
		for(String acPart : acronymParts){
			ac += acPart.substring(0, 1);
		}
		this.acronym = ac;
		//this.restrictions = new int[numGames];
	}
	
	public Team(String filePath){
		//inits from a file 
	}
	
	
}
