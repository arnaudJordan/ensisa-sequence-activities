package sequence.utilities;

import java.util.ArrayList;
import java.util.List;

import sequence.ui.component.activity.ActivityRenderer;
import sequence.ui.component.activity.ActivityRenderingModel;
import sequence.ui.component.sequence.subSequence.SubSequenceRenderingModel;
import sequence.ui.utilities.drawer.BackgroundDrawer;

/**
 * The Class OptionEventDispatcher provides a link between option components
 * (e.g. scale sliders) and components that need to listen to those option
 * components. When an option components event is fired, it will fire the
 * appropriate static method of this class that will notify its listeners.
 */
public class OptionEventDispatcher {

	/** The background listeners. */
	private static List<BackgroundListener> backgroundListeners = new ArrayList<BackgroundListener>();

	/** The scale listeners. */
	private static List<ScaleListener> scaleListeners = new ArrayList<ScaleListener>();

	/** The threshold listeners. */
	private static List<ThresholdListener> thresholdListeners = new ArrayList<ThresholdListener>();

	/**
	 * Adds the.
	 * 
	 * @param bl
	 *            the background listener
	 */
	public static void add(final BackgroundListener bl) {
		backgroundListeners.add(bl);
	}

	/**
	 * Removes the.
	 * 
	 * @param bl
	 *            the background listener
	 */
	public static void remove(final BackgroundListener bl) {
		backgroundListeners.remove(bl);
	}

	/**
	 * Background changed.
	 * 
	 * @param bd
	 *            the background drawer
	 */
	public static void backgroundChanged(final BackgroundDrawer bd) {
		ActivityRenderer.CURRENT_BACKGROUND_DRAWER = bd;
		for (final BackgroundListener bl : backgroundListeners)
			bl.backgroundChanged(bd);
	}

	/**
	 * Adds the.
	 * 
	 * @param sl
	 *            the scale listener
	 */
	public static void add(final ScaleListener sl) {
		scaleListeners.add(sl);
	}

	/**
	 * Removes the.
	 * 
	 * @param sl
	 *            the scale listener
	 */
	public static void remove(final ScaleListener sl) {
		scaleListeners.remove(sl);
	}

	/**
	 * Scale changed.
	 * 
	 * @param scale
	 *            the scale
	 */
	public static void scaleChanged(final float scale) {
		ActivityRenderingModel.CURRENT_SCALE = scale;
		for (final ScaleListener sl : scaleListeners)
			sl.scaleChanged(scale);
	}

	/**
	 * Adds the.
	 * 
	 * @param tl
	 *            the threshold listener
	 */
	public static void add(final ThresholdListener tl) {
		thresholdListeners.add(tl);
	}

	/**
	 * Removes the.
	 * 
	 * @param tl
	 *            the threshold listener
	 */
	public static void remove(final ThresholdListener tl) {
		thresholdListeners.remove(tl);
	}

	/**
	 * Threshold changed.
	 * 
	 * @param threshold
	 *            the threshold
	 */
	public static void thresholdChanged(final int threshold) {
		SubSequenceRenderingModel.CURRENT_DURATION_THRESHOLD = threshold;
		for (final ThresholdListener tl : thresholdListeners)
			tl.thresholdChanged(threshold);
	}
}
