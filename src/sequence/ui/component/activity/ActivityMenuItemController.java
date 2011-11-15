package sequence.ui.component.activity;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

import sequence.model.activity.Activity;
import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.processor.ColorChange;
import sequence.ui.window.MainWindow;

public class ActivityMenuItemController extends Controller implements
		ActionListener {

	public ActivityMenuItemController(Model model, View view) {
		super(model, view);
	}

	public void actionPerformed(ActionEvent e) {
		ActivityRenderingModel renderingModel = ((ActivityRenderingModel)(((ActivityView)getView()).getRenderingModel()));
		Color oldColor = renderingModel.getColor();
		Color newColor = JColorChooser.showDialog(getView(), "Choose a new color", oldColor);
		if(newColor != null && !newColor.equals(oldColor)) {
			Container parent = getView().getParent();
			if(parent != null) {
				Activity model = (Activity)getView().getModel();
				for(int i=0 ; i<parent.getComponentCount() ; i++) {
					Activity componentModel = (Activity)((View)parent.getComponent(i)).getModel();
					ActivityRenderingModel componentRenderingModel = ((ActivityRenderingModel)((View)parent.getComponent(i)).getRenderingModel());
					if(componentModel != null && componentRenderingModel != null
							&& componentModel.getAction().equals(model.getAction())
							&& componentModel.getTreatedStructure().equals(model.getTreatedStructure())
							&& componentModel.getUsedInstrument().equals(model.getUsedInstrument()))
					{
						ColorChange command = new ColorChange(componentRenderingModel, newColor);
						((MainWindow) getView().getTopLevelAncestor()).getProcessor().Do(command);
						//componentRenderingModel.setColor(newColor);
					}
				}
			}
		}
	}
}
