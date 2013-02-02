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

/**
 * Class representing a applet for a game of Yahtzee. Responsible for all user
 * input and output for the game.
 * 
 * This class is nearly identical to YahtzeeFrame, the only difference is it
 * displays the game in a JApplet instead of a JFrame.
 * 
 * @author Ryan Harrison
 * 
 */
public class YahtzeeApplet extends JApplet
{
	private static final long serialVersionUID = 1L;

	/**
	 * Set of Yahtzee dice for use in the game
	 */
	private YahtzeeDice[] dice;

	/**
	 * The player of the Yahtzee game
	 */
	private Player playerOne;

	/**
	 * The set of scores for the player in the game
	 */
	private ScoreBoard scores;

	/**
	 * Button to roll the set of dice
	 */
	private JButton rollDiceButton;

	/**
	 * Button to start a new game of Yahtzee
	 */
	private JButton newGame;

	/**
	 * The panel used to display the game
	 */
	private JPanel mainPanel;

	/**
	 * Create a new Yahtzee applet game
	 */
	public YahtzeeApplet()
	{
		// Initialise the panel
		mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

		dice = new YahtzeeDice[5];
		playerOne = new Player("Sebastian");
		scores = new ScoreBoard(playerOne, dice);

		// Initially nothing can be selected as the game has not yet started
		// (started when the player rolls the dice for the first time)
		scores.setCanBeSelected(false);

		// Create the roll dice button, adding an action listener and adding to
		// the frame
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

		// Initialise the array of Yahtzee dice, adding each one to the frame
		for (int i = 0; i < 5; i++)
		{
			dice[i] = new YahtzeeDice(100);
			mainPanel.add(dice[i]);
		}

		// Add a change listener to the ScoreBoard to listen for changes in the
		// scores
		scores.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				// If the game has finished, show a prompt to the user
				if (checkGameFinished())
				{
					showFinalScorePrompt();
				}
				else
				{
					// Otherwise the player has ended a turn so no other
					// category can be selected.
					// The player needs to roll the dice to be able to select
					// another category.
					scores.setCanBeSelected(false);
					rollDiceButton.setEnabled(true);
					playerOne.resetRollCount();
				}
			}
		});

		mainPanel.add(scores);

		// Initialise the new game button, adding an ActionListener to start a
		// new game when pressed.
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

		// Create an add a 4 pixel black border to the entire panel
		Border border = BorderFactory.createLineBorder(Color.BLACK, 4);
		mainPanel.setBorder(border);

		// Finally add the game panel to the applet
		add(mainPanel);
	}

	/**
	 * Check to see if the game has been finished
	 * 
	 * @return True if the game has been finished (all categories have been
	 *         chosen by the player, otherwise false
	 */
	private boolean checkGameFinished()
	{
		return scores.isAllChosen();
	}

	/**
	 * Start a new game of Yahtzee. Reset the ScoreBoard, Player and each dice
	 */
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

	/**
	 * Method called whenever the roll dice button is clicked
	 */
	private void rollDiceButtonClick()
	{
		// When the player presses the button, they are able to select a score
		// group from the board
		scores.setCanBeSelected(true);
		scores.setUsingJokerRulesOverrideScore(false);

		// If the player has not reached the maximum roll count of 3
		if (playerOne.getRollCount() < 3)
		{
			// If this is the first roll in a turn, then no dice is being held
			if (playerOne.getRollCount() == 0)
			{
				for (int i = 0; i < 5; i++)
				{
					dice[i].setHoldState(false);
				}
			}

			// If the dice is not being held, roll it
			for (int i = 0; i < 5; i++)
			{
				if (dice[i].getHoldState() == false)
					dice[i].roll();
			}

			playerOne.incrementRollCount();

			// If the roll count is now 3, then disable the button
			if (playerOne.getRollCount() == 3)
			{
				rollDiceButton.setEnabled(false);
			}
		}
		scores.setDice(dice);
	}

	/**
	 * Show a prompt to the user when the game has finished showing their final
	 * score and an option to start a new game. If 'YES' is selected, a new game
	 * is started.
	 */
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
