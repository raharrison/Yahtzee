package YahtzeeGame.Components;

import javax.swing.JComponent;
import java.awt.*;
import java.awt.event.*;

public class YahtzeeDice extends JComponent implements MouseListener, Resettable
{
    private static final long serialVersionUID = 1L;

    private Dimension size;

    private Dimension dot;
    private Dimension arc;

    private int value;

    private boolean holdState;
    private boolean mouseEntered;

    public YahtzeeDice(int width)
    {
	super();

	size = new Dimension(width, width);
	dot = new Dimension((int) (size.width / 3), (int) (size.height / 3));
	arc = new Dimension((int) Math.sqrt(size.width), (int) Math.sqrt(size.height));

	enableInputMethods(true);
	addMouseListener(this);

	setSize(this.size.width, size.height);
	setFocusable(true);

	holdState = false;
	mouseEntered = false;
	value = 1;
    }

    public void paintComponent(Graphics g)
    {
	super.paintComponent(g);

	// turn on anti-alias mode
	Graphics2D antiAlias = (Graphics2D) g;
	antiAlias.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	// draw white rectangle
	if (holdState)
	    g.setColor(Color.RED);
	else
	    g.setColor(Color.WHITE);

	g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc.width, arc.height);

	// draw black border
	if (mouseEntered)
	    g.setColor(Color.YELLOW);
	else
	    g.setColor(Color.BLACK);
	g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc.width, arc.height);

	// draw inside light border
	g.setColor(Color.decode("#c0c0c0"));
	g.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, arc.width, arc.height);

	int height;
	int width = height = dot.height * 2 / 3;

	// possible positions for each dot on the dice
	// ~ x axis
	int left = getWidth() * 1 / 3 - dot.width / 2 - width / 4;
	int center = getWidth() * 2 / 3 - dot.width / 2 - width / 2;
	int right = getWidth() * 3 / 3 - dot.width / 2 - width * 3 / 4;

	// ~ y axis
	int top = getHeight() * 1 / 3 - dot.height / 2 - height / 4;
	int middle = getHeight() * 2 / 3 - dot.height / 2 - height / 2;
	int bottom = getHeight() * 3 / 3 - dot.height / 2 - height * 3 / 4;

	// draw grid
	g.setColor(Color.RED);
	for (int x = 0; x < 3; x++)
	{
	    for (int y = 0; y < 3; y++)
	    {
		// g.drawRect(x*getWidth()/3,y*getHeight()/3,getWidth()/3,getHeight()/3);
	    }
	}

	// draw the dots
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
    
    public void roll()
    {
	value = (int) (Math.random() * 6 + 1);
	// give this dice a random number from 1-6
	//value = (int) (Math.random() * 6 + 1);
	repaint();
    }

    public void mouseClicked(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {
	mouseEntered = true;

	setCursor(new Cursor(Cursor.HAND_CURSOR));

	repaint();
    }

    public void mouseExited(MouseEvent e)
    {
	mouseEntered = false;

	setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

	repaint();
    }

    public void mousePressed(MouseEvent e)
    {
	holdState = !holdState;
	repaint();
    }

    public void mouseReleased(MouseEvent e)
    {
    }

    public Dimension getPreferredSize()
    {
	return new Dimension(getWidth(), getHeight());
    }

    public Dimension getMinimumSize()
    {
	return getPreferredSize();
    }

    public Dimension getMaximumSize()
    {
	return getPreferredSize();
    }

    public boolean getHoldState()
    {
	return holdState;
    }

    public void setHoldState(boolean hold)
    {
	holdState = hold;
    }

    public int getValue()
    {
	return value;
    }
    
    public void setvalue(int value)
    {
	this.value = value;
    }

    public void reset()
    {
	value = 1;
	holdState = false;
	repaint();
    }
}
