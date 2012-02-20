package sequence.ui.component.sequence;

import javax.swing.JInternalFrame;

public class SequenceFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private SequenceContainer sequenceContainer;

	public SequenceFrame(String title, SequenceContainer sc) {
		super(title, true, true, true, true);
		sequenceContainer = sc;
	}
	
	public SequenceContainer getSequenceContainer() {
		return sequenceContainer;
	}	
}
