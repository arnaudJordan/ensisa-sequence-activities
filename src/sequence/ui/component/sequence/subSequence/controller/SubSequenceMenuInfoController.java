package sequence.ui.component.sequence.subSequence.controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sequence.model.Sequence;
import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.ui.window.InfoSequenceWindow;

public class SubSequenceMenuInfoController extends Controller implements ActionListener {
	public SubSequenceMenuInfoController(Model model, View view) {
		super(model, view);
	}

	public void actionPerformed(ActionEvent e) {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				new InfoSequenceWindow((Sequence) getView().getModel()).setVisible(true);
			}
		});
	}
}
