package sequence.utilities;

import java.util.EventListener;

/**
 * The listener interface for receiving threshold events.
 * The class that is interested in processing a threshold
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addThresholdListener<code> method. When
 * the threshold event occurs, that object's appropriate
 * method is invoked.
 */
public interface ThresholdListener extends EventListener {
	
	/**
	 * Threshold changed.
	 *
	 * @param threshold the threshold
	 */
	public void thresholdChanged(int threshold);
}
