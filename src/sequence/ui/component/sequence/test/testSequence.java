package sequence.ui.component.sequence.test;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFrame;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import sequence.model.Sequence;
import sequence.parser.SequenceHandler;
import sequence.ui.component.sequence.summarizedSequence.SummarizedSequenceView;

public class testSequence extends JFrame {
	private static final long serialVersionUID = 1L;

	public testSequence() {
		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();

			SAXParser parser = factory.newSAXParser();

			File parsedFile = new File("src/data.xml");
			SequenceHandler sequenceHandler = new SequenceHandler();
			parser.parse(parsedFile, sequenceHandler);
			
			Sequence model = new Sequence(sequenceHandler.getSequence());
			SummarizedSequenceView view = new SummarizedSequenceView(model);

			this.add(view);
			this.pack();
			this.setVisible(true);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run(){
				new testSequence();
			}
		});
	}
}
