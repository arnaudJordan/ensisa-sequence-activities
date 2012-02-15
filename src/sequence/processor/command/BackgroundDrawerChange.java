package sequence.processor.command;

import sequence.ui.utilities.drawer.BackgroundDrawer;
import sequence.ui.utilities.drawer.FullBackgroundDrawer;
import sequence.ui.utilities.drawer.StripedBackgroundDrawer;
import sequence.utilities.EventDispatcher;

public class BackgroundDrawerChange extends Command {
	private final BackgroundDrawer bd;

	public BackgroundDrawerChange(final BackgroundDrawer bd) {
		this.bd = bd;
		final BackgroundDrawer bd2 = (bd instanceof StripedBackgroundDrawer) ? new FullBackgroundDrawer()
				: new StripedBackgroundDrawer();
		undo = new BackgroundDrawerChange(bd2, this);
	}

	public BackgroundDrawerChange(final BackgroundDrawer bd,
			final BackgroundDrawerChange stripedChange) {
		this.bd = bd;
		undo = stripedChange;
	}

	@Override
	public void Do() {
		EventDispatcher.backgroundChanged(bd);
	}
}
