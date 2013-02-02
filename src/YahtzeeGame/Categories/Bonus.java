package YahtzeeGame.Categories;

import YahtzeeGame.Components.YahtzeeDice;

class Bonus extends Category
{
    public int getScore(YahtzeeDice[] dice)
    {
	return 0;
    }

    public String toString()
    {
	return "Bonus";
    }

    public int getCategoryIndex()
    {
	return 15;
    }

    public int getYahtzeeBonusOverrideScore(YahtzeeDice[] dice)
    {
	return 0;
    }
}
