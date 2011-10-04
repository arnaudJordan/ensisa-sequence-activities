package sequence.ui.component.activity;

import sequence.mvc.Model;
import sequence.mvc.View;

public class ActivityView extends View {

	public ActivityView(Model model) {
		super(model);
		setRenderer(new ActivityRenderer(this));
	}
}
