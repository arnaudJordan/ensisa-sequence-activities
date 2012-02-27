package sequence.ui.component.timeIndicator;

import sequence.model.Phase;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.utilities.Scaleable;
import sequence.utilities.Timeable;

/**
 * The Class TimeIndicatorView.
 */
public class TimeIndicatorView extends View implements Timeable, Scaleable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new time indicator view.
	 *
	 * @param model the model
	 */
	public TimeIndicatorView(final Model model) {
		super(model);
		setRenderer(new TimeIndicatorRenderer(this));
		setRenderingModel(new TimeIndicatorRenderingModel());
		setToolTipText(((Phase) model).getName());
	}

	/* (non-Javadoc)
	 * @see sequence.utilities.Scaleable#getHScale()
	 */
	@Override
	public float getHScale() {
		return ((TimeIndicatorRenderingModel) getRenderingModel()).getHScale();
	}

	/* (non-Javadoc)
	 * @see sequence.utilities.Scaleable#getVScale()
	 */
	@Override
	public float getVScale() {
		return ((TimeIndicatorRenderingModel) getRenderingModel()).getVScale();
	}

	/* (non-Javadoc)
	 * @see sequence.utilities.Timeable#getStartTime()
	 */
	@Override
	public int getStartTime() {
		return ((Phase) getModel()).getDate();
	}

	/* (non-Javadoc)
	 * @see sequence.utilities.Timeable#getStopTime()
	 */
	@Override
	public int getStopTime() {
		return ((Phase) getModel()).getStopTime();
	}

	/* (non-Javadoc)
	 * @see sequence.utilities.Timeable#getDuration()
	 */
	@Override
	public int getDuration() {
		return ((Phase) getModel()).getStopTime()
				- ((Phase) getModel()).getDate();
	}

}
