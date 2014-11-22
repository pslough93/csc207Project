package sportsScheduler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A Class that contains information about a team. Contains information about team name,
 * scheduling restrictions, and lists games to be played.
 * 
 * @author Patrick Slough, Noah Schlager, Samee Zahid
 *
 */
public class Team
{

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
  public Team(String teamName, String ac)
  {
    this.name = teamName;
    this.acronym = ac;
    
    this.homeGames = new ArrayList<Game>();
    this.closeAwayGames = new ArrayList<Game>();
    this.farAwayGames = new ArrayList<Game>();

  }

  //inits all things for team from file
  public Team(String filePath) throws FileNotFoundException
  {

    //Initialize a scanner reading the specified file
    Scanner sc = new Scanner(new File(filePath));
    
    //obtain name and acronym
    this.name = sc.nextLine();
    this.acronym = sc.nextLine();
    
    //obtain string of restrictions and split them
    String restrictionsString = sc.nextLine();
    String[] restrictionCodes = restrictionsString.split("-");
    this.restrictions = new int[restrictionCodes.length];
    
    //put them in restrictions array
    for (int i = 0; i < restrictionCodes.length; i++)
      {
        this.restrictions[i] = Integer.parseInt(restrictionCodes[i]);
      }
    
    this.homeGames = new ArrayList<Game>();
    this.closeAwayGames = new ArrayList<Game>();
    this.farAwayGames = new ArrayList<Game>();
  }

  public void setRestrictions(int[] restrict)
  {
    //set restrictions if not initialized in constructor
    this.restrictions = restrict;
  }

  public void getGameTypes()
  {
    //puts games that a team must play in categories of home,
    //short away, and long away

    //Will read through the gamesList in the conference class
    //and find games involving the team and categorize them appropriately
  }

  public String toString()
  {
    return this.acronym + " - " + this.name;
  }

  public boolean equals(Team t2){
    return this.name.equals(t2.name);
  }
}
