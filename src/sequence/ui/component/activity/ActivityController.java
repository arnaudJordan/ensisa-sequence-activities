package sequence.ui.component.activity;

import java.awt.Color;
import java.awt.event.MouseEvent;

import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;

public class ActivityController extends Controller {

	public ActivityController(Model model, View view) {
		super(model, view);
	}

	public void mouseClicked(MouseEvent e) {
		((ActivityRenderingModel)(((ActivityView)getView()).getRenderingModel())).setColor(Color.BLUE);
		getView().repaint();
	}
}
