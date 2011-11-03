package sequence.ui.component.activity;

import java.awt.Container;
import java.awt.event.MouseEvent;
import sequence.model.activity.Activity;
import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;

public class ActivityController extends Controller {

	public ActivityController(Model model, View view) {
		super(model, view);
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == LEFT_MOUSE_BUTTON) {
			Container parent = getView().getParent();
			if(parent != null) {
				ActivityRenderingModel clickedActivityRenderingModel = (ActivityRenderingModel)getView().getRenderingModel();
				if(clickedActivityRenderingModel.isTransparent()) {
					for(int i=0 ; i<parent.getComponentCount() ; i++) {
						ActivityRenderingModel currentActivityRenderingModel = ((ActivityRenderingModel)((View)parent.getComponent(i)).getRenderingModel());
						currentActivityRenderingModel.setOpaque();
					}
				} else {
					Activity clickedActivity = (Activity)getView().getModel();
					for(int i=0 ; i<parent.getComponentCount() ; i++) {
						Activity currentActivity = (Activity)((View)parent.getComponent(i)).getModel();
						ActivityRenderingModel currentActivityRenderingModel = ((ActivityRenderingModel)((View)parent.getComponent(i)).getRenderingModel());
						if(currentActivity != null && currentActivityRenderingModel != null
								&& (!currentActivity.getAction().equals(clickedActivity.getAction())
										|| !currentActivity.getTreatedStructure().equals(clickedActivity.getTreatedStructure())
										|| !currentActivity.getUsedInstrument().equals(clickedActivity.getUsedInstrument())))
							currentActivityRenderingModel.setTransparent();
					}
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
