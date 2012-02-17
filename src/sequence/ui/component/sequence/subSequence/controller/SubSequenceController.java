package sequence.ui.component.sequence.subSequence.controller;

import java.awt.event.MouseEvent;

import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.ui.component.activity.ActivityView;
import sequence.ui.component.sequence.subSequence.SubSequenceView;

public class SubSequenceController extends Controller {

	public SubSequenceController(final Model model, final View view) {
		super(model, view);
	}

	@Override
	public void mouseClicked(final MouseEvent e) {
		if (e.getButton() == LEFT_MOUSE_BUTTON) {
			for (int i = 0; i < getView().getComponentCount(); i++)
				((ActivityView) getView().getComponent(i)).select();
			getView().repaint();
		}
	}

	@Override
	public void mousePressed(final MouseEvent e) {
		checkPopup(e);
	}

	@Override
	public void mouseReleased(final MouseEvent e) {
		checkPopup(e);
	}

	private void checkPopup(final MouseEvent e) {
		if (e.getButton() == RIGHT_MOUSE_BUTTON && e.isPopupTrigger()) {
			((SubSequenceView) getView()).getPopup().show(e.getComponent(),
					e.getX(), e.getY());
		}
	}
}
