package YahtzeeGame.Categories;

import YahtzeeGame.Components.YahtzeeDice;

class Sixes extends Category
{
    public int getScore(YahtzeeDice[] dice)
    {
	return addUpDice(6, dice);
    }

    public int getCategoryIndex()
    {
	return 6;
    }

    public String toString()
    {
	return "Sixes";
    }

    public int getYahtzeeBonusOverrideScore(YahtzeeDice[] dice)
    {
	return 0;
    }
}
