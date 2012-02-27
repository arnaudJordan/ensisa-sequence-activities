package sequence.ui.component.timeLine.test;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import sequence.model.Phases;
import sequence.model.Sequence;
import sequence.parser.SequenceHandler;
import sequence.ui.component.timeLine.TimeLineView;

/**
 * The Class testTimeLine.
 */
public class testTimeLine extends JFrame {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new test time line.
	 */
	public testTimeLine() {
		final SAXParserFactory factory = SAXParserFactory.newInstance();

		SAXParser parser;
		try {
			parser = factory.newSAXParser();
			final File parsedFile = new File(
					"C:\\Documents and Settings\\voegtlin\\Bureau\\data.xml");
			final SequenceHandler sequenceHandler = new SequenceHandler();
			parser.parse(parsedFile, sequenceHandler);

			final Sequence sequence = sequenceHandler.getSequence();
			final Phases model = sequence.getPhases();
			final TimeLineView view = new TimeLineView(model);

			this.add(view);
			pack();
			setVisible(true);
		} catch (final ParserConfigurationException e) {
			e.printStackTrace();
		} catch (final SAXException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new testTimeLine();
			}
		});
	}
}
