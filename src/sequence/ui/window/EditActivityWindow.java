/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EditActivityWindow.java
 *
 * Created on 18 nov. 2011, 09:59:18
 */
package sequence.ui.window;

import com.jidesoft.swing.AutoCompletion;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sequence.model.Actuator;
import sequence.model.Note;
import sequence.model.Sequence;
import sequence.model.activity.Action;
import sequence.model.activity.Activity;
import sequence.processor.Processor;
import sequence.processor.command.ActivityChange;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import sequence.model.Position;
import sequence.model.activity.AnatomicStructure;
import sequence.model.activity.BodyPart;
import sequence.model.activity.Instrument;
import sequence.model.activity.UsedInstruments;
import sequence.ui.utilities.ComponentLocation;
import sequence.ui.utilities.ListLayout;
/**
 *
 * @author jordan
 */
public class EditActivityWindow extends javax.swing.JDialog {

    /** Creates new form EditActivityWindow */
    public EditActivityWindow() {
    	setModalityType(DEFAULT_MODALITY_TYPE);
        initComponents();
    }
    public EditActivityWindow(final MainWindow mainWindow, final Activity activity, final Sequence sequence) {
    	this(); ;
    	ComponentLocation.setLocation(mainWindow, this);
    	
        id.setText(Integer.toString(activity.getId()));
        DefaultComboBoxModel stateModel = new DefaultComboBoxModel(sequence.states());
        stateEdit.setModel(stateModel);
        stateEdit.setSelectedIndex(stateModel.getIndexOf(activity.getState()));
        
        DefaultComboBoxModel disciplineModel = new DefaultComboBoxModel(sequence.disciplines());
        disciplineEdit.setModel(disciplineModel);
        disciplineEdit.setSelectedIndex(disciplineModel.getIndexOf(activity.getDiscipline()));
        
        DefaultComboBoxModel typeModel = new DefaultComboBoxModel(sequence.types());
        typeEdit.setModel(typeModel);
        typeEdit.setSelectedIndex(typeModel.getIndexOf(activity.getType()));
        
        DefaultComboBoxModel positionModel = new DefaultComboBoxModel(sequence.Positions());
        positionEdit.setModel(positionModel);
        positionEdit.setSelectedIndex(positionModel.getIndexOf(activity.getActuator().getPosition()));
        positionEdit.setEditable(true);
        
        DefaultComboBoxModel bodyPartModel = new DefaultComboBoxModel(sequence.BodyParts());
        bodyPartEdit.setModel(bodyPartModel);
        bodyPartEdit.setSelectedIndex(bodyPartModel.getIndexOf(activity.getActuator().getUsedbodypart()));
        bodyPartEdit.setEditable(true);
        final Object[] asi = (Object[]) sequence.ActionsStructuresInstruments();
        DefaultComboBoxModel actionModel = new DefaultComboBoxModel((Object[])asi[0]);
        actionEdit.setModel(actionModel);
        actionEdit.setSelectedItem(activity.getAction());
        actionEdit.setEditable(true);

        AutoCompletion ac = new AutoCompletion(actionEdit);
        ac.setStrict(false);
        
        DefaultComboBoxModel anatomicStructureModel = new DefaultComboBoxModel((Object[])asi[1]);
        anatomicStructureEdit.setModel(anatomicStructureModel);
        anatomicStructureEdit.setSelectedItem(activity.getTreatedStructure());
        anatomicStructureEdit.setEditable(true);

        AutoCompletion as = new AutoCompletion(anatomicStructureEdit);
        as.setStrict(false);

        usedInstrumentList.setLayout(new ListLayout());
        for(Instrument current : activity.getUsedInstrument())
        {
            final JPanel instrumentPanel = new JPanel();
            final DefaultComboBoxModel instrumentModel = new DefaultComboBoxModel((Object[])asi[2]);
            JComboBox currentEdit = new JComboBox(instrumentModel);
            currentEdit.setSelectedItem(current);
            currentEdit.setEditable(true);
            
            instrumentPanel.add(currentEdit);
            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    usedInstrumentList.remove(instrumentPanel);
                    usedInstrumentList.revalidate();
                    usedInstrumentList.repaint();
                }
            });
            instrumentPanel.add(closeButton);
            usedInstrumentList.add(instrumentPanel);
        }
        JButton addInstrumentButton = new JButton("Add Instrument");
        addInstrumentButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                final JPanel instrumentPanel = new JPanel();
                final DefaultComboBoxModel instrumentModel = new DefaultComboBoxModel((Object[])asi[2]);
                JComboBox currentEdit = new JComboBox(instrumentModel);
                currentEdit.setEditable(true);
            
                instrumentPanel.add(currentEdit);
                JButton closeButton = new JButton("Remove");
                closeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    usedInstrumentList.remove(instrumentPanel);
                    usedInstrumentList.revalidate();
                    usedInstrumentList.repaint();
                 }
                });
                instrumentPanel.add(closeButton);
                usedInstrumentList.add(instrumentPanel);
            }
        });
        usedInstrumentList.add(addInstrumentButton);
        startTimeEdit.setText(Integer.toString(activity.getActivitytime().getStartTime()));
        endTimeEdit.setText(Integer.toString(activity.getActivitytime().getStopTime()));
        durationEdit.setText(Integer.toString(activity.getActivitytime().getDuration()));
        noteText.setText(activity.getNote().getNote());
        
        final JDialog dialog = this;
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        });
        
        final Processor proc = mainWindow.getProcessor();
        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Activity newActivity = new Activity(activity.getId(), stateEdit.getSelectedItem().toString(), Integer.parseInt(disciplineEdit.getSelectedItem().toString()), Integer.parseInt(typeEdit.getSelectedItem().toString()));
                newActivity.setAction(new Action(actionEdit.getSelectedItem().toString()));
                Actuator newActuator = new Actuator();
                if(positionEdit.getSelectedItem() != null)
                    newActuator.setPosition(new Position(positionEdit.getSelectedItem().toString()));
                if(bodyPartEdit.getSelectedItem() != null)
                    newActuator.setUsedbodypart(new BodyPart(bodyPartEdit.getSelectedItem().toString()));
                UsedInstruments newUsedInstrument = new UsedInstruments();

                for(int i=0; i<usedInstrumentList.getComponentCount(); i++)
                {
                    Component current = usedInstrumentList.getComponent(i);
                    if(current instanceof JPanel)
                    {
                        JPanel panel = (JPanel) current;
                        for(int j=0; j<panel.getComponentCount(); j++)
                        {
                            Component curre = panel.getComponent(j);
                            if(curre instanceof JComboBox)
                            {
                                JComboBox box=(JComboBox) curre;
                                newUsedInstrument.addInstrument(new Instrument(box.getSelectedItem().toString()));
                            }
                        }
                    }
                }
                newActivity.setUsedInstrument(newUsedInstrument);
                
                newActivity.setActuator(newActuator);
                newActivity.setNote(new Note(noteText.getText()));
                newActivity.setTreatedStructure(new AnatomicStructure(anatomicStructureEdit.getSelectedItem().toString()));
                newActivity.getActivitytime().setDuration(Integer.parseInt(durationEdit.getText()));
                newActivity.getActivitytime().setStartTime(Integer.parseInt(startTimeEdit.getText()));
                newActivity.getActivitytime().setStopTime(Integer.parseInt(startTimeEdit.getText()));
                proc.Do(new ActivityChange(activity, newActivity));
                mainWindow.repaint();
                dialog.setVisible(false);
            }
        });
        pack();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        title = new javax.swing.JLabel();
        state = new javax.swing.JLabel();
        discipline = new javax.swing.JLabel();
        type = new javax.swing.JLabel();
        stateEdit = new javax.swing.JComboBox();
        disciplineEdit = new javax.swing.JComboBox();
        typeEdit = new javax.swing.JComboBox();
        actuator = new javax.swing.JLabel();
        action = new javax.swing.JLabel();
        usedInstruments = new javax.swing.JLabel();
        anatomicStructure = new javax.swing.JLabel();
        note = new javax.swing.JLabel();
        noteEdit = new javax.swing.JScrollPane();
        noteText = new javax.swing.JTextArea();
        startTime = new javax.swing.JLabel();
        duration = new javax.swing.JLabel();
        endTime = new javax.swing.JLabel();
        startTimeEdit = new javax.swing.JTextField();
        durationEdit = new javax.swing.JTextField();
        endTimeEdit = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        id = new javax.swing.JLabel();
        actionEdit = new javax.swing.JComboBox();
        positionEdit = new javax.swing.JComboBox();
        bodyPartEdit = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        usedInstrumentList = new javax.swing.JPanel();
        anatomicStructureEdit = new javax.swing.JComboBox();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setTitle("Edit Activity");
        setAlwaysOnTop(true);

        title.setFont(new java.awt.Font("Tahoma", 1, 18));
        title.setText("Activity Edition");

        state.setText("State");

        discipline.setText("Discipline");

        type.setText("Type");

        stateEdit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        disciplineEdit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        typeEdit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        actuator.setText("Actuator");

        action.setText("Action");

        usedInstruments.setText("Used Instruments");

        anatomicStructure.setText("AnatomicStructure");

        note.setText("Note");

        noteText.setColumns(20);
        noteText.setRows(5);
        noteText.setText("note");
        noteEdit.setViewportView(noteText);

        startTime.setText("Start time");

        duration.setText("Duration");

        endTime.setText("End time");

        startTimeEdit.setText("startTime");

        durationEdit.setText("duration");

        endTimeEdit.setText("endTime");

        saveButton.setText("OK");

        cancelButton.setText("Cancel");

        id.setText("ID");

        actionEdit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        positionEdit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        bodyPartEdit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jScrollPane1.setHorizontalScrollBar(null);

        javax.swing.GroupLayout usedInstrumentListLayout = new javax.swing.GroupLayout(usedInstrumentList);
        usedInstrumentList.setLayout(usedInstrumentListLayout);
        usedInstrumentListLayout.setHorizontalGroup(
            usedInstrumentListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 384, Short.MAX_VALUE)
        );
        usedInstrumentListLayout.setVerticalGroup(
            usedInstrumentListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(usedInstrumentList);

        anatomicStructureEdit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(title)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 399, Short.MAX_VALUE)
                        .addComponent(id))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(state)
                            .addComponent(discipline)
                            .addComponent(type)
                            .addComponent(actuator)
                            .addComponent(action)
                            .addComponent(usedInstruments))
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                            .addComponent(actionEdit, 0, 387, Short.MAX_VALUE)
                            .addComponent(bodyPartEdit, 0, 387, Short.MAX_VALUE)
                            .addComponent(stateEdit, 0, 387, Short.MAX_VALUE)
                            .addComponent(disciplineEdit, javax.swing.GroupLayout.Alignment.LEADING, 0, 387, Short.MAX_VALUE)
                            .addComponent(typeEdit, javax.swing.GroupLayout.Alignment.LEADING, 0, 387, Short.MAX_VALUE)
                            .addComponent(positionEdit, javax.swing.GroupLayout.Alignment.LEADING, 0, 387, Short.MAX_VALUE)))
                    .addComponent(noteEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(startTime)
                            .addComponent(duration)
                            .addComponent(endTime))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(endTimeEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                            .addComponent(durationEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                            .addComponent(startTimeEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(saveButton)
                        .addGap(18, 18, 18)
                        .addComponent(cancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(anatomicStructure)
                            .addComponent(note))
                        .addGap(52, 52, 52)
                        .addComponent(anatomicStructureEdit, 0, 385, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(title)
                    .addComponent(id))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(state)
                    .addComponent(stateEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(discipline)
                    .addComponent(disciplineEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(type)
                    .addComponent(typeEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(actuator)
                    .addComponent(positionEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(bodyPartEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(action)
                    .addComponent(actionEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usedInstruments)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(anatomicStructure)
                        .addGap(18, 18, 18)
                        .addComponent(note))
                    .addComponent(anatomicStructureEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(noteEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startTime)
                    .addComponent(startTimeEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(duration)
                    .addComponent(durationEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(endTime)
                    .addComponent(endTimeEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(saveButton))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new EditActivityWindow().setVisible(true);
			}
		});
	}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel action;
    private javax.swing.JComboBox actionEdit;
    private javax.swing.JLabel actuator;
    private javax.swing.JLabel anatomicStructure;
    private javax.swing.JComboBox anatomicStructureEdit;
    private javax.swing.JComboBox bodyPartEdit;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel discipline;
    private javax.swing.JComboBox disciplineEdit;
    private javax.swing.JLabel duration;
    private javax.swing.JTextField durationEdit;
    private javax.swing.JLabel endTime;
    private javax.swing.JTextField endTimeEdit;
    private javax.swing.JLabel id;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel note;
    private javax.swing.JScrollPane noteEdit;
    private javax.swing.JTextArea noteText;
    private javax.swing.JComboBox positionEdit;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel startTime;
    private javax.swing.JTextField startTimeEdit;
    private javax.swing.JLabel state;
    private javax.swing.JComboBox stateEdit;
    private javax.swing.JLabel title;
    private javax.swing.JLabel type;
    private javax.swing.JComboBox typeEdit;
    private javax.swing.JPanel usedInstrumentList;
    private javax.swing.JLabel usedInstruments;
    // End of variables declaration//GEN-END:variables
}
