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
		
		try {
			FileInputStream configFile = new FileInputStream("config.ser");
			ObjectInputStream ois = new ObjectInputStream(configFile);
			setConfig((Config) ois.readObject());
			}
			catch (Exception e) {
				setConfig(new Config());
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
			System.out.println(e);
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
