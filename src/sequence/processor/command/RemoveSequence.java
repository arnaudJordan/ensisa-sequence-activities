package sequence.processor.command;

import sequence.model.Sequence;
import sequence.ui.window.MainWindow;

public class RemoveSequence extends Command {
	private final MainWindow mainWindow;

	public RemoveSequence(final Sequence sequence, final MainWindow mainWindow) {
		model = sequence;
		this.mainWindow = mainWindow;
		undo = new AddSequence(sequence, mainWindow, this);
	}

	public RemoveSequence(final Sequence sequence, final MainWindow mainWindow,
			final AddSequence addSequence) {
		model = sequence;
		this.mainWindow = mainWindow;
		undo = addSequence;
	}

	@Override
	public void Do() {
		mainWindow.remove((Sequence) model);
	}
}
