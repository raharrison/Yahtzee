package YahtzeeGame.Categories;

import YahtzeeGame.Components.YahtzeeDice;

/**
 * Ones category
 * 
 * @author Ryan Harrison
 * 
 */
class Ones extends Category
{
	@Override
	public int getCategoryIndex()
	{
		return 1;
	}

	/**
	 * Sum the values of a set of dice where the value of the dice is 1
	 */
	@Override
	public int getScore(YahtzeeDice[] dice)
	{
		return addUpDice(1, dice);
	}

	@Override
	public int getYahtzeeBonusOverrideScore(YahtzeeDice[] dice)
	{
		return 0;
	}

	@Override
	public String toString()
	{
		return "Ones";
	}
}
