package sequence.mvc;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class DefaultRenderer implements Renderer {

	private final static Dimension DEFAULT_DIMENSION = new Dimension(200, 200);
	private View view;

	public DefaultRenderer(final View view) {
		this.view = view;
	}

	@Override
	public View getView() {
		return view;
	}

	@Override
	public void setView(final View view) {
		this.view = view;
	}

	@Override
	public void renderView(final Graphics2D g) {
		final RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHints(rh);
	}

	@Override
	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	@Override
	public Dimension getPreferredSize() {
		return DEFAULT_DIMENSION;
	}

	@Override
	public Dimension getMaximumSize() {
		return getPreferredSize();
	}
}
