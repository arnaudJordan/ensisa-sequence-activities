package sequence.utilities;

import java.util.EventListener;

import sequence.ui.utilities.drawer.BackgroundDrawer;

public interface BackgroundListener extends EventListener {
	 public void backgroundChanged(BackgroundDrawer bd);
}
