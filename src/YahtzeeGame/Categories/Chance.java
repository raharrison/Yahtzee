package YahtzeeGame.Categories;

import YahtzeeGame.Components.YahtzeeDice;

class Chance extends Category
{
    public int getScore(YahtzeeDice[] dice)
    {
	int sum = 0;

	for (int i = 0; i < 5; i++)
	{
	    sum += dice[i].getValue();
	}

	return sum;
    }

    public int getCategoryIndex()
    {
	return 12;
    }

    public String toString()
    {
	return "Chance";
    }

    public int getYahtzeeBonusOverrideScore(YahtzeeDice[] dice)
    {
	return getScore(dice);
    }
}
