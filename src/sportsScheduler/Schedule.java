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
  Game[][] Schedule;
  public Schedule(int games, int byes, Conference conf)
  {
    this.numGames = games;
    this.numByes = byes;
    this.conferenceForScheduling = conf;
    
    this.Schedule = new Game[this.numGames][this.conferenceForScheduling.teams.length];
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
    
    int[] currentIndex = new int[numGames];
    
    for(int a = 0; a < numGames; a++){
      currentIndex[a] = 0;
    }
        
    for(int i = 0; i < this.conferenceForScheduling.teams.length; i++){
      
      Team tm = this.conferenceForScheduling.teams[i];
      
      System.out.println(tm.toString());
      for(int j = 0; j < tm.restrictions.length; j++){
        
        if(tm.restrictions[j] == 5){
          Game gm = new Game(tm, tm);
          System.out.println(gm.toString());
         
          Schedule[j][currentIndex[j]++] = gm;
          conferenceForScheduling.gamesList.remove(gm);
          
          tm.byes.remove(gm);
        }
        
        Team[] teams = this.conferenceForScheduling.teams;
        
        for(Date dt: this.conferenceForScheduling.datesOfCompetition){
          
          
          
          
        }
        
        
        
        
//        if(tm.restrictions[j] == 0){
//          if(tm.farAwayGames.size() != 0){
//            Game gm = tm.farAwayGames.get(rand.nextInt(tm.farAwayGames.size()));
//            Team opponent = gm.homeTeam;
//            
//            Schedule[j][currentIndex[j]++] = gm;
//            
//            conferenceForScheduling.gamesList.remove(gm);
//            
//            tm.farAwayGames.remove(gm);
//            
//            opponent.homeGames.remove(new Game(opponent, tm));
//          }
//          
//          
//        }
        
//        if(tm.restrictions[j] == 2){
//          if(tm.farAwayGames.size() != 0){
//            Game gm = tm.farAwayGames.get(rand.nextInt(tm.farAwayGames.size()));
//            Team opponent = gm.homeTeam;
//            
//            Schedule[j][currentIndex[j]++] = gm;
//            
//            conferenceForScheduling.gamesList.remove(gm);
//            
//            tm.farAwayGames.remove(gm);
//            
//            opponent.homeGames.remove(new Game(opponent, tm));
//          }
//          
//          
//        }
      }
      
    }
    System.out.println("here");

  }

  public void printSchedule()
  {
    for(int i = 0; i < numGames; i++){
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
  
  public void makeDummySchedule(){
    
    
    int numTeams = this.conferenceForScheduling.teams.length;
    
    int[] roundRobin = new int[numTeams];
    
    int[] currentIndex = new int[numGames];
    
    for(int a = 0; a < numGames; a++){
      currentIndex[a] = 0;
    }
    
    for(int i = 0; i < numTeams; i++){
      roundRobin[i] = i;
    }
    
    int index = numTeams - 1;
    for(int i = 1; i < numGames/2 + 1; i++){
      for(int j = 0; j < index; j++){
        Game gm1 = new Game(this.conferenceForScheduling.teams[roundRobin[j]], this.conferenceForScheduling.teams[roundRobin[index - j]]);
        Game gm2 = new Game(this.conferenceForScheduling.teams[roundRobin[index - j]], this.conferenceForScheduling.teams[roundRobin[j]]);
        
        this.Schedule[i][currentIndex[i]++] = gm1;
        this.Schedule[i + numGames/2][currentIndex[i + numGames/2]++] = gm2;
      }
      
      for(int k = 1; k < numTeams; k++){
        if(roundRobin[k] == 1)
          roundRobin[k] = 9;
        else{
          roundRobin[k]--;
        }
      }
    }
  }

}
