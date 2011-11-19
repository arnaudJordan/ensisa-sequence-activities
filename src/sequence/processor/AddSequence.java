package sequence.processor;

import sequence.model.Sequence;
import sequence.ui.component.sequence.SequenceContainer;
import sequence.ui.window.MainWindow;
import sequence.ui.window.RemoveSequence;

public class AddSequence extends Command {
	private MainWindow mainWindow;
	public AddSequence(Sequence model, MainWindow mainWindow)
	{
		this.model=model;
		this.mainWindow=mainWindow;
		this.undo=new RemoveSequence(model, mainWindow, this);
	}
	public AddSequence(Sequence sequence, MainWindow mainWindow, RemoveSequence removeSequence) {
		this.model=sequence;
		this.mainWindow=mainWindow;
		this.undo=removeSequence;
	}
	
	@Override
	public void Do() {
		SequenceContainer sc = new SequenceContainer((Sequence) this.model, this.mainWindow);
        this.mainWindow.getSequenceContainers().add(sc);
        this.mainWindow.getMainPane().add(sc);
        this.mainWindow.validate();
        this.mainWindow.pack();
        this.mainWindow.setVisible(true);
	}
}
