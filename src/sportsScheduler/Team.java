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
   */
  int[] restrictions;

  //inits just name of team and acronym
  public Team(String teamName, String ac)
  {
    this.name = teamName;
    this.acronym = ac;
  }//Team(String, String)

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
      }//for
  }//Team(String)

  public void setRestrictions(int[] restrict)
  {
    //set restrictions if not initialized in constructor
    this.restrictions = restrict;
  }//setRestrictions(int[])
  
  @Override
  public boolean equals(Object obj)
  {
    Team tm = (Team)obj;
    
    return (this.name.equals(tm.name));
  }//equals


  public String toString()
  {
    return this.acronym + " - " + this.name;
  }//toString()

  public boolean equals(Team t2){
    return this.name.equals(t2.name);
  }//equals()
}//class Team.java
