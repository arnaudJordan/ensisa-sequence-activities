package sequence.ui;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import sequence.model.Sequence;
import sequence.parser.SequenceHandler;
import sequence.ui.component.sequence.SequenceView;

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
		this.init();
		this.pack();
		this.setVisible(true);
	}
	public void init()
	{
		File lastOpenedFile = getConfig().getLastOpenedFile();
		if(lastOpenedFile==null)
			return;
		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			SequenceHandler sequenceHandler = new SequenceHandler();
			parser.parse(lastOpenedFile, sequenceHandler);
			
			Sequence sequence = sequenceHandler.getSequence();
            SequenceView view = new SequenceView(sequence);
            add(view);
            setSequence(sequence);

		}catch(Exception ex){
			ex.printStackTrace();
		}
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
