package sequence.ui.component.activity.test;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFrame;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import sequence.model.Sequence;
import sequence.parser.SequenceHandler;
import sequence.ui.component.activity.ActivityController;
import sequence.ui.component.activity.ActivityView;

public class testActivity extends JFrame {

	public testActivity() {
		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();

			SAXParser parser = factory.newSAXParser();

			File parsedFile = new File("src/data.xml");
			SequenceHandler sequenceHandler = new SequenceHandler();
			parser.parse(parsedFile, sequenceHandler);
			
			Sequence model = new Sequence(sequenceHandler.getSequence());
			ActivityView view = new ActivityView(model.getLastActivity());
			ActivityController controller = new ActivityController(model, view);
			
			this.add(view);
			this.pack();
			this.setVisible(true);
			
		}catch(Exception e){
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				new testActivity();
			}
		});
	}
}
