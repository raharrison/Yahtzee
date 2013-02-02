package YahtzeeGame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

import YahtzeeGame.Categories.Category;
import YahtzeeGame.Components.Resettable;
import YahtzeeGame.Components.ScoreGroup;
import YahtzeeGame.Components.StaticScoreGroup;
import YahtzeeGame.Components.YahtzeeDice;

class ScoreBoard extends JPanel implements Resettable
{
	private static final long serialVersionUID = 1L;

	private final EventListenerList listenerList;

	private ScoreGroup[] categories;
	private YahtzeeDice[] dice;

	private Player player;

	private JPanel higherCategories;
	private JPanel lowerCategories;

	private StaticScoreGroup upperSectionBonus;
	private StaticScoreGroup upperSectionTotal;
	private StaticScoreGroup lowerSectionYahtzeeBonus;
	private StaticScoreGroup grandTotal;

	public ScoreBoard(Player player, YahtzeeDice[] dice)
	{
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		this.setPreferredSize(new Dimension(750, 300));

		listenerList = new EventListenerList();
		this.dice = dice;
		this.player = player;
		categories = new ScoreGroup[13];

		higherCategories = new JPanel();
		higherCategories.setPreferredSize(new Dimension(350, 300));
		higherCategories.setLayout(new BoxLayout(higherCategories,
				BoxLayout.Y_AXIS));

		lowerCategories = new JPanel();
		lowerCategories.setPreferredSize(new Dimension(350, 300));
		lowerCategories.setLayout(new BoxLayout(lowerCategories,
				BoxLayout.Y_AXIS));

		fillCategories(categories);
		addCategories(categories);
		addEvents(categories);
	}

	private void addCategories(ScoreGroup[] categories2)
	{
		for (int i = 0; i < 6; i++)
		{
			addCategory(higherCategories, categories[i]);
		}

		addCategory(higherCategories, upperSectionBonus);
		addCategory(higherCategories, upperSectionTotal);

		for (int i = 6; i < categories.length; i++)
		{
			addCategory(lowerCategories, categories[i]);
		}

		addCategory(lowerCategories, lowerSectionYahtzeeBonus);
		addCategory(lowerCategories, grandTotal);

		this.add(higherCategories);
		this.add(lowerCategories);
	}

	private void addCategory(JPanel panel, ScoreGroup group)
	{
		panel.add(group);
		panel.add(Box.createVerticalStrut(7));
	}

	public void addChangeListener(ChangeListener listener)
	{
		listenerList.add(ChangeListener.class, listener);
	}

	private void addEvents(ScoreGroup[] categories2)
	{
		for (int i = 0; i < categories.length; i++)
		{
			categories[i].addMouseListener(new MouseListener()
			{
				@Override
				public void mouseClicked(MouseEvent e)
				{}

				@Override
				public void mouseEntered(MouseEvent e)
				{
					ScoreGroup sg = (ScoreGroup) e.getSource();
					if (sg.getCanBeSelected())
					{
						if (!sg.getChosen())
						{
							sg.updateScore(dice);
						}
					}
				}

				@Override
				public void mouseExited(MouseEvent e)
				{
					ScoreGroup sg = (ScoreGroup) e.getSource();
					if (sg.getCanBeSelected())
					{
						if (!sg.getChosen())
						{
							sg.setTextToCategory();
						}
					}
				}

				@Override
				public void mousePressed(MouseEvent e)
				{
					ScoreGroup sg = (ScoreGroup) e.getSource();
					if (sg.getCanBeSelected())
					{
						if (!sg.getChosen())
						{
							sg.updateScore(dice);
							player.addScore(sg.getScore(dice), sg.getIsUpper());
							sg.setChosen(true);

							fireChangeEvent(new ChangeEvent(sg.getScore(dice)));
							// check Yahtzee
							if (sg.isYahtzeeScoreGroup()
									&& sg.getScore(dice) == 50)
								player.setHaveYahtzee();
						}
					}
				}

				@Override
				public void mouseReleased(MouseEvent e)
				{}
			});
		}
	}

	private void fillCategories(ScoreGroup[] categories)
	{
		for (int i = 0; i < categories.length; i++)
		{
			categories[i] = new ScoreGroup(Category.getCategory(i + 1));
		}

		upperSectionBonus = new StaticScoreGroup("Upper Section Bonus");
		upperSectionTotal = new StaticScoreGroup("Upper Total");
		lowerSectionYahtzeeBonus = new StaticScoreGroup("Yahtzee Bonus");
		grandTotal = new StaticScoreGroup("Grand Total");
	}

