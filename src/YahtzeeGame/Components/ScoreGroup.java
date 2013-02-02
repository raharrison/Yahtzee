package YahtzeeGame.Components;

import java.awt.*;
import javax.swing.*;

import YahtzeeGame.Categories.*;

public class ScoreGroup extends JPanel implements Resettable
{
	private static final long serialVersionUID = 1L;

	protected Category category;
	protected boolean canBeSelected;
	protected boolean chosen;
	protected boolean usingYahtzeeBonusOverrideScore;
	protected String categoryName;

	protected JLabel text;
	protected JLabel score;

	public ScoreGroup(Category category)
	{
		super();

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		canBeSelected = true;
		chosen = false;
		usingYahtzeeBonusOverrideScore = false;

		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		text = new JLabel();
		text.setPreferredSize(new Dimension(150, 30));
		score = new JLabel();
		score.setFont(new Font(score.getFont().getFontName(), Font.BOLD, score
				.getFont().getSize()));

		add(Box.createHorizontalStrut(4));
		add(text);
		add(score);
		add(Box.createGlue());

		this.category = category;
		this.categoryName = category.toString();
		this.text.setText(categoryName);

		repaint();
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		if (text != null)
		{
			int s = 130;
			g.drawLine(s, 0, s, (int) this.getSize().getHeight());
		}
	}

	public int getScore(YahtzeeDice[] dice)
	{
		return category.getScore(dice);
	}

	public void updateScore(YahtzeeDice[] dice)
	{
		if (!chosen)
		{
			if (usingYahtzeeBonusOverrideScore)
			{
				this.score.setText(Integer.toString(category
						.getYahtzeeBonusOverrideScore(dice)));
			}
			else
			{
				this.score.setText(Integer.toString(category.getScore(dice)));
			}

			this.text.setText(categoryName);
		}
	}

	public void setCanBeSelected(boolean canSelect)
	{
		canBeSelected = canSelect;
	}

	public boolean getCanBeSelected()
	{
		return canBeSelected;
	}

	public boolean getChosen()
	{
		return chosen;
	}

	public void setChosen(boolean isChosen)
	{
		chosen = isChosen;
	}

	public void setUsingOverrideScore(boolean isUsing)
	{
		usingYahtzeeBonusOverrideScore = isUsing;
	}

	public boolean getUsingOverrideScore()
	{
		return usingYahtzeeBonusOverrideScore;
	}

	public boolean isYahtzeeScoreGroup()
	{
		return category instanceof Yahtzee;
	}

	public void reset()
	{
		chosen = false;
		canBeSelected = false;
		this.text.setText(category.toString());
		this.score.setText("");
	}

	public boolean getIsUpper()
	{
		return category.getCategoryIndex() <= 6 ? true : false;
	}

	public String getCategoryName()
	{
		return this.categoryName;
	}

	public void setTextToCategory()
	{
		this.text.setText(categoryName);
		this.score.setText("");
	}
}