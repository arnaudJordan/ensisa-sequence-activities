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

import sequence.utilities.Config;

public class StyleWindow extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel styleLabel;

	public StyleWindow(final JFrame parent)
	{
		super(parent, "Style", true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		initComponents();
		final LookAndFeel oldLaf = UIManager.getLookAndFeel();

		for(int i=0; i< UIManager.getInstalledLookAndFeels().length; i++)
			jComboBox1.addItem(UIManager.getInstalledLookAndFeels()[i].getName());
		jComboBox1.setSelectedItem(UIManager.getLookAndFeel().getName());
		
		final JDialog dialog = this;
		jComboBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = ((JComboBox)e.getSource()).getSelectedIndex();
				LookAndFeelInfo laf =UIManager.getInstalledLookAndFeels()[index];
				changeLookAndFeel(parent, dialog, laf.getClassName());
			}
		});
		
		
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeLookAndFeel(parent, dialog, oldLaf.getClass().getName());
				dialog.setVisible(false);
			}
		});
		
		this.setVisible(true);
	}
	private void changeLookAndFeel(final JFrame parent,
			final JDialog dialog, String laf) {
		try {
			UIManager.setLookAndFeel(laf);
			((MainWindow) parent).getConfig().setStyle(laf);
			Config.serialize(((MainWindow) parent).getConfig());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(parent);
		SwingUtilities.updateComponentTreeUI(dialog);
	}
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(styleLabel)
                    .addComponent(jComboBox1, 0, 206, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(saveButton)
                        .addGap(18, 18, 18)
                        .addComponent(cancelButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(styleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(saveButton))
                .addContainerGap())
        );

        pack();
    }
}