	private void fireChangeEvent(ChangeEvent event)
	{
		updateUpperBonus();

		for (ChangeListener listener : listenerList
				.getListeners(ChangeListener.class))
		{
			listener.stateChanged(event);
		}
	}

	// 1. Set can be selected to appropriate upper section box
	// 2. If the box is already chosen. release all lower section boxes that are
	// not chosen
	// 3. If none free score zero in any box left
	private void initiateJokerRules()
	{
		String num = Category.getUpperCategoryFromNum(
				Integer.toString(dice[0].getValue())).toString();

		// set upper category can be selected based on num
		for (int i = 0; i < categories.length; i++)
		{
			if (categories[i].getCategoryName().equals(num)
					&& !categories[i].getChosen())
			{
				categories[i].setCanBeSelected(true);
			}
			else
			{
				categories[i].setCanBeSelected(false);
			}
		}

		// If none can be selected in upper section release all lower sections
		if (isNoneCanBeSelected())
		{
			for (int i = 6; i < categories.length; i++)
			{
				categories[i].setCanBeSelected(true);
				categories[i].setUsingOverrideScore(true);
			}

			// If all lower sections full release all upper sections
			if (isAllLowerSectionsIsChosen())
			{
				for (int i = 0; i < 6; i++)
				{
					categories[i].setCanBeSelected(true);
					categories[i].setUsingOverrideScore(true);
				}
			}
		}
	}

	public boolean isAllChosen()
	{
		for (int i = 0; i < categories.length; i++)
		{
			if (!categories[i].getChosen())
			{
				return false;
			}
		}

		return true;
	}

	private boolean isAllLowerSectionsIsChosen()
	{
		for (int i = 6; i < categories.length; i++)
		{
			if (!categories[i].getChosen())
			{
				return false;
			}
		}

		return true;
	}

	private boolean isDiceYahtzee(YahtzeeDice[] dice)
	{
		return Category.getYahtzeeScore(dice) == 50;
	}

	private boolean isNoneCanBeSelected()
	{
		for (int i = 0; i < categories.length; i++)
		{
			if (categories[i].getCanBeSelected())
			{
				return false;
			}
		}

		return true;
	}

	public void removeChangeListener(ChangeListener listener)
	{
		listenerList.remove(ChangeListener.class, listener);
	}

	/*
	 * Jokers Rules - Score total of all five dice in appropriate upper section
	 * box if already filled in score in lower section as normal If both filled
	 * in score zero in any free upper section
	 */

	@Override
	public void reset()
	{
		for (int i = 0; i < categories.length; i++)
		{
			categories[i].reset();
		}
		upperSectionBonus.reset();
		upperSectionTotal.reset();
		lowerSectionYahtzeeBonus.reset();
		grandTotal.reset();
	}

	public void setCanBeSelected(boolean selected)
	{
		for (int i = 0; i < categories.length; i++)
		{
			categories[i].setCanBeSelected(selected);
		}
	}

	public void setDice(YahtzeeDice[] dice)
	{
		this.dice = dice;
		updateYahtzeeBonus();

		if (isDiceYahtzee(dice) && player.getHaveYahtzee())
		{
			initiateJokerRules();
		}
	}

	public void setPlayer(Player player)
	{
		this.player = player;
	}

	public void setUsingYahtzeeBonusOverrideScore(boolean selected)
	{
		for (int i = 0; i < categories.length; i++)
		{
			categories[i].setUsingOverrideScore(selected);
		}
	}

	private void updateUpperBonus()
	{
		if (player.getUpperBonus() == 0 && player.getUpperScore() >= 63)
		{
			player.addUpperBonus();
		}

		upperSectionBonus.setScore(player.getUpperBonus());
		upperSectionTotal.setScore(player.getUpperScore());
		grandTotal.setScore(player.getScore());

		// Upper Section bonus - if total above 63 then upper bonus of 35 points
	}

	private void updateYahtzeeBonus()
	{
		if (player.getHaveYahtzee() && isDiceYahtzee(dice))
		{
			player.addYahtzeeBonus();
		}
		lowerSectionYahtzeeBonus.setScore(player.getYahtzeeBonus());
		grandTotal.setScore(player.getScore());

		// Yahtzee bonus - if Yahtzee already scored, subsequent get 100 bonus
	}
}
