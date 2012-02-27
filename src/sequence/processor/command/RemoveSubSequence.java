package sequence.processor.command;

import java.awt.Component;

import sequence.model.Sequence;
import sequence.ui.component.sequence.SequenceContainer;

/**
 * The command that is used to remove the sequence container that contains the
 * sub sequence, from the parent of its summarized sequence view.
 */
public class RemoveSubSequence extends Command {
	
	/** The parent. */
	private final SequenceContainer parent;

	/**
	 * Instantiates a new removes the sub sequence.
	 *
	 * @param sequence the sequence
	 * @param parent the parent
	 */
	public RemoveSubSequence(final Sequence sequence,
			final SequenceContainer parent) {
		model = sequence;
		this.parent = parent;
		undo = new AddSubSequence(sequence, parent, this);
	}

	/**
	 * Instantiates a new removes the sub sequence.
	 *
	 * @param sequence the sequence
	 * @param parent the parent
	 * @param addSubSequence the add sub sequence
	 */
	public RemoveSubSequence(final Sequence sequence,
			final SequenceContainer parent, final AddSubSequence addSubSequence) {
		model = sequence;
		this.parent = parent;
		undo = addSubSequence;
	}

	/* (non-Javadoc)
	 * @see sequence.processor.command.Command#Do()
	 */
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