package sequence.ui.window;

import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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

import sequence.model.Sequence;
import sequence.ui.component.sequence.SequenceView;

public class OpenedFilesSelectWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
    private JButton saveButton;
    private JButton cancelButton;
    private JList jList;
    private JScrollPane scrollPane;

	public OpenedFilesSelectWindow(List<SequenceView> list, final JFileChooser fc) throws HeadlessException {
		super("Select file");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		initComponents();
		
		DefaultListModel listModel = new DefaultListModel();
		for(int i=0; i<list.size();i++)
		{
			listModel.addElement(list.get(i).getModel());
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
				int returnVal = fc.showSaveDialog(jFrame.getParent());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					try {
						((Sequence) jList.getSelectedValue()).toFile(file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
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
