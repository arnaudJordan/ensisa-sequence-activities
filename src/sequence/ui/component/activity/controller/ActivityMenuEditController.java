package sequence.ui.component.activity.controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sequence.model.Sequence;
import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.ui.component.activity.ActivityView;
import sequence.ui.component.sequence.subSequence.SubSequenceView;
import sequence.ui.window.EditActivityWindow;
import sequence.ui.window.MainWindow;

public class ActivityMenuEditController extends Controller implements
		ActionListener {

	public ActivityMenuEditController(final Model model, final View view) {
		super(model, view);
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new EditActivityWindow((MainWindow) getView()
						.getTopLevelAncestor(), (ActivityView) getView(),
						(Sequence) ((SubSequenceView) getView().getParent())
								.getModel()).setVisible(true);
			}
		});
	}
}
