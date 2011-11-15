package sequence.ui.window;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import sequence.model.Sequence;
import sequence.parser.SequenceHandler;
import sequence.ui.component.sequence.SequenceView;
import sequence.utilities.Config;


public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;

	public MenuBar(final JFrame parent) {
		super();
		JMenu file = new JMenu("File");

		JMenuItem open = new JMenuItem("Open");
		open.setMnemonic('O');
		open.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.Event.CTRL_MASK));

		final JFileChooser fc = new JFileChooser();

		FileNameExtensionFilter filter= new FileNameExtensionFilter("XML file", "XML", "xml");
		fc.setFileFilter(filter);
		final Config config = ((MainWindow) parent).getConfig();
		if(config.getLastOpenedDirectory() !=null)
			fc.setCurrentDirectory(config.getLastOpenedDirectory());
		
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(parent);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					config.setLastOpenedDirectory(fc.getCurrentDirectory());
					config.addOpenedFile(file);
					try {
						config.serialize();
					}
					catch (java.io.IOException ex) {
						ex.printStackTrace();
					}
					
					try{
						SAXParserFactory factory = SAXParserFactory.newInstance();
						SAXParser parser = factory.newSAXParser();
						File parsedFile = file;
						SequenceHandler sequenceHandler = new SequenceHandler();
						parser.parse(parsedFile, sequenceHandler);
						
						Sequence sequence = sequenceHandler.getSequence();
						((MainWindow) parent).addSequence(new SequenceView(sequence));
					}catch(Exception ex){
						ex.printStackTrace();
						JOptionPane.showMessageDialog(parent, ex.toString(), ex.getClass().toString(), JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		file.add(open);


		JMenuItem save = new JMenuItem("Save As");
		save.setMnemonic('S');
		save.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.Event.CTRL_MASK));
		
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable(){
					public void run(){
						new OpenedFilesSelectWindow(((MainWindow) parent).getSequence(), fc);
					}
				});
			}
		});

		file.add(save);
		
		
		JMenuItem export = new JMenuItem("Export");
		export.setMnemonic('E');
		export.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.Event.CTRL_MASK));
		file.add(export);


		JMenuItem quit = new JMenuItem("Quit");
		quit.setMnemonic('Q');
		quit.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.Event.CTRL_MASK));
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		file.add(quit);

		add(file);
		
		JMenu edit = new JMenu("Edit");
		JMenuItem undo = new JMenuItem("Undo");
		undo.setMnemonic('Z');
		undo.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.Event.CTRL_MASK));

		undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((MainWindow) parent).getProcessor().Undo();
			}
		});
		edit.add(undo);
		JMenuItem redo = new JMenuItem("Undo");
		redo.setMnemonic('Y');
		redo.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.Event.CTRL_MASK));

		redo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((MainWindow) parent).getProcessor().Redo();
			}
		});
		edit.add(redo);
		
		add(edit);
		JMenu help = new JMenu("Help");

		JMenuItem info = new JMenuItem("File Information");
		info.setMnemonic('I');
		info.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.Event.CTRL_MASK));

		info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(((MainWindow) parent).getSequence()!=null)
					EventQueue.invokeLater(new Runnable(){
						public void run(){
							new InfoWindow("Information", ((MainWindow) parent).getSequence());
						}
					});
			}
		});

		help.add(info);
		
		JMenuItem option = new JMenuItem("Option");
		option.setMnemonic('O');

		option.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(((MainWindow) parent).getSequence()!=null)
					EventQueue.invokeLater(new Runnable(){
						public void run(){
							new OptionWindow(parent);
						}
					});
			}
		});

		help.add(option);
		
		add(help);
	}


}