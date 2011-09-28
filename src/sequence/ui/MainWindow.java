package sequence.ui;

import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	public MainWindow(String title) throws HeadlessException {
		super(title);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setPreferredSize(new Dimension(800, 600));
		this.setJMenuBar(new MenuBar());
		
		this.pack();
	    this.setVisible(true);
	}


}
