package sportsScheduler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class for making an overarching Conference from which a schedule will be generated
 * Has fields for teams, mileage between teams, game dates, etc. Can be initialized in several ways.
 * @author Patrick Slough, Noah Schlager, Samee Zahid
 *
 */
public class Conference
{
  //name, teams, and dates;
  String confName;
  Team[] teams;
  Date[] datesOfCompetition;

  //distances to and from each team.
  int[][] mileage;

  //list of all games that must be played
  ArrayList<GameWithMileage> gamesList;

  public Conference(Team[] teams, Date[] dates)
  {
    //initialize a conference from arrays
  }//Conference

  /**
   * Initializes a conference from file inputs and integers for teams and dates
   * @param confData
   * @param dateData
   * @param numTeams
   * @param numDates
   * @throws FileNotFoundException
   * 
   * Preconditions - File of confData MUST be in format seen in mwc.txt or
   *    (Team Name)
   *    (team acronym)
   *    (restriction codes separated by "-")
   *    
   *    File of dateData MUST be in format seen in dates.txt or
   *    (mm/dd/yy)
   *    
   * Postconditions - Initializes all necessary fields for conference object
   */
  public Conference(String confData, String dateData, int numTeams, int numDates)
    throws FileNotFoundException
  {
    //create a scanner to read the confData file
    Scanner sc1 = new Scanner(new File(confData));
    this.teams = new Team[numTeams];
    for (int i = 0; i < numTeams; i++)
      {
        // Get all team parameters
        String name = sc1.nextLine();
        String ac = sc1.nextLine();
        String restrictions = sc1.nextLine();
        
        //initialize team
        this.teams[i] = new Team(name, ac);
        //process restrictionCodes and put them in place
        String[] restrictionCodes = restrictions.split("-");
        this.teams[i].restrictions = new int[restrictionCodes.length];
        for (int j = 0; j < restrictionCodes.length; j++)
          {
            this.teams[i].restrictions[j] =
                Integer.parseInt(restrictionCodes[j]);
          }//for j
      }//for i
    
    //create a scanner to read dates
    Scanner sc2 = new Scanner(new File(dateData));
    this.datesOfCompetition = new Date[numDates];
    for (int j = 0; j < numDates; j++)
      {
        String dateString = sc2.nextLine();
        this.datesOfCompetition[j] = new Date(dateString);
      }//for j
    
    this.gamesList = new ArrayList<GameWithMileage>();
  }
  
  /**
   * Fills in a 2-d array with mileage data from file inputs and integers for team mileage.
   * @param mileData
   * @throws FileNotFoundException
   * 
   * Preconditions - File of mileData MUST be in format seen in mileage.txt
   *    
   * Postconditions - Allows the 2-d array mileage to be used for the scheduler
   */
  
  public void readMileage(String mileData)
    throws FileNotFoundException
  {
    //read in mileage data
    Scanner sc = new Scanner(new File(mileData));
    int teamCount = this.teams.length;
    this.mileage = new int[teamCount][teamCount];

    sc.nextLine();
    for (int i = 0; i < teamCount; i++)
      {
        sc.next();
        for (int j = 0; j < teamCount; j++)
          {
            this.mileage[i][j] = sc.nextInt();
          }//for j
      }//for i
  }// readMileage(String mileData)

  public void generateGamesLists()
  {
    for(int i = 0; i < this.teams.length; i++){
      GameWithMileage bye = new GameWithMileage(this.teams[i], this.teams[i], 0);
      this.teams[i].homeGames.add(bye);
      this.gamesList.add(bye); //bye game
      for(int j = i + 1; j < this.teams.length; j++){
        GameWithMileage homeAgainstJ = new GameWithMileage(this.teams[i], this.teams[j], this.mileage[i][j]);
        GameWithMileage awayAgainstJ = new GameWithMileage(this.teams[j], this.teams[i], this.mileage[j][i]);
        this.gamesList.add(homeAgainstJ);
        this.gamesList.add(awayAgainstJ);
        
        if(this.mileage[i][j] <= 270){
          this.teams[i].homeGames.add(homeAgainstJ);
          this.teams[j].closeAwayGames.add(homeAgainstJ);
          this.teams[i].closeAwayGames.add(awayAgainstJ);
          this.teams[j].homeGames.add(awayAgainstJ);
        }
        else{
          this.teams[i].homeGames.add(homeAgainstJ);
          this.teams[j].farAwayGames.add(homeAgainstJ);
          this.teams[i].farAwayGames.add(awayAgainstJ);
          this.teams[j].homeGames.add(awayAgainstJ);
        }
      }
    }
    //generates all games that should be played and 
    //puts them in gamesList
  }//generateGamesList()

}
