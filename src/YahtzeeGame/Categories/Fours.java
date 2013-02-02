package YahtzeeGame.Categories;

import YahtzeeGame.Components.YahtzeeDice;

class Fours extends Category
{
    public int getScore(YahtzeeDice[] dice)
    {
	return addUpDice(4, dice);
    }

    public int getCategoryIndex()
    {
	return 4;
    }

    public String toString()
    {
	return "Fours";
    }

    public int getYahtzeeBonusOverrideScore(YahtzeeDice[] dice)
    {
	return 0;
    }
}
