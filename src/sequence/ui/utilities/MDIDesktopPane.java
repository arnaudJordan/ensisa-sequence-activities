package sequence.ui.utilities;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultDesktopManager;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

/**
 * An extension of JDesktopPane that supports often used MDI functionality. This
 * class also handles setting scroll bars for when windows move too far to the
 * left or bottom, providing the MDIDesktopPane is in a ScrollPane.
 */
public class MDIDesktopPane extends JDesktopPane {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The FRAM e_ offset. */
	private static int FRAME_OFFSET = 20;
	
	/** The manager. */
	private final MDIDesktopManager manager;
	
	/** The frames. */
	private final List<JInternalFrame> frames;

	/**
	 * Instantiates a new mDI desktop pane.
	 */
	public MDIDesktopPane() {
		manager = new MDIDesktopManager(this);
		setDesktopManager(manager);
		setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		frames = new ArrayList<JInternalFrame>();
	}

	/* (non-Javadoc)
	 * @see java.awt.Component#setBounds(int, int, int, int)
	 */
	@Override
	public void setBounds(final int x, final int y, final int w, final int h) {
		super.setBounds(x, y, w, h);
		checkDesktopSize();
	}
	
	/**
	 * Adds the.
	 *
	 * @param frame the frame
	 * @return the component
	 */
	public Component add(final JInternalFrame frame) {
		frames.add(frame);
		final JInternalFrame[] array = getAllFrames();
		Point p;
		int w;
		int h;

		final Component retval = super.add(frame);
		checkDesktopSize();
		if (array.length > 0) {
			p = array[0].getLocation();
			p.x = p.x + FRAME_OFFSET;
			p.y = p.y + FRAME_OFFSET;
		} else {
			p = new Point(0, 0);
		}
		frame.setLocation(p.x, p.y);
		if (frame.isResizable()) {
			final Dimension d = getVisibleRect().getSize();
			if (getBorder() != null) {
				d.setSize(d.getWidth() - getInsets().left - getInsets().right,
						d.getHeight() - getInsets().top - getInsets().bottom);
			}
			w = d.width - (d.width / 3);
			// h = getHeight() - (getHeight()/3);
			h = d.height / 2;
			if (w < frame.getMinimumSize().getWidth())
				w = (int) frame.getMinimumSize().getWidth();
			if (h < frame.getMinimumSize().getHeight())
				h = (int) frame.getMinimumSize().getHeight();
			frame.setSize(w, h);
		}
		moveToFront(frame);
		frame.setVisible(true);
		try {
			frame.setSelected(true);
		} catch (final PropertyVetoException e) {
			frame.toBack();
		}
		return retval;
	}

	/* (non-Javadoc)
	 * @see java.awt.Container#add(java.awt.Component)
	 */
	@Override
	public Component add(final Component c) {
		final Component comp = super.add(c);
		if (c instanceof JInternalFrame)
			frames.add((JInternalFrame) c);
		return comp;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Container#remove(java.awt.Component)
	 */
	@Override
	public void remove(final Component c) {
		super.remove(c);
		checkDesktopSize();
		frames.remove(c);
	}

	/**
	 * Cascade all internal frames.
	 */
	public void cascadeFrames() {
		System.out.println(frames);
		int x = 0;
		int y = 0;
		// JInternalFrame allFrames[] = getAllFrames();

		manager.setNormalSize();
		final Dimension d = getVisibleRect().getSize();
		if (getBorder() != null) {
			d.setSize(d.getWidth() - getInsets().left - getInsets().right,
					d.getHeight() - getInsets().top - getInsets().bottom);
		}
		final int frameHeight = (d.height - 5) - frames.size() * FRAME_OFFSET;
		final int frameWidth = (d.width - 5) - frames.size() * FRAME_OFFSET;
		/*
		 * for (int i = allFrames.length - 1; i >= 0; i--) {
		 * allFrames[i].setSize(frameWidth,frameHeight);
		 * allFrames[i].setLocation(x,y); x = x + FRAME_OFFSET; y = y +
		 * FRAME_OFFSET; }
		 */
		for (final JInternalFrame f : frames) {
			f.setSize(frameWidth, frameHeight);
			f.setLocation(x, y);
			try {
				f.setSelected(true);
			} catch (final PropertyVetoException e) {
				e.printStackTrace();
			}
			x = x + FRAME_OFFSET;
			y = y + FRAME_OFFSET;
		}
		manager.resizeDesktop();
	}

	/**
	 * Tile all internal frames.
	 */
	public void tileFrames() {
		final java.awt.Component allFrames[] = getAllFrames();
		manager.setNormalSize();
		int totalHeight = 0;
		int i = 0;
		while (i < allFrames.length && totalHeight <= getHeight()) {
			totalHeight += allFrames[i++].getHeight();
		}

		final Dimension d = getVisibleRect().getSize();
		if (getBorder() != null) {
			d.setSize(d.getWidth() - getInsets().left - getInsets().right,
					d.getHeight() - getInsets().top - getInsets().bottom);
		}
		int frameWidth = d.width;
		if (totalHeight > d.height
				&& !manager.getScrollPane().getVerticalScrollBar().isVisible())
			frameWidth -= manager.getScrollPane().getVerticalScrollBar()
					.getPreferredSize().width;
		// int frameHeight = getBounds().height/allFrames.length;
		int frameHeight = 0;
		int y = 0;
		/*
		 * for (int j = 0; j < allFrames.length; j++) { frameHeight =
		 * allFrames[j].getHeight();
		 * allFrames[j].setSize(frameWidth,frameHeight);
		 * allFrames[j].setLocation(0,y); y = y + frameHeight; }
		 */
		for (final JInternalFrame f : frames) {
			frameHeight = f.getPreferredSize().height;
			f.setSize(frameWidth, frameHeight);
			frameHeight = f.getPreferredSize().height;
			f.setSize(frameWidth, frameHeight);
			f.setLocation(0, y);
			y = y + frameHeight;
		}
		manager.resizeDesktop();
	}

	/**
	 * Sets all component size properties ( maximum, minimum, preferred) to the
	 * given dimension.
	 *
	 * @param d the new all size
	 */
	public void setAllSize(final Dimension d) {
		setMinimumSize(d);
		setMaximumSize(d);
		setPreferredSize(d);
	}

	/**
	 * Sets all component size properties ( maximum, minimum, preferred) to the
	 * given width and height.
	 *
	 * @param width the width
	 * @param height the height
	 */
	public void setAllSize(final int width, final int height) {
		setAllSize(new Dimension(width, height));
	}

	/**
	 * Check desktop size.
	 */
	private void checkDesktopSize() {
		if (getParent() != null && isVisible())
			manager.resizeDesktop();
	}
}

/**
 * Private class used to replace the standard DesktopManager for JDesktopPane.
 * Used to provide scrollbar functionality.
 */
class MDIDesktopManager extends DefaultDesktopManager {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final MDIDesktopPane desktop;

