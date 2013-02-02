package YahtzeeGame.Components;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

/**
 * Class representing a dice in a game of Yahtzee
 * 
 * The dice is it's own component that displays one side of a dice
 * 
 * When clicked the background toggles red/white inferring whether or not the
 * value will change on the next roll
 * 
 * @author Ryan Harrison
 * 
 */
public class YahtzeeDice extends JComponent implements MouseListener,
		Resettable
{
	private static final long serialVersionUID = 1L;

	/**
	 * The size of the Dice
	 */
	private Dimension size;

	/**
	 * The size of each dot displayed on the dice
	 */
	private Dimension dot;

	/**
	 * The size of the arc on the rounded rectangle of the dice itself
	 */
	private Dimension arc;

	/**
	 * The current value of this dice (displayed by the component)
	 */
	private int value;

	/**
	 * Determines whether or not the dice is being held. If so the background
	 * will turn red. Toggled when the user clicks anywhere on the dice
	 */
	private boolean holdState;

	/*
	 * Determines whether or not the mouse is currently hovering over the
	 * component or not. If so the outer stroke of the dice changes colour
	 */
	private boolean mouseEntered;

	/**
	 * Construct a new Yahtzee dice with specified width
	 * 
	 * @param width
	 *            The width of the dice in pixels
	 */
	public YahtzeeDice(int width)
	{
		super();

		// Every dice is square
		size = new Dimension(width, width);
		dot = new Dimension(size.width / 3, size.height / 3);
		arc = new Dimension((int) Math.sqrt(size.width),
				(int) Math.sqrt(size.height));

		// Add listeners to the component
		enableInputMethods(true);
		addMouseListener(this);

		setSize(this.size.width, size.height);
		setFocusable(true);

		// Set the default state of the object
		holdState = false;
		mouseEntered = false;
		value = 1;
	}

	/**
	 * Getter for the hold state
	 * 
	 * @return True if dice is currently being held, false otherwise.
	 */
	public boolean getHoldState()
	{
		return holdState;
	}

	/**
	 * Make sure dice is properly rendered when added to a panel/frame.
	 */
	@Override
	public Dimension getMaximumSize()
	{
		return getPreferredSize();
	}

	/**
	 * Make sure dice is properly rendered when added to a panel/frame.
	 */
	@Override
	public Dimension getMinimumSize()
	{
		return getPreferredSize();
	}

	/**
	 * Make sure dice is properly rendered when added to a panel/frame.
	 */
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(getWidth(), getHeight());
	}

	/**
	 * Get the value of the dice
	 * 
	 * @return The current value of the dice
	 */
	public int getValue()
	{
		return value;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{}

	/**
	 * Set flag and change cursor when mouse enters the boundaries of the dice
	 */
	@Override
	public void mouseEntered(MouseEvent e)
	{
		mouseEntered = true;

		setCursor(new Cursor(Cursor.HAND_CURSOR));

		repaint();
	}

	/**
	 * Reset flag and cursor when mouse exits boundaries of the dice.
	 */
	@Override
	public void mouseExited(MouseEvent e)
	{
		mouseEntered = false;

		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

		repaint();
	}

	/**
	 * Toggle the hold state when mouse is pressed
	 */
	@Override
	public void mousePressed(MouseEvent e)
	{
		holdState = !holdState;
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{}

	/**
	 * Paint the dice display onto the component. Displays the current value in
	 * dots on the dice side. If the mouse is currently hovering over the dice,
	 * the outer stroke is yellow, else black.
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		// Turn on anti-aliasing to improve the appearance
		Graphics2D antiAlias = (Graphics2D) g;
		antiAlias.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// Draw the background rounded rectangle of the dice, red if dice is
		// being held
		if (holdState)
			g.setColor(Color.RED);
		else
			g.setColor(Color.WHITE);

		g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc.width,
				arc.height);

		// Draw the border of the dice, yellow is mouse is currently over the
		// component, otherwise black
		if (mouseEntered)
			g.setColor(Color.YELLOW);
		else
			g.setColor(Color.BLACK);
		g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc.width,
				arc.height);

		// Draw inside light border
		g.setColor(Color.decode("#c0c0c0"));
		g.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, arc.width,
				arc.height);

		int height;
		int width = height = dot.height * 2 / 3;

		// Determine possible positions for each dot on the dice (in grid
		// format)
		// ~ x axis
		int left = getWidth() * 1 / 3 - dot.width / 2 - width / 4;
		int center = getWidth() * 2 / 3 - dot.width / 2 - width / 2;
		int right = getWidth() * 3 / 3 - dot.width / 2 - width * 3 / 4;

		// ~ y axis
		int top = getHeight() * 1 / 3 - dot.height / 2 - height / 4;
		int middle = getHeight() * 2 / 3 - dot.height / 2 - height / 2;
		int bottom = getHeight() * 3 / 3 - dot.height / 2 - height * 3 / 4;

		// Draw the dots depending on the value of the dice
		g.setColor(Color.BLACK);
		switch (value)
		{
		case 0:
			break;
		case 1:
			g.fillOval(center, middle, width, height);
			break;
		case 2:
			g.fillOval(right, top, width, height);
			g.fillOval(left, bottom, width, height);
			break;
		case 3:
			g.fillOval(right, top, width, height);
			g.fillOval(center, middle, width, height);
			g.fillOval(left, bottom, width, height);
			break;
		case 4:
			g.fillOval(left, top, width, height);
			g.fillOval(left, bottom, width, height);
			g.fillOval(right, top, width, height);
			g.fillOval(right, bottom, width, height);
			break;
		case 5:
			g.fillOval(left, top, width, height);
			g.fillOval(left, bottom, width, height);
			g.fillOval(right, top, width, height);
			g.fillOval(right, bottom, width, height);
			g.fillOval(center, middle, width, height);
			break;
		case 6:
			g.fillOval(left, top, width, height);
			g.fillOval(left, middle, width, height);
			g.fillOval(left, bottom, width, height);
			g.fillOval(right, top, width, height);
			g.fillOval(right, middle, width, height);
			g.fillOval(right, bottom, width, height);
			break;
		}
	}

	/**
	 * Reset the state of the dice
	 */
	@Override
	public void reset()
	{
		value = 1;
		holdState = false;
		repaint();
	}

	/**
	 * Roll the Yahtzee dice. Give the dice a new random value and repaint the
	 * display.
	 */
	public void roll()
	{
		value = (int) (Math.random() * 6 + 1);
		repaint();
	}

	/**
	 * Setter for the hold state
	 * 
	 * @param hold
	 *            The new hold state
	 */
	public void setHoldState(boolean hold)
	{
		holdState = hold;
	}
}
