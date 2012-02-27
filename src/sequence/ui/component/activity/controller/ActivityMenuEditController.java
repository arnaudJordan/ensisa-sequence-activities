package sequence.ui.component.activity.controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sequence.model.Sequence;
import sequence.model.activity.Activity;
import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.ui.component.sequence.subSequence.SubSequenceView;
import sequence.ui.window.EditActivityWindow;
import sequence.ui.window.MainWindow;

/**
 * The Class ActivityMenuEditController.
 */
public class ActivityMenuEditController extends Controller implements
		ActionListener {

	/**
	 * Instantiates a new activity menu edit controller.
	 *
	 * @param model the model
	 * @param view the view
	 */
	public ActivityMenuEditController(final Model model, final View view) {
		super(model, view);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(final ActionEvent e) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new EditActivityWindow((MainWindow) getView()
						.getTopLevelAncestor(),
						(Activity) getView().getModel(),
						(Sequence) ((SubSequenceView) getView().getParent())
								.getModel()).setVisible(true);
			}
		});
	}
}
