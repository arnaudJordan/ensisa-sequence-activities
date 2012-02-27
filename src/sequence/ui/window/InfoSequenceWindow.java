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

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
import sequence.ui.utilities.ComponentLocation;

/**
 * The Class InfoSequenceWindow.
 *
 * @author jordan
 */
public class InfoSequenceWindow extends javax.swing.JDialog {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates new form InfoActivityWindow.
	 */
	public InfoSequenceWindow() {
		setModalityType(DEFAULT_MODALITY_TYPE);
		initComponents();
	}

	/**
	 * Instantiates a new info sequence window.
	 *
	 * @param parent the parent
	 * @param sequence the sequence
	 */
	public InfoSequenceWindow(final Container parent, final Sequence sequence) {
		this();
		setSize(600, 550);
		ComponentLocation.setLocation(parent, this);

		id.setText(sequence.getWorkflowID());
		completeDuration.setText(Integer.toString(sequence.completeDuration()));
		workDuration.setText(Integer.toString(sequence.completeWorkDuration()));
		activityNumber.setText(Integer.toString(sequence.activityNumber()));
		meanActivityDuration.setText(Integer.toString(sequence
				.meanActivityDuration()));
		final int[] numbers = sequence.ActionStructureInstrumentNumber();
		actionNumber.setText(Integer.toString(numbers[0]));
		structureNumber.setText(Integer.toString(numbers[1]));
		instrumentNumber.setText(Integer.toString(numbers[2]));

		graphicsPane.add(createActionChart(sequence));
		graphicsPane.add(createZoneChart(sequence));

		final JDialog dialog = this;
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				dialog.setVisible(false);
			}
		});
	}

	/**
	 * Creates the action chart.
	 *
	 * @param sequence the sequence
	 * @return the chart panel
	 */
	private ChartPanel createActionChart(final Sequence sequence) {
		final Map<Action, Integer> activityMap = new HashMap<Action, Integer>();
		for (final Activity activity : sequence) {
			if (activityMap.containsKey(activity.getAction())) {
				final int duration = (activityMap.get(activity.getAction()));
				activityMap.put(activity.getAction(), duration
						+ activity.getActivitytime().getDuration());
			} else {
				activityMap.put(activity.getAction(), activity
						.getActivitytime().getDuration());
			}
		}

		final Collection<Integer> values = activityMap.values();
		final ArrayList<Integer> valuesList = new ArrayList<Integer>(values);
		Collections.sort(valuesList);
		final Set<Action> keys = activityMap.keySet();
		final DefaultPieDataset dataset = new DefaultPieDataset();

		final Iterator<Action> it = keys.iterator();
		while (it.hasNext()) {
			final Action cle = it.next();
			final int valeur = activityMap.get(cle);
			dataset.setValue(cle.getAction(), valeur);
		}

		final JFreeChart chart = ChartFactory.createPieChart("Actions", // chart
																		// title
				dataset, // data
				false, // include legend
				true, false);

		final PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		plot.setCircular(false);
		plot.setLabelGap(0.02);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(500, 200));
		return chartPanel;
	}

	/**
	 * Creates the zone chart.
	 *
	 * @param sequence the sequence
	 * @return the chart panel
	 */
	private ChartPanel createZoneChart(final Sequence sequence) {
		final Map<AnatomicStructure, Integer> activityMap = new HashMap<AnatomicStructure, Integer>();
		for (final Activity activity : sequence) {
			if (activityMap.containsKey(activity.getTreatedStructure())) {
				final int duration = (activityMap.get(activity.getAction()));
				activityMap.put(activity.getTreatedStructure(), duration
						+ activity.getActivitytime().getDuration());
			} else {
				activityMap.put(activity.getTreatedStructure(), activity
						.getActivitytime().getDuration());
			}
		}

		final Collection<Integer> values = activityMap.values();
		final ArrayList<Integer> valuesList = new ArrayList<Integer>(values);
		Collections.sort(valuesList);
		final Set<AnatomicStructure> keys = activityMap.keySet();
		final DefaultPieDataset dataset = new DefaultPieDataset();

		final Iterator<AnatomicStructure> it = keys.iterator();
		while (it.hasNext()) {
			final AnatomicStructure cle = it.next();
			final int valeur = activityMap.get(cle);
			dataset.setValue(cle.getAnatomicStructure(), valeur);
		}

		final JFreeChart chart = ChartFactory.createPieChart(
				"Anatomic structure", // chart title
				dataset, // data
				false, // include legend
				true, false);

		final PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		plot.setCircular(false);
		plot.setLabelGap(0.02);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(500, 200));
		return chartPanel;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
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
			@Override
			public void actionPerformed(final java.awt.event.ActionEvent evt) {
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

		final javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel2)
																						.addComponent(
																								jlabel5))
																		.addGap(186,
																				186,
																				186)
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								workDuration)
																						.addComponent(
																								completeDuration)))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel4)
																						.addComponent(
																								jLabel3)
																						.addComponent(
																								jLabel1)
																						.addComponent(
																								jLabel5)
																						.addComponent(
																								jLabel6))
																		.addGap(123,
																				123,
																				123)
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								instrumentNumber)
																						.addComponent(
																								structureNumber)
																						.addComponent(
																								actionNumber)
																						.addComponent(
																								meanActivityDuration)
																						.addComponent(
																								activityNumber))))
										.addContainerGap(61, Short.MAX_VALUE)));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel2)
																		.addGap(18,
																				18,
																				18)
																		.addComponent(
																				jlabel5))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				completeDuration)
																		.addGap(18,
																				18,
																				18)
																		.addComponent(
																				workDuration)))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(
																activityNumber))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel3)
														.addComponent(
																meanActivityDuration))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel4)
														.addComponent(
																actionNumber))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel5)
														.addComponent(
																structureNumber))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel6)
														.addComponent(
																instrumentNumber))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jTabbedPane1.addTab("Data", jPanel1);
		jTabbedPane1.addTab("Graphics", graphicsPane);

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
														jTabbedPane1,
														javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(okButton)
												.addGroup(
														javax.swing.GroupLayout.Alignment.LEADING,
														layout.createSequentialGroup()
																.addComponent(
																		title)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(
																		id)))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(title)
												.addComponent(id))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jTabbedPane1).addGap(18, 18, 18)
								.addComponent(okButton).addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	/**
	 * Ok button action performed.
	 *
	 * @param evt the evt
	 */
	private void okButtonActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_okButtonActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_okButtonActionPerformed

	/**
	 * The main method.
	 *
	 * @param args the command line arguments
	 */
	public static void main(final String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				new InfoSequenceWindow().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	/** The action number. */
	private javax.swing.JLabel actionNumber;
	
	/** The activity number. */
	private javax.swing.JLabel activityNumber;
	
	/** The complete duration. */
	private javax.swing.JLabel completeDuration;
	
	/** The graphics pane. */
	private javax.swing.JPanel graphicsPane;
	
	/** The id. */
	private javax.swing.JLabel id;
	
	/** The instrument number. */
	private javax.swing.JLabel instrumentNumber;
	
	/** The j label1. */
	private javax.swing.JLabel jLabel1;
	
	/** The j label2. */
	private javax.swing.JLabel jLabel2;
	
	/** The j label3. */
	private javax.swing.JLabel jLabel3;
	
	/** The j label4. */
	private javax.swing.JLabel jLabel4;
	
	/** The j label5. */
	private javax.swing.JLabel jLabel5;
	
	/** The j label6. */
	private javax.swing.JLabel jLabel6;
	
	/** The j panel1. */
	private javax.swing.JPanel jPanel1;
	
	/** The j tabbed pane1. */
	private javax.swing.JTabbedPane jTabbedPane1;
	
	/** The jlabel5. */
	private javax.swing.JLabel jlabel5;
	
	/** The mean activity duration. */
	private javax.swing.JLabel meanActivityDuration;
	
	/** The ok button. */
	private javax.swing.JButton okButton;
	
	/** The structure number. */
	private javax.swing.JLabel structureNumber;
	
	/** The title. */
	private javax.swing.JLabel title;
	
	/** The work duration. */
	private javax.swing.JLabel workDuration;
	// End of variables declaration//GEN-END:variables
}
