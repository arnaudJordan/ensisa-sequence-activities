package sequence.ui;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.JFrame;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import sequence.model.Sequence;
import sequence.parser.SequenceHandler;
import sequence.ui.component.sequence.SequenceView;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private static Config config;

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

		this.setJMenuBar(new MenuBar());
		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();

			SAXParser parser = factory.newSAXParser();

			File parsedFile = new File("src/data.xml");
			SequenceHandler sequenceHandler = new SequenceHandler();
			parser.parse(parsedFile, sequenceHandler);

			Sequence model = new Sequence(sequenceHandler.getSequence());
			SequenceView view = new SequenceView(model);

			this.add(view);

		}catch(Exception e){
			e.printStackTrace();
		}

		this.pack();
		this.setVisible(true);
	}

	public static Config getConfig() {
		return config;
	}

	public static void setConfig(Config config) {
		MainWindow.config = config;
	}


}
