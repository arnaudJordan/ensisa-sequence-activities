package sequence.ui.component.activity.controller;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

import sequence.model.activity.Activity;
import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.processor.command.ColorChange;
import sequence.processor.command.CommandList;
import sequence.ui.component.activity.ActivityRenderingModel;
import sequence.ui.component.activity.ActivityView;
import sequence.ui.window.MainWindow;

/**
 * The Class ActivityMenuColorController.
 */
public class ActivityMenuColorController extends Controller implements
		ActionListener {

	/**
	 * Instantiates a new activity menu color controller.
	 *
	 * @param model the model
	 * @param view the view
	 */
	public ActivityMenuColorController(final Model model, final View view) {
		super(model, view);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(final ActionEvent e) {
		final ActivityRenderingModel renderingModel = ((ActivityRenderingModel) (((ActivityView) getView())
				.getRenderingModel()));
		final Color oldColor = renderingModel.getColor();
		final Color newColor = JColorChooser.showDialog(getView(),
				"Choose a new color", oldColor);
		if (newColor != null && !newColor.equals(oldColor)) {
			final Container parent = getView().getParent();
			if (parent != null) {
				final Activity model = (Activity) getView().getModel();
				final CommandList command = new CommandList();
				for (int i = 0; i < parent.getComponentCount(); i++) {
					final Activity componentModel = (Activity) ((View) parent
							.getComponent(i)).getModel();
					final ActivityRenderingModel componentRenderingModel = ((ActivityRenderingModel) ((View) parent
							.getComponent(i)).getRenderingModel());
					if (componentModel != null
							&& componentRenderingModel != null
							&& componentModel.getAction().equals(
									model.getAction())
							&& componentModel.getTreatedStructure().equals(
									model.getTreatedStructure())
							&& componentModel.getUsedInstrument().equals(
									model.getUsedInstrument())) {
						command.Add(new ColorChange(componentRenderingModel,
								newColor));
					}
					((MainWindow) getView().getTopLevelAncestor())
							.getProcessor().Do(command);
				}
			}
		}
	}
}
