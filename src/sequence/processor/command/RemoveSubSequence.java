package sequence.processor.command;

import sequence.model.Sequence;
import sequence.ui.component.sequence.SequenceContainer;

public class RemoveSubSequence extends Command {
	private SequenceContainer parent;
	
	public RemoveSubSequence(Sequence sequence, SequenceContainer parent)
	{
		this.model=sequence;
		this.parent = parent;
		this.undo = new AddSubSequence(sequence, parent, this);
	}
	public RemoveSubSequence(Sequence sequence, SequenceContainer parent, AddSubSequence addSubSequence) {
		this.model=sequence;
		this.parent = parent;
		this.undo = addSubSequence;
	}
	@Override
	public void Do() {
		for(SequenceContainer current : parent.getChilds()) {
			if(current.getView().getModel().equals(model)) {
				parent.getChilds().remove(current);
				parent.remove(current);
				break;
			}
		}
		parent.revalidate();
		parent.repaint();
	}
}