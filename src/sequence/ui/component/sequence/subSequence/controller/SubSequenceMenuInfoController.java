package sequence.ui.component.sequence.subSequence.controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sequence.model.Sequence;
import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.ui.window.InfoSequenceWindow;

public class SubSequenceMenuInfoController extends Controller implements
		ActionListener {
	public SubSequenceMenuInfoController(final Model model, final View view) {
		super(model, view);
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new InfoSequenceWindow(getView().getTopLevelAncestor(),
						(Sequence) getView().getModel()).setVisible(true);
			}
		});
	}
}
