package sequence.parser.test;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import sequence.parser.SequenceHandler;

/**
 * The Class ParserTest.
 */
public class ParserTest {
	
	/** The Constant FILEPATH. */
	private final static String FILEPATH = "src/data.xml";

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws SAXException the sAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(final String[] args)
			throws ParserConfigurationException, SAXException, IOException {
		try {
			final SAXParserFactory factory = SAXParserFactory.newInstance();

			final SAXParser parser = factory.newSAXParser();

			final File parsedFile = new File(FILEPATH);
			final SequenceHandler sequenceHandler = new SequenceHandler();
			parser.parse(parsedFile, sequenceHandler);
			System.out.println(sequenceHandler.getSequence());

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
