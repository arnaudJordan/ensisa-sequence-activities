package sequence.ui.utilities;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class SelectRectangle extends JPanel implements MouseMotionListener,
		MouseListener {
	private static final long serialVersionUID = 1L;
	private Rectangle selection;
	private Point anchor;
	private final Container context;

	public SelectRectangle() {
		addMouseListener(this);
		addMouseMotionListener(this);
		context = getParent();
	}

	public SelectRectangle(final Container context) {
		addMouseListener(this);
		addMouseMotionListener(this);
		this.context = context;
	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
		if (selection != null) {
			final Graphics2D g2d = (Graphics2D) g;
			g2d.draw(selection);
		}
	}

	public boolean contains(final Component c) {
		for (int i = 0; i < context.getComponentCount(); i++) {
			if (c.equals(context.getComponent(i)))
				return true;
		}
		return false;
	}

	private void selectComponents(final Point p) {
		final Component component = context.getComponentAt(p);
		if (!contains(component) && !component.equals(context))
			add(component);
		for (int i = 0; i < getComponentCount(); i++) {
			if (!selection.intersects(getComponent(i).getBounds()))
				remove(getComponent(i));
		}
	}

	@Override
	public void mousePressed(final MouseEvent e) {
		anchor = e.getPoint();
		selection = new Rectangle(anchor);
		selectComponents(e.getPoint());
	}

	@Override
	public void mouseDragged(final MouseEvent e) {
		selection.setBounds(Math.min(anchor.x, e.getX()),
				Math.min(anchor.y, e.getY()), Math.abs(e.getX() - anchor.x),
				Math.abs(e.getY() - anchor.y));
		selectComponents(e.getPoint());
		repaint();
	}

	@Override
	public void mouseReleased(final MouseEvent e) {
		selection = null;
		repaint();
	}

	@Override
	public void mouseMoved(final MouseEvent e) {
	}

	@Override
	public void mouseClicked(final MouseEvent e) {
	}

	@Override
	public void mouseEntered(final MouseEvent e) {
	}

	@Override
	public void mouseExited(final MouseEvent e) {
	}
}