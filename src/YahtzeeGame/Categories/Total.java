package YahtzeeGame.Categories;

import YahtzeeGame.Components.YahtzeeDice;

/**
 * Total category
 * 
 * @author Ryan Harrison
 * 
 */
class Total extends Category
{
	@Override
	public int getCategoryIndex()
	{
		return 14;
	}

	/**
	 * A total category cannot generate a score, but is given one instead.
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
		return "Total";
	}
}
