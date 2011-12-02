package sequence.ui.component.activity.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.ui.component.activity.ActivityRenderer;
import sequence.utilities.BackgroundDrawer;
import sequence.utilities.StripedBackgroundDrawer;

public class ActivityMenuStripedController extends Controller implements
		ActionListener {

	public ActivityMenuStripedController(Model model, View view) {
		super(model, view);
	}

	public void actionPerformed(ActionEvent e) {
		ActivityRenderer activityRenderer = ((ActivityRenderer) getView().getRenderer());
		if(activityRenderer.getBackgroundDrawer() instanceof StripedBackgroundDrawer)
			activityRenderer.setBackgroundDrawer(new BackgroundDrawer());
		else
			activityRenderer.setBackgroundDrawer(new StripedBackgroundDrawer());
		getView().repaint();
	}
}
