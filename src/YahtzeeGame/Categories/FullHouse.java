package YahtzeeGame.Categories;

import java.util.Arrays;

import YahtzeeGame.Components.YahtzeeDice;

/**
 * Full House category
 * 
 * @author Ryan Harrison
 * 
 */
class FullHouse extends Category
{
	@Override
	public int getCategoryIndex()
	{
		return 9;
	}

	/**
	 * Return 25 if there is a three of a kind and a two of a kind with
	 * different values, otherwise 0s
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

		// Three of a kind and a two of a kind
		if ((((theDice[0] == theDice[1]) && (theDice[1] == theDice[2]))
				&& (theDice[3] == theDice[4]) && // Two of a Kind
		(theDice[2] != theDice[3])) // make sure they are of different values

				// or two of a kind and a three of a kind
				|| ((theDice[0] == theDice[1]) && // Two of a Kind
						// Three of a kind
						((theDice[2] == theDice[3]) && (theDice[3] == theDice[4]))
				// make sure they are of different values
				&& (theDice[1] != theDice[2])))
		{
			sum = 25;
		}

		return sum;
	}

	@Override
	public int getYahtzeeBonusOverrideScore(YahtzeeDice[] dice)
	{
		return 25;
	}

	@Override
	public String toString()
	{
		return "Full House";
	}
}
