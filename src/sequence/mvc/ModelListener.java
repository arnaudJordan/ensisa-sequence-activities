package sequence.mvc;

import java.util.EventListener;

public interface ModelListener extends EventListener {
	public void modelChanged(Model m);
}
