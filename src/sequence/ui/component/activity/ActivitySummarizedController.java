package sequence.ui.component.activity;

import java.awt.event.MouseEvent;
import java.util.List;

import sequence.model.activity.Activity;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.ui.component.sequence.summarizedSequence.SummarizedSequenceController;
import sequence.ui.component.sequence.summarizedSequence.SummarizedSequenceView;
import sequence.ui.utilities.drawer.BackgroundDrawer;
import sequence.ui.utilities.drawer.BorderBackgroundDrawer;

public class ActivitySummarizedController extends SummarizedSequenceController {
	
	public ActivitySummarizedController(Model model, View view) {
		super(model, view);
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == LEFT_MOUSE_BUTTON) {
			List<Activity> selectedActivities = ((SummarizedSequenceView) getView().getParent()).getSelectedActivities();
			SummarizedSequenceView parent = (SummarizedSequenceView) e.getComponent().getParent();
			Activity activity = (Activity) ((ActivityView) e.getComponent()).getModel();
			if((e.getModifiers() & SHIFT) == SHIFT && !selectedActivities.isEmpty()) {
				int beginIndex = selectedActivities.get(selectedActivities.size()-1).getId();
				int endIndex = activity.getId();
				if(beginIndex > endIndex) {
					endIndex = beginIndex;
					beginIndex = activity.getId();
				}
				for(int i=beginIndex; i<endIndex; i++) {
					if(!selectedActivities.contains(((View) parent.getComponent(i)).getModel())) {
						((ActivityRenderer) ((View) parent.getComponent(i)).getRenderer()).setBackgroundDrawer(new BorderBackgroundDrawer());
						selectedActivities.add((Activity) ((ActivityView) parent.getComponent(i)).getModel());
					}
				}
			} else if((e.getModifiers() & CTRL) == 0) {
				for(int i=0; i<parent.getComponentCount(); i++)
					((ActivityRenderer) ((View) parent.getComponent(i)).getRenderer()).setBackgroundDrawer(new BackgroundDrawer());
				selectedActivities.clear();
			}
			if(!selectedActivities.contains(activity)) {
				((ActivityRenderer) ((ActivityView) e.getComponent()).getRenderer()).setBackgroundDrawer(new BorderBackgroundDrawer());
				selectedActivities.add(activity);
			}
		}
	}

	protected void checkPopup(MouseEvent e) {
		if (e.getButton() == RIGHT_MOUSE_BUTTON && e.isPopupTrigger()) {
			((SummarizedSequenceView)getView().getParent()).getPopup().show(e.getComponent(), e.getX(), e.getY());
		}
	}
}
