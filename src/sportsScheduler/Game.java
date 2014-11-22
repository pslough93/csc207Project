package sportsScheduler;

/**
 * A generic class for games with just a home team and away team
 * @author Patrick Slough, Noah Schlager, Samee Zahid
 *
 */
public class Game
{

  Team homeTeam;
  Team awayTeam;

  public Game(Team home, Team away)
  {
    this.homeTeam = home;
    this.awayTeam = away;
  }

  public String toString()
  {
    if (this.homeTeam.equals(this.awayTeam))
      {
        return this.homeTeam.name + " - BYE";
      }
    else
      {
        String returnString = this.awayTeam.name + " at " + this.homeTeam.name;
        return returnString;
      }
  }
}
