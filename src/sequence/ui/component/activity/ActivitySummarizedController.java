package sequence.ui.component.activity;

import java.awt.event.MouseEvent;
import java.util.List;

import sequence.model.activity.Activity;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.ui.component.sequence.summarizedSequence.SummarizedSequenceController;
import sequence.ui.component.sequence.summarizedSequence.SummarizedSequenceView;
import sequence.utilities.BackgroundDrawer;
import sequence.utilities.BorderBackgroundDrawer;

public class ActivitySummarizedController extends SummarizedSequenceController {
	
	public ActivitySummarizedController(Model model, View view) {
		super(model, view);
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == LEFT_MOUSE_BUTTON) {
			List<Activity> selectedActivities = ((SummarizedSequenceView) getView().getParent()).getSelectedActivities();
			if((e.getModifiers() & CTRL) == 0) {
				SummarizedSequenceView parent = (SummarizedSequenceView) e.getComponent().getParent();
				for(int i=0; i<parent.getComponentCount(); i++)
					((ActivityRenderer) ((View) parent.getComponent(i)).getRenderer()).setBackgroundDrawer(new BackgroundDrawer());
				selectedActivities.clear();
			}
			Activity activity = (Activity) ((ActivityView) e.getComponent()).getModel();
			if(!selectedActivities.contains(activity))
			{
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
