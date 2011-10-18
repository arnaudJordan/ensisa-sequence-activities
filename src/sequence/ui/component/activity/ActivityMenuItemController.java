package sequence.ui.component.activity;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;

public class ActivityMenuItemController extends Controller implements
		ActionListener {

	public ActivityMenuItemController(Model model, View view) {
		super(model, view);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ActivityRenderingModel renderingModel = ((ActivityRenderingModel)(((ActivityView)getView()).getRenderingModel()));
		Color newColor = JColorChooser.showDialog(getView(), "Choose a new color", renderingModel.getColor());
		if(newColor!= null && newColor != renderingModel.getColor()) {
			renderingModel.setColor(newColor);
			getView().repaint();
		}
	}

}
