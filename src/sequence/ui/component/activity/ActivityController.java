package sequence.ui.component.activity;

import java.awt.Container;
import java.awt.event.MouseEvent;
import sequence.model.activity.Activity;
import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.utilities.ColorFactory;

public class ActivityController extends Controller {

	public ActivityController(Model model, View view) {
		super(model, view);
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == LEFT_MOUSE_BUTTON) {
			Container parent = getView().getParent();
			if(parent != null) {
				Activity model = (Activity)getView().getModel();
				for(int i=0 ; i<parent.getComponentCount() ; i++) {
					Activity componentModel = (Activity)((View)parent.getComponent(i)).getModel();
					ActivityRenderingModel componentRenderingModel = ((ActivityRenderingModel)((View)parent.getComponent(i)).getRenderingModel());
					if(componentModel != null && componentRenderingModel != null
							&& (!componentModel.getAction().equals(model.getAction())
							|| !componentModel.getTreatedStructure().equals(model.getTreatedStructure())
							|| !componentModel.getUsedInstrument().equals(model.getUsedInstrument())))
						componentRenderingModel.setColor(ColorFactory.setAlpha(componentRenderingModel.getColor(), 100));
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
