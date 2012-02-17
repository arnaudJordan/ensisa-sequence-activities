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
	private final SequenceContainer parent;;

	public AddSubSequence(final Sequence sequence,
			final SequenceContainer parent) {
		model = sequence;
		this.parent = parent;
		undo = new RemoveSubSequence(sequence, parent, this);
	}

	public AddSubSequence(final Sequence sequence,
			final SequenceContainer parent,
			final RemoveSubSequence removeSubSequence) {
		model = sequence;
		this.parent = parent;
		undo = removeSubSequence;
	}

	@Override
	public void Do() {
		final SummarizedSequenceView view = (SummarizedSequenceView) parent
				.getView();
		List<Activity> selectedActivities = view.getSelectedActivities();
		if (!selectedActivities.isEmpty()) {
			if (selectedActivities.size() == 1) {
				final Activity selectedActivity = selectedActivities.get(0);
				final Sequence sequence = (Sequence) view.getModel();
				selectedActivities = sequence.getActivitiesInPhase(sequence
						.getPhaseOfActivity(selectedActivity));
			} else {
				Collections.sort(selectedActivities,
						new Comparator<Activity>() {
							@Override
							public int compare(final Activity a1,
									final Activity a2) {
								return a1.getId() < a2.getId() ? -1 : 1;
							}
						});
			}
			final Sequence subSequenceModel = new Sequence(
					((Sequence) view.getModel()).getWorkflowID(),
					selectedActivities);
			final SubSequenceView subView = new SubSequenceView(
					subSequenceModel, view);
			new SubSequenceController(subSequenceModel, subView);
			final SequenceContainer subSequence = new SequenceContainer(
					subView, "Sub sequence", parent);
			parent.add(subSequence);
			parent.getChilds().add(subSequence);
			parent.revalidate();
			parent.repaint();
		}
	}
}
