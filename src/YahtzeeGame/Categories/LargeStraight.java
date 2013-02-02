package YahtzeeGame.Categories;

import java.util.Arrays;

import YahtzeeGame.Components.YahtzeeDice;

class LargeStraight extends Category
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

	if (((theDice[0] == 1) && (theDice[1] == 2) && (theDice[2] == 3) && (theDice[3] == 4) && (theDice[4] == 5)) || ((theDice[0] == 2) && (theDice[1] == 3) && (theDice[2] == 4) && (theDice[3] == 5) && (theDice[4] == 6)))
	{
	    sum = 40;
	}

	return sum;
    }

    public int getCategoryIndex()
    {
	return 11;
    }

    public String toString()
    {
	return "Large Straight";
    }

    public int getYahtzeeBonusOverrideScore(YahtzeeDice[] dice)
    {
	return 40;
    }
}
