package sequence.ui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
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

import sequence.model.Sequence;
import sequence.mvc.Model;
import sequence.ui.component.sequence.SequenceView;

public class InfoWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	public InfoWindow(String title, List<SequenceView> list) throws HeadlessException {
		super(title);
		Sequence sequence = (Sequence) list.get(0).getModel();

		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setPreferredSize(new Dimension(300, 300));
		
		Container pane = this.getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		
		pane.add(new Label("General information"));
		JPanel generalInfo = new JPanel();
		generalInfo.setLayout(new GridLayout(0, 2));
		generalInfo.add(new Label("Number of activities"));
		generalInfo.add(new Label(new Integer(list.size()).toString()));
		
		pane.add(generalInfo);
		
		pane.add(new Label("Patient information"));
		JPanel patientInfo = new JPanel();
		patientInfo.setLayout(new GridLayout(0,2));
		patientInfo.add(new Label("Age : "));
		patientInfo.add(new Label(new Integer(sequence.getPatient().getAge()).toString()));
		patientInfo.add(new Label("Sex : "));
		patientInfo.add(new Label(sequence.getPatient().getSex().toString()));
		patientInfo.add(new Label("Actuator : "));
		patientInfo.add(new Label(sequence.getPatient().getActuator().getPosition()));
		patientInfo.add(new Label("Note : "));
		patientInfo.add(new Label(sequence.getPatient().getNote().getNote()));
		
		pane.add(patientInfo);
		
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
		this.pack();
		this.setVisible(true);
	}

}
