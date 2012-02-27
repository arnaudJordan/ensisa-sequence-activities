package sequence.ui.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.WindowConstants;

import sequence.ui.utilities.ComponentLocation;
import sequence.utilities.Config;

/**
 * The Class StyleWindow.
 */
public class StyleWindow extends JDialog {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The cancel button. */
	private javax.swing.JButton cancelButton;
	
	/** The j combo box1. */
	private javax.swing.JComboBox jComboBox1;
	
	/** The j separator1. */
	private javax.swing.JSeparator jSeparator1;
	
	/** The save button. */
	private javax.swing.JButton saveButton;
	
	/** The style label. */
	private javax.swing.JLabel styleLabel;

	/**
	 * Instantiates a new style window.
	 *
	 * @param parent the parent
	 */
	public StyleWindow(final JFrame parent) {
		super(parent, "Style", true);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		initComponents();
		ComponentLocation.setLocation(parent, this);
		final LookAndFeel oldLaf = UIManager.getLookAndFeel();

		for (int i = 0; i < UIManager.getInstalledLookAndFeels().length; i++)
			jComboBox1.addItem(UIManager.getInstalledLookAndFeels()[i]
					.getName());
		jComboBox1.setSelectedItem(UIManager.getLookAndFeel().getName());

		final JDialog dialog = this;
		jComboBox1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				final int index = ((JComboBox) e.getSource())
						.getSelectedIndex();
				final LookAndFeelInfo laf = UIManager
						.getInstalledLookAndFeels()[index];
				changeLookAndFeel(parent, dialog, laf.getClassName());
			}
		});

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				dialog.setVisible(false);
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				changeLookAndFeel(parent, dialog, oldLaf.getClass().getName());
				dialog.setVisible(false);
			}
		});
		setVisible(true);
	}

	/**
	 * Change look and feel.
	 *
	 * @param parent the parent
	 * @param dialog the dialog
	 * @param laf the laf
	 */
	private void changeLookAndFeel(final JFrame parent, final JDialog dialog,
			final String laf) {
		try {
			UIManager.setLookAndFeel(laf);
			((MainWindow) parent).getConfig().setStyle(laf);
			Config.serialize(((MainWindow) parent).getConfig());
		} catch (final Exception ex) {
			ex.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(parent);
		SwingUtilities.updateComponentTreeUI(dialog);
	}

	/**
	 * Inits the components.
	 */
	private void initComponents() {

		styleLabel = new javax.swing.JLabel();
		jSeparator1 = new javax.swing.JSeparator();
		jComboBox1 = new javax.swing.JComboBox();
		saveButton = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();

		styleLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
		styleLabel.setText("Window style");

		saveButton.setText("Save");

		cancelButton.setText("Cancel");

		final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(styleLabel)
												.addComponent(jComboBox1, 0,
														206, Short.MAX_VALUE)
												.addComponent(
														jSeparator1,
														javax.swing.GroupLayout.Alignment.TRAILING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														206, Short.MAX_VALUE)
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addComponent(
																		saveButton)
																.addGap(18, 18,
																		18)
																.addComponent(
																		cancelButton)))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(styleLabel)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jComboBox1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jSeparator1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										10,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(cancelButton)
												.addComponent(saveButton))
								.addContainerGap()));

		pack();
	}
}
