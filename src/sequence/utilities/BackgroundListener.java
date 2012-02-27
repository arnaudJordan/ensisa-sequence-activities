package sequence.utilities;

import java.util.EventListener;

import sequence.ui.utilities.drawer.BackgroundDrawer;

/**
 * The listener interface for receiving background events.
 * The class that is interested in processing a background
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addBackgroundListener<code> method. When
 * the background event occurs, that object's appropriate
 * method is invoked.
 */
public interface BackgroundListener extends EventListener {
	
	/**
	 * Background changed.
	 *
	 * @param bd the background drawer
	 */
	public void backgroundChanged(BackgroundDrawer bd);
}
