package sequence.utilities;

import java.util.ArrayList;
import java.util.List;

import sequence.ui.component.activity.ActivityRenderer;
import sequence.ui.component.activity.ActivityRenderingModel;
import sequence.ui.utilities.drawer.BackgroundDrawer;

public class EventDispatcher {
	private static List<BackgroundListener> backgroundListeners = new ArrayList<BackgroundListener>();
	private static List<ScaleListener> scaleListeners = new ArrayList<ScaleListener>();

	public static void add(final BackgroundListener bl) {
		backgroundListeners.add(bl);
	}

	public static void remove(final BackgroundListener bl) {
		backgroundListeners.remove(bl);
	}

	public static void backgroundChanged(final BackgroundDrawer bd) {
		ActivityRenderer.CURRENT_BACKGROUND_DRAWER = bd;
		for (final BackgroundListener bl : backgroundListeners)
			bl.backgroundChanged(bd);
	}

	public static void add(final ScaleListener sl) {
		scaleListeners.add(sl);
	}

	public static void remove(final ScaleListener sl) {
		scaleListeners.remove(sl);
	}

	public static void scaleChanged(final float scale) {
		ActivityRenderingModel.CURRENT_SCALE = scale;
		for (final ScaleListener sl : scaleListeners)
			sl.scaleChanged(scale);
	}
}
