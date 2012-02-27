package sequence.mvc;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class DefaultModel.
 */
public class DefaultModel implements Model {
	
	/** The listeners. */
	private final List<ModelListener> listeners;

	/**
	 * Instantiates a new default model.
	 */
	public DefaultModel() {
		listeners = new ArrayList<ModelListener>();
	}

	/* (non-Javadoc)
	 * @see sequence.mvc.Model#addModelListener(sequence.mvc.ModelListener)
	 */
	@Override
	public void addModelListener(final ModelListener l) {
		listeners.add(l);
	}

	/* (non-Javadoc)
	 * @see sequence.mvc.Model#removeModelListener(sequence.mvc.ModelListener)
	 */
	@Override
	public void removeModelListener(final ModelListener l) {
		listeners.remove(l);
	}

	/* (non-Javadoc)
	 * @see sequence.mvc.Model#modelChange()
	 */
	@Override
	public void modelChange() {
		for (final ModelListener m : listeners)
			m.modelChanged(this);
	}
}