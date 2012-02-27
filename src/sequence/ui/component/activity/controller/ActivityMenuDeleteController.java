package sequence.ui.component.activity.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import sequence.model.Sequence;
import sequence.model.activity.Activity;
import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.processor.command.RemoveActivity;
import sequence.ui.window.MainWindow;

/**
 * The Class ActivityMenuDeleteController.
 */
public class ActivityMenuDeleteController extends Controller implements
		ActionListener {

	/**
	 * Instantiates a new activity menu delete controller.
	 *
	 * @param model the model
	 * @param view the view
	 */
	public ActivityMenuDeleteController(final Model model, final View view) {
		super(model, view);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(final ActionEvent e) {
		final int response = JOptionPane.showConfirmDialog(getView(),
				"Are you sur you want to delete this activity?",
				"Delete activity", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE);
		if (response == JOptionPane.NO_OPTION)
			return;
		((MainWindow) getView().getTopLevelAncestor()).getProcessor().Do(
				new RemoveActivity((Activity) getModel(),
						(Sequence) ((View) getView().getParent()).getModel()));
	}
}
