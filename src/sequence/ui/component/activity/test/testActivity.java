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
	private static final long serialVersionUID = 1L;

	public testActivity() {
		try {
			final SAXParserFactory factory = SAXParserFactory.newInstance();

			final SAXParser parser = factory.newSAXParser();

			final File parsedFile = new File("src/data.xml");
			final SequenceHandler sequenceHandler = new SequenceHandler();
			parser.parse(parsedFile, sequenceHandler);

			final Sequence model = new Sequence(sequenceHandler.getSequence());
			final ActivityView view = new ActivityView(model.getLastActivity());
			new ActivityController(model, view);

			this.add(view);
			pack();
			setVisible(true);

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new testActivity();
			}
		});
	}
}
