package YahtzeeGame.Categories;

import java.util.Arrays;

import YahtzeeGame.Components.YahtzeeDice;

/**
 * Small straight category
 * 
 * @author Ryan Harrison
 * 
 */
class SmallStraight extends Category
{
	@Override
	public int getCategoryIndex()
	{
		return 10;
	}

	/**
	 * Return 30 if four of the die have consecutive values (e.g 1, 2, 3 ,4),
	 * otherwise 0
	 */
	@Override
	public int getScore(YahtzeeDice[] dice)
	{
		int sum = 0;

		int[] theDice = new int[5];

		theDice[0] = dice[0].getValue();
		theDice[1] = dice[1].getValue();
		theDice[2] = dice[2].getValue();
		theDice[3] = dice[3].getValue();
		theDice[4] = dice[4].getValue();

		Arrays.sort(theDice);

		// Problem can arise hear, if there is more than one of the same number,
		// so
		// we must move any doubles to the end
		for (int j = 0; j < 4; j++)
		{
			int temp = 0;
			if (theDice[j] == theDice[j + 1])
			{
				temp = theDice[j];

				for (int k = j; k < 4; k++)
				{
					theDice[k] = theDice[k + 1];
				}

				theDice[4] = temp;
			}
		}

		if (((theDice[0] == 1) && (theDice[1] == 2) && (theDice[2] == 3) && (theDice[3] == 4))
				|| ((theDice[0] == 2) && (theDice[1] == 3) && (theDice[2] == 4) && (theDice[3] == 5))
				|| ((theDice[0] == 3) && (theDice[1] == 4) && (theDice[2] == 5) && (theDice[3] == 6))
				|| ((theDice[1] == 1) && (theDice[2] == 2) && (theDice[3] == 3) && (theDice[4] == 4))
				|| ((theDice[1] == 2) && (theDice[2] == 3) && (theDice[3] == 4) && (theDice[4] == 5))
				|| ((theDice[1] == 3) && (theDice[2] == 4) && (theDice[3] == 5) && (theDice[4] == 6)))
		{
			sum = 30;
		}

		return sum;
	}

	@Override
	public int getYahtzeeBonusOverrideScore(YahtzeeDice[] dice)
	{
		return 30;
	}

	@Override
	public String toString()
	{
		return "Small Straight";
	}
}
