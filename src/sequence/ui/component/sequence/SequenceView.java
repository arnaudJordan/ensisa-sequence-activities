package sequence.ui.component.sequence;

import sequence.mvc.Model;
import sequence.mvc.View;

public class SequenceView extends View {

	public SequenceView(Model model) {
		super(model);
		setRenderer(new SequenceRenderer(this));
	}
}
