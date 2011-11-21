package sequence.processor;

import sequence.ui.component.sequence.SequenceContainer;

public class HideSubSequence extends Command {
	private SequenceContainer parent;
	
	public HideSubSequence(SequenceContainer parent)
	{
		this.parent=parent;
		this.undo=new DisplaySubSequence(parent, this);
	}
	public HideSubSequence(SequenceContainer parent, DisplaySubSequence displaySubSequence) {
		this.parent=parent;
		this.undo=displaySubSequence;
	}
	@Override
	public void Do() {
		parent.getSubSequenceView().setVisible(false);
	}
}