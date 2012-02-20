package sequence.ui.window;

import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import sequence.model.Sequence;
import sequence.ui.component.sequence.SequenceContainer;
import sequence.ui.utilities.ComponentLocation;
import sequence.utilities.Config;
import sequence.utilities.SVGFactory;
import sequence.utilities.TikzFactory;

public class OpenedFilesSelectWindow extends JDialog {
	private static final long serialVersionUID = 1L;

	private JButton saveButton;
	private JButton cancelButton;
	private JList jList;
	private JScrollPane scrollPane;
	private JFileChooser fc;
	private MainWindow parent;
	private Sequence selectedSequence;

	public OpenedFilesSelectWindow(final List<SequenceContainer> list,
			final MainWindow parent) throws HeadlessException {
		super(parent, "Select file", true);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.parent = parent;
		initComponents();
		initFileChooser();
		ComponentLocation.setLocation(parent, this);

		final DefaultListModel listModel = new DefaultListModel();
		for (final SequenceContainer current : list) {
			listModel.addElement(current.getView().getModel());
		}

		class MyCellRenderer extends DefaultListCellRenderer {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(final JList list,
					final Object value, final int index,
					final boolean isSelected, final boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index,
						isSelected, cellHasFocus);
				if (value instanceof Icon) {
					setIcon((Icon) value);
					setText("");
				} else if (value instanceof Sequence) {
					setIcon(null);
					setText((((Sequence) value).getWorkflowID()));
				} else {
					setIcon(null);
					setText((value == null) ? "" : value.toString());
				}

				return this;
			}
		}
		jList.setCellRenderer(new MyCellRenderer());
		jList.setModel(listModel);

		final JDialog dialog = this;
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				dialog.setVisible(false);
				final int returnVal = fc.showSaveDialog(parent);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					selectedSequence = ((Sequence) jList.getSelectedValue());
					parent.getConfig().setLastOpenedDirectory(
							fc.getCurrentDirectory());
					try {
						Config.serialize(parent.getConfig());
					} catch (final java.io.IOException ex) {
						ex.printStackTrace();
					}
					String ext = "";
					if (f.getName().contains(".")) {
						final int mid = f.getName().lastIndexOf('.') + 1;
						ext = f.getName().substring(mid);
					} else {
						try {
							ext = ((FileNameExtensionFilter) fc.getFileFilter())
									.getExtensions()[0];
						} catch (final Exception ex) {
							ex.printStackTrace();
							ext = "xml";
						}
						final String name = fc.getSelectedFile().getPath()
								+ '.' + ext;
						f = new File(name);
					}
					if (f.exists()) {
						final int response = JOptionPane.showConfirmDialog(fc,
								"The file " + fc.getSelectedFile().getName()
										+ " already exists.\n"
										+ "Do you want to replace it?",
								"Overwrite file", JOptionPane.YES_NO_OPTION,
								JOptionPane.WARNING_MESSAGE);
						if (response == JOptionPane.NO_OPTION)
							return;
					}
					if (tikzExport(f, ext))
						;
					else if (save(f, ext))
						;
					else if (svgExport(f, ext))
						;
					else if (imageExport(ext, f))
						;
					else
						JOptionPane.showMessageDialog(fc,
								"No correct file selected", "Error",
								JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				dialog.setVisible(false);
			}
		});
		setVisible(true);
	}

	private void initFileChooser() {
		fc = new JFileChooser();

		fc.addChoosableFileFilter(new FileNameExtensionFilter("Image File",
				ImageIO.getWriterFormatNames()));
		fc.addChoosableFileFilter(new FileNameExtensionFilter("Tex (Tikz)",
				"tex"));
		fc.addChoosableFileFilter(new FileNameExtensionFilter("Vectorial",
				"svg"));
		fc.addChoosableFileFilter(new FileNameExtensionFilter("XML", "xml"));

		final Config config = parent.getConfig();
		if (config.getLastOpenedDirectory() != null)
			fc.setCurrentDirectory(config.getLastOpenedDirectory());
	}

	private boolean save(final File f, final String ext) {
		if (!ext.equalsIgnoreCase("xml"))
			return false;
		try {
			selectedSequence.toFile(f);
		} catch (final IOException e1) {
			e1.printStackTrace();
		}
		return true;
	}

	private boolean svgExport(final File f, final String ext) {
		if (!ext.equalsIgnoreCase("svg"))
			return false;
		FileWriter fstream;
		try {
			fstream = new FileWriter(f);
			final BufferedWriter out = new BufferedWriter(fstream);
			out.write(SVGFactory.AddHeader(SVGFactory.SequenceToSVG(parent
					.getSequenceContainer(selectedSequence).getView())));
			out.close();
			return true;
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean tikzExport(final File f, final String ext) {
		if (!ext.equalsIgnoreCase("tex"))
			return false;
		try {
			final FileWriter fstream = new FileWriter(f);
			final BufferedWriter out = new BufferedWriter(fstream);
			out.write(TikzFactory.AddHeader(TikzFactory.SequenceToTikz(parent
					.getSequenceContainer(selectedSequence).getView())));
			out.close();
			return true;
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean imageExport(final String ext, final File f) {
		for (int i = 0; i < ImageIO.getWriterFormatNames().length; i++) {
			if (ImageIO.getWriterFormatNames()[i].equals(ext)) {
				try {
					ImageIO.write(parent.getSequenceContainer(selectedSequence)
							.getView().createImage(), ext, f);
					return true;
				} catch (final IOException e) {
					e.printStackTrace();
				}
				return false;
			}
		}
		return false;
	}

	private void initComponents() {

		scrollPane = new javax.swing.JScrollPane();
		jList = new javax.swing.JList();
		saveButton = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();

		jList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(jList);

		saveButton.setText("Save");

		cancelButton.setText("Cancel");

		final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(
														scrollPane,
														javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														100, Short.MAX_VALUE)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		saveButton)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		50,
																		Short.MAX_VALUE)
																.addComponent(
																		cancelButton)))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(scrollPane,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										50, Short.MAX_VALUE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(cancelButton)
												.addComponent(saveButton))
								.addContainerGap()));

		pack();
	}
}
