package sequence.processor;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

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
		JDesktopPane mainPane = mainWindow.getMainPane();
		final SequenceContainer sc = new SequenceContainer(view, "Summarized sequence");
		JInternalFrame f = new JInternalFrame(((Sequence) model).getWorkflowID(), true, true, true, true);
		f.addInternalFrameListener(new InternalFrameListener() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
			}
			@Override
			public void internalFrameIconified(InternalFrameEvent arg0) {
			}
			@Override
			public void internalFrameDeiconified(InternalFrameEvent arg0) {
			}
			@Override
			public void internalFrameDeactivated(InternalFrameEvent arg0) {
			}
			@Override
			public void internalFrameClosing(InternalFrameEvent arg0) {
			}
			@Override
			public void internalFrameClosed(InternalFrameEvent arg0) {
				mainWindow.remove(sc);
			}
			@Override
			public void internalFrameActivated(InternalFrameEvent arg0) {
			}
		});
		f.getContentPane().add(new JScrollPane(sc));
		f.setPreferredSize(new Dimension(mainPane.getWidth(), 200));
		if(mainPane.getComponentCount() > 0) {
			Component c = mainPane.getComponent(0);
			f.setLocation(0, c.getY() + c.getHeight());
		}
		f.setVisible(true);
		f.pack();
		mainWindow.getSequenceContainers().add(sc);
        mainPane.add(f);
        try {
            f.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
	}
}
