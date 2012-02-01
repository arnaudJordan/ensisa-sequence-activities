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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;


import sequence.model.Sequence;
import sequence.ui.component.sequence.SequenceContainer;
import sequence.utilities.Config;
import sequence.utilities.SVGFactory;
import sequence.utilities.TikzFactory;

public class OpenedFilesSelectWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
    private JButton saveButton;
    private JButton cancelButton;
    private JList jList;
    private JScrollPane scrollPane;
	private JFileChooser fc;
	private MainWindow parent;
	private Sequence selectedSequence;

	public OpenedFilesSelectWindow(List<SequenceContainer> list, final MainWindow parent) throws HeadlessException {
		super("Select file");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.parent=parent;
		initComponents();
		initFileChooser();
		
		DefaultListModel listModel = new DefaultListModel();
		for(SequenceContainer current : list)
		{
			listModel.addElement(current.getView().getModel());
		}
		
		class MyCellRenderer extends DefaultListCellRenderer{
			private static final long serialVersionUID = 1L;
			public Component getListCellRendererComponent(
			        JList list,
			        Object value,
			        int index,
			        boolean isSelected,
			        boolean cellHasFocus)
			    {
					super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			        if (value instanceof Icon) {
			            setIcon((Icon)value);
			            setText("");
			        }
			        else if (value instanceof Sequence) {
			            setIcon(null);
			            setText((((Sequence) value).getWorkflowID()));
			        }
			        else {
			            setIcon(null);
			            setText((value == null) ? "" : value.toString());
			        }

			        return this;
			    }
		 }
		jList.setCellRenderer(new MyCellRenderer());
		jList.setModel(listModel);
		
		final JFrame jFrame = this;
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jFrame.setVisible(false);
				int returnVal = fc.showSaveDialog(parent);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					selectedSequence=((Sequence) jList.getSelectedValue());
					parent.getConfig().setLastOpenedDirectory(fc.getCurrentDirectory());
					try {
						Config.serialize(parent.getConfig());
					}
					catch (java.io.IOException ex) {
						ex.printStackTrace();
					}
					String ext= "";
					if(f.getName().contains("."))
					{
						int mid = f.getName().lastIndexOf('.') + 1;
						ext = f.getName().substring(mid);
					}
					else
					{
						try
						{
							ext = ((FileNameExtensionFilter) fc.getFileFilter()).getExtensions()[0];
						}
						catch(Exception ex)
						{
							ex.printStackTrace();
							ext = "xml";
						}
						String name = fc.getSelectedFile().getPath()+'.'+ext;
						f = new File(name);
					}
					if(f.exists()) {
						int response = JOptionPane.showConfirmDialog(
								fc,
								"The file " + fc.getSelectedFile().getName() + " already exists.\n" +
								"Do you want to replace it?",
								"Overwrite file",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.WARNING_MESSAGE
						);
						if (response == JOptionPane.NO_OPTION)
							return;
					}
					if(tikzExport(f, ext));
					else if(save(f, ext));
					else if(svgExport(f, ext));
					else if(imageExport(ext, f)); 				
					else
						JOptionPane.showMessageDialog(fc, "No correct file selected", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jFrame.setVisible(false);
			}
		});
		this.setVisible(true);
	}
	
	private void initFileChooser() {
		this.fc = new JFileChooser();
		
		fc.addChoosableFileFilter(new FileNameExtensionFilter("Image File", ImageIO.getWriterFormatNames()));
		fc.addChoosableFileFilter(new FileNameExtensionFilter("Tex (Tikz)", "tex"));
		fc.addChoosableFileFilter(new FileNameExtensionFilter("Vectorial", "svg"));
		fc.addChoosableFileFilter(new FileNameExtensionFilter("XML", "xml"));
		
		Config config = parent.getConfig();
		if(config.getLastOpenedDirectory() !=null)
			fc.setCurrentDirectory(config.getLastOpenedDirectory());
	}
	
	private boolean save(File f, String ext) {
		if(!ext.equalsIgnoreCase("xml")) return false;
		try {
			selectedSequence.toFile(f);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return true;
	}

	private boolean svgExport(File f, String ext) {
		if(!ext.equalsIgnoreCase("svg")) return false;
		FileWriter fstream;
		try {
			fstream = new FileWriter(f);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(SVGFactory.AddHeader(SVGFactory.SequenceToSVG(parent.getSequenceContainers(selectedSequence))));
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean tikzExport(File f, String ext) {
		if(!ext.equalsIgnoreCase("tex")) return false;
		try {
			FileWriter fstream = new FileWriter(f);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(TikzFactory.AddHeader(TikzFactory.SequenceToTikz(parent.getSequenceContainers(selectedSequence))));
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean imageExport(String ext, File f)
	{
		for(int i=0; i<ImageIO.getWriterFormatNames().length; i++)
		{
			if(ImageIO.getWriterFormatNames()[i].equals(ext))
			{
				try {
					ImageIO.write(parent.getSequenceContainers(selectedSequence).createImage(), ext, f);
					return true;
				} catch (IOException e) {
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(saveButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        pack();
    }
}
