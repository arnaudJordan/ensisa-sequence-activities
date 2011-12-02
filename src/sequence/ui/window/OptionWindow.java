package sequence.ui.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import sequence.utilities.Config;

public class OptionWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel styleLabel;

	public OptionWindow(final JFrame parent)
	{
		super("Option");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		initComponents();
		
		for(int i=0; i< UIManager.getInstalledLookAndFeels().length; i++)
			jComboBox1.addItem(UIManager.getInstalledLookAndFeels()[i].getName());
		final JFrame jFrame = this;
		jComboBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = ((JComboBox)e.getSource()).getSelectedIndex();
				LookAndFeelInfo laf =UIManager.getInstalledLookAndFeels()[index];
				try {
					UIManager.setLookAndFeel(laf.getClassName());
					((MainWindow) parent).getConfig().setStyle(laf.getClassName());
					Config.serialize(((MainWindow) parent).getConfig());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				SwingUtilities.updateComponentTreeUI(parent);
				SwingUtilities.updateComponentTreeUI(jFrame);
			}
		});
		
		
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jFrame.setVisible(false);
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jFrame.setVisible(false);
			}
		});
		
		this.setVisible(true);
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
                        .addComponent(cancelButton)
                        .addGap(18, 18, 18)
                        .addComponent(saveButton)))
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
                    .addComponent(saveButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        pack();
    }
}
