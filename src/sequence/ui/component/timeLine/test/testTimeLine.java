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

public class testTimeLine extends JFrame {
	private static final long serialVersionUID = 1L;

	public testTimeLine() {
		SAXParserFactory factory = SAXParserFactory.newInstance();

		SAXParser parser;
		try {
			parser = factory.newSAXParser();
			File parsedFile = new File("src/data.xml");
			SequenceHandler sequenceHandler = new SequenceHandler();
			parser.parse(parsedFile, sequenceHandler);

			Sequence sequence = sequenceHandler.getSequence();
			Phases model = sequence.getPhases();
			System.out.println(model);
			TimeLineView view = new TimeLineView(model);

			this.add(view);
			this.pack();
			this.setVisible(true);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				new testTimeLine();
			}
		});
	}
}
