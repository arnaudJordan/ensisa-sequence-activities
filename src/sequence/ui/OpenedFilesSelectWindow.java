package sequence.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import sequence.model.Sequence;
import sequence.ui.component.sequence.SequenceView;

public class OpenedFilesSelectWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	public OpenedFilesSelectWindow(List<SequenceView> list, final JFileChooser fc) throws HeadlessException {
		super("Select file");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		DefaultListModel listModel = new DefaultListModel();
		for(int i=0; i<list.size();i++)
		{
			listModel.addElement(list.get(i).getModel());
		}
		
		final JList jlist = new JList(listModel);
		jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlist.setLayoutOrientation(JList.VERTICAL_WRAP);
		jlist.setVisibleRowCount(-1);
		class MyCellRenderer extends DefaultListCellRenderer{
			/**
			 * 
			 */
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
		jlist.setCellRenderer(new MyCellRenderer());
		JScrollPane listScroller = new JScrollPane(jlist);
		
		add(listScroller, BorderLayout.CENTER);
		
		JButton closeButton = new JButton();
		closeButton.setText("Select");
		closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		final JFrame jFrame = this;
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jFrame.setVisible(false);
				int returnVal = fc.showSaveDialog(jFrame.getParent());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					try {
						FileWriter fw = new FileWriter(file);
						fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?><iccas xmlns=\"http://www.iccas.de/projects/workflow\">");
						fw.write(((Sequence) jlist.getSelectedValue()).toXML());
						fw.write("</iccas>");
						fw.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		add(closeButton, BorderLayout.SOUTH);
		
		this.pack();
		this.setVisible(true);
	}

}
