package YahtzeeGame.Categories;

import YahtzeeGame.Components.YahtzeeDice;

class Threes extends Category
{
    public int getScore(YahtzeeDice[] dice)
    {
	return addUpDice(3, dice);
    }

    public int getCategoryIndex()
    {
	return 3;
    }

    public String toString()
    {
	return "Threes";
    }

    public int getYahtzeeBonusOverrideScore(YahtzeeDice[] dice)
    {
	return 0;
    }
}
