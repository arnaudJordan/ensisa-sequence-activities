package sequence.ui;

import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import sequence.model.Sequence;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private Config config;
	private Sequence sequence;

	public MainWindow(String title) throws HeadlessException {
		super(title);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		this.setPreferredSize(new Dimension(800, 600));
		setConfig(new Config());
		try {
			setConfig(getConfig().deserialize());
		}catch(Exception e){
			e.printStackTrace();
		}
		this.setJMenuBar(new MenuBar(this));

		this.pack();
		this.setVisible(true);
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public Sequence getSequence() {
		return sequence;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}


}
