package sequence.processor.command;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sequence.model.Sequence;
import sequence.model.activity.Activity;
import sequence.ui.component.sequence.SequenceContainer;
import sequence.ui.component.sequence.subSequence.SubSequenceView;
import sequence.ui.component.sequence.subSequence.controller.SubSequenceController;
import sequence.ui.component.sequence.summarizedSequence.SummarizedSequenceView;

public class AddSubSequence extends Command {
	private SequenceContainer parent;;
	
	public AddSubSequence(Sequence sequence, SequenceContainer parent)
	{
		this.model=sequence;
		this.parent=parent;
		this.undo=new RemoveSubSequence(sequence, parent, this);
	}
	public AddSubSequence(Sequence sequence, SequenceContainer parent, RemoveSubSequence removeSubSequence) {
		this.model=sequence;
		this.parent=parent;
		this.undo=removeSubSequence;
	}
	@Override
	public void Do() {
		SummarizedSequenceView view = (SummarizedSequenceView) parent.getView();
		List<Activity> selectedActivities = view.getSelectedActivities();
		if(!selectedActivities.isEmpty()) {
			if(selectedActivities.size() == 1) {
				Activity selectedActivity = selectedActivities.get(0);
				Sequence sequence = (Sequence) view.getModel();
				selectedActivities = sequence.getActivitiesInPhase(sequence.getPhaseOfActivity(selectedActivity));
			} else {
				Collections.sort(selectedActivities, new Comparator<Activity>() {
					@Override
					public int compare(Activity a1, Activity a2) {
						return a1.getId() < a2.getId() ? -1 : 1;
					}
				});
			}
			Sequence subSequenceModel = new Sequence(((Sequence) view.getModel()).getWorkflowID(), selectedActivities);
			SubSequenceView subView = new SubSequenceView(subSequenceModel, view);
			new SubSequenceController(subSequenceModel, subView);
			SequenceContainer subSequence = new SequenceContainer(subView, "Sub sequence", parent);
			parent.add(subSequence);
			parent.getChilds().add(subSequence);
			parent.revalidate();
			parent.repaint();
		}
	}
}
