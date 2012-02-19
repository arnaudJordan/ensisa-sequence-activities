package sequence.processor.command;

import sequence.model.Sequence;
import sequence.ui.component.sequence.SequenceContainer;
import sequence.ui.component.sequence.subSequence.SubSequenceView;
import sequence.ui.component.sequence.subSequence.controller.SubSequenceController;
import sequence.ui.component.sequence.summarizedSequence.SummarizedSequenceView;

public class AddSubSequence extends Command {
	private final SequenceContainer parent;

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
		final SubSequenceView subView = new SubSequenceView(model, view);
		new SubSequenceController(model, subView);
		final SequenceContainer subSequence = new SequenceContainer(subView,
				"Sub sequence", parent);
		parent.add(subSequence);
		parent.revalidate();
		parent.repaint();
	}
}