	public MDIDesktopManager(final MDIDesktopPane desktop) {
		this.desktop = desktop;
	}

	@Override
	public void endResizingFrame(final JComponent f) {
		super.endResizingFrame(f);
		resizeDesktop();
	}

	@Override
	public void endDraggingFrame(final JComponent f) {
		super.endDraggingFrame(f);
		resizeDesktop();
	}

	public void setNormalSize() {
		final JScrollPane scrollPane = getScrollPane();
		final int x = 0;
		final int y = 0;
		final Insets scrollInsets = getScrollPaneInsets();

		if (scrollPane != null) {
			final Dimension d = scrollPane.getVisibleRect().getSize();
			if (scrollPane.getBorder() != null) {
				d.setSize(
						d.getWidth() - scrollInsets.left - scrollInsets.right,
						d.getHeight() - scrollInsets.top - scrollInsets.bottom);
			}

			d.setSize(d.getWidth() - 20, d.getHeight() - 20);
			desktop.setAllSize(x, y);
			scrollPane.invalidate();
			scrollPane.validate();
		}
	}

	private Insets getScrollPaneInsets() {
		final JScrollPane scrollPane = getScrollPane();
		if (scrollPane == null)
			return new Insets(0, 0, 0, 0);
		else
			return getScrollPane().getBorder().getBorderInsets(scrollPane);
	}

	protected JScrollPane getScrollPane() {
		if (desktop.getParent() instanceof JViewport) {
			final JViewport viewPort = (JViewport) desktop.getParent();
			if (viewPort.getParent() instanceof JScrollPane)
				return (JScrollPane) viewPort.getParent();
		}
		return null;
	}

	protected void resizeDesktop() {
		int x = 0;
		int y = 0;
		final JScrollPane scrollPane = getScrollPane();
		final Insets scrollInsets = getScrollPaneInsets();

		if (scrollPane != null) {
			final JInternalFrame allFrames[] = desktop.getAllFrames();
			for (int i = 0; i < allFrames.length; i++) {
				if (allFrames[i].getX() + allFrames[i].getWidth() > x) {
					x = allFrames[i].getX() + allFrames[i].getWidth();
				}
				if (allFrames[i].getY() + allFrames[i].getHeight() > y) {
					y = allFrames[i].getY() + allFrames[i].getHeight();
				}
			}
			final Dimension d = scrollPane.getVisibleRect().getSize();
			if (scrollPane.getBorder() != null) {
				d.setSize(
						d.getWidth() - scrollInsets.left - scrollInsets.right,
						d.getHeight() - scrollInsets.top - scrollInsets.bottom);
			}

			if (x <= d.getWidth())
				x = ((int) d.getWidth()) - 20;
			if (y <= d.getHeight())
				y = ((int) d.getHeight()) - 20;
			desktop.setAllSize(x, y);
			scrollPane.invalidate();
			scrollPane.validate();
		}
	}
}
