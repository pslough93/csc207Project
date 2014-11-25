package sportsScheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Schedule
{

  int numGames;

  int numByes;

  Conference conferenceForScheduling;

  //ArrayList<ArrayList<Game>> Schedule;
  GameWithMileage[][] Schedule;
  public Schedule(int games, int byes, Conference conf)
  {
    this.numGames = games;
    this.numByes = byes;
    this.conferenceForScheduling = conf;
    
    this.Schedule = new GameWithMileage[this.numGames][this.conferenceForScheduling.teams.length/2];
  }

  public void generateSchedule() throws Exception
  {
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
    
    //this.setByes();
    
    //Random rand = new Random();
    int[] teams = {0, 5, 7, 4, 6, 1, 2, 3, 8, 9};
    this.Schedule = makeDummySchedule(teams);
  

  }

  public void printSchedule()
  {
    System.out.println(this.numGames);
    for(int i = 0; i < this.numGames; i++){
      System.out.print(this.conferenceForScheduling.datesOfCompetition[i].toString() + " - ");
      for(int j = 0; j < this.Schedule[i].length; j++){
        if(this.Schedule[i][j] != null){
          System.out.print(this.Schedule[i][j].toString() + " - ");
        }
      }
      System.out.println();
    }
  }

  public boolean[] verifyByes()
  {
    int numTeams = this.conferenceForScheduling.teams.length;
    boolean[] validByes = new boolean[numTeams];

    for (int i = 0; i < numTeams; i++)
      {
        Team currentTeam = this.conferenceForScheduling.teams[i];
        int byes = 0;

        for (int j = 0; j < currentTeam.restrictions.length; j++)
          {
            if (currentTeam.restrictions[j] == 5)
              byes++;
          }

        if (byes == this.numByes)
          {
            validByes[i] = true;
          }
        else
          {
            validByes[i] = false;
          }
      }

    return validByes;
  }

  public void setByes()
    throws Exception
  {
    boolean[] byes = this.verifyByes();
    int numTeams = this.conferenceForScheduling.teams.length;
    for (int i = 0; i < this.numGames; i++)
      {
        int byesCount = 0;

        Team tempTeam = this.conferenceForScheduling.teams[0];

        for (int j = 0; j < numTeams; j++)
          {
            if (this.conferenceForScheduling.teams[j].restrictions[i] == 5)
              byesCount++;

            if (this.conferenceForScheduling.teams[j].restrictions[i] < tempTeam.restrictions[i])
              {
                tempTeam = this.conferenceForScheduling.teams[j];
              }
          }

        if (byesCount % 2 != 0)
          {
            if (tempTeam.restrictions[i] <= 1)
              {
                tempTeam.restrictions[i] = 5;
                byes = this.verifyByes();
              }
            else
              {
                throw new Exception("Schedule is impossible");
              }
          }
      }

    for (int a = 0; a < numTeams; a++)
      {
        System.out.print(byes[a] + " ");
      }
    System.out.println();

    Random rand = new Random();
    int randomNumDate;
    while (!allTrueByes(byes))
      {
        randomNumDate =
            Math.abs(rand.nextInt()
                     % this.conferenceForScheduling.datesOfCompetition.length);

        int restrictionCount = 0;
        for(Team tm : this.conferenceForScheduling.teams){
          restrictionCount += tm.restrictions[randomNumDate];
        }
        
        
        if(restrictionCount <= numTeams){
          int byeCount = 0;
          for(int k = 0; k < numTeams; k++){
            if(byeCount < 2 && !byes[k]){
              this.conferenceForScheduling.teams[k].restrictions[randomNumDate] = 5;
              byeCount++;
            }
          }
        }
        
        byes = this.verifyByes();
      }
  }

  public boolean allTrueByes(boolean[] tf)
  {
    for (boolean i : tf)
      {
        if (!i)
          {
            return false;
          }
      }
    return true;
  }
  
  public GameWithMileage[][] makeDummySchedule(int[] teamNums){
    
    
    int numTeams = this.conferenceForScheduling.teams.length;
   
    GameWithMileage[][] schedule = new GameWithMileage[this.numGames][numTeams/2];
    
    int[] currentIndex = new int[numGames];
    
    for(int a = 0; a < numGames; a++){
      currentIndex[a] = 0;
    }//for
    
    int index = numTeams - 1;
    for(int i = 0; i < numGames/2; i++){
      for(int j = 0; j < numTeams/2; j++){
        GameWithMileage gm1 = new GameWithMileage(this.conferenceForScheduling.teams[teamNums[j]], this.conferenceForScheduling.teams[teamNums[index - j]], this.conferenceForScheduling.mileage[teamNums[j]][teamNums[index - j]]);
        GameWithMileage gm2 = new GameWithMileage(this.conferenceForScheduling.teams[teamNums[index - j]], this.conferenceForScheduling.teams[teamNums[j]], this.conferenceForScheduling.mileage[teamNums[index - j]][teamNums[j]]);
        
        schedule[i][currentIndex[i]++] = gm1;
        schedule[i + numGames/2][currentIndex[i + numGames/2]++] = gm2;
      }//for
      
      int a = teamNums[numTeams - 1];
      for(int k = numTeams - 1; k > 1; k--)
      {
          teamNums[k] = teamNums[k - 1];
      }//for
      teamNums[1] = a;
    }//for
    
    return schedule;
  }//makeDummySchedule(int[])
}
