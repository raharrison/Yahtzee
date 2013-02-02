package YahtzeeGame.Categories;

import YahtzeeGame.Components.YahtzeeDice;

/**
 * Four of a kind category
 * 
 * @author Ryan Harrison
 * 
 */
class FourOfAKind extends Category
{
	@Override
	public int getCategoryIndex()
	{
		return 8;
	}

	/**
	 * If four of the die have the same value, then return the sum of those four
	 * die, otherwise 0
	 */
	@Override
	public int getScore(YahtzeeDice[] dice)
	{
		int sum = 0;

		boolean fourOfAKind = false;

		for (int i = 1; i <= 6; i++)
		{
			int count = 0;
			for (int j = 0; j < 5; j++)
			{
				if (dice[j].getValue() == i)
					count++;

				if (count > 3)
					fourOfAKind = true;
			}
		}

		if (fourOfAKind)
		{
			for (int k = 0; k < 5; k++)
			{
				sum += dice[k].getValue();
			}
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
		return "Four Of A Kind";
	}
}
