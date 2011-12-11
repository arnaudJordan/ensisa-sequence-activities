package sequence.processor;

import sequence.model.Sequence;
import sequence.ui.component.sequence.SequenceContainer;
import sequence.ui.component.sequence.summarizedSequence.SummarizedSequenceController;
import sequence.ui.component.sequence.summarizedSequence.SummarizedSequenceView;
import sequence.ui.window.MainWindow;

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
		SummarizedSequenceView view = new SummarizedSequenceView(model);
		new SummarizedSequenceController(model, view);
		SequenceContainer sc = new SequenceContainer(view, ((Sequence) model).getWorkflowID(), "Summarized sequence", this.mainWindow);
		this.mainWindow.getSequenceContainers().add(sc);
        this.mainWindow.getMainPane().add(sc);
        this.mainWindow.getMainPane().revalidate();
        this.mainWindow.getMainPane().repaint();
        this.mainWindow.setVisible(true);
	}
}
