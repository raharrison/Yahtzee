package YahtzeeGame.Categories;

import YahtzeeGame.Components.YahtzeeDice;

/**
 * Bonus category
 * 
 * @author Ryan Harrison
 * 
 */
class Bonus extends Category
{
	/**
	 * A bonus category cannot generate a score, but is given one instead.
	 */
	public int getScore(YahtzeeDice[] dice)
	{
		return 0;
	}

	public String toString()
	{
		return "Bonus";
	}

	public int getCategoryIndex()
	{
		return 15;
	}

	public int getYahtzeeBonusOverrideScore(YahtzeeDice[] dice)
	{
		return 0;
	}
}
