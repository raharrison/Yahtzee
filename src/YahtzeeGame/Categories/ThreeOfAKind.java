package YahtzeeGame.Categories;

import YahtzeeGame.Components.YahtzeeDice;

/**
 * Three of a kind category
 * 
 * @author Ryan Harrison
 * 
 */
class ThreeOfAKind extends Category
{
	@Override
	public int getCategoryIndex()
	{
		return 7;
	}

	/**
	 * If three die have the same value then return the sum of those die,
	 * otherwise 0
	 */
	@Override
	public int getScore(YahtzeeDice[] dice)
	{
		int sum = 0;

		boolean threeOfAKind = false;

		for (int i = 1; i <= 6; i++)
		{
			int count = 0;
			for (int j = 0; j < 5; j++)
			{
				if (dice[j].getValue() == i)
					count++;

				if (count > 2)
					threeOfAKind = true;
			}
		}

		if (threeOfAKind)
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
		return "Three Of A Kind";
	}
}
