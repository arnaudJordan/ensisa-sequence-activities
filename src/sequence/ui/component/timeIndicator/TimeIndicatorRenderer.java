package sequence.ui.component.timeIndicator;

import java.awt.Dimension;
import java.awt.Graphics2D;

import sequence.model.Phase;
import sequence.mvc.DefaultRenderer;
import sequence.mvc.Renderer;
import sequence.mvc.View;
import sequence.ui.utilities.drawer.TimeIndicatorDrawer;

public class TimeIndicatorRenderer extends DefaultRenderer implements Renderer {
	protected TimeIndicatorDrawer tid;

	public TimeIndicatorRenderer(final View view) {
		super(view);
		tid = new TimeIndicatorDrawer();
	}

	@Override
	public void renderView(final Graphics2D g) {
		super.renderView(g);
		renderTimeIndicator(g);
	}

	private void renderTimeIndicator(final Graphics2D g) {
		renderBackground(g);
	}

	private void renderBackground(final Graphics2D g) {
		final Phase phase = (Phase) ((TimeIndicatorView) getView()).getModel();
		if (phase != null)
			tid.Draw(g, (int) (getView().getSize().getWidth()),
					(int) (getView().getSize().getHeight()),
					((TimeIndicatorRenderingModel) getView()
							.getRenderingModel()).getColor());
	}

	public void setBackgroundDrawer(final TimeIndicatorDrawer tid) {
		this.tid = tid;
		getView().repaint();
	}

	public TimeIndicatorDrawer getBackgroundDrawer() {
		return tid;
	}

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
