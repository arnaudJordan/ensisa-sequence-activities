package sequence.utilities;

import java.util.ArrayList;
import java.util.List;

import sequence.ui.component.activity.ActivityRenderer;
import sequence.ui.utilities.drawer.BackgroundDrawer;

public class EventDispatcher {
	private static List<BackgroundListener> listeners = new ArrayList<BackgroundListener>();

	public static void add(BackgroundListener bl) {
		listeners.add(bl);
	}
	
	public static void remove(BackgroundListener bl) {
		listeners.remove(bl);
	}
	
	public static void backgroundChanged(BackgroundDrawer bd) {
		ActivityRenderer.CURRENT_BACKGROUND_DRAWER = bd;
		for(BackgroundListener bl : listeners)
			bl.backgroundChanged(bd);
	}
}
