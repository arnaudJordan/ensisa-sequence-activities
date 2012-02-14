package sequence.processor.command;

import sequence.ui.utilities.drawer.BackgroundDrawer;
import sequence.ui.utilities.drawer.StripedBackgroundDrawer;
import sequence.utilities.EventDispatcher;

public class BackgroundDrawerChange extends Command {
	private BackgroundDrawer bd;
	
	public BackgroundDrawerChange(BackgroundDrawer bd)
	{
		this.bd=bd;
		BackgroundDrawer bd2 = (bd instanceof StripedBackgroundDrawer) ? new BackgroundDrawer() : new StripedBackgroundDrawer();
		this.undo=new BackgroundDrawerChange(bd2, this);
	}
	public BackgroundDrawerChange(BackgroundDrawer bd, BackgroundDrawerChange stripedChange) {
		this.bd=bd;
		this.undo=stripedChange;
	}
	@Override
	public void Do() {
		EventDispatcher.backgroundChanged(bd);
	}
}
