package sequence.processor.command;

import sequence.model.Sequence;
import sequence.ui.component.sequence.SequenceContainer;

public class RemoveSubSequence extends Command {
	private final SequenceContainer parent;

	public RemoveSubSequence(final Sequence sequence,
			final SequenceContainer parent) {
		model = sequence;
		this.parent = parent;
		undo = new AddSubSequence(sequence, parent, this);
	}

	public RemoveSubSequence(final Sequence sequence,
			final SequenceContainer parent, final AddSubSequence addSubSequence) {
		model = sequence;
		this.parent = parent;
		undo = addSubSequence;
	}

	@Override
	public void Do() {
		for (final SequenceContainer current : parent.getChilds()) {
			if (current.getView().getModel().equals(model)) {
				parent.getChilds().remove(current);
				parent.remove(current);
				break;
			}
		}
		parent.revalidate();
		parent.repaint();
	}
}