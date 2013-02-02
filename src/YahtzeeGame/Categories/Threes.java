package YahtzeeGame.Categories;

import YahtzeeGame.Components.YahtzeeDice;

/**
 * Threes category
 * 
 * @author Ryan Harrison
 * 
 */
class Threes extends Category
{
	@Override
	public int getCategoryIndex()
	{
		return 3;
	}

	/**
	 * Sum the values of a set of dice where the value of the dice is 3
	 */
	@Override
	public int getScore(YahtzeeDice[] dice)
	{
		return addUpDice(3, dice);
	}

	@Override
	public int getYahtzeeBonusOverrideScore(YahtzeeDice[] dice)
	{
		return 0;
	}

	@Override
	public String toString()
	{
		return "Threes";
	}
}
