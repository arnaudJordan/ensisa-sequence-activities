package sequence.ui.component.sequence.subSequence;

import sequence.mvc.DefaultModel;
import sequence.mvc.RenderingModel;

public class SubSequenceRenderingModel extends DefaultModel implements
		RenderingModel {

	public static int CURRENT_DURATION_THRESHOLD = 0;

	private int durationThreshold;

	public SubSequenceRenderingModel() {
		this(CURRENT_DURATION_THRESHOLD);
	}

	public SubSequenceRenderingModel(final int durationThreshold) {
		this.durationThreshold = durationThreshold;
	}

	public void setDurationThreshold(final int durationThreshold) {
		if (this.durationThreshold == durationThreshold)
			return;
		this.durationThreshold = durationThreshold;
		modelChange();
	}

	public int getDurationThreshold() {
		return durationThreshold;
	}
}
