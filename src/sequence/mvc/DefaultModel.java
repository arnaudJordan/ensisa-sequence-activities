package sequence.mvc;

import java.util.ArrayList;
import java.util.List;

public class DefaultModel implements Model {
	private final List<ModelListener> listeners;

	public DefaultModel() {
		listeners = new ArrayList<ModelListener>();
	}

	@Override
	public void addModelListener(final ModelListener l) {
		listeners.add(l);
	}

	@Override
	public void removeModelListener(final ModelListener l) {
		listeners.remove(l);
	}

	@Override
	public void modelChange() {
		for (final ModelListener m : listeners)
			m.modelChanged(this);
	}
}