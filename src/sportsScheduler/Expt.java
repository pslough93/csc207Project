package sportsScheduler;

import java.io.FileNotFoundException;

public class Expt
{

  public static void main(String[] args) throws Exception
  {
    
    Conference mwc = new Conference("mwc.txt", "dates.txt", 10, 19);
    
    for(int i = 0; i < mwc.teams.length; i++){
      System.out.println(mwc.teams[i].toString());
      for(int j = 0; j < mwc.teams[i].restrictions.length; j++){
        System.out.print(mwc.teams[i].restrictions[j] + " ");
      }
      System.out.println();
    }
    
    for(int j = 0; j < mwc.datesOfCompetition.length; j++){
      System.out.println(mwc.datesOfCompetition[j].toString());
    }
    
    mwc.readMileage("milage.txt");
    for(int i = 0; i < mwc.teams.length; i++){
      for(int j = 0; j < mwc.teams.length; j++){
        System.out.print(mwc.mileage[i][j] + " ");
      }
      System.out.println();
    }
    
    mwc.generateGamesLists();
    
    Schedule mwcSchedule = new Schedule(19, 1, mwc);
    
    mwcSchedule.makeDummySchedule();
    
    mwcSchedule.printSchedule();
    
    //mwcSchedule.setByes();
    /*
    for(int i = 0; i < mwc.teams.length; i++){
      System.out.println(mwc.teams[i].toString());
      for(int j = 0; j < mwc.teams[i].restrictions.length; j++){
        System.out.print(mwc.teams[i].restrictions[j] + " ");
      }
      System.out.println();
    }
    */
    /*
    GameWithMileage game = new GameWithMileage(mwc.teams[0], mwc.teams[0], mwc.mileage[0][0]);
    
    System.out.println(game.toString() + " " + game.mileage);
     
    for(int i = 0; i < mwc.teams.length; i++){
      System.out.println(mwc.teams[i].name + ":");
      System.out.print("Home Games: ");
      for(Game g: mwc.teams[i].homeGames){
        System.out.print(g.awayTeam.name + " - ");
      }
      System.out.println();
      
      System.out.print("Close Away Games: ");
      for(Game g: mwc.teams[i].closeAwayGames){
        System.out.print(g.homeTeam.name + " - ");
      }
      System.out.println();
      
      System.out.print("Far Away Games: ");
      for(Game g: mwc.teams[i].farAwayGames){
        System.out.print(g.homeTeam.name + " - ");
      }
      System.out.println();
      
      System.out.print("Byes: ");
      for(Game g: mwc.teams[i].byes){
        System.out.print(g.homeTeam.name + " - ");
      }
      System.out.println();
    }*/
    
  }

}
