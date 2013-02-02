package YahtzeeGame;

import YahtzeeGame.Components.Resettable;

/**
 * Class representing a player in a game of Yahtzee. Each player has a score and
 * a series of bonuses adding to their score that can be set
 * 
 * @author Ryan Harrison
 * 
 */
class Player implements Resettable
{
	/**
	 * The name of the player
	 */
	private String name;

	/**
	 * The number of times this player his rolled the dice
	 */
	private int rollCount;

	/**
	 * The total score for this player
	 */
	private int totalScore;

	/**
	 * The score for all the upper categories only for this player
	 */
	private int upperScore;

	/**
	 * The upper bonus for this player
	 */
	private int upperBonus;

	/**
	 * The Yahtzee bonus for this player
	 */
	private int yahtzeeBonus;

	/**
	 * Determines whether or not this player has scored a Yahtzee
	 */
	private boolean haveYahtzee;

	/**
	 * Construct a new Player object with the specified name. Each field is set
	 * to the default value
	 * 
	 * @param name
	 *            The name of the new Player
	 */
	public Player(String name)
	{
		this.name = name;
		rollCount = 0;
		totalScore = 0;
		upperScore = 0;
		upperBonus = 0;
		yahtzeeBonus = 0;
		haveYahtzee = false;
	}

	/**
	 * Add a score to this player. If the score is from an upper category, then
	 * also add to the upper score total
	 * 
	 * @param score
	 *            The score to add
	 * @param isUpperCategory
	 *            Is the score from an upper category
	 */
	public void addScore(int score, boolean isUpperCategory)
	{
		if (isUpperCategory)
			this.upperScore += score;
		else
			this.totalScore += score;
	}

	/**
	 * Adds an upper bonus onto this players score. This consists of 35 points
	 * onto the upper score
	 */
	public void addUpperBonus()
	{
		upperBonus += 35;
		upperScore += 35;
	}

	/**
	 * Add a Yahtzee bonus score onto this players score. This consists of 100
	 * points onto the score
	 */
	public void addYahtzeeBonus()
	{
		yahtzeeBonus += 100;
	}

	/**
	 * Has the player scored a Yahtzee yet
	 * 
	 * @return True if the player has scored a Yahtzee, otherwise false
	 */
	public boolean getHaveYahtzee()
	{
		return haveYahtzee;
	}

	/**
	 * Get the name of the player
	 * 
	 * @return The name of the player
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Get the number of times this player has rolled the dice
	 * 
	 * @return The number of times the player has rolled the dice
	 */
	public int getRollCount()
	{
		return rollCount;
	}

	/**
	 * Get the total score for this player. This consists of the score for the
	 * lower and upper categories along with the bonuses
	 * 
	 * @return The grand total score for this player.
	 */
	public int getScore()
	{
		return totalScore + yahtzeeBonus + upperScore;
	}

	/**
	 * Get the total score as a formatted string
	 * 
	 * @return A formatted string for the players score
	 */
	public String getStringScore()
	{
		if (totalScore < 10)
		{
			return totalScore + " ";
		}
		else
		{
			return Integer.toString(totalScore);
		}
	}

	/**
	 * Get the upper bonus score for this player
	 * 
	 * @return The upper bonus score for this player
	 */
	public int getUpperBonus()
	{
		return upperBonus;
	}

	/**
	 * Get the upper score for this player
	 * 
	 * @return The upper score for this player
	 */
	public int getUpperScore()
	{
		return this.upperScore;
	}

	/**
	 * Get the Yahtzee bonus score for this player
	 * 
	 * @return The Yahtzee bonus score for this player
	 */
	public int getYahtzeeBonus()
	{
		return yahtzeeBonus;
	}

	/**
	 * Increment the number of times the player has rolled the dice
	 */
	public void incrementRollCount()
	{
		rollCount++;
	}

	/**
	 * Reset the state of this object. Reset all fields for this player back to
	 * default values.
	 */
	@Override
	public void reset()
	{
		rollCount = 0;
		totalScore = 0;
		upperScore = 0;
		upperBonus = 0;
		yahtzeeBonus = 0;
		haveYahtzee = false;
	}

	/**
	 * Reset the roll count for this player back to 0
	 */
	public void resetRollCount()
	{
		rollCount = 0;
	}

	/**
	 * Set if the player has scored a Yahtzee
	 */
	public void setHaveYahtzee()
	{
		haveYahtzee = true;
	}
}
