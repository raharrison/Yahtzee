package YahtzeeGame.Categories;

import YahtzeeGame.Components.YahtzeeDice;

/**
 * Sixes category
 * 
 * @author Ryan Harrison
 * 
 */
class Sixes extends Category
{
	@Override
	public int getCategoryIndex()
	{
		return 6;
	}

	/**
	 * Sum the values of a set of dice where the value of the dice is 6
	 */
	@Override
	public int getScore(YahtzeeDice[] dice)
	{
		return addUpDice(6, dice);
	}

	@Override
	public int getYahtzeeBonusOverrideScore(YahtzeeDice[] dice)
	{
		return 0;
	}

	@Override
	public String toString()
	{
		return "Sixes";
	}
}
