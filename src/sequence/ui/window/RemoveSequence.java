package sequence.ui.window;

import sequence.model.Sequence;
import sequence.processor.AddSequence;
import sequence.processor.Command;

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
        this.mainWindow.removeSequence((Sequence) this.model);
	}

}
