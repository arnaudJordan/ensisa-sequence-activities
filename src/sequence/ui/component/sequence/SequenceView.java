package sequence.ui.component.sequence;

import sequence.mvc.Model;
import sequence.mvc.View;

public class SequenceView extends View {

	public SequenceView(Model model) {
		super(model);
		setRenderingModel(new SequenceRenderingModel());
		setRenderer(new SequenceRenderer(this));
	}
	
	public void modelChanged(Model m) {
		removeAll();
		((SequenceRenderer)getRenderer()).initialize();
	}
}
