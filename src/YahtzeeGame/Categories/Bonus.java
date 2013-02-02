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
	@Override
	public int getCategoryIndex()
	{
		return 15;
	}

	/**
	 * A bonus category cannot generate a score, but is given one instead.
	 */
	@Override
	public int getScore(YahtzeeDice[] dice)
	{
		return 0;
	}

	@Override
	public int getYahtzeeBonusOverrideScore(YahtzeeDice[] dice)
	{
		return 0;
	}

	@Override
	public String toString()
	{
		return "Bonus";
	}
}
