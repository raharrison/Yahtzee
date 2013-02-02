package YahtzeeGame.Categories;

import YahtzeeGame.Components.YahtzeeDice;

public class Yahtzee extends Category
{
    public int getScore(YahtzeeDice[] dice)
    {
	return getYahtzeeScore(dice);
    }

    public int getCategoryIndex()
    {
	return 13;
    }

    public String toString()
    {
	return "Yahtzee";
    }
    
    public int getYahtzeeBonusOverrideScore(YahtzeeDice[] dice)
    {
	return 50;
    }
}
