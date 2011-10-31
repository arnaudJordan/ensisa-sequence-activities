package sequence.ui.component.sequence;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.imageio.ImageIO;

import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;

public class SequenceController extends Controller implements ActionListener {

	public SequenceController(Model model, View view) {
		super(model, view);
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			ImageIO.write(getView().createImage(), "png", new File("D:/User/test.png"));
		} catch (Exception ex) {
			ex.printStackTrace();
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
        	((SequenceView)getView()).getPopup().show(e.getComponent(), e.getX(), e.getY());
        }
    }
}
