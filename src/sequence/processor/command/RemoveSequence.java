package sequence.processor.command;

import java.io.IOException;

import sequence.model.Sequence;
import sequence.ui.component.sequence.SequenceContainer;
import sequence.ui.component.sequence.SequenceFrame;
import sequence.ui.window.MainWindow;
import sequence.ui.window.MenuBar;
import sequence.utilities.Config;

/**
 * The command that is used to remove the sequence frame that contains the
 * sequence, from the dekstop pane
 */
public class RemoveSequence extends Command {

	/** The main window. */
	private final MainWindow mainWindow;

	/**
	 * Instantiates a new removes the sequence.
	 * 
	 * @param sequence
	 *            the sequence
	 * @param mainWindow
	 *            the main window
	 */
	public RemoveSequence(final Sequence sequence, final MainWindow mainWindow) {
		model = sequence;
		this.mainWindow = mainWindow;
		undo = new AddSequence(sequence, mainWindow, this);
	}

	/**
	 * Instantiates a new removes the sequence.
	 * 
	 * @param sequence
	 *            the sequence
	 * @param mainWindow
	 *            the main window
	 * @param addSequence
	 *            the add sequence
	 */
	public RemoveSequence(final Sequence sequence, final MainWindow mainWindow,
			final AddSequence addSequence) {
		model = sequence;
		this.mainWindow = mainWindow;
		undo = addSequence;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sequence.processor.command.Command#Do()
	 */
	@Override
	public void Do() {
		final SequenceContainer sc = mainWindow
				.getSequenceContainer((Sequence) model);
		final SequenceFrame f = mainWindow.getSequenceFrame((Sequence) model);
		f.dispose();
		((MenuBar) mainWindow.getJMenuBar()).removeFrameMenuItem(f);
		mainWindow.getSequenceContainers().remove(sc);
		mainWindow.getConfig().removeOpenedFile(((Sequence) model).getFile());
		try {
			Config.serialize(mainWindow.getConfig());
		} catch (IOException e) {
		}
	}
}
