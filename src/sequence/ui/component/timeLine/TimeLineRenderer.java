package sequence.ui.component.timeLine;

import java.awt.Dimension;
import java.awt.Graphics2D;

import sequence.model.Phase;
import sequence.model.Phases;
import sequence.mvc.DefaultRenderer;
import sequence.mvc.Renderer;
import sequence.mvc.View;
import sequence.ui.component.timeIndicator.TimeIndicatorRenderingModel;
import sequence.ui.component.timeIndicator.TimeIndicatorView;
import sequence.ui.utilities.TimeLayout;

/**
 * The Class TimeLineRenderer.
 */
public class TimeLineRenderer extends DefaultRenderer implements Renderer {
	
	/** The scale. */
	private float scale = 1;
	
	/** The height. */
	private final int height = 10;

	/**
	 * Instantiates a new time line renderer.
	 *
	 * @param view the view
	 */
	public TimeLineRenderer(final View view) {
		super(view);
		getView().setLayout(new TimeLayout());
		setScale();
		initialize();
	}

	/**
	 * Initialize.
	 */
	public void initialize() {
		final Phases phases = (Phases) getView().getModel();
		for (final Phase phase : phases) {
			final TimeIndicatorView timeIndicatorView = new TimeIndicatorView(
					phase);
			final TimeIndicatorRenderingModel renderingModel = (TimeIndicatorRenderingModel) timeIndicatorView
					.getRenderingModel();
			renderingModel.setHScale(scale);
			getView().add(timeIndicatorView);
		}
	}

	/**
	 * Sets the scale.
	 */
	public void setScale() {
		final Phases phases = (Phases) getView().getModel();
		if (phases.size() > 0) {
			final int sequenceSize = phases.getLastPhase().getStopTime()
					- phases.get(0).getDate();
			scale = (float) (getView().getSize().getWidth() / sequenceSize);
		}
	}

	/* (non-Javadoc)
	 * @see sequence.mvc.DefaultRenderer#renderView(java.awt.Graphics2D)
	 */
	@Override
	public void renderView(final Graphics2D g) {
		super.renderView(g);
		setScale();
		renderTimeLine(g);
	}

	/**
	 * Render time line.
	 *
	 * @param g the g
	 */
	private void renderTimeLine(final Graphics2D g) {
		for (int i = 0; i < getView().getComponentCount(); i++) {
			final TimeIndicatorView timeIndicatorView = (TimeIndicatorView) getView()
					.getComponent(i);
			((TimeIndicatorRenderingModel) timeIndicatorView
					.getRenderingModel()).setHScale(scale);
		}
		getView().revalidate();
	}

	/* (non-Javadoc)
	 * @see sequence.mvc.DefaultRenderer#getPreferredSize()
	 */
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(getView().getParent().getWidth(), height);
	}
}
