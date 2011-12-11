package sequence.ui.component.sequence.subSequence.controller;

import java.awt.event.MouseEvent;

import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.ui.component.activity.ActivityView;
import sequence.ui.component.sequence.subSequence.SubSequenceView;

public class SubSequenceController extends Controller {
	
	public SubSequenceController(Model model, View view) {
		super(model, view);
	}	

	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == LEFT_MOUSE_BUTTON) {
			for(int i=0 ; i<getView().getComponentCount() ; i++)
				((ActivityView) getView().getComponent(i)).deselect();
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
			((SubSequenceView)getView()).getPopup().show(e.getComponent(), e.getX(), e.getY());
		}
	}
}
