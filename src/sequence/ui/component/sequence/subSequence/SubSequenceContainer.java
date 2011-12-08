package sequence.ui.component.sequence.subSequence;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

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
		JSeparator separator = new JSeparator();

		add(label);
		add(button);
		add(separator);
		add(subSequenceView);
		setVisible(true);
	}

	public SequenceContainer getParent() {
		return parent;
	}

	public SubSequenceView getSubSequenceView() {
		return subSequenceView;
	}

}
