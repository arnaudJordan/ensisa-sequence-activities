package sequence.ui.component.activity.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.processor.command.BackgroundDrawerChange;
import sequence.ui.component.activity.ActivityRenderer;
import sequence.ui.utilities.drawer.BackgroundDrawer;
import sequence.ui.utilities.drawer.StripedBackgroundDrawer;
import sequence.ui.window.MainWindow;

public class ActivityMenuStripedController extends Controller implements
		ActionListener {

	public ActivityMenuStripedController(Model model, View view) {
		super(model, view);
	}

	public void actionPerformed(ActionEvent e) {
		ActivityRenderer activityRenderer = ((ActivityRenderer) getView().getRenderer());

		if(activityRenderer.getBackgroundDrawer() instanceof StripedBackgroundDrawer)
			((MainWindow) getView().getTopLevelAncestor()).getProcessor().Do(new BackgroundDrawerChange(activityRenderer, new BackgroundDrawer()));
		else
			((MainWindow) getView().getTopLevelAncestor()).getProcessor().Do(new BackgroundDrawerChange(activityRenderer, new StripedBackgroundDrawer()));
	}
}
