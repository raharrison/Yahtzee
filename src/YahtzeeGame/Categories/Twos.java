package YahtzeeGame.Categories;

import YahtzeeGame.Components.YahtzeeDice;

/**
 * Twos category
 * 
 * @author Ryan Harrison
 * 
 */
class Twos extends Category
{
	@Override
	public int getCategoryIndex()
	{
		return 2;
	}

	/**
	 * Sum the values of a set of dice where the value of the dice is 2
	 */
	@Override
	public int getScore(YahtzeeDice[] dice)
	{
		return addUpDice(2, dice);
	}

	@Override
	public int getYahtzeeBonusOverrideScore(YahtzeeDice[] dice)
	{
		return 0;
	}

	@Override
	public String toString()
	{
		return "Twos";
	}
}
