package YahtzeeGame.Categories;

import YahtzeeGame.Components.YahtzeeDice;

/**
 * Fours category
 * 
 * @author Ryan Harrison
 * 
 */
class Fours extends Category
{
	@Override
	public int getCategoryIndex()
	{
		return 4;
	}

	/**
	 * Sum the values of a set of dice where the value of the dice is 4
	 */
	@Override
	public int getScore(YahtzeeDice[] dice)
	{
		return addUpDice(4, dice);
	}

	@Override
	public int getYahtzeeBonusOverrideScore(YahtzeeDice[] dice)
	{
		return 0;
	}

	@Override
	public String toString()
	{
		return "Fours";
	}
}
