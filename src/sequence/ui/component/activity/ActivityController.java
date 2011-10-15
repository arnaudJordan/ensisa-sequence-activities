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
		if(e.getButton() == LEFT_MOUSE_BUTTON) {
			ActivityRenderingModel renderingModel = ((ActivityRenderingModel)(((ActivityView)getView()).getRenderingModel()));
			Color newColor = JColorChooser.showDialog(getView(), "Choose a new color", renderingModel.getColor());
			if(newColor!= null && newColor != renderingModel.getColor())
				renderingModel.setColor(newColor);
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
