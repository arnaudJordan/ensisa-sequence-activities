package sequence.ui.component.sequence.summarizedSequence;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.processor.Command;
import sequence.processor.DisplaySubSequence;
import sequence.ui.component.activity.ActivityRenderer;
import sequence.ui.component.activity.ActivityView;
import sequence.ui.component.sequence.SequenceContainer;
import sequence.ui.window.MainWindow;
import sequence.utilities.BorderBackgroundDrawer;

public class SummarizedSequenceController extends Controller implements ActionListener  {
	
	public SummarizedSequenceController(Model model, View view) {
		super(model, view);
	}	

	public void actionPerformed(ActionEvent e) {
		Command command = new DisplaySubSequence((SequenceContainer) getView().getParent());
		((MainWindow) getView().getTopLevelAncestor()).getProcessor().Do(command);
	}

	public void mousePressed(MouseEvent e) {
		checkPopup(e);
	}
	 
	public void mouseReleased(MouseEvent e) {
		checkPopup(e);
	}
	
	protected void checkPopup(MouseEvent e) {
		if (e.getButton() == RIGHT_MOUSE_BUTTON && e.isPopupTrigger()) {
			((SummarizedSequenceView)getView()).getPopup().show(e.getComponent(), e.getX(), e.getY());
		}
	}
}
