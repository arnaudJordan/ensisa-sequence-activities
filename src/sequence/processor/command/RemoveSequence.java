package sequence.processor.command;

import sequence.model.Sequence;
import sequence.ui.window.MainWindow;

public class RemoveSequence extends Command {
	private MainWindow mainWindow;

	public RemoveSequence(Sequence sequence, MainWindow mainWindow) {
		this.model = sequence;
		this.mainWindow=mainWindow;
		this.undo=new AddSequence(sequence, mainWindow, this);
	}

	public RemoveSequence(Sequence sequence, MainWindow mainWindow, AddSequence addSequence) {
		this.model = sequence;
		this.mainWindow=mainWindow;
		this.undo=addSequence;
	}

	@Override
	public void Do() {
        this.mainWindow.remove((Sequence) this.model);
	}
}
