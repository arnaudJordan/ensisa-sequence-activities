package sequence.ui;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
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


public class MenuBar extends JMenuBar {
	private Sequence sequence;

        public MenuBar() {
                super();
                JMenu file = new JMenu("File");
                
                JMenuItem open = new JMenuItem("Open");
                open.setMnemonic('O');
                open.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.Event.CTRL_MASK));
                
                final JFileChooser fc = new JFileChooser();
                
                FileNameExtensionFilter filter= new FileNameExtensionFilter("XML file", "XML", "xml");
                fc.setFileFilter(filter);
                final Container parent = getParent();
                sequence=null;
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
                                        sequence=sequenceHandler.getSequence();
                                        JOptionPane.showMessageDialog(parent, "Succes : " + sequenceHandler.getSequence().size() + " activities loaded", "File loaded", JOptionPane.INFORMATION_MESSAGE);

                                }catch(Exception ex){
                                        JOptionPane.showMessageDialog(parent, ex.toString(), ex.getClass().toString(), JOptionPane.ERROR_MESSAGE);
                                }
                            
                        }
                        }
                });
                
                file.add(open);
                
                
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
                add(edit);
                JMenu help = new JMenu("Help");
                
                JMenuItem info = new JMenuItem("File Information");
                info.setMnemonic('I');
                info.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.Event.CTRL_MASK));
                
                info.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                        	if(sequence!=null)
                        		EventQueue.invokeLater(new Runnable(){
                    			public void run(){
                    				new InfoWindow("Information", sequence);
                    			}
                    		});
                        }
                });
                
                help.add(info);
                
                add(help);
        }
        

}