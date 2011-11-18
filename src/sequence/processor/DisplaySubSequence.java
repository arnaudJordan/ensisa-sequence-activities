package sequence.processor;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sequence.model.Sequence;
import sequence.model.activity.Activity;
import sequence.ui.component.sequence.SequenceContainer;
import sequence.ui.component.sequence.subSequence.SubSequenceView;
import sequence.ui.component.sequence.summarizedSequence.SummarizedSequenceView;

public class DisplaySubSequence extends Command {
	private SequenceContainer parent;
	
	public DisplaySubSequence(SequenceContainer parent)
	{
		this.parent=parent;
		this.undo=new HideSubSequence(parent, this);
	}
	public DisplaySubSequence(SequenceContainer parent, HideSubSequence hideSubSequence) {
		this.parent=parent;
		this.undo=hideSubSequence;
	}
	@Override
	public void Do() {
		SummarizedSequenceView view = parent.getSummarizedSequenceView();
		List<Activity> selectedActivities = view.getSelectedActivities();
		if(!selectedActivities.isEmpty()) {
			if(selectedActivities.size() == 1) {
				Activity selectedActivity = selectedActivities.get(0);
				Sequence sequence = (Sequence) view.getModel();
				selectedActivities = sequence.getActivitiesInPhase(sequence.getPhaseOfActivity(selectedActivity));
			} else {
				Collections.sort(selectedActivities, new Comparator<Activity>() {
					public int compare(Activity a1, Activity a2) {
						return a1.getId() < a2.getId() ? -1 : 1;
					}
				});
			}
			Sequence subSequenceModel = new Sequence(((Sequence) view.getModel()).getWorkflowID(), selectedActivities);
			SubSequenceView subView = parent.getSubSequenceView();
			subView.setModel(subSequenceModel);
			if(!subView.isVisible())
				subView.setVisible(true);
		}
	}
}
