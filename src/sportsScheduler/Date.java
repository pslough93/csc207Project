package sportsScheduler;

/**
 * A class that handles dates of games. Has two constructors and methods for comparing and printing
 * @author Patrick Slough, Noah Schlager, Samee Zahid
 *
 */
public class Date
{

  int month;
  int day;
  int year;

  public Date(int mm, int dd, int yy)
  {
    this.month = mm;
    this.day = dd;
    if (yy < 100)
      {
        //presumably, using 2 digit year format
        this.year = yy + 2000;
      }
    else
      {
        this.year = yy;
      }
  }

  public Date(String dateString)
  {
    String[] dateParts = dateString.split("/");
    this.month = Integer.parseInt(dateParts[0]);
    this.day = Integer.parseInt(dateParts[1]);
    int yr = Integer.parseInt(dateParts[2]);
    if (yr < 100)
      {
        this.year = yr + 2000;
      }
    else
      {
        this.year = yr;
      }
  }

  public String toString()
  {
    String returnString = this.month + "/" + this.day + "/" + this.year;
    return returnString;
  }
  
  public boolean equals (Date day){
    return (day.day == this.day && day.month == this.month && day.year == this.year);
  }
}
