package sequence.ui.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class OptionWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	public OptionWindow(final JFrame parent)
	{
		super("Option");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		JComboBox comboBox = new JComboBox();
		for(int i=0; i< UIManager.getInstalledLookAndFeels().length; i++)
			comboBox.addItem(UIManager.getInstalledLookAndFeels()[i].getName());
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = ((JComboBox)e.getSource()).getSelectedIndex();
				LookAndFeelInfo laf =UIManager.getInstalledLookAndFeels()[index];
				try {
					UIManager.setLookAndFeel(laf.getClassName());
					((MainWindow) parent).getConfig().setStyle(laf.getClassName());
					((MainWindow) parent).getConfig().serialize();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				SwingUtilities.updateComponentTreeUI(parent);
			}
		});
		add(comboBox);
		
		this.pack();
		this.setVisible(true);
	}

}
