package sequence.ui.component.timeIndicator.test;

import java.awt.EventQueue;

import javax.swing.JFrame;

import sequence.model.Phase;
import sequence.ui.component.timeIndicator.TimeIndicatorView;

public class testTimeIndicator extends JFrame {
	private static final long serialVersionUID = 1L;

	public testTimeIndicator() {
		final Phase model = new Phase(10);
		model.setStopTime(200);
		final TimeIndicatorView view = new TimeIndicatorView(model);

		this.add(view);
		pack();
		setVisible(true);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new testTimeIndicator();
			}
		});
	}
}
