package sequence.ui.component.timeLine;

import sequence.mvc.Model;
import sequence.mvc.View;

public class TimeLineView extends View {
	private static final long serialVersionUID = 1L;

	public TimeLineView(final Model model) {
		super(model);
		setRenderer(new TimeLineRenderer(this));
	}
}
