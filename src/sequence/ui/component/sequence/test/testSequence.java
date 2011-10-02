package sequence.ui.component.sequence.test;

import java.io.File;

import javax.swing.JFrame;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import sequence.model.Sequence;
import sequence.parser.SequenceHandler;
import sequence.ui.component.sequence.SequenceView;

public class testSequence extends JFrame {

	public testSequence() {
		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();

			SAXParser parser = factory.newSAXParser();

			File parsedFile = new File("src/data.xml");
			SequenceHandler sequenceHandler = new SequenceHandler();
			parser.parse(parsedFile, sequenceHandler);
			
			Sequence model = new Sequence(sequenceHandler.getSequence());
			SequenceView view = new SequenceView(model);

			this.add(view);
			this.pack();
			this.setVisible(true);
			
		}catch(Exception e){
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		new testSequence();
	}
}
