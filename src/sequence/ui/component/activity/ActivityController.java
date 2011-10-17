package sequence.ui.component.activity;

import java.awt.Color;
import java.awt.Container;
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
		if(e.getButton() == LEFT_MOUSE_BUTTON) {
			ActivityRenderingModel renderingModel = ((ActivityRenderingModel)(((ActivityView)getView()).getRenderingModel()));
			Color oldColor = renderingModel.getColor();
			Color newColor = JColorChooser.showDialog(getView(), "Choose a new color", oldColor);
			if(newColor!= null && !newColor.equals(oldColor))
				renderingModel.setColor(newColor);
			Container parent = getView().getParent();
			if(parent != null) {
				for(int i=0 ; i<parent.getComponentCount() ; i++) {
					ActivityRenderingModel componentRenderingModel = ((ActivityRenderingModel)((View)parent.getComponent(i)).getRenderingModel());
					if(componentRenderingModel != null && componentRenderingModel.getColor().equals(oldColor))
						componentRenderingModel.setColor(newColor);
				}
			}
		}
	}
	
	public void mousePressed(MouseEvent e) {
		checkPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
    	checkPopup(e);
    }

    private void checkPopup(MouseEvent e) {
        if (e.getButton() == RIGHT_MOUSE_BUTTON && e.isPopupTrigger()) {
        	((ActivityView)getView()).popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }
}
