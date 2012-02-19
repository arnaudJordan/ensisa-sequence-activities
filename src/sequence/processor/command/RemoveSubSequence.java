package sequence.processor.command;

import java.awt.Component;

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
		for (int i = 0; i < parent.getComponentCount(); i++) {
			final Component c = parent.getComponent(i);
			if (c instanceof SequenceContainer) {
				if (((SequenceContainer) c).getView().getModel().equals(model)) {
					parent.remove(c);
					break;
				}
			}
		}
		parent.revalidate();
		parent.repaint();
	}
}