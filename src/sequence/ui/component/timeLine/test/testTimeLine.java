package sequence.ui.component.timeLine.test;

import java.awt.EventQueue;
import javax.swing.JFrame;

import sequence.model.Phase;
import sequence.model.Phases;
import sequence.ui.component.timeLine.TimeLineView;

public class testTimeLine extends JFrame {
	private static final long serialVersionUID = 1L;

	public testTimeLine() {
			
			Phases model = new Phases();
			Phase phase = new Phase(10);
			phase.setStopTime(20);
			model.add(phase);
			phase = new Phase(30);
			phase.setStopTime(60);
			model.add(phase);
			
			TimeLineView view = new TimeLineView(model);

			this.add(view);
			this.pack();
			this.setVisible(true);

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				new testTimeLine();
			}
		});
	}
}
