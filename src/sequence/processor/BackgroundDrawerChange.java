package sequence.processor;

import sequence.ui.component.activity.ActivityRenderer;
import sequence.utilities.BackgroundDrawer;

public class BackgroundDrawerChange extends Command {
	private ActivityRenderer activityRenderer;
	private BackgroundDrawer bd;
	
	public BackgroundDrawerChange(ActivityRenderer renderer, BackgroundDrawer bd)
	{
		this.activityRenderer=renderer;
		this.bd=bd;
		this.undo=new BackgroundDrawerChange(renderer,renderer.getBackgroundDrawer(), this);
	}
	public BackgroundDrawerChange(ActivityRenderer renderer, BackgroundDrawer bd, BackgroundDrawerChange stripedChange) {
		this.activityRenderer=renderer;
		this.bd=bd;
		this.undo=stripedChange;
	}
	@Override
	public void Do() {
		activityRenderer.setBackgroundDrawer(bd);
	}
}
