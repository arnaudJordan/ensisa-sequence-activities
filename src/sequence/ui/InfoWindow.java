package sequence.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import sequence.model.Sequence;

public class InfoWindow extends JFrame {

	public InfoWindow(String title, Sequence sequence) throws HeadlessException {
		super(title);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(300, 300));

		this.setLayout(new GridLayout(0,2));
		this.add(new Label("General information"));
		this.add(new Label(""));
		this.add(new Label("Nomber of activity"));
		this.add(new Label(new Integer(sequence.size()).toString()));
		this.add(new Label("Patient information"));
		this.add(new Label(""));
		this.add(new Label("Age : "));
		this.add(new Label(new Integer(sequence.getPatient().getAge()).toString()));
		this.add(new Label("Sex : "));
		this.add(new Label(sequence.getPatient().getSex().toString()));
		this.add(new Label("Actuator : "));
		this.add(new Label(sequence.getPatient().getActuator().getPosition()));
		this.add(new Label("Note : "));
		this.add(new Label(sequence.getPatient().getNote().getNote()));
		JButton closeButton = new JButton();
		closeButton.setText("Close");
		
		final JFrame jFrame = this;
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jFrame.setVisible(false);
			}
		});

		this.add(closeButton);
		this.pack();
		this.setVisible(true);
	}

}
