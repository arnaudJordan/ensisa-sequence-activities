package sequence.ui.component.activity;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;

public class ActivityMenuController extends Controller {
	
	public ActivityMenuController(Model model, View view) {
		super(model, view);
	}

	public void mousePressed(MouseEvent e) {
        maybeShowPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
        maybeShowPopup(e);
    }

    private void maybeShowPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
        	((ActivityView)getView()).popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }

}
