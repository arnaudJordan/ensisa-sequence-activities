/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InfoActivityWindow.java
 *
 * Created on 22 nov. 2011, 10:07:28
 */
package sequence.ui.window;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JDialog;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import sequence.model.Sequence;
import sequence.model.activity.Action;
import sequence.model.activity.Activity;
import sequence.model.activity.AnatomicStructure;

/**
 *
 * @author jordan
 */
public class InfoSequenceWindow extends javax.swing.JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Creates new form InfoActivityWindow */
	public InfoSequenceWindow() {
		setModalityType(DEFAULT_MODALITY_TYPE);
		initComponents();
	}
	public InfoSequenceWindow(Sequence sequence)
	{
		this();
		setSize(600, 550);
		id.setText(sequence.getWorkflowID());
		completeDuration.setText(Integer.toString(sequence.completeDuration()));
		workDuration.setText(Integer.toString(sequence.completeWorkDuration()));
		activityNumber.setText(Integer.toString(sequence.activityNumber()));
		meanActivityDuration.setText(Integer.toString(sequence.meanActivityDuration()));
		int[] numbers = sequence.ActionStructureInstrumentNumber();
		actionNumber.setText(Integer.toString(numbers[0]));
		structureNumber.setText(Integer.toString(numbers[1]));
		instrumentNumber.setText(Integer.toString(numbers[2]));

		
		graphicsPane.add(createActionChart(sequence));
		graphicsPane.add(createZoneChart(sequence));
		

		final JDialog dialog = this;
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
			}
		});
	}
	private ChartPanel createActionChart(Sequence sequence) {
		Map<Action, Integer> activityMap = new HashMap<Action, Integer>();
		for(Activity activity : sequence)
		{
			if (activityMap.containsKey(activity.getAction()))
			{
				int duration = ((Integer)activityMap.get(activity.getAction()));
				activityMap.put(activity.getAction(), duration+activity.getActivitytime().getDuration());
			}
			else
			{
				activityMap.put(activity.getAction(), activity.getActivitytime().getDuration());
			}
		}
		
        Collection<Integer> values = activityMap.values();
        ArrayList<Integer> valuesList = new ArrayList<Integer>(values);
        Collections.sort(valuesList);
		Set<Action> keys = activityMap.keySet();
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		Iterator<Action> it = keys.iterator();
		while (it.hasNext()){
			Action cle = (Action)it.next();
			int valeur = (Integer)activityMap.get(cle);
			dataset.setValue(cle.getAction(), valeur);
		}

		JFreeChart chart = ChartFactory.createPieChart(
				"Actions",		// chart title
				dataset,		// data
				false,			// include legend
				true,
				false
				);

		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		plot.setCircular(false);
		plot.setLabelGap(0.02);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(500, 200));
		return chartPanel;
	}
	private ChartPanel createZoneChart(Sequence sequence) {
		Map<AnatomicStructure, Integer> activityMap = new HashMap<AnatomicStructure, Integer>();
		for(Activity activity : sequence)
		{
			if (activityMap.containsKey(activity.getTreatedStructure()))
			{
				int duration = ((Integer)activityMap.get(activity.getAction()));
				activityMap.put(activity.getTreatedStructure(), duration+activity.getActivitytime().getDuration());
			}
			else
			{
				activityMap.put(activity.getTreatedStructure(), activity.getActivitytime().getDuration());
			}
		}
		
        Collection<Integer> values = activityMap.values();
        ArrayList<Integer> valuesList = new ArrayList<Integer>(values);
        Collections.sort(valuesList);
		Set<AnatomicStructure> keys = activityMap.keySet();
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		Iterator<AnatomicStructure> it = keys.iterator();
		while (it.hasNext()){
			AnatomicStructure cle = (AnatomicStructure)it.next();
			int valeur = (Integer)activityMap.get(cle);
			dataset.setValue(cle.getAnatomicStructure(), valeur);
		}

		JFreeChart chart = ChartFactory.createPieChart(
				"Anatomic structure",		// chart title
				dataset,		// data
				false,			// include legend
				true,
				false
				);

		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		plot.setCircular(false);
		plot.setLabelGap(0.02);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(500, 200));
		return chartPanel;
	}
	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		okButton = new javax.swing.JButton();
		title = new javax.swing.JLabel();
		id = new javax.swing.JLabel();
		jTabbedPane1 = new javax.swing.JTabbedPane();
		jPanel1 = new javax.swing.JPanel();
		jlabel5 = new javax.swing.JLabel();
		workDuration = new javax.swing.JLabel();
		structureNumber = new javax.swing.JLabel();
		instrumentNumber = new javax.swing.JLabel();
		meanActivityDuration = new javax.swing.JLabel();
		actionNumber = new javax.swing.JLabel();
		completeDuration = new javax.swing.JLabel();
		activityNumber = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		graphicsPane = new javax.swing.JPanel();

		setTitle("Sequence information");

		okButton.setText("OK");
		okButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				okButtonActionPerformed(evt);
			}
		});

		title.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		title.setText("Informartion about sequence");

		id.setText("id");

		jlabel5.setText("Total work duration");

		workDuration.setText("workDuration");

		structureNumber.setText("structureNumber");

		instrumentNumber.setText("instrumentNumber");

		meanActivityDuration.setText("meanActivityDuration");

		actionNumber.setText("actionNumber");

		completeDuration.setText("completeDuration");

		activityNumber.setText("activityNumber");

		jLabel4.setText("Different action number");

		jLabel3.setText("Mean activities duration");

		jLabel2.setText("Total duration");

		jLabel1.setText("Number of activities");

		jLabel6.setText("Different instrument number");

		jLabel5.setText("Different structure number");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel2)
												.addComponent(jlabel5))
												.addGap(186, 186, 186)
												.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(workDuration)
														.addComponent(completeDuration)))
														.addGroup(jPanel1Layout.createSequentialGroup()
																.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(jLabel4)
																		.addComponent(jLabel3)
																		.addComponent(jLabel1)
																		.addComponent(jLabel5)
																		.addComponent(jLabel6))
																		.addGap(123, 123, 123)
																		.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(instrumentNumber)
																				.addComponent(structureNumber)
																				.addComponent(actionNumber)
																				.addComponent(meanActivityDuration)
																				.addComponent(activityNumber))))
																				.addContainerGap(61, Short.MAX_VALUE))
				);
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addComponent(jLabel2)
										.addGap(18, 18, 18)
										.addComponent(jlabel5))
										.addGroup(jPanel1Layout.createSequentialGroup()
												.addComponent(completeDuration)
												.addGap(18, 18, 18)
												.addComponent(workDuration)))
												.addGap(18, 18, 18)
												.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(activityNumber))
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(jLabel3)
																.addComponent(meanActivityDuration))
																.addGap(18, 18, 18)
																.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(jLabel4)
																		.addComponent(actionNumber))
																		.addGap(18, 18, 18)
																		.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(jLabel5)
																				.addComponent(structureNumber))
																				.addGap(18, 18, 18)
																				.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(jLabel6)
																						.addComponent(instrumentNumber))
																						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);

		jTabbedPane1.addTab("Data", jPanel1);
		jTabbedPane1.addTab("Graphics", graphicsPane);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(okButton)
								.addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
										.addComponent(title)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(id)))
										.addContainerGap())
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(title)
								.addComponent(id))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jTabbedPane1)
								.addGap(18, 18, 18)
								.addComponent(okButton)
								.addContainerGap())
				);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
		// TODO add your handling code here:
	}//GEN-LAST:event_okButtonActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new InfoSequenceWindow().setVisible(true);
			}
		});
	}
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel actionNumber;
	private javax.swing.JLabel activityNumber;
	private javax.swing.JLabel completeDuration;
	private javax.swing.JPanel graphicsPane;
	private javax.swing.JLabel id;
	private javax.swing.JLabel instrumentNumber;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JLabel jlabel5;
	private javax.swing.JLabel meanActivityDuration;
	private javax.swing.JButton okButton;
	private javax.swing.JLabel structureNumber;
	private javax.swing.JLabel title;
	private javax.swing.JLabel workDuration;
	// End of variables declaration//GEN-END:variables
}
