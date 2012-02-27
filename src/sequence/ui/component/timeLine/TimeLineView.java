package sequence.ui.component.timeLine;

import sequence.mvc.Model;
import sequence.mvc.View;

/**
 * The Class TimeLineView.
 */
public class TimeLineView extends View {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new time line view.
	 *
	 * @param model the model
	 */
	public TimeLineView(final Model model) {
		super(model);
		setRenderer(new TimeLineRenderer(this));
	}
}
