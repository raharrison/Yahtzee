package YahtzeeGame;

import YahtzeeGame.Components.Resettable;

class Player implements Resettable
{
	private String name;
	private int rollCount;

	private int totalScore;
	private int upperScore;

	private int upperBonus;
	private int yahtzeeBonus;

	private boolean haveYahtzee;

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

	public void addScore(int score, boolean isUpperCategory)
	{
		if (isUpperCategory)
			this.upperScore += score;
		else
			this.totalScore += score;
	}

	public void addUpperBonus()
	{
		upperBonus += 35;
		upperScore += 35;
	}

	public void addYahtzeeBonus()
	{
		yahtzeeBonus += 100;
	}

	public boolean getHaveYahtzee()
	{
		return haveYahtzee;
	}

	public String getName()
	{
		return name;
	}

	public int getRollCount()
	{
		return rollCount;
	}

	public int getScore()
	{
		return totalScore + yahtzeeBonus + upperScore;
	}

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

	public int getUpperBonus()
	{
		return upperBonus;
	}

	public int getUpperScore()
	{
		return this.upperScore;
	}

	public int getYahtzeeBonus()
	{
		return yahtzeeBonus;
	}

	public void IncrementRollCount()
	{
		rollCount++;
	}

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

	public void resetRollCount()
	{
		rollCount = 0;
	}

	public void setHaveYahtzee()
	{
		haveYahtzee = true;
	}
}
