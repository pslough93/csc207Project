package sportsScheduler;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class UI
{
  /**
   * A simple UI that prompts the user to file inputs and then generates a schedule based on those files
   * 
   * Authors: Patrick Slough, Noah Schlager, Samee Zahid
   * @throws Exception
   */
  public static void mainUI(String confFile, String dateFile, String mileFile,
                            String numTeamsString, String numDatesString, String nameFile)
    throws Exception
  {
    int numTeams = Integer.parseInt(numTeamsString);
    int numDates = Integer.parseInt(numDatesString);
    
  //init conference
    Conference conf =
        new Conference(confFile, dateFile, numTeams, numDates);

    //read in mileage
    conf.readMileage(mileFile);

    //init schedule
    Schedule confSchedule = new Schedule(numDates, conf);
    //generate schedule
    confSchedule.generateSchedule();
    //print to a file
    dump(confSchedule, numDates, conf, nameFile);
 
  }//UI()
  
  /**
   * Method for printing out list of possible schedules to a specified file.
   * @param confSchedule - the generated schedule
   * @param numDates - number of game days
   * @param conf - conference
   * @throws FileNotFoundException - if the specified output file doesn't exist
   * @throws UnsupportedEncodingException
   */
  public static void dump(Schedule confSchedule, int numDates, Conference conf, String name) 
      throws FileNotFoundException, UnsupportedEncodingException{
    Scanner sc = new Scanner(System.in);
    Schedule temp = new Schedule(numDates, conf);
    PrintWriter writer = new PrintWriter(name, "UTF-8");
    if (!name.equals(null))
      {
        for(GameWithMileage[][] sch : confSchedule.possibleSchedules)
          {
            temp.Schedule = sch;
            writer.flush();
            temp.printSchedule(writer);
            writer.flush();
          }//for          
      }//if    
    else{
      System.out.println("asdas");
    }
    sc.close();
  }//dump()
}//class UI.java
