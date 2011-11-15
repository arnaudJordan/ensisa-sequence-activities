package sequence.ui.window;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import sequence.model.Phases;
import sequence.model.Sequence;
import sequence.ui.component.sequence.SequenceView;

public class InfoWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTabbedPane jTabbedPane;

	public InfoWindow(String title, List<SequenceView> list) throws HeadlessException {
		super(title);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setPreferredSize(new Dimension(300, 300));
		
		jTabbedPane = new JTabbedPane();
		add(jTabbedPane);
		
		for(int i=0; i<list.size();i++)
		{
			Sequence sequence = (Sequence) list.get(i).getModel();
			createTab(sequence);
		}
		
		this.pack();
		this.setVisible(true);
	}

	private void createTab(Sequence sequence) {
		Container pane = new JPanel();
		
		pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		
		Font titleFont = new Font("Courier New", Font.BOLD, 16);
		Label generalInfoLabel = new Label("General information");
		generalInfoLabel.setFont(titleFont);
		pane.add(generalInfoLabel);
		JPanel generalInfo = new JPanel();
		generalInfo.setLayout(new GridLayout(0, 2));
		generalInfo.add(new Label("Number of activities"));
		generalInfo.add(new Label(new Integer(sequence.size()).toString()));
		
		pane.add(generalInfo);
		
		Label patientInfoLabel = new Label("Patient information");
		patientInfoLabel.setFont(titleFont);
		pane.add(patientInfoLabel);
		JPanel patientInfo = new JPanel();
		patientInfo.setLayout(new GridLayout(0,2));
		patientInfo.add(new Label("Age : "));
		patientInfo.add(new Label(new Integer(sequence.getPatient().getAge()).toString()));
		patientInfo.add(new Label("Sex : "));
		patientInfo.add(new Label(sequence.getPatient().getSex().toString()));
		patientInfo.add(new Label("Actuator : "));
		patientInfo.add(new Label(sequence.getPatient().getPosotion().getPosition()));
		patientInfo.add(new Label("Note : "));
		patientInfo.add(new Label(sequence.getPatient().getNote().getNote()));
		pane.add(patientInfo);
		
		Phases phases = sequence.getPhases();
		List<Integer> phasesDuration = sequence.phaseDuration();
		DefaultPieDataset dataset = new DefaultPieDataset();
		for(int i=0 ; i< phases.size() ; i++)
			dataset.setValue(phases.get(i).getName(), phasesDuration.get(i));
		JFreeChart chart = ChartFactory.createPieChart(
	            "Phase duration",  // chart title
	            dataset,             // data
	            false,               // include legend
	            true,
	            false
	        );
		
		PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setCircular(false);
        plot.setLabelGap(0.02);
       
        pane.add(new ChartPanel(chart));
		
		JButton closeButton = new JButton();
		closeButton.setText("Close");
		closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		final JFrame jFrame = this;
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jFrame.setVisible(false);
			}
		});
		
		pane.add(closeButton);
		
		jTabbedPane.addTab(sequence.getWorkflowID(), pane);
	}

}
