package sequence.ui.window;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import sequence.model.Sequence;
import sequence.parser.SequenceHandler;
import sequence.processor.command.BackgroundDrawerChange;
import sequence.ui.component.activity.ActivityRenderer;
import sequence.ui.utilities.drawer.BackgroundDrawer;
import sequence.ui.utilities.drawer.StripedBackgroundDrawer;
import sequence.utilities.Config;
import sequence.utilities.EventDispatcher;


public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	private JMenu windowsMenu;
	private ButtonGroup frameMenuItemGroup;

	public MenuBar(final MainWindow parent) {
		super();
		createFileMenu(parent);
		createEditMenu(parent);
		createOptionMenu(parent);
		createWindowMenu(parent);
		createHelpMenu(parent);
	}

	private void createFileMenu(final MainWindow parent) {
		JMenu file = new JMenu("File");

		JMenuItem open = new JMenuItem("Open");
		open.setMnemonic('O');
		open.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.Event.CTRL_MASK));

		final JFileChooser fc = new JFileChooser();

		FileNameExtensionFilter filter= new FileNameExtensionFilter("XML file", "XML", "xml");
		fc.setFileFilter(filter);
		final Config config = parent.getConfig();
		if(config.getLastOpenedDirectory() !=null)
			fc.setCurrentDirectory(config.getLastOpenedDirectory());
		fc.setMultiSelectionEnabled(true);
		
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(parent);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File[] file = fc.getSelectedFiles();
					for(int i=0; i<file.length; i++)
					{
						config.setLastOpenedDirectory(fc.getCurrentDirectory());
						config.addOpenedFile(file[i]);
						try {
							Config.serialize(config);
						}
						catch (java.io.IOException ex) {
							ex.printStackTrace();
						}
						
						try{
							SAXParserFactory factory = SAXParserFactory.newInstance();
							SAXParser parser = factory.newSAXParser();
							File parsedFile = file[i];
							SequenceHandler sequenceHandler = new SequenceHandler();
							parser.parse(parsedFile, sequenceHandler);
							
							Sequence sequence = sequenceHandler.getSequence();
							parent.add(sequence);
						}catch(Exception ex){
							ex.printStackTrace();
							JOptionPane.showMessageDialog(parent, ex.toString(), ex.getClass().toString(), JOptionPane.ERROR_MESSAGE);
						}
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
						new OpenedFilesSelectWindow(parent.getSequenceContainers(), parent);
					}
				});
			}
		});

		file.add(save);


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
	}

	private void createEditMenu(final MainWindow parent) {
		JMenu edit = new JMenu("Edit");
		JMenuItem undo = new JMenuItem("Undo");
        undo.setMnemonic('Z');
        undo.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.Event.CTRL_MASK));

        undo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	parent.getProcessor().Undo();
                }
        });
        edit.add(undo);
        JMenuItem redo = new JMenuItem("Redo");
        redo.setMnemonic('Y');
        redo.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.Event.CTRL_MASK));

        redo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	parent.getProcessor().Redo();
                }
        });
        edit.add(redo);

		add(edit);
	}

	private void createOptionMenu(final MainWindow parent) {
		JMenu options = new JMenu("Options");
		
		JMenu activitiesStyle = new JMenu("Activities style");
		ButtonGroup activitiesStyleGroup = new ButtonGroup();
		
		JRadioButtonMenuItem full = new JRadioButtonMenuItem("Full");

		full.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(ActivityRenderer.CURRENT_BACKGROUND_DRAWER instanceof BackgroundDrawer))
					parent.getProcessor().Do(new BackgroundDrawerChange(new BackgroundDrawer()));
			}
		});
		full.setSelected(true);
		
		activitiesStyle.add(full);
		activitiesStyleGroup.add(full);
		
		JRadioButtonMenuItem stripped = new JRadioButtonMenuItem("Stripped");

		stripped.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(ActivityRenderer.CURRENT_BACKGROUND_DRAWER instanceof StripedBackgroundDrawer))
					parent.getProcessor().Do(new BackgroundDrawerChange(new StripedBackgroundDrawer()));
			}
		});
		
		activitiesStyle.add(stripped);
		activitiesStyleGroup.add(stripped);

		options.add(activitiesStyle);
		
		JMenuItem windowStyle = new JMenuItem("Window style");

		windowStyle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(parent.getSequenceContainers()!=null)
					EventQueue.invokeLater(new Runnable(){
						public void run(){
							new StyleWindow(parent);
						}
					});
			}
		});

		options.add(windowStyle);
		
		add(options);
	}
	
	private void createWindowMenu(final MainWindow parent) {
		windowsMenu = new JMenu("Windows");
		
		JMenuItem cascade = new JMenuItem("Cascade");
		cascade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.getMainPane().cascadeFrames();
			}
		});
		windowsMenu.add(cascade);
		
		JMenuItem tile = new JMenuItem("Tile");
		tile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.getMainPane().tileFrames();
			}
		});
		windowsMenu.add(tile);
		windowsMenu.addSeparator();
		
		JMenuItem close = new JMenuItem("Close");
		close.setMnemonic('W');
		close.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.Event.CTRL_MASK));

		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrame f = parent.getMainPane().getSelectedFrame();
				if(f!=null)
					f.doDefaultCloseAction();
			}
		});
		windowsMenu.add(close);
		
		JMenuItem closeAll = new JMenuItem("Close all");
		closeAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrame[] f = parent.getMainPane().getAllFrames();
				for(int i=0; i<f.length; i++)
					f[i].doDefaultCloseAction();
			}
		});
		windowsMenu.add(closeAll);
		
		add(windowsMenu);
	}
	
	private void createHelpMenu(final MainWindow parent) {
		JMenu help = new JMenu("Help");

		JMenuItem info = new JMenuItem("File Information");
		info.setMnemonic('I');
		info.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.Event.CTRL_MASK));

		info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(parent.getSequenceContainers()!=null)
					EventQueue.invokeLater(new Runnable(){
						public void run(){
							new InfoWindow(parent.getSequenceContainers()).setVisible(true);
						}
					});
			}
		});

		help.add(info);
		
		add(help);
	}

	
	public void addFrameMenuItem(final JInternalFrame frame) {
		if(frameMenuItemGroup == null) {
			windowsMenu.addSeparator();
			frameMenuItemGroup = new ButtonGroup();
		}
		FrameMenuItem item = new FrameMenuItem(frame);
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					frame.setSelected(true);
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				}
			}
		});
		item.setIcon(frame.getFrameIcon());
		frameMenuItemGroup.add(item);
		windowsMenu.add(item);
	}
	
	public void removeFrameMenuItem(final JInternalFrame frame) {
		Enumeration<AbstractButton> items = frameMenuItemGroup.getElements();
		FrameMenuItem item = null;
		while(items.hasMoreElements()) {
			item = (FrameMenuItem) items.nextElement();
			if(item.getFrame() == frame)
				break;
		}
		frameMenuItemGroup.remove(item);
		windowsMenu.remove(item);
		if(frameMenuItemGroup.getButtonCount() == 0) {
			windowsMenu.remove(windowsMenu.getMenuComponentCount()-1);
			frameMenuItemGroup = null;
		}
	}
	
	public void selectFrameMenuItem(final JInternalFrame frame) {
		Enumeration<AbstractButton> items = frameMenuItemGroup.getElements();
		FrameMenuItem item = null;
		while(items.hasMoreElements()) {
			item = (FrameMenuItem) items.nextElement();
			if(item.getFrame() == frame)
				break;
		}
		frameMenuItemGroup.setSelected(item.getModel(), true);
	}
	
	class FrameMenuItem extends JRadioButtonMenuItem {
		private JInternalFrame frame;

		public FrameMenuItem(JInternalFrame frame) {
			super(frame.getTitle());
			this.frame = frame;
		}

		public JInternalFrame getFrame() {
			return frame;
		}
	}
}