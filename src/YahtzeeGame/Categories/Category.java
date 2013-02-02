package YahtzeeGame.Categories;

import YahtzeeGame.Components.YahtzeeDice;

/**
 * Class representing a category in a game of Yahtzee. Each category has a name
 * and can generate a score depending on a set of dice.
 * 
 * @author Ryan Harrison
 * 
 */
public abstract class Category
{
	/**
	 * Get a Category associated with the specified index.
	 * 
	 * @param index
	 *            The index to get the Category associated with
	 * @return A Category associated with the specified index. Null if the index
	 *         is not recognised.
	 */
	public static Category getCategory(int index)
	{
		switch (index)
		{
		case 1:
			return new Ones();
		case 2:
			return new Twos();
		case 3:
			return new Threes();
		case 4:
			return new Fours();
		case 5:
			return new Fives();
		case 6:
			return new Sixes();
		case 7:
			return new ThreeOfAKind();
		case 8:
			return new FourOfAKind();
		case 9:
			return new FullHouse();
		case 10:
			return new SmallStraight();
		case 11:
			return new LargeStraight();
		case 12:
			return new Chance();
		case 13:
			return new Yahtzee();
		case 14:
			return new Total();
		case 15:
			return new Bonus();
		default:
			return null;
		}
	}

	/**
	 * Get an upper category from a String
	 * 
	 * @param num
	 *            The String to generate an upper Category from
	 * @return An upper category associated with the specified String. Null if
	 *         the String is not recognised as being a valid upper category.
	 */
	public static Category getUpperCategoryFromNum(String num)
	{
		if (num.equals("1"))
			return new Ones();
		else if (num.equals("2"))
			return new Twos();
		else if (num.equals("3"))
			return new Threes();
		else if (num.equals("4"))
			return new Fours();
		else if (num.equals("5"))
			return new Fives();
		else if (num.equals("6"))
			return new Sixes();

		return null;
	}

	/**
	 * Generate a score for a Yahtzee from the specified set of dice. If more
	 * than four die have the same value from 1 to 6, then the score is 50,
	 * otherwise it is 0
	 * 
	 * @param dice
	 *            The dice to generate a Yahtzee score for
	 * @return 50 if more than four die have the same value from 1 to 6,
	 *         otherwise 0.
	 */
	public static int getYahtzeeScore(YahtzeeDice[] dice)
	{
		int sum = 0;

		for (int i = 1; i <= 6; i++)
		{
			int count = 0;
			for (int j = 0; j < 5; j++)
			{
				if (dice[j].getValue() == i)
					count++;

				if (count > 4)
					sum = 50;
			}
		}

		return sum;
	}

	/**
	 * Sum the values of a set of dice where the value of the dice is value
	 * 
	 * @param value
	 *            The value a dice must be if added to the sum
	 * @param dice
	 *            The set of dice to sum
	 * @return A sum of the values of a set of dice where the value of the dice
	 *         is the specified value
	 */
	protected int addUpDice(int value, YahtzeeDice[] dice)
	{
		int sum = 0;

		for (int i = 0; i < 5; i++)
		{
			if (dice[i].getValue() == value)
			{
				sum += value;
			}
		}

		return sum;
	}

	/**
	 * Get an index to identify each Category
	 * 
	 * @return A unique index to identify the current Category
	 */
	public abstract int getCategoryIndex();

	/**
	 * Get the score for this dice depending on the values of a set of dice.
	 * 
	 * @param dice
	 *            The set of dice to use when determining the score
	 * @return The score for this category from the specified set of dice.
	 */
	public abstract int getScore(YahtzeeDice[] dice);

	/**
	 * Get the score for this dice depending on the values of a set of dice
	 * using alternate scoring rules when a Yahztee has been scored.
	 * 
	 * @param dice
	 *            The set of dice to use when determining the score
	 * @return The score for this category from the specified set of dice using
	 *         alternate scoring rules when a Yahztee has been scored.
	 */
	public abstract int getYahtzeeBonusOverrideScore(YahtzeeDice[] dice);

	/**
	 * Return a String representation of the current Category
	 */
	@Override
	public abstract String toString();
}
