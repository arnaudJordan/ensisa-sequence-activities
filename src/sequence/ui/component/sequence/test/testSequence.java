package sequence.ui.component.sequence.test;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFrame;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import sequence.model.Sequence;
import sequence.parser.SequenceHandler;
import sequence.ui.component.sequence.summarizedSequence.SummarizedSequenceView;

/**
 * The Class testSequence.
 */
public class testSequence extends JFrame {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new test sequence.
	 */
	public testSequence() {
		try {
			final SAXParserFactory factory = SAXParserFactory.newInstance();

			final SAXParser parser = factory.newSAXParser();

			final File parsedFile = new File("src/data.xml");
			final SequenceHandler sequenceHandler = new SequenceHandler();
			parser.parse(parsedFile, sequenceHandler);

			final Sequence model = new Sequence(sequenceHandler.getSequence());
			final SummarizedSequenceView view = new SummarizedSequenceView(
					model);

			this.add(view);
			pack();
			setVisible(true);

		} catch (final Exception e) {
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
				new testSequence();
			}
		});
	}
}
