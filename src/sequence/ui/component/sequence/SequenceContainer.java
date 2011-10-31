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
import javax.swing.border.TitledBorder;

import sequence.model.Sequence;
import sequence.ui.MainWindow;

public class SequenceContainer extends JPanel {
	private static final long serialVersionUID = 1L;
	private SequenceView sequenceView;
	private MainWindow mainWindow;
	
	public SequenceContainer(final SequenceView sequenceView, final MainWindow mainWindow)
	{
		this.sequenceView = sequenceView;
		this.mainWindow = mainWindow;

		setBackground(Color.WHITE);
		setBorder(BorderFactory.createTitledBorder(((Sequence)sequenceView.getModel()).getWorkflowID()));
		setLayout(new BorderLayout());
		
		ImageIcon icon = new ImageIcon("icons/dialog-close.png");
		JButton button = new JButton(icon);
		final SequenceContainer sc= this;
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.removeSequence(sc);
			}
		});
		add(button, BorderLayout.PAGE_START);;
		add(sequenceView, BorderLayout.CENTER);
		setVisible(true);
	}

	public  SequenceView getSequenceView() {
		return sequenceView;
	}
}
