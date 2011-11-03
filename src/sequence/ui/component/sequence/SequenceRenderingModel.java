package sequence.ui.component.sequence;

import sequence.mvc.DefaultModel;
import sequence.mvc.RenderingModel;

public class SequenceRenderingModel extends DefaultModel implements RenderingModel {

	private static final int DEFAULT_DURATION_THRESHOLD = 0;
	
	private int durationThreshold;
	
	public SequenceRenderingModel() {
		this(DEFAULT_DURATION_THRESHOLD);
	}
	
	public SequenceRenderingModel(int durationThreshold) {
		setDurationThreshold(durationThreshold);
	}

	public void setDurationThreshold(int durationThreshold) {
		if(this.durationThreshold == durationThreshold) return;
		this.durationThreshold = durationThreshold;
		this.modelChange();
	}

	public int getDurationThreshold() {
		return durationThreshold;
	}
}
