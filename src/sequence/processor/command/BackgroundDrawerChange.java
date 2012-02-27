package sequence.processor.command;

import sequence.ui.utilities.drawer.BackgroundDrawer;
import sequence.ui.utilities.drawer.FullBackgroundDrawer;
import sequence.ui.utilities.drawer.StripedBackgroundDrawer;
import sequence.utilities.OptionEventDispatcher;

/**
 * The command that is used to process a change of the background drawer.
 */
public class BackgroundDrawerChange extends Command {
	
	/** The background drawer. */
	private final BackgroundDrawer bd;

	/**
	 * Instantiates a new background drawer change.
	 *
	 * @param bd the background drawer
	 */
	public BackgroundDrawerChange(final BackgroundDrawer bd) {
		this.bd = bd;
		final BackgroundDrawer bd2 = (bd instanceof StripedBackgroundDrawer) ? new FullBackgroundDrawer()
				: new StripedBackgroundDrawer();
		undo = new BackgroundDrawerChange(bd2, this);
	}

	/**
	 * Instantiates a new background drawer change.
	 *
	 * @param bd the background drawer
	 * @param stripedChange the striped change
	 */
	public BackgroundDrawerChange(final BackgroundDrawer bd,
			final BackgroundDrawerChange stripedChange) {
		this.bd = bd;
		undo = stripedChange;
	}

	/* (non-Javadoc)
	 * @see sequence.processor.command.Command#Do()
	 */
	@Override
	public void Do() {
		OptionEventDispatcher.backgroundChanged(bd);
	}
}
