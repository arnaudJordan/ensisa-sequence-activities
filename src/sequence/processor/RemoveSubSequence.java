package sequence.processor;

import sequence.model.Sequence;
import sequence.ui.component.sequence.SequenceContainer;
import sequence.ui.component.sequence.subSequence.SubSequenceContainer;

public class RemoveSubSequence extends Command {
	private SequenceContainer parent;
	
	public RemoveSubSequence(Sequence sequence, SequenceContainer parent)
	{
		this.model = sequence;
		this.parent = parent;
		this.undo = new AddSubSequence(sequence, parent, this);
	}
	public RemoveSubSequence(Sequence sequence, SequenceContainer parent, AddSubSequence addSubSequence) {
		this.model = sequence;
		this.parent = parent;
		this.undo = addSubSequence;
	}
	@Override
	public void Do() {
		for(SubSequenceContainer current : parent.getSubSequenceContainers()) {
			if(current.getSubSequenceView().getModel().equals(model)) {
				parent.getSubSequenceContainers().remove(current);
				parent.remove(current);
				break;
			}
		}
		parent.revalidate();
		parent.repaint();
	}
}