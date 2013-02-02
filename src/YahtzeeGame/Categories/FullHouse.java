package YahtzeeGame.Categories;

import java.util.Arrays;

import YahtzeeGame.Components.YahtzeeDice;

class FullHouse extends Category
{
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

	if ((((theDice[0] == theDice[1]) && (theDice[1] == theDice[2])) && // Three of a Kind
	(theDice[3] == theDice[4]) && // Two of a Kind
	(theDice[2] != theDice[3])) || ((theDice[0] == theDice[1]) && // Two of a Kind
	((theDice[2] == theDice[3]) && (theDice[3] == theDice[4])) && // Three of a Kind
	(theDice[1] != theDice[2])))
	{
	    sum = 25;
	}

	return sum;
    }

    public int getCategoryIndex()
    {
	return 9;
    }

    public String toString()
    {
	return "Full House";
    }

    public int getYahtzeeBonusOverrideScore(YahtzeeDice[] dice)
    {
	return 25;
    }
}
