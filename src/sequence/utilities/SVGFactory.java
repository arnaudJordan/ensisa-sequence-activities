package sequence.utilities;

import java.awt.Color;
import java.awt.Component;

import sequence.model.Sequence;
import sequence.model.activity.Activity;
import sequence.mvc.View;
import sequence.ui.component.activity.ActivityRenderer;
import sequence.ui.component.activity.ActivityRenderingModel;
import sequence.ui.component.activity.ActivityView;

/**
 * A factory for creating SVG graphics from <code>View</code>. Only
 * <code>ActivityView</code> are convert to SVG.
 * 
 * @see sequence.mvc.View
 * @see sequence.ui.component.activity.ActivityView
 */
public class SVGFactory {

	/** The Constant NEW_LINE. */
	final static String NEW_LINE = System.getProperty("line.separator");

	/**
	 * Activity to SVG.
	 * 
	 * @param view
	 *            the view
	 * @return the SVG result
	 */
	public static String ActivityToSVG(final View view) {

		final StringBuilder sb = new StringBuilder();
		sb.append("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\""
				+ view.getWidth() + "\" height=\"" + view.getHeight() + "\">");
		sb.append(NEW_LINE);
		if (view.getModel() instanceof Activity) {
			final Activity activity = (Activity) view.getModel();
			sb.append("<title>Activity : " + activity.getId() + "</title>");
			sb.append(NEW_LINE);
		}
		sb.append(SequenceActivityToSVG(view));
		return sb.toString();
	}

	/**
	 * Sequence activity to SVG.
	 * 
	 * @param view
	 *            the view
	 * @return the SVG result
	 */
	private static String SequenceActivityToSVG(final View view) {

		final StringBuilder sb = new StringBuilder();
		sb.append("<g>");
		sb.append(NEW_LINE);
		sb.append("<rect width=\""
				+ view.getWidth()
				+ "\" height=\""
				+ view.getHeight()
				+ "\" x=\""
				+ view.getX()
				+ "\" y=\""
				+ view.getY()
				+ "\" fill=\"#"
				+ ColorToHexa(((ActivityRenderingModel) view
						.getRenderingModel()).getColor()) + "\" />");
		sb.append(NEW_LINE);
		if (view.getRenderer() instanceof ActivityRenderer)
			if (((ActivityRenderer) view.getRenderer()).isContracted()) {
				sb.append("<line x1=\""
						+ (view.getX() + view.getWidth() / 2 - 10) + "\" y1=\""
						+ (view.getY() + view.getHeight()) + "\" x2=\""
						+ (view.getX() + view.getWidth() / 2 - 5) + "\" y2=\""
						+ view.getY()
						+ "\" style=\"stroke:white;stroke-width:2\" />");
				sb.append(NEW_LINE);
				sb.append("<line x1=\""
						+ (view.getX() + view.getWidth() / 2 + 5) + "\" y1=\""
						+ (view.getY() + view.getHeight()) + "\" x2=\""
						+ (view.getX() + view.getWidth() / 2 + 10) + "\" y2=\""
						+ view.getY()
						+ "\" style=\"stroke:white;stroke-width:2\" />");
				sb.append(NEW_LINE);
			}
		sb.append("</g>");
		sb.append(NEW_LINE);
		return sb.toString();
	}

	/**
	 * Sequence to SVG.
	 * 
	 * @param view
	 *            the view
	 * @return the SVG result
	 */
	public static String SequenceToSVG(final View view) {

		final StringBuilder sb = new StringBuilder();
		sb.append("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\""
				+ view.getWidth() + "\" height=\"" + view.getHeight() + "\">");
		sb.append(NEW_LINE);
		if (view.getModel() instanceof Sequence) {
			final Sequence sequence = (Sequence) view.getModel();
			sb.append("<title>Sequence : " + sequence.getWorkflowID()
					+ "</title>");
			sb.append(NEW_LINE);
		}
		for (int i = 0; i < view.getComponentCount(); i++) {
			final Component objet = view.getComponent(i);
			if (objet instanceof ActivityView) {
				sb.append(SVGFactory.SequenceActivityToSVG((View) objet));
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
		final StringBuilder sb = new StringBuilder(
				"<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append(NEW_LINE);
		sb.append(content);
		sb.append("</svg>");
		return sb.toString();
	}

	private static String ColorToHexa(final Color color) {
		final StringBuilder sb = new StringBuilder();
		if (Integer.toHexString(color.getRed()).length() == 1)
			sb.append("0");
		sb.append(Integer.toHexString(color.getRed()));
		if (Integer.toHexString(color.getGreen()).length() == 1)
			sb.append("0");
		sb.append(Integer.toHexString(color.getGreen()));
		if (Integer.toHexString(color.getBlue()).length() == 1)
			sb.append("0");
		sb.append(Integer.toHexString(color.getBlue()));
		return sb.toString();
	}
}
