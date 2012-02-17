package sequence.utilities;

import java.util.EventListener;

public interface ThresholdListener extends EventListener {
	public void thresholdChanged(int threshold);
}
