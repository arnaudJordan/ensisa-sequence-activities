package sequence.parser.test;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import sequence.parser.SequenceHandler;

public class ParserTest {
	private final static String FILEPATH = "src/data.xml";
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();

			SAXParser parser = factory.newSAXParser();

			File parsedFile = new File(FILEPATH);
			SequenceHandler sequenceHandler = new SequenceHandler();
			parser.parse(parsedFile, sequenceHandler);
			System.out.println( sequenceHandler.getSequence());

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
