package sequence.processor;

import javax.swing.JPanel;

import sequence.model.Sequence;
import sequence.mvc.View;
import sequence.ui.component.sequence.SequenceContainer;
import sequence.ui.component.sequence.subSequence.SubSequenceView;

public class RemoveSubSequence extends Command {
	private SequenceContainer parent;
	
	public RemoveSubSequence(Sequence sequence, SequenceContainer parent)
	{
		this.model = sequence;
		this.parent=parent;
		this.undo=new AddSubSequence(sequence, parent, this);
	}
	public RemoveSubSequence(Sequence sequence, SequenceContainer parent, AddSubSequence addSubSequence) {
		this.model = sequence;
		this.parent=parent;
		this.undo=addSubSequence;
	}
	@Override
	public void Do() {
		for(JPanel current : parent.getSubSequenceContainers()) {
			if(((View) current.getComponent(3)).getModel().equals(model)) {
				parent.getSubSequenceViews().remove(current);
				parent.getSubSequenceContainers().remove(current);
				parent.remove(current);
				break;
			}
		}
		parent.revalidate();
		parent.repaint();
	}
}