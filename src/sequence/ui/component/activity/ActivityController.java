package sequence.ui.component.activity;

import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JColorChooser;
import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;

public class ActivityController extends Controller {

	public ActivityController(Model model, View view) {
		super(model, view);
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			ActivityRenderingModel renderingModel = ((ActivityRenderingModel)(((ActivityView)getView()).getRenderingModel()));
			Color newColor = JColorChooser.showDialog(getView(), "Choose a new color", renderingModel.getColor());
			if(newColor!= null && newColor != renderingModel.getColor()) {
				renderingModel.setColor(newColor);
				getView().repaint();
			}
		}
	}
}
