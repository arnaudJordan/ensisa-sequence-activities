package sequence.ui.component.activity;

import java.awt.Container;
import java.awt.event.MouseEvent;

import sequence.model.activity.Activity;
import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;

public class ActivityController extends Controller {

	public ActivityController(final Model model, final View view) {
		super(model, view);
	}

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
			((ActivityView) getView()).popup.show(e.getComponent(), e.getX(),
					e.getY());
		}
	}
}
