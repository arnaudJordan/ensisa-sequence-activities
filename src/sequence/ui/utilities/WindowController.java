package sequence.ui.utilities;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import sequence.model.Sequence;
import sequence.parser.SequenceHandler;
import sequence.ui.window.MainWindow;
import sequence.utilities.Config;


public class WindowController implements WindowListener {
	private MainWindow window;
	
	public WindowController(MainWindow window)
	{
		this.window = window;
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		Config config =this.window.getConfig();
		this.window.setSize(config.getWindowSize());
		this.window.setExtendedState(config.getExtendedState());
		String laf = config.getStyle();
		if(laf!=null)
		try {
			UIManager.setLookAndFeel(laf);
			SwingUtilities.updateComponentTreeUI(window);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		else
		{
			for (UIManager.LookAndFeelInfo lf : UIManager.getInstalledLookAndFeels())
			{
				if ("Nimbus".equals(lf.getName()))
					try
					{
						UIManager.setLookAndFeel(lf.getClassName());
					}
					catch (Exception e1) {}
			}
			SwingUtilities.updateComponentTreeUI(window);
		}
		
		List<File> lastOpenedFiles = config.getLastOpenedFiles();
		for(File file:lastOpenedFiles)
		{
			try{
				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser parser = factory.newSAXParser();
				SequenceHandler sequenceHandler = new SequenceHandler(file);
				parser.parse(file, sequenceHandler);				
				
				Sequence sequence = sequenceHandler.getSequence();
				window.add(sequence);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		Config config = this.window.getConfig();
		config.setWindowSize(this.window.getSize());
		config.setExtendedState(this.window.getExtendedState());
		try {
			Config.serialize(this.window.getConfig());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

}
