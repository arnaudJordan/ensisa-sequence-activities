package sequence.ui.component.activity;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import sequence.model.activity.Activity;
import sequence.mvc.DefaultRenderer;
import sequence.mvc.Renderer;
import sequence.mvc.View;
import sequence.ui.utilities.drawer.BackgroundDrawer;
import sequence.ui.utilities.drawer.FullBackgroundDrawer;

/**
 * The Class ActivityRenderer.
 */
public class ActivityRenderer extends DefaultRenderer implements Renderer {
	
	/** The current background drawer that is used by every activities. */
	public static BackgroundDrawer CURRENT_BACKGROUND_DRAWER = new FullBackgroundDrawer();

	/** The background drawer. */
	protected BackgroundDrawer bd;
	
	/** The contracted. */
	private boolean contracted;

	/**
	 * Instantiates a new activity renderer.
	 *
	 * @param view the view
	 */
	public ActivityRenderer(final View view) {
		super(view);
		bd = CURRENT_BACKGROUND_DRAWER;
		contracted = false;
	}

	/* (non-Javadoc)
	 * @see sequence.mvc.DefaultRenderer#renderView(java.awt.Graphics2D)
	 */
	@Override
	public void renderView(final Graphics2D g) {
		renderActivity(g);
	}

	/**
	 * Render activity.
	 *
	 * @param g the g
	 */
	private void renderActivity(final Graphics2D g) {
		renderBackground(g);
	}

	/**
	 * Render background.
	 *
	 * @param g the g
	 */
	private void renderBackground(final Graphics2D g) {
		final Activity activity = (Activity) ((ActivityView) getView())
				.getModel();
		g.setColor(((ActivityRenderingModel) getView().getRenderingModel())
				.getColor());
		if (activity == null)
			return;

		final ActivityRenderingModel renderingModel = ((ActivityRenderingModel) getView()
				.getRenderingModel());
		final int parentWidth = getView().getParent().getWidth();
		final int activityWidth = (int) (activity.getActivitytime()
				.getDuration() * renderingModel.getHScale());
		bd.draw(g,
				activityWidth,
				(int) (renderingModel.getHeight() * renderingModel.getVScale()),
				((ActivityRenderingModel) getView().getRenderingModel())
						.getColor());
		if (parentWidth < activityWidth) {
			contracted = true;
			g.drawLine(parentWidth / 2 - 5, -5, parentWidth / 2 + 5,
					(int) getView().getSize().getHeight() + 5);
			drawCutMark(g, parentWidth / 2);
		} else
			contracted = false;
		// g.setColor(Color.WHITE);
		// g.drawString(String.valueOf(activity.getId()), (int)
		// getView().getSize().getWidth()/2, (int)
		// getView().getSize().getHeight()/2);
	}

	/**
	 * Draw cut mark.
	 *
	 * @param g the g
	 * @param middle the middle
	 */
	private void drawCutMark(final Graphics2D g, final int middle) {
		final Graphics2D g2 = (Graphics2D) g.create();
		g2.setColor(Color.WHITE);
		final int gap = 5;
		g2.drawLine(middle - 2 * gap, 0, middle, (int) getView().getSize()
				.getHeight() + 5);
		g2.drawLine(middle, 0, middle + 2 * gap, (int) getView().getSize()
				.getHeight() + 5);
	}

	/* (non-Javadoc)
	 * @see sequence.mvc.DefaultRenderer#getPreferredSize()
	 */
	@Override
	public Dimension getPreferredSize() {
		final Activity activity = (Activity) ((ActivityView) getView())
				.getModel();
		final ActivityRenderingModel renderingModel = ((ActivityRenderingModel) getView()
				.getRenderingModel());
		if (activity != null && renderingModel != null) {
			final int parentWidth = getView().getParent().getWidth();
			final int activityWidth = (int) (activity.getActivitytime()
					.getDuration() * renderingModel.getHScale());
			if (parentWidth < activityWidth)
				return new Dimension(parentWidth,
						(int) (renderingModel.getHeight() * renderingModel
								.getVScale()));
			else
				return new Dimension(activityWidth,
						(int) (renderingModel.getHeight() * renderingModel
								.getVScale()));
		}

		return super.getPreferredSize();
	}

	/**
	 * Sets the background drawer.
	 *
	 * @param bd the new background drawer
	 */
	public void setBackgroundDrawer(final BackgroundDrawer bd) {
		this.bd = bd;
		if (getView().getParent() != null)
			getView().getParent().repaint();
	}

	/**
	 * Gets the background drawer.
	 *
	 * @return the background drawer
	 */
	public BackgroundDrawer getBackgroundDrawer() {
		return bd;
	}

	/**
	 * Checks if is contracted.
	 *
	 * @return true, if is contracted
	 */
	public boolean isContracted() {
		return contracted;
	}
}
