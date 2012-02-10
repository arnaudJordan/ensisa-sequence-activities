package sequence.processor;

import sequence.model.Sequence;
import sequence.model.activity.Activity;

public class RemoveActivity extends Command {
	private Sequence sequence;

	public RemoveActivity(Activity activity, Sequence sequence) {
		this.model = activity;
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
