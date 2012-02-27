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
import sequence.ui.utilities.drawer.FullBackgroundDrawer;
import sequence.ui.utilities.drawer.StripedBackgroundDrawer;
import sequence.utilities.Config;

/**
 * The Class MenuBar.
 */
public class MenuBar extends JMenuBar {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The windows menu. */
	private JMenu windowsMenu;
	
	/** The frame menu item group. */
	private ButtonGroup frameMenuItemGroup;

	/**
	 * Instantiates a new menu bar.
	 *
	 * @param parent the parent
	 */
	public MenuBar(final MainWindow parent) {
		super();
		createFileMenu(parent);
		createEditMenu(parent);
		createOptionMenu(parent);
		createWindowMenu(parent);
		createHelpMenu(parent);
	}

	/**
	 * Creates the file menu.
	 *
	 * @param parent the parent
	 */
	private void createFileMenu(final MainWindow parent) {
		final JMenu file = new JMenu("File");

		final JMenuItem open = new JMenuItem("Open");
		open.setMnemonic('O');
		open.setAccelerator(KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_O, java.awt.Event.CTRL_MASK));

		final JFileChooser fc = new JFileChooser();

		final FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"XML file", "XML", "xml");
		fc.setFileFilter(filter);
		final Config config = parent.getConfig();
		if (config.getLastOpenedDirectory() != null)
			fc.setCurrentDirectory(config.getLastOpenedDirectory());
		fc.setMultiSelectionEnabled(true);

		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				final int returnVal = fc.showOpenDialog(parent);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					final File[] file = fc.getSelectedFiles();
					for (int i = 0; i < file.length; i++) {
						config.setLastOpenedDirectory(fc.getCurrentDirectory());
						config.addOpenedFile(file[i]);
						try {
							Config.serialize(config);
						} catch (final java.io.IOException ex) {
							ex.printStackTrace();
						}

						try {
							final SAXParserFactory factory = SAXParserFactory
									.newInstance();
							final SAXParser parser = factory.newSAXParser();
							final File parsedFile = file[i];
							final SequenceHandler sequenceHandler = new SequenceHandler();
							parser.parse(parsedFile, sequenceHandler);

							final Sequence sequence = sequenceHandler
									.getSequence();
							parent.add(sequence);
						} catch (final Exception ex) {
							ex.printStackTrace();
							JOptionPane.showMessageDialog(parent,
									ex.toString(), ex.getClass().toString(),
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});

		file.add(open);

		final JMenuItem save = new JMenuItem("Save As");
		save.setMnemonic('S');
		save.setAccelerator(KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_S, java.awt.Event.CTRL_MASK));

		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						new OpenedFilesSelectWindow(parent
								.getSequenceContainers(), parent);
					}
				});
			}
		});

		file.add(save);

		final JMenuItem quit = new JMenuItem("Quit");
		quit.setMnemonic('Q');
		quit.setAccelerator(KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_Q, java.awt.Event.CTRL_MASK));
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				System.exit(0);
			}
		});
		file.add(quit);

		add(file);
	}

	/**
	 * Creates the edit menu.
	 *
	 * @param parent the parent
	 */
	private void createEditMenu(final MainWindow parent) {
		final JMenu edit = new JMenu("Edit");
		final JMenuItem undo = new JMenuItem("Undo");
		undo.setMnemonic('Z');
		undo.setAccelerator(KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_Z, java.awt.Event.CTRL_MASK));

		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				parent.getProcessor().Undo();
			}
		});
		edit.add(undo);
		final JMenuItem redo = new JMenuItem("Redo");
		redo.setMnemonic('Y');
		redo.setAccelerator(KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_Y, java.awt.Event.CTRL_MASK));

		redo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				parent.getProcessor().Redo();
			}
		});
		edit.add(redo);

		add(edit);
	}

	/**
	 * Creates the option menu.
	 *
	 * @param parent the parent
	 */
	private void createOptionMenu(final MainWindow parent) {
		final JMenu options = new JMenu("Options");

		final JMenu activitiesStyle = new JMenu("Activities style");
		final ButtonGroup activitiesStyleGroup = new ButtonGroup();

		final JRadioButtonMenuItem full = new JRadioButtonMenuItem("Full");

		full.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (!(ActivityRenderer.CURRENT_BACKGROUND_DRAWER instanceof FullBackgroundDrawer))
					parent.getProcessor().Do(
							new BackgroundDrawerChange(
									new FullBackgroundDrawer()));
			}
		});
		full.setSelected(true);

		activitiesStyle.add(full);
		activitiesStyleGroup.add(full);

		final JRadioButtonMenuItem stripped = new JRadioButtonMenuItem(
				"Stripped");

		stripped.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (!(ActivityRenderer.CURRENT_BACKGROUND_DRAWER instanceof StripedBackgroundDrawer))
					parent.getProcessor().Do(
							new BackgroundDrawerChange(
									new StripedBackgroundDrawer()));
			}
		});

		activitiesStyle.add(stripped);
		activitiesStyleGroup.add(stripped);

		options.add(activitiesStyle);

		final JMenu threshold = new JMenu("Minimal duration threshold");
		threshold.add(parent.getThresholdField());

		options.add(threshold);

		final JMenu zoom = new JMenu("Zoom");
		zoom.add(parent.getScaleSlider());

		options.add(zoom);

		final JMenuItem windowStyle = new JMenuItem("Window style");

		windowStyle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (parent.getSequenceContainers() != null)
					EventQueue.invokeLater(new Runnable() {
						@Override
						public void run() {
							new StyleWindow(parent);
						}
					});
			}
		});

		options.add(windowStyle);

		add(options);
	}

	/**
	 * Creates the window menu.
	 *
	 * @param parent the parent
	 */
	private void createWindowMenu(final MainWindow parent) {
		windowsMenu = new JMenu("Windows");

		final JMenuItem cascade = new JMenuItem("Cascade");
		cascade.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				parent.getMainPane().cascadeFrames();
			}
		});
		windowsMenu.add(cascade);

		final JMenuItem tile = new JMenuItem("Tile");
		tile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				parent.getMainPane().tileFrames();
			}
		});
		windowsMenu.add(tile);
		windowsMenu.addSeparator();

		final JMenuItem close = new JMenuItem("Close");
		close.setMnemonic('W');
		close.setAccelerator(KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_W, java.awt.Event.CTRL_MASK));

		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				final JInternalFrame f = parent.getMainPane()
						.getSelectedFrame();
				if (f != null)
					f.doDefaultCloseAction();
			}
		});
		windowsMenu.add(close);

		final JMenuItem closeAll = new JMenuItem("Close all");
		closeAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				final JInternalFrame[] f = parent.getMainPane().getAllFrames();
				for (int i = 0; i < f.length; i++)
					f[i].doDefaultCloseAction();
			}
		});
		windowsMenu.add(closeAll);

		add(windowsMenu);
	}

	/**
	 * Creates the help menu.
	 *
	 * @param parent the parent
	 */
	private void createHelpMenu(final MainWindow parent) {
		final JMenu help = new JMenu("Help");

		final JMenuItem info = new JMenuItem("File Information");
		info.setMnemonic('I');
		info.setAccelerator(KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_I, java.awt.Event.CTRL_MASK));

		info.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (parent.getSequenceContainers() != null)
					EventQueue.invokeLater(new Runnable() {
						@Override
						public void run() {
							new InfoWindow(parent).setVisible(true);
						}
					});
			}
		});

		help.add(info);

		add(help);
	}

	/**
	 * Adds the frame menu item.
	 *
	 * @param frame the frame
	 */
	public void addFrameMenuItem(final JInternalFrame frame) {
		if (frameMenuItemGroup == null) {
			windowsMenu.addSeparator();
			frameMenuItemGroup = new ButtonGroup();
		}
		final FrameMenuItem item = new FrameMenuItem(frame);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent ae) {
				try {
					frame.setSelected(true);
				} catch (final PropertyVetoException e) {
					e.printStackTrace();
				}
			}
		});
		item.setIcon(frame.getFrameIcon());
		frameMenuItemGroup.add(item);
		windowsMenu.add(item);
	}

	/**
	 * Removes the frame menu item.
	 *
	 * @param frame the frame
	 */
	public void removeFrameMenuItem(final JInternalFrame frame) {
		final Enumeration<AbstractButton> items = frameMenuItemGroup
				.getElements();
		FrameMenuItem item = null;
		while (items.hasMoreElements()) {
			item = (FrameMenuItem) items.nextElement();
			if (item.getFrame() == frame)
				break;
		}
		frameMenuItemGroup.remove(item);
		windowsMenu.remove(item);
		if (frameMenuItemGroup.getButtonCount() == 0) {
			windowsMenu.remove(windowsMenu.getMenuComponentCount() - 1);
			frameMenuItemGroup = null;
		}
	}

	/**
	 * Select frame menu item.
	 *
	 * @param frame the frame
	 */
	public void selectFrameMenuItem(final JInternalFrame frame) {
		final Enumeration<AbstractButton> items = frameMenuItemGroup
				.getElements();
		FrameMenuItem item = null;
		while (items.hasMoreElements()) {
			item = (FrameMenuItem) items.nextElement();
			if (item.getFrame() == frame)
				break;
		}
		frameMenuItemGroup.setSelected(item.getModel(), true);
	}

	/**
	 * The Class FrameMenuItem.
	 */
	class FrameMenuItem extends JRadioButtonMenuItem {
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;
		
		/** The frame. */
		private final JInternalFrame frame;

		/**
		 * Instantiates a new frame menu item.
		 *
		 * @param frame the frame
		 */
		public FrameMenuItem(final JInternalFrame frame) {
			super(frame.getTitle());
			this.frame = frame;
		}

		/**
		 * Gets the frame.
		 *
		 * @return the frame
		 */
		public JInternalFrame getFrame() {
			return frame;
		}
	}
}