package sequence.ui.utilities;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import sequence.model.Sequence;
import sequence.parser.SequenceHandler;
import sequence.ui.window.MainWindow;
import sequence.utilities.Config;

public class WindowController implements WindowListener {
	private final MainWindow window;

	public WindowController(final MainWindow window) {
		this.window = window;
	}

	@Override
	public void windowOpened(final WindowEvent e) {
		final Config config = window.getConfig();
		final List<File> lastOpenedFiles = config.getLastOpenedFiles();
		for (final File file : lastOpenedFiles) {
			try {
				final SAXParserFactory factory = SAXParserFactory.newInstance();
				final SAXParser parser = factory.newSAXParser();
				final SequenceHandler sequenceHandler = new SequenceHandler(
						file);
				parser.parse(file, sequenceHandler);

				final Sequence sequence = sequenceHandler.getSequence();
				window.add(sequence);
			} catch (final Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void windowClosing(final WindowEvent e) {
		final Config config = window.getConfig();
		config.setWindowSize(window.getSize());
		config.setWindowLocation(window.getLocation());
		config.setExtendedState(window.getExtendedState());
		try {
			Config.serialize(window.getConfig());
		} catch (final IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void windowClosed(final WindowEvent e) {
	}

	@Override
	public void windowIconified(final WindowEvent e) {
	}

	@Override
	public void windowDeiconified(final WindowEvent e) {
	}

	@Override
	public void windowActivated(final WindowEvent e) {
	}

	@Override
	public void windowDeactivated(final WindowEvent e) {
	}

}
