package sequence.utilities;

import java.util.EventListener;

/**
 * The listener interface for receiving scale events.
 * The class that is interested in processing a scale
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addScaleListener<code> method. When
 * the scale event occurs, that object's appropriate
 * method is invoked.
 */
public interface ScaleListener extends EventListener {
	
	/**
	 * Scale changed.
	 *
	 * @param scale the scale
	 */
	public void scaleChanged(float scale);
}
