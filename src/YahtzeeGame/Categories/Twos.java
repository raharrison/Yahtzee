package YahtzeeGame.Categories;

import YahtzeeGame.Components.YahtzeeDice;

class Twos extends Category
{
    public int getScore(YahtzeeDice[] dice)
    {
	return addUpDice(2, dice);
    }

    public int getCategoryIndex()
    {
	return 2;
    }

    public String toString()
    {
	return "Twos";
    }
   
    public int getYahtzeeBonusOverrideScore(YahtzeeDice[] dice)
    {
	return 0;
    }
}
