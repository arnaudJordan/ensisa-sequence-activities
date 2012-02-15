package sequence.utilities;

import java.awt.Component;

import sequence.model.activity.Activity;
import sequence.mvc.View;
import sequence.ui.component.activity.ActivityRenderingModel;
import sequence.ui.component.activity.ActivityView;

/**
 * A factory for creating Tikz graphics from <code>View</code>. Only
 * <code>ActivityView</code> are convert to Tikz.
 * 
 * @see sequence.mvc.View
 * @see sequence.ui.component.activity.ActivityView
 */
public class TikzFactory {

	/** The Constant NEW_LINE. */
	final static String NEW_LINE = System.getProperty("line.separator");

	/** The Constant HSCALE. */
	final static double HSCALE = 0.045;

	/** The Constant FIRSTHSCALE. */
	final static int FIRSTHSCALE = 8;

	/** The Constant VSCALE. */
	final static double VSCALE = 0.04;

	/** The Constant DefaultHscale. */
	final static int DefaultHscale = 4;

	/** The Constant DefaultVscale. */
	final static int DefaultVscale = 10;

	/** The Constant PDFHeight. */
	final static int PDFHeight = 3508;

	/** The Constant PDFWidth. */
	final static int PDFWidth = 2480;

	/** The Constant HMARGIN. */
	final static int HMARGIN = 30;

	/** The Constant VMARGIN. */
	final static int VMARGIN = 40;

	/**
	 * Activity to tikz.
	 * 
	 * @param view
	 *            the view
	 * @return the tikz result
	 */
	public static String ActivityToTikz(final View view) {
		final StringBuilder sb = new StringBuilder();
		int id = 0;
		if (view.getModel() instanceof Activity)
			id = ((Activity) view.getModel()).getId();
		sb.append("\\draw[draw=none,fill=" + id + "] (0,.8) rectangle ("
				+ view.getWidth() + ",1.1);");
		return sb.toString();
	}

	/**
	 * Sequence activity to tikz.
	 * 
	 * @param elementView
	 *            the element view
	 * @param containerView
	 *            the container view
	 * @return the tikz result
	 */
	private static String SequenceActivityToTikz(final View elementView,
			final View containerView) {
		final StringBuilder sb = new StringBuilder();
		final float Hscale = (PDFWidth - HMARGIN) / containerView.getWidth();
		final float Vscale = 1;
		int id = 0;
		if (elementView.getModel() instanceof Activity)
			id = ((Activity) elementView.getModel()).getId();
		sb.append("\\draw[draw=none,fill=color"
				+ id
				+ "] ("
				+ elementView.getX()
				* Hscale
				/ (FIRSTHSCALE)
				+ ","
				+ (containerView.getHeight() - elementView.getY())
				/ Vscale
				+ ") rectangle ("
				+ (elementView.getX() + elementView.getWidth())
				* Hscale
				/ (FIRSTHSCALE)
				+ ","
				+ (containerView.getHeight() - elementView.getY() - elementView
						.getHeight()) / Vscale + ");");
		return sb.toString();
	}

	/**
	 * Sequence to tikz.
	 * 
	 * @param view
	 *            the view
	 * @return the tikz result
	 */
	public static String SequenceToTikz(final View view) {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < view.getComponentCount(); i++) {
			final Component objet = view.getComponent(i);
			if (objet instanceof View) {
				sb.append(TikzFactory.TikzColor((ActivityView) objet));
				sb.append(NEW_LINE);
			}
		}
		sb.append("\\begin{scope}[yshift=-0]");
		for (int i = 0; i < view.getComponentCount(); i++) {
			final Component objet = view.getComponent(i);
			if (objet instanceof View) {
				sb.append(TikzFactory
						.SequenceActivityToTikz((View) objet, view));
				sb.append(NEW_LINE);
			}
		}
		return sb.toString();
	}

	/**
	 * Adds the header.
	 * 
	 * @param content
	 *            the content
	 * @return the content with header
	 */
	public static String AddHeader(final String content) {
		final StringBuilder sb = new StringBuilder();
		sb.append("\\documentclass{article}");
		sb.append(NEW_LINE);
		sb.append("\\usepackage{tikz}");
		sb.append(NEW_LINE);
		sb.append("\\begin{document}");
		sb.append(NEW_LINE);
		sb.append("\\begin{tikzpicture}[yscale=" + VSCALE + ", xscale="
				+ HSCALE + "] ");
		sb.append(NEW_LINE);
		sb.append(content);
		sb.append(NEW_LINE);
		sb.append("\\end{scope}");
		sb.append(NEW_LINE);
		sb.append("\\end{tikzpicture}");
		sb.append(NEW_LINE);
		sb.append("\\end{document}");
		return sb.toString();
	}

	/**
	 * Tikz color.
	 * 
	 * @param activityView
	 *            the activity view
	 * @return the string
	 */
	private static String TikzColor(final ActivityView activityView) {
		final StringBuilder sb = new StringBuilder();
		sb.append("\\definecolor{color"
				+ ((Activity) activityView.getModel()).getId()
				+ "}{RGB}{"
				+ ((ActivityRenderingModel) activityView.getRenderingModel())
						.getColor().getRed()
				+ ","
				+ ((ActivityRenderingModel) activityView.getRenderingModel())
						.getColor().getGreen()
				+ ","
				+ ((ActivityRenderingModel) activityView.getRenderingModel())
						.getColor().getBlue() + "});");
		return sb.toString();
	}
}
