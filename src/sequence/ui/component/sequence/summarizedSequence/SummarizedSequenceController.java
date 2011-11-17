package sequence.ui.component.sequence.summarizedSequence;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sequence.model.Sequence;
import sequence.model.activity.Activity;
import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.ui.component.sequence.SequenceContainer;
import sequence.ui.component.sequence.subSequence.SubSequenceView;

public class SummarizedSequenceController extends Controller implements ActionListener  {
	
	public SummarizedSequenceController(Model model, View view) {
		super(model, view);
	}	

	public void actionPerformed(ActionEvent e) {
		SummarizedSequenceView view = (SummarizedSequenceView) getView();
		List<Activity> selectedActivities = view.getSelectedActivities();
		if(!selectedActivities.isEmpty()) {
			Collections.sort(selectedActivities, new Comparator<Activity>() {
				public int compare(Activity a1, Activity a2) {
					return a1.getId() < a2.getId() ? -1 : 1;
				}
			});
			Sequence subSequenceModel = new Sequence(((Sequence) view.getModel()).getWorkflowID(), selectedActivities);
			SubSequenceView subView = ((SequenceContainer) view.getParent()).getSubSequenceView();
			subView.setModel(subSequenceModel);
			getView().getParent().add(subView, BorderLayout.SOUTH);
		}
	}

	public void mousePressed(MouseEvent e) {
		checkPopup(e);
	}
	 
	public void mouseReleased(MouseEvent e) {
		checkPopup(e);
	}
	
	protected void checkPopup(MouseEvent e) {
		if (e.getButton() == RIGHT_MOUSE_BUTTON && e.isPopupTrigger()) {
			((SummarizedSequenceView)getView()).getPopup().show(e.getComponent(), e.getX(), e.getY());
		}
	}
}
