package sequence.ui.component.sequence.summarizedSequence;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import sequence.model.Sequence;
import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.ui.component.sequence.SequenceContainer;

public class SummarizedSequenceController extends Controller implements
		ActionListener {

	public SummarizedSequenceController(final Model model, final View view) {
		super(model, view);
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		final Sequence subSequenceModel = new Sequence(
				((Sequence) getModel()).getWorkflowID(),
				((SummarizedSequenceView) getView())
						.getSortedSelectedActivities());
		((SequenceContainer) getView().getParent()).add(subSequenceModel);
	}

	@Override
	public void mousePressed(final MouseEvent e) {
		checkPopup(e);
	}

	@Override
	public void mouseReleased(final MouseEvent e) {
		checkPopup(e);
	}

	protected void checkPopup(final MouseEvent e) {
		if (e.getButton() == RIGHT_MOUSE_BUTTON && e.isPopupTrigger()) {
			((SummarizedSequenceView) getView()).getPopup().show(
					e.getComponent(), e.getX(), e.getY());
		}
	}
}
