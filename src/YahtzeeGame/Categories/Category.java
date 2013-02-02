package YahtzeeGame.Categories;

import YahtzeeGame.Components.YahtzeeDice;

public abstract class Category
{
    public abstract int getScore(YahtzeeDice[] dice);

    public abstract int getCategoryIndex();
    
    public abstract int getYahtzeeBonusOverrideScore(YahtzeeDice[] dice);

    public static Category getCategory(int index)
    {
	switch (index)
	{
	    case 1:
		return new Ones();
	    case 2:
		return new Twos();
	    case 3:
		return new Threes();
	    case 4:
		return new Fours();
	    case 5:
		return new Fives();
	    case 6:
		return new Sixes();
	    case 7:
		return new ThreeOfAKind();
	    case 8:
		return new FourOfAKind();
	    case 9:
		return new FullHouse();
	    case 10:
		return new SmallStraight();
	    case 11:
		return new LargeStraight();
	    case 12:
		return new Chance();
	    case 13:
		return new Yahtzee();
	    case 14:
		return new Total();
	    case 15:
		return new Bonus();
	    default:
		return null;
	}
    }

    protected int addUpDice(int dieNumber, YahtzeeDice[] dice)
    {
	int sum = 0;

	for (int i = 0; i < 5; i++)
	{
	    if (dice[i].getValue() == dieNumber)
	    {
		sum += dieNumber;
	    }
	}

	return sum;
    }

    public static int getYahtzeeScore(YahtzeeDice[] dice)
    {
	int sum = 0;

	for (int i = 1; i <= 6; i++)
	{
	    int count = 0;
	    for (int j = 0; j < 5; j++)
	    {
		if (dice[j].getValue() == i)
		    count++;

		if (count > 4)
		    sum = 50;
	    }
	}

	return sum;
    }
    
    public static Category getUpperCategoryFromNum(String num)
    {
	if(num.equals("1"))
	    return new Ones();
	else if(num.equals("2"))
	    return new Twos();
	else if(num.equals("3"))
	    return new Threes();
	else if(num.equals("4"))
	    return new Fours();
	else if(num.equals("5"))
	    return new Fives();
	else if(num.equals("6"))
	    return new Sixes();
	
	return null;
    }

    public abstract String toString();
}
