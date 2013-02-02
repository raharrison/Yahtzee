package YahtzeeGame.Categories;

import YahtzeeGame.Components.YahtzeeDice;

/**
 * Chance category
 * 
 * @author Ryan Harrison
 * 
 */
class Chance extends Category
{
	@Override
	public int getCategoryIndex()
	{
		return 12;
	}

	/**
	 * Return the sum of the values of every die
	 */
	@Override
	public int getScore(YahtzeeDice[] dice)
	{
		int sum = 0;

		for (int i = 0; i < 5; i++)
		{
			sum += dice[i].getValue();
		}

		return sum;
	}

	@Override
	public int getYahtzeeBonusOverrideScore(YahtzeeDice[] dice)
	{
		return getScore(dice);
	}

	@Override
	public String toString()
	{
		return "Chance";
	}
}
