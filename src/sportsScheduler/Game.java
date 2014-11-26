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
  }//Game(Team, Team)
  
  @Override
  public boolean equals(Object obj)
  {
    Game gm = (Game)obj;
    
    return (this.homeTeam.equals(gm.homeTeam)) && (this.awayTeam.equals(gm.awayTeam));
  }//equals

  public String toString()
  {
    if (this.homeTeam.equals(this.awayTeam))
      {
        return this.homeTeam.name + " - BYE";
      }//if
    else
      {
        String returnString = this.awayTeam.name + " at " + this.homeTeam.name;
        return returnString;
      }//else
  }//toString()
}//class Game.java
