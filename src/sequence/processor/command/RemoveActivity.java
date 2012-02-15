package sequence.processor.command;

import sequence.model.Sequence;
import sequence.model.activity.Activity;

public class RemoveActivity extends Command {
	private final Sequence sequence;

	public RemoveActivity(final Activity activity, final Sequence sequence) {
		model = activity;
		this.sequence = sequence;
	}

	@Override
	public void Do() {
		sequence.removeActivity((Activity) model);

	}

	@Override
	public void Undo() {
		sequence.addActivitySorted((Activity) model);
	}
}
