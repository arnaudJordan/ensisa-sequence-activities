package sequence.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import sequence.parser.SequenceHandler;


public class MenuBar extends JMenuBar {

	public MenuBar() {
		super();
		JMenu file = new JMenu("File");
		
		JMenuItem open = new JMenuItem("Open");
		
		final JFileChooser fc = new JFileChooser();
		final Container parent = getParent();
		
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(parent);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            try{
		    			SAXParserFactory factory = SAXParserFactory.newInstance();

		    			SAXParser parser = factory.newSAXParser();

		    			File parsedFile = file;
		    			SequenceHandler sequenceHandler = new SequenceHandler();
		    			parser.parse(parsedFile, sequenceHandler);
		    			JOptionPane.showMessageDialog(parent, "Succes : " + sequenceHandler.getSequence().size() + " activities loaded", "File loaded", JOptionPane.INFORMATION_MESSAGE);

		    		}catch(Exception ex){
		    			JOptionPane.showMessageDialog(parent, ex.toString(), ex.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		    		}
		            
		        }
			}
		});
		
		file.add(open);
		
		file.add(new JMenuItem("Export"));
		
		
		JMenuItem quit = new JMenuItem("Quit");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		file.add(quit);
		
		add(file);
		JMenu edit = new JMenu("Edit");
		add(edit);
	}
	

}
