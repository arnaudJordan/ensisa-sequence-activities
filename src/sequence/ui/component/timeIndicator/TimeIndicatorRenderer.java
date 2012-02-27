package sequence.ui.component.timeIndicator;

import java.awt.Dimension;
import java.awt.Graphics2D;

import sequence.model.Phase;
import sequence.mvc.DefaultRenderer;
import sequence.mvc.Renderer;
import sequence.mvc.View;
import sequence.ui.utilities.drawer.TimeIndicatorDrawer;

/**
 * The Class TimeIndicatorRenderer.
 */
public class TimeIndicatorRenderer extends DefaultRenderer implements Renderer {
	
	/** The tid. */
	protected TimeIndicatorDrawer tid;

	/**
	 * Instantiates a new time indicator renderer.
	 *
	 * @param view the view
	 */
	public TimeIndicatorRenderer(final View view) {
		super(view);
		tid = new TimeIndicatorDrawer();
	}

	/* (non-Javadoc)
	 * @see sequence.mvc.DefaultRenderer#renderView(java.awt.Graphics2D)
	 */
	@Override
	public void renderView(final Graphics2D g) {
		super.renderView(g);
		renderTimeIndicator(g);
	}

	/**
	 * Render time indicator.
	 *
	 * @param g the g
	 */
	private void renderTimeIndicator(final Graphics2D g) {
		renderBackground(g);
	}

	/**
	 * Render background.
	 *
	 * @param g the g
	 */
	private void renderBackground(final Graphics2D g) {
		final Phase phase = (Phase) ((TimeIndicatorView) getView()).getModel();
		if (phase != null)
			tid.Draw(g, (int) (getView().getSize().getWidth()),
					(int) (getView().getSize().getHeight()),
					((TimeIndicatorRenderingModel) getView()
							.getRenderingModel()).getColor());
	}

	/**
	 * Sets the background drawer.
	 *
	 * @param tid the new background drawer
	 */
	public void setBackgroundDrawer(final TimeIndicatorDrawer tid) {
		this.tid = tid;
		getView().repaint();
	}

	/**
	 * Gets the background drawer.
	 *
	 * @return the background drawer
	 */
	public TimeIndicatorDrawer getBackgroundDrawer() {
		return tid;
	}

	/* (non-Javadoc)
	 * @see sequence.mvc.DefaultRenderer#getPreferredSize()
	 */
	@Override
	public Dimension getPreferredSize() {
		final Phase phase = (Phase) ((TimeIndicatorView) getView()).getModel();
		final TimeIndicatorRenderingModel renderingModel = ((TimeIndicatorRenderingModel) getView()
				.getRenderingModel());
		if (phase != null && renderingModel != null)
			return new Dimension((int) (((TimeIndicatorView) getView())
					.getDuration() * renderingModel.getHScale()) + 1,
					(int) (((TimeIndicatorRenderingModel) getView()
							.getRenderingModel()).getHeight() * renderingModel
							.getVScale()));
		return super.getPreferredSize();
	}

}
