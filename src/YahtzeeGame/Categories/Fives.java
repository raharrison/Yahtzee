package YahtzeeGame.Categories;

import YahtzeeGame.Components.YahtzeeDice;

/**
 * Fives category
 * 
 * @author Ryan Harrison
 * 
 */
class Fives extends Category
{
	@Override
	public int getCategoryIndex()
	{
		return 5;
	}

	/**
	 * Sum the values of a set of dice where the value of the dice is 5
	 */
	@Override
	public int getScore(YahtzeeDice[] dice)
	{
		return addUpDice(5, dice);
	}

	@Override
	public int getYahtzeeBonusOverrideScore(YahtzeeDice[] dice)
	{
		return 0;
	}

	@Override
	public String toString()
	{
		return "Fives";
	}
}
