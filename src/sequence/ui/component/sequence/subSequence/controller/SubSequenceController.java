package sequence.ui.component.sequence.subSequence.controller;

import java.awt.event.MouseEvent;

import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.ui.component.activity.ActivityView;
import sequence.ui.component.sequence.subSequence.SubSequenceView;

/**
 * The Class SubSequenceController.
 */
public class SubSequenceController extends Controller {

	/**
	 * Instantiates a new sub sequence controller.
	 *
	 * @param model the model
	 * @param view the view
	 */
	public SubSequenceController(final Model model, final View view) {
		super(model, view);
	}

	/* (non-Javadoc)
	 * @see sequence.mvc.Controller#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(final MouseEvent e) {
		if (e.getButton() == LEFT_MOUSE_BUTTON) {
			for (int i = 0; i < getView().getComponentCount(); i++)
				((ActivityView) getView().getComponent(i)).select();
			getView().repaint();
		}
	}

	/* (non-Javadoc)
	 * @see sequence.mvc.Controller#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(final MouseEvent e) {
		checkPopup(e);
	}

	/* (non-Javadoc)
	 * @see sequence.mvc.Controller#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(final MouseEvent e) {
		checkPopup(e);
	}

	/**
	 * Check popup.
	 *
	 * @param e the e
	 */
	private void checkPopup(final MouseEvent e) {
		if (e.getButton() == RIGHT_MOUSE_BUTTON && e.isPopupTrigger()) {
			((SubSequenceView) getView()).getPopup().show(e.getComponent(),
					e.getX(), e.getY());
		}
	}
}
