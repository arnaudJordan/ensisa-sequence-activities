package sequence.processor.command;

import sequence.model.Sequence;
import sequence.ui.component.sequence.SequenceContainer;
import sequence.ui.component.sequence.subSequence.SubSequenceView;
import sequence.ui.component.sequence.subSequence.controller.SubSequenceController;
import sequence.ui.component.sequence.summarizedSequence.SummarizedSequenceView;

/**
 * The command that is used to add a sub sequence from a summarized sequence. A sequence container
 * containing a sub sequence view instanciated with the sequence will be
 * added to the parent of the summarized sequence.
 */
public class AddSubSequence extends Command {
	
	/** The parent. */
	private final SequenceContainer parent;

	/**
	 * Instantiates a new adds the sub sequence.
	 *
	 * @param sequence the sequence
	 * @param parent the parent
	 */
	public AddSubSequence(final Sequence sequence,
			final SequenceContainer parent) {
		model = sequence;
		this.parent = parent;
		undo = new RemoveSubSequence(sequence, parent, this);
	}

	/**
	 * Instantiates a new adds the sub sequence.
	 *
	 * @param sequence the sequence
	 * @param parent the parent
	 * @param removeSubSequence the remove sub sequence
	 */
	public AddSubSequence(final Sequence sequence,
			final SequenceContainer parent,
			final RemoveSubSequence removeSubSequence) {
		model = sequence;
		this.parent = parent;
		undo = removeSubSequence;
	}

	/* (non-Javadoc)
	 * @see sequence.processor.command.Command#Do()
	 */
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
