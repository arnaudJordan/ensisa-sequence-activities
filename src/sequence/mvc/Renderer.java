package sequence.mvc;

import java.awt.Dimension;
import java.awt.Graphics2D;

/**
 * The Interface Renderer.
 */
public interface Renderer {

	/**
	 * Sets the view.
	 *
	 * @param view the new view
	 */
	public void setView(View view);

	/**
	 * Gets the view.
	 *
	 * @return the view
	 */
	public View getView();

	/**
	 * Render view.
	 *
	 * @param g the g
	 */
	public void renderView(Graphics2D g);

	/**
	 * Gets the minimum size.
	 *
	 * @return the minimum size
	 */
	public Dimension getMinimumSize();

	/**
	 * Gets the preferred size.
	 *
	 * @return the preferred size
	 */
	public Dimension getPreferredSize();

	/**
	 * Gets the maximum size.
	 *
	 * @return the maximum size
	 */
	public Dimension getMaximumSize();
}
