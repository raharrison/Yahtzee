package YahtzeeGame.Components;

import YahtzeeGame.Categories.*;

public class StaticScoreGroup extends ScoreGroup implements Resettable
{
	private static final long serialVersionUID = 1L;

	public StaticScoreGroup()
	{
		super(Category.getCategory(14));
		this.chosen = true;
		this.setTextToCategory();
		this.score.setText("0");
	}

	public StaticScoreGroup(String totalName)
	{
		this();
		this.categoryName = totalName;
		this.setTextToCategory();
		this.score.setText("0");
	}

	public void setScore(int score)
	{
		this.score.setText(Integer.toString(score));
	}

	public void reset()
	{
		super.reset();
		this.setTextToCategory();
		this.score.setText("0");
	}
}