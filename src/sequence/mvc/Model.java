package sequence.mvc;

/**
 * The Interface Model.
 */
public interface Model {
	
	/**
	 * Adds the model listener.
	 *
	 * @param l the l
	 */
	public void addModelListener(ModelListener l);

	/**
	 * Removes the model listener.
	 *
	 * @param l the l
	 */
	public void removeModelListener(ModelListener l);

	/**
	 * Model change.
	 */
	public void modelChange();
}
