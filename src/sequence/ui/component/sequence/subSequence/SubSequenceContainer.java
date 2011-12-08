package sequence.ui.component.sequence.subSequence;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import sequence.model.Sequence;
import sequence.ui.component.sequence.SequenceContainer;
import sequence.ui.component.sequence.subSequence.controller.SubSequenceMenuExportController;
import sequence.utilities.CustomLayout;

public class SubSequenceContainer extends JPanel {
	private SequenceContainer parent;
	private SubSequenceView subSequenceView;
	
	public SubSequenceContainer(Sequence model, SequenceContainer parent) {
		this.parent = parent;
		subSequenceView = new SubSequenceView(model, this);
		new SubSequenceMenuExportController(model, subSequenceView);

		setBorder(BorderFactory.createTitledBorder(""));
		setLayout(new CustomLayout());
		
		
		JLabel label = new JLabel("Sub sequence :");
		ImageIcon icon = new ImageIcon("icons/dialog-close.png");
		JButton button = new JButton(icon);
		button.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
		final SequenceContainer sc = this.parent;
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sc.removeSubSequence((Sequence) subSequenceView.getModel());
			}
		});

		add(label);
		add(button);
		add(subSequenceView);
	}

	public SequenceContainer getParent() {
		return parent;
	}

	public SubSequenceView getSubSequenceView() {
		return subSequenceView;
	}

	public Dimension getPreferredSize() {
		int Hinsets = 2*(parent.getInsets().left + parent.getInsets().right);
		return new Dimension(parent.getWidth() - Hinsets, getLayout().minimumLayoutSize(this).height);
	}
}
