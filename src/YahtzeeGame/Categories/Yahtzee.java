package YahtzeeGame.Categories;

import YahtzeeGame.Components.YahtzeeDice;

/**
 * Yahtzee category
 * 
 * @author Ryan Harrison
 * 
 */
public class Yahtzee extends Category
{
	@Override
	public int getCategoryIndex()
	{
		return 13;
	}

	/**
	 * Return 50 if all five die have the same value, otherwise 0
	 */
	@Override
	public int getScore(YahtzeeDice[] dice)
	{
		return getYahtzeeScore(dice);
	}

	@Override
	public int getYahtzeeBonusOverrideScore(YahtzeeDice[] dice)
	{
		return 50;
	}

	@Override
	public String toString()
	{
		return "Yahtzee";
	}
}
