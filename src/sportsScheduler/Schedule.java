package sportsScheduler;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Schedule
{
  //counts of games, teams and violations
  int numGames;
  int numTeams;
  int travelViolations;

  Conference conferenceForScheduling;

  GameWithMileage[][] Schedule;

  /**
   * Constructor for Schedule. Initializes schedule for use;
   * @param games
   * @param conf
   */
  public Schedule(int games, Conference conf)
  {
    this.numGames = games;
    this.conferenceForScheduling = conf;
    this.numTeams = this.conferenceForScheduling.teams.length;
    this.Schedule =
        new GameWithMileage[this.numGames][this.conferenceForScheduling.teams.length / 2];
    this.travelViolations = 0;
  }//Schedule(int, Conference)

  
  /**
   * Primary Scheduling algorithm: 
   *    Primarily uses the Round Robin scheduling algorithm (adapted from http://en.wikipedia.org/wiki/Round-robin_tournament#Scheduling_algorithm)
   *    First, we vary the input to makeDRRSchedule() to vary the initial round in a double round robin tournament. Then we go through and randomize
   *    the "rows" of the schedule (ie. randomly swapping weeks). Then, we go through and count the number of times the schedule requires more
   *    travel than should be necessary for a given team on a given day. If this algorithm features fewer violations, it is saved as the current
   *    best schedule and the process repeats. This Algorithm should be O(n^2), as no piece of the algorithm features anything that depends on more
   *    than n^2 where n is the number of teams.
   *    
   * @throws Exception
   */
  public void generateSchedule()
    throws Exception
  {
    
    //intializes violation counts and schedules
    
    int lowViolationCount = numTeams * numTeams;
    int violationCount;
    GameWithMileage[][] currentSchedule =
        new GameWithMileage[this.numGames][this.numTeams];
    GameWithMileage[][] bestSchedule =
        new GameWithMileage[this.numGames][this.numTeams];
    
    //initializes team input array.
    int[] teams = { 0, 5, 7, 4, 6, 1, 2, 3, 8, 9 };
    int tries = 0;

    Random rand = new Random();

    //while we are under the maximum number of tries and have more than 0 violations
    while (tries < 100 && lowViolationCount > 0)
      {

        //permute last 5 elements of input array
        for (int i = 0; i < 4; i++)
          {
            //two random ints in range
            int i1 = rand.nextInt(5) + 5;
            int i2 = rand.nextInt(5) + 5;
            
            //swap
            int tmp = teams[i1];
            teams[i1] = teams[i2];
            teams[i2] = tmp;
          }//for

        //possibly permute first 3 elements of imput array
        if (rand.nextBoolean())
          {
            int i1 = rand.nextInt(3);
            int i2 = rand.nextInt(3);

            int tmp = teams[i1];
            teams[i1] = teams[i2];
            teams[i2] = tmp;
          }//if
        
        //possibly permute 4th and 5th elements of input array
        if (rand.nextBoolean())
          {
            int i1 = rand.nextInt(2) + 3;
            int i2 = rand.nextInt(2) + 3;

            int tmp = teams[i1];
            teams[i1] = teams[i2];
            teams[i2] = tmp;
          }//if

        //Make a DRR Schedule with the input array
        currentSchedule = makeDRRSchedule(teams);
        
        //count violations
        violationCount = countViolations(currentSchedule);

        //if this schedule is better than the best yet, reassigne lowViolationCount and bestSchedule
        if (violationCount < lowViolationCount)
          {
            lowViolationCount = violationCount;
            bestSchedule = currentSchedule;
          }//if
        
        //repeat 20 times
        for (int k = 0; k < 20; k++)
          {
            //fifty times
            for (int i = 0; i < 50; i++)
              {
                //select two rows of schedule(not including first) and swap
                int i1 = rand.nextInt(17) + 1;
                int i2 = rand.nextInt(17) + 1;

                GameWithMileage[] tmp = currentSchedule[i1];
                currentSchedule[i1] = currentSchedule[i2];
                currentSchedule[i2] = tmp;
              }//for
            //update violation count;
            violationCount = countViolations(currentSchedule);
            
            //if this schedule is the best, update
            if (violationCount < lowViolationCount)
              {
                lowViolationCount = violationCount;
                bestSchedule = currentSchedule;
              }//if
          }//for
        //increment number of tries
        tries++;
      }//while

    //assign best schedule to class variable
    this.Schedule = bestSchedule;
    this.travelViolations = lowViolationCount;
  }

  /**
   * Uses pen to print the schedule that has been generated.
   * @param pen
   */
  public void printSchedule(PrintWriter pen)
  {
    for (int i = 0; i < this.numGames; i++)
      {
        if (this.conferenceForScheduling.datesOfCompetition[i].equals(new Date("11/22/14")))
          {
            pen.println("11/22/14 or 12/10/14 (School Exam Schedules Permitting) - ");
          }//if
        else
          {
            pen.println(this.conferenceForScheduling.datesOfCompetition[i].toString()
                               + " - ");
          }//else
        for (int j = 0; j < this.Schedule[i].length; j++)
          {
            if (this.Schedule[i][j] != null)
              {
                pen.println("\t - " + this.Schedule[i][j].toString());
              }//if
          }//for
        pen.println();
      }//for
    pen.println("This Schedule Contains " + this.travelViolations + " instances where excess travel is needed");
  }//printSchedule(PrintWriter)

  /**
   * Makes a Double Round Robin Schedule based on the algorithm from http://en.wikipedia.org/wiki/Round-robin_tournament#Scheduling_algorithm.
   * @param teamNums
   * @return
   */
  public GameWithMileage[][] makeDRRSchedule(int[] teamNums)
  {
    //initialize game array
    GameWithMileage[][] schedule =
        new GameWithMileage[this.numGames][this.numTeams / 2];

    //Stores the current index that should be used to put a game in the game array
    int[] currentIndex = new int[numGames];

    for (int a = 0; a < numGames; a++)
      {
        currentIndex[a] = 0;
      }//for

    int index = this.numTeams - 1;
    for (int i = 0; i < numGames / 2; i++)
      {
        for (int j = 0; j < this.numTeams / 2; j++)
          {
            //make game with team 1 home, team 2 away
            GameWithMileage gm1 =
                new GameWithMileage(
                                    this.conferenceForScheduling.teams[teamNums[j]],
                                    this.conferenceForScheduling.teams[teamNums[index
                                                                                - j]],
                                    this.conferenceForScheduling.mileage[teamNums[j]][teamNums[index
                                                                                               - j]]);
            //make game with team 1 away, team 2 home
            GameWithMileage gm2 =
                new GameWithMileage(
                                    this.conferenceForScheduling.teams[teamNums[index
                                                                                - j]],
                                    this.conferenceForScheduling.teams[teamNums[j]],
                                    this.conferenceForScheduling.mileage[teamNums[index
                                                                                  - j]][teamNums[j]]);
            
            //put both in the schedule
            schedule[i][currentIndex[i]++] = gm1;
            schedule[i + numGames / 2][currentIndex[i + numGames / 2]++] = gm2;
          }//for
        
        //As per RR scheduling algorithm, we have a pivot (element 0), so we cycle the rest of the array
        //n-1 times to create all permutations
        int a = teamNums[this.numTeams - 1];
        for (int k = this.numTeams - 1; k > 1; k--)
          {
            teamNums[k] = teamNums[k - 1];
          }//for
        teamNums[1] = a;
      }//for

    return schedule;
  }//makeDummySchedule(int[])

  /**
   * Counts the number of travel violations caused by a schedule
   * @param sched
   * @return
   */
  public int countViolations(GameWithMileage[][] sched)
  {
    int count = 0;
    //for all dates
    for (int i = 0; i < this.numGames; i++)
      {
        //for all games
        for (int j = 0; j < this.numTeams / 2; j++)
          {
            //if away team should be playing close but isnt, increment count
            if (sched[i][j].awayTeam.restrictions[i] == 1
                && sched[i][j].mileage > 270)
              count++;
          }//for
      }//for
    return count;
  }//countViolations(GameWithMileage[][])
}//class Schedule.java
