package sequence.ui.component.sequence;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import sequence.model.Sequence;
import sequence.mvc.View;
import sequence.processor.command.AddSubSequence;
import sequence.processor.command.RemoveSubSequence;
import sequence.ui.component.timeLine.TimeLineView;
import sequence.ui.utilities.CustomLayout;
import sequence.ui.window.MainWindow;

/**
 * The Class SequenceContainer.
 */
public class SequenceContainer extends JPanel {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The view. */
	private View view;

	/**
	 * Instantiates a new sequence container.
	 *
	 * @param view the view
	 * @param label the label
	 * @param parent the parent
	 */
	public SequenceContainer(final View view, final String label,
			final SequenceContainer parent) {
		this(view, "", label, parent);
	}

	/**
	 * Instantiates a new sequence container.
	 *
	 * @param view the view
	 * @param label the label
	 */
	public SequenceContainer(final View view, final String label) {
		this.view = view;

		setBackground(Color.WHITE);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setLayout(new CustomLayout());

		final JLabel l = new JLabel(label);

		add(l);
		add(new TimeLineView(((Sequence) view.getModel()).getPhases()));
		super.add(this.view);
	}

	/**
	 * Instantiates a new sequence container.
	 *
	 * @param view the view
	 * @param title the title
	 * @param label the label
	 * @param parent the parent
	 */
	public SequenceContainer(final View view, final String title,
			final String label, final SequenceContainer parent) {
		this.view = view;

		setBackground(Color.WHITE);
		setBorder(BorderFactory.createTitledBorder(title));
		setLayout(new CustomLayout());

		final JLabel l = new JLabel(label);
		final ImageIcon icon = new ImageIcon("icons/dialog-close.png");
		final JButton button = new JButton(icon);
		button.setPreferredSize(new Dimension(icon.getIconWidth(), icon
				.getIconHeight()));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				final int response = JOptionPane.showConfirmDialog(
						getTopLevelAncestor(),
						"Are you sur you want to close this sub sequence?",
						"Close sub sequence", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);
				if (response == JOptionPane.NO_OPTION)
					return;
				parent.remove((Sequence) view.getModel());
			}
		});
		add(l);
		add(button);
		super.add(this.view);
	}

	/**
	 * Adds the.
	 *
	 * @param sequence the sequence
	 */
	public void add(final Sequence sequence) {
		((MainWindow) getTopLevelAncestor()).getProcessor().Do(
				new AddSubSequence(sequence, this));
	}

	/**
	 * Removes the.
	 *
	 * @param sequence the sequence
	 */
	public void remove(final Sequence sequence) {
		((MainWindow) getTopLevelAncestor()).getProcessor().Do(
				new RemoveSubSequence(sequence, this));
	}

	/**
	 * Gets the view.
	 *
	 * @return the view
	 */
	public View getView() {
		return view;
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#getPreferredSize()
	 */
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(getParent().getWidth(), getLayout()
				.minimumLayoutSize(this).height);
	}
}
