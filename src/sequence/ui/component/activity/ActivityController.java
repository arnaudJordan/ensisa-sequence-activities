package sequence.ui.component.activity;

import java.awt.Container;
import java.awt.event.MouseEvent;

import sequence.model.activity.Activity;
import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;

/**
 * The Class ActivityController.
 */
public class ActivityController extends Controller {

	/**
	 * Instantiates a new activity controller.
	 *
	 * @param model the model
	 * @param view the view
	 */
	public ActivityController(final Model model, final View view) {
		super(model, view);
	}

	/* (non-Javadoc)
	 * @see sequence.mvc.Controller#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(final MouseEvent e) {
		if (e.getButton() == LEFT_MOUSE_BUTTON) {
			final Container parent = getView().getParent();
			if (parent != null) {
				if (!((ActivityView) getView()).isSelected()) {
					for (int i = 0; i < parent.getComponentCount(); i++)
						((ActivityView) parent.getComponent(i)).select();
				} else {
					final Activity clickedActivity = (Activity) getView()
							.getModel();
					for (int i = 0; i < parent.getComponentCount(); i++) {
						final Activity currentActivity = (Activity) ((View) parent
								.getComponent(i)).getModel();
						if (currentActivity != null
								&& (!currentActivity.getAction().equals(
										clickedActivity.getAction())
										|| !currentActivity
												.getTreatedStructure()
												.equals(clickedActivity
														.getTreatedStructure()) || !currentActivity
										.getUsedInstrument().equals(
												clickedActivity
														.getUsedInstrument())))
							((ActivityView) parent.getComponent(i)).deselect();
					}
				}
				parent.repaint();
			}
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
			((ActivityView) getView()).popup.show(e.getComponent(), e.getX(),
					e.getY());
		}
	}
}
