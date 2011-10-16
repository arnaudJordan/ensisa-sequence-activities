package sequence.ui.component.sequence;

import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;

public class SequenceController extends Controller {

	public SequenceController(Model model, View view) {
		super(model, view);
	}

	public void modelChanged(Model m) {
		getView().removeAll();
		getView().setRenderer(new SequenceRenderer(getView()));
	}
}
