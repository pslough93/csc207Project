package sportsScheduler;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class UI
{
  /**
   * A simple UI that prompts the user to file inputs and then generates a schedule based on those files
   * 
   * Authors: Patrick Slough, Noah Schlager, Samee Zahid
   * @throws Exception
   */
  public static void mainUI()
    throws Exception
  {
    //make printWriter, greet, and make scanner
    PrintWriter pen = new PrintWriter(System.out, true);

    pen.println("Welcome To Sports Scheduler 1.0!");
    Scanner sc = new Scanner(System.in);

    boolean continuing = true;

    while (continuing)
      {
        //prompt user for all relevent variables and store them
        pen.print("Please enter title of conference information file: ");
        pen.flush();
        String confFile = sc.nextLine();

        pen.print("Please enter title of date information file: ");
        pen.flush();
        String dateFile = sc.nextLine();

        pen.print("Please enter title of mileage information file: ");
        pen.flush();
        String mileFile = sc.nextLine();

        pen.print("How many teams are in the conference? ");
        pen.flush();
        int numTeams = Integer.parseInt(sc.nextLine());

        pen.print("How many game days are there? ");
        pen.flush();
        int numDates = Integer.parseInt(sc.nextLine());

        pen.println("Generating Schedule - ");
        pen.flush();

        try
          {
            //init conference
            Conference conf =
                new Conference(confFile, dateFile, numTeams, numDates);

            //read in mileage
            conf.readMileage(mileFile);

            //init schedule
            Schedule confSchedule = new Schedule(numDates, conf);
            //generate schedule and print it
            confSchedule.generateSchedule();

            confSchedule.printSchedule(pen);
            pen.flush();

            //prompt for repeat
            pen.print("Would you like to generate another schedule (Input Y or N)? ");
            pen.flush();
            String response = sc.nextLine();
            if (response.equals("Y"))
              {
                continuing = true;
              }//if
            else
              {
                continuing = false;
              }//else
          }//try
        catch (FileNotFoundException e)
          {
            //if file not found, ask to try again
            pen.println("One or more of your files was not found.");
            pen.print("Would you like to try again (Input Y or N)? ");
            pen.flush();
            String response = sc.nextLine();
            if (response.equals("Y"))
              {
                continuing = true;
              }//if
            else
              {
                continuing = false;
              }//else
          }//catch
      }//while
  }//UI()
}//class UI.java
