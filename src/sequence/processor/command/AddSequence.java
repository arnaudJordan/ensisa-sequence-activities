package sequence.processor.command;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import sequence.model.Sequence;
import sequence.ui.component.sequence.SequenceContainer;
import sequence.ui.component.sequence.SequenceFrame;
import sequence.ui.component.sequence.summarizedSequence.SummarizedSequenceController;
import sequence.ui.component.sequence.summarizedSequence.SummarizedSequenceView;
import sequence.ui.utilities.MDIDesktopPane;
import sequence.ui.window.MainWindow;
import sequence.ui.window.MenuBar;

public class AddSequence extends Command {
	private final MainWindow mainWindow;

	public AddSequence(final Sequence sequence, final MainWindow mainWindow) {
		model = sequence;
		this.mainWindow = mainWindow;
		undo = new RemoveSequence(sequence, mainWindow, this);
	}

	public AddSequence(final Sequence sequence, final MainWindow mainWindow,
			final RemoveSequence removeSequence) {
		model = sequence;
		this.mainWindow = mainWindow;
		undo = removeSequence;
	}

	@Override
	public void Do() {
		final SummarizedSequenceView view = new SummarizedSequenceView(model);
		new SummarizedSequenceController(model, view);
		final MDIDesktopPane mainPane = mainWindow.getMainPane();
		final SequenceContainer sc = new SequenceContainer(view,
				"Summarized sequence");
		final SequenceFrame f = new SequenceFrame(
				((Sequence) model).getWorkflowID(), sc);
		f.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		f.addInternalFrameListener(new InternalFrameListener() {
			@Override
			public void internalFrameOpened(final InternalFrameEvent arg0) {
				((MenuBar) mainWindow.getJMenuBar()).addFrameMenuItem(f);
			}

			@Override
			public void internalFrameIconified(final InternalFrameEvent arg0) {
			}

			@Override
			public void internalFrameDeiconified(final InternalFrameEvent arg0) {
			}

			@Override
			public void internalFrameDeactivated(final InternalFrameEvent arg0) {
			}

			@Override
			public void internalFrameClosing(final InternalFrameEvent arg0) {
				final int response = JOptionPane.showConfirmDialog(
						mainWindow,
						"Are you sur you want to close the sequence '"
								+ f.getTitle() + "'?", "Close sequence",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (response == JOptionPane.NO_OPTION)
					return;
				mainWindow.remove((Sequence) model);
			}

			@Override
			public void internalFrameClosed(final InternalFrameEvent arg0) {
			}

			@Override
			public void internalFrameActivated(final InternalFrameEvent arg0) {
				((MenuBar) mainWindow.getJMenuBar()).selectFrameMenuItem(f);
			}
		});
		f.getContentPane().add(new JScrollPane(sc));
		f.setVisible(true);
		f.pack();
		mainWindow.getSequenceContainers().add(sc);
		mainPane.add(f);
		try {
			f.setSelected(true);
		} catch (final java.beans.PropertyVetoException e) {
		}
	}
}
