package sequence.ui.component.sequence.subSequence;

import sequence.mvc.DefaultModel;
import sequence.mvc.RenderingModel;

/**
 * The Class SubSequenceRenderingModel.
 */
public class SubSequenceRenderingModel extends DefaultModel implements
		RenderingModel {

	/** The current duration threshold that is used by every sub sequences. */
	public static int CURRENT_DURATION_THRESHOLD = 0;

	/** The duration threshold. */
	private int durationThreshold;

	/**
	 * Instantiates a new sub sequence rendering model.
	 */
	public SubSequenceRenderingModel() {
		this(CURRENT_DURATION_THRESHOLD);
	}

	/**
	 * Instantiates a new sub sequence rendering model.
	 *
	 * @param durationThreshold the duration threshold
	 */
	public SubSequenceRenderingModel(final int durationThreshold) {
		this.durationThreshold = durationThreshold;
	}

	/**
	 * Sets the duration threshold.
	 *
	 * @param durationThreshold the new duration threshold
	 */
	public void setDurationThreshold(final int durationThreshold) {
		if (this.durationThreshold == durationThreshold)
			return;
		this.durationThreshold = durationThreshold;
		modelChange();
	}

	/**
	 * Gets the duration threshold.
	 *
	 * @return the duration threshold
	 */
	public int getDurationThreshold() {
		return durationThreshold;
	}
}
