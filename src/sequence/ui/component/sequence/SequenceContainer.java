package sequence.ui.component.sequence;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import sequence.model.Sequence;
import sequence.ui.component.sequence.subSequence.SubSequenceController;
import sequence.ui.component.sequence.subSequence.SubSequenceView;
import sequence.ui.component.sequence.summarizedSequence.SummarizedSequenceController;
import sequence.ui.component.sequence.summarizedSequence.SummarizedSequenceView;
import sequence.ui.window.MainWindow;

public class SequenceContainer extends JPanel {
	private static final long serialVersionUID = 1L;
	private MainWindow mainWindow;
	private SummarizedSequenceView summarizedSequenceView;
	private SubSequenceView subSequenceView;
	
	public SequenceContainer(final Sequence sequence, final MainWindow mainWindow)
	{
		this.mainWindow = mainWindow;
		summarizedSequenceView = new SummarizedSequenceView(sequence, this);
		new SummarizedSequenceController(sequence, summarizedSequenceView);
		subSequenceView = new SubSequenceView(sequence, this);
		new SubSequenceController(sequence, subSequenceView);
		subSequenceView.setVisible(false);
		
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createTitledBorder(((Sequence)summarizedSequenceView.getModel()).getWorkflowID()));
		setLayout(new BorderLayout());
		
		ImageIcon icon = new ImageIcon("icons/dialog-close.png");
		JButton button = new JButton(icon);
		//button.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
		final SequenceContainer sc = this;
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
	
	public Dimension getPreferredSize() {
		int Hinsets = 2*(getInsets().left + getInsets().right);
		return new Dimension(mainWindow.getWidth() - Hinsets, getMinimumSize().height);
	}
}
