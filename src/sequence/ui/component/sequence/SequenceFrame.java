package sequence.ui.component.sequence;

import javax.swing.JInternalFrame;

/**
 * The Class SequenceFrame.
 */
public class SequenceFrame extends JInternalFrame {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The sequence container. */
	private SequenceContainer sequenceContainer;

	/**
	 * Instantiates a new sequence frame.
	 *
	 * @param title the title
	 * @param sc the sc
	 */
	public SequenceFrame(String title, SequenceContainer sc) {
		super(title, true, true, true, true);
		sequenceContainer = sc;
	}
	
	/**
	 * Gets the sequence container.
	 *
	 * @return the sequence container
	 */
	public SequenceContainer getSequenceContainer() {
		return sequenceContainer;
	}	
}
