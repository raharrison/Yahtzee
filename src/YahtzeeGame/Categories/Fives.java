package YahtzeeGame.Categories;

import YahtzeeGame.Components.YahtzeeDice;

class Fives extends Category
{

    public int getScore(YahtzeeDice[] dice)
    {
	return addUpDice(5, dice);
    }
    
    public int getCategoryIndex()
    {
	return 5;
    }
    
    public String toString()
    {
	return "Fives";
    }

    public int getYahtzeeBonusOverrideScore(YahtzeeDice[] dice)
    {
	return 0;
    }
}
