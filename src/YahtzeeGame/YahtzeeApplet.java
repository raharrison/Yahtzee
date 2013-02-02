package YahtzeeGame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import YahtzeeGame.Components.YahtzeeDice;

public class YahtzeeApplet extends JApplet
{
	private static final long serialVersionUID = 1L;
	private YahtzeeDice[] dice;
	private Player playerOne;

	private ScoreBoard scores;

	private JButton rollDiceButton;
	private JButton newGame;

	private JPanel mainPanel;

	public YahtzeeApplet()
	{
		mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

		dice = new YahtzeeDice[5];
		playerOne = new Player("Sebastian");

		scores = new ScoreBoard(playerOne, dice);

		scores.setCanBeSelected(false);

		rollDiceButton = new JButton("Roll Dice");
		rollDiceButton.setFont(new Font(rollDiceButton.getFont().getFontName(),
				Font.PLAIN, 20));
		rollDiceButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				rollDiceButtonClick();
			}
		});
		mainPanel.add(rollDiceButton);

		for (int i = 0; i < 5; i++)
		{
			dice[i] = new YahtzeeDice(100);
			mainPanel.add(dice[i]);
		}

		scores.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				if (checkGameFinished())
				{
					showFinalScorePrompt();
				}
				else
				{
					scores.setCanBeSelected(false);
					rollDiceButton.setEnabled(true);
					playerOne.resetRollCount();
				}
			}
		});

		mainPanel.add(scores);

		newGame = new JButton("New Game");
		newGame.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				newGame();
			}
		});

		mainPanel.add(newGame);

		Border b1 = BorderFactory.createLineBorder(Color.BLACK, 4);

		mainPanel.setBorder(b1);
		add(mainPanel);

	}

	private boolean checkGameFinished()
	{
		if (isAllSelected())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private boolean isAllSelected()
	{
		return scores.isAllChosen();
	}

	public void newGame()
	{
		scores.reset();
		playerOne.reset();
		rollDiceButton.setEnabled(true);

		for (int i = 0; i < dice.length; i++)
		{
			dice[i].reset();
		}
	}

	private void rollDiceButtonClick()
	{
		scores.setCanBeSelected(true);
		scores.setUsingYahtzeeBonusOverrideScore(false);

		if (playerOne.getRollCount() < 3)
		{
			if (playerOne.getRollCount() == 0)
			{
				for (int i = 0; i < 5; i++)
				{
					dice[i].setHoldState(false);
				}
			}

			for (int i = 0; i < 5; i++)
			{
				if (dice[i].getHoldState() == false)
					dice[i].roll();
			}

			playerOne.IncrementRollCount();

			if (playerOne.getRollCount() == 3)
			{
				rollDiceButton.setEnabled(false);
			}
		}
		scores.setDice(dice);
	}

	private void showFinalScorePrompt()
	{
		if (JOptionPane.showOptionDialog(this, "Your final score is "
				+ playerOne.getScore()
				+ ". Would you like to start a new game?", "Game End",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				null, null) == 0)
		{
			newGame();
		}
	}
}
