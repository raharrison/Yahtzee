package YahtzeeGame.Categories;

import YahtzeeGame.Components.YahtzeeDice;

class Ones extends Category
{
    public int getScore(YahtzeeDice[] dice)
    {
	return addUpDice(1, dice);
    }

    public int getCategoryIndex()
    {
	return 1;
    }

    public String toString()
    {
	return "Ones";
    }

    public int getYahtzeeBonusOverrideScore(YahtzeeDice[] dice)
    {
	return 0;
    }
}
