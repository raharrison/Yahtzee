package YahtzeeGame.Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import YahtzeeGame.Categories.Category;
import YahtzeeGame.Categories.Yahtzee;

/**
 * Represents a score group in a game of Yahtzee Provides a graphical
 * representation of a Yahtzee category that can be selected and chosen and have
 * a score associated with it.
 * 
 * @author Ryan Harrison
 * 
 */
public class ScoreGroup extends JPanel implements Resettable
{
	private static final long serialVersionUID = 1L;

	/**
	 * The Yahtzee Category that this object wraps around
	 */
	protected Category category;

	/**
	 * Determines whether or not this group can be selected by the user
	 */
	protected boolean canBeSelected;

	/**
	 * Determines whether or not the user has already chosen this category. If
	 * so it cannot be selected or chosen again. The score is therefore final
	 * until a new game is started.
	 */
	protected boolean chosen;

	/**
	 * Determines whether or not this group is using an alternative scoring rule
	 * when a Yahtzee has been made and the Yahtzee category is zero.
	 */
	protected boolean usingJokerRules;

	/**
	 * The name of the category this component wraps. Displayed by the component
	 */
	protected String categoryName;

	/**
	 * Label displaying the category name
	 */
	protected JLabel text;

	/**
	 * Label displaying the score this category has generated if the group has
	 * been chosen by the user
	 */
	protected JLabel score;

	/**
	 * Create a new ScoreGroup with the specified Yahtzee Category. The
	 * component provides a graphical representation of the categor.
	 * 
	 * @param category
	 *            The Yahtzee category to wrap around
	 */
	public ScoreGroup(Category category)
	{
		super();

		// Set default state

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		canBeSelected = true;
		chosen = false;
		usingJokerRules = false;

		// Every group has a border to improve appearance
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		text = new JLabel();
		text.setPreferredSize(new Dimension(150, 30));
		score = new JLabel();

		this.category = category;
		this.categoryName = category.toString();
		this.text.setText(categoryName);

		// Add the components to the panel
		add(Box.createHorizontalStrut(4));
		add(text);
		add(score);
		add(Box.createGlue());

		// Paint the display of the group this object represents
		repaint();
	}

	/**
	 * Getter to determine if the group can be selected by the user
	 * 
	 * @return Can the component be selected by the user
	 */
	public boolean getCanBeSelected()
	{
		return canBeSelected;
	}

	/**
	 * Getter for the name of the category this object wraps
	 * 
	 * @return The name of the category this object wraps
	 */
	public String getCategoryName()
	{
		return this.categoryName;
	}

	/**
	 * Getter to determine if the group has been chosen
	 * 
	 * @return Has the group been chosen
	 */
	public boolean getChosen()
	{
		return chosen;
	}

	/**
	 * Is this group wrapping an upper category
	 * 
	 * @return True if this group is wrapping an upper category, false otherwise
	 */
	public boolean getIsUpper()
	{
		return category.getCategoryIndex() <= 6 ? true : false;
	}

	/**
	 * Get the score from the category given a set of Yahtzee dice
	 * 
	 * @param dice
	 *            The set of dice to generate the score from
	 * @return The score from this category given the values of the dice
	 */
	public int getScore(YahtzeeDice[] dice)
	{
		return category.getScore(dice);
	}

	/**
	 * Getter to determine if the group is using the alternate scoring rules
	 * 
	 * @param isUsing
	 *            Is the group using alternate scoring rules
	 */
	public boolean getUsingOverrideScore()
	{
		return usingJokerRules;
	}

	/**
	 * Does this group wrap the Yahtzee category
	 * 
	 * @return True if this group wraps the Yahtzee category, otherwise false
	 */
	public boolean isYahtzeeScoreGroup()
	{
		return category instanceof Yahtzee;
	}

	/**
	 * Override the paint method to add a line to separate the name from the
	 * score in the box
	 */
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		if (text != null)
		{
			// Draw the line separator
			int s = 130;
			g.drawLine(s, 0, s, (int) this.getSize().getHeight());
		}
	}

	/**
	 * Reset the state of the group
	 */
	@Override
	public void reset()
	{
		chosen = false;
		canBeSelected = false;
		this.text.setText(category.toString());
		this.score.setText("");
	}

	/**
	 * Setter to determine if the group can be selected by the user
	 * 
	 * @param canSelect
	 *            Can the component be selected by the user
	 */
	public void setCanBeSelected(boolean canSelect)
	{
		canBeSelected = canSelect;
	}

	/**
	 * Setter to determine if the group has been chosen
	 * 
	 * @param isChosen
	 *            Has the group been chosen
	 */
	public void setChosen(boolean isChosen)
	{
		chosen = isChosen;
	}

	/**
	 * Reset the text label to the name of the category and set the score label
	 * to display nothing
	 */
	public void setTextToCategory()
	{
		this.text.setText(categoryName);
		this.score.setText("");
	}

	/**
	 * Setter to determine if the group is using the alternate scoring rules
	 * 
	 * @param isUsing
	 *            Is the group using alternate scoring rules
	 */
	public void setUsingOverrideScore(boolean isUsing)
	{
		usingJokerRules = isUsing;
	}

	/**
	 * Update the score of this group taking the values from a series of Yahtzee
	 * dice This method updates the score label if the group has not already
	 * been chosen
	 * 
	 * @param dice
	 *            The set of dice to use when generating the new score for this
	 *            group.
	 */
	public void updateScore(YahtzeeDice[] dice)
	{
		if (!chosen)
		{
			// Get the score of the alternate system if needed
			if (usingJokerRules)
			{
				this.score.setText(Integer.toString(category
						.getYahtzeeBonusOverrideScore(dice)));
			}
			else
			{
				// Otherwise get the default score from the category
				this.score.setText(Integer.toString(getScore(dice)));
			}

			this.text.setText(categoryName);
		}
	}
}