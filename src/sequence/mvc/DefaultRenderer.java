package sequence.mvc;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * The Class DefaultRenderer.
 */
public class DefaultRenderer implements Renderer {

	/** The Constant DEFAULT_DIMENSION. */
	private final static Dimension DEFAULT_DIMENSION = new Dimension(200, 200);
	
	/** The view. */
	private View view;

	/**
	 * Instantiates a new default renderer.
	 *
	 * @param view the view
	 */
	public DefaultRenderer(final View view) {
		this.view = view;
	}

	/* (non-Javadoc)
	 * @see sequence.mvc.Renderer#getView()
	 */
	@Override
	public View getView() {
		return view;
	}

	/* (non-Javadoc)
	 * @see sequence.mvc.Renderer#setView(sequence.mvc.View)
	 */
	@Override
	public void setView(final View view) {
		this.view = view;
	}

	/* (non-Javadoc)
	 * @see sequence.mvc.Renderer#renderView(java.awt.Graphics2D)
	 */
	@Override
	public void renderView(final Graphics2D g) {
		final RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHints(rh);
	}

	/* (non-Javadoc)
	 * @see sequence.mvc.Renderer#getMinimumSize()
	 */
	@Override
	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	/* (non-Javadoc)
	 * @see sequence.mvc.Renderer#getPreferredSize()
	 */
	@Override
	public Dimension getPreferredSize() {
		return DEFAULT_DIMENSION;
	}

	/* (non-Javadoc)
	 * @see sequence.mvc.Renderer#getMaximumSize()
	 */
	@Override
	public Dimension getMaximumSize() {
		return getPreferredSize();
	}
}
