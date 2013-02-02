package YahtzeeGame.Categories;

import YahtzeeGame.Components.YahtzeeDice;

class Total extends Category
{

    public int getScore(YahtzeeDice[] dice)
    {
	return 0;
    }

    public String toString()
    {
	return "Total";
    }

    public int getCategoryIndex()
    {
	return 14;
    }

    public int getYahtzeeBonusOverrideScore(YahtzeeDice[] dice)
    {
	return 0;
    }
}
