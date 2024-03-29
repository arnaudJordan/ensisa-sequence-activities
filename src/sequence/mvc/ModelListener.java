package sequence.mvc;

import java.util.EventListener;

/**
 * The listener interface for receiving model events.
 * The class that is interested in processing a model
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addModelListener<code> method. When
 * the model event occurs, that object's appropriate
 * method is invoked.
 */
public interface ModelListener extends EventListener {
	
	/**
	 * Model changed.
	 *
	 * @param m the model
	 */
	public void modelChanged(Model m);
}
