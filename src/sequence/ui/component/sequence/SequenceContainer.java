package sequence.ui.component.sequence;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import sequence.model.Sequence;
import sequence.ui.component.sequence.subSequence.SubSequenceView;
import sequence.ui.component.sequence.summarizedSequence.SummarizedSequenceView;
import sequence.ui.window.MainWindow;

public class SequenceContainer extends JPanel {
	private static final long serialVersionUID = 1L;
	private SummarizedSequenceView summarizedSequenceView;
	private SubSequenceView subSequenceView;
	
	public SequenceContainer(final SummarizedSequenceView summarizedSequenceView, final SubSequenceView subSequenceView, final MainWindow mainWindow)
	{
		this.summarizedSequenceView = summarizedSequenceView;
		this.subSequenceView = subSequenceView;

		setBackground(Color.WHITE);
		setBorder(BorderFactory.createTitledBorder(((Sequence)subSequenceView.getModel()).getWorkflowID()));
		setLayout(new BorderLayout());
		
		ImageIcon icon = new ImageIcon("icons/dialog-close.png");
		JButton button = new JButton(icon);
		final SequenceContainer sc= this;
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.removeSequence(sc);
			}
		});
		add(button, BorderLayout.NORTH);
		add(summarizedSequenceView, BorderLayout.CENTER);
		add(subSequenceView, BorderLayout.SOUTH);
		setVisible(true);
	}

	public SummarizedSequenceView getSummarizedSequenceView() {
		return summarizedSequenceView;
	}

	public SubSequenceView getSubSequenceView() {
		return subSequenceView;
	}
}
