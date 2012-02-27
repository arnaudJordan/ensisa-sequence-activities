package sequence.processor.command;

import sequence.model.Sequence;
import sequence.model.activity.Activity;

/**
 * The command that is used to remove an activity in a sequence.
 */
public class RemoveActivity extends Command {
	
	/** The sequence. */
	private final Sequence sequence;

	/**
	 * Instantiates a new removes the activity.
	 *
	 * @param activity the activity
	 * @param sequence the sequence
	 */
	public RemoveActivity(final Activity activity, final Sequence sequence) {
		model = activity;
		this.sequence = sequence;
	}

	/* (non-Javadoc)
	 * @see sequence.processor.command.Command#Do()
	 */
	@Override
	public void Do() {
		sequence.removeActivity((Activity) model);

	}

	/* (non-Javadoc)
	 * @see sequence.processor.command.Command#Undo()
	 */
	@Override
	public void Undo() {
		sequence.addActivitySorted((Activity) model);
	}
}
