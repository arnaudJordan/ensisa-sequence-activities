package sequence.ui.component.sequence.subSequence;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.imageio.ImageIO;

import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.ui.component.activity.ActivityRenderingModel;
import sequence.ui.component.activity.ActivityView;
import sequence.utilities.SVGFactory;
import sequence.utilities.TikzFactory;

public class SubSequenceMenuExportController extends Controller implements ActionListener {

	public SubSequenceMenuExportController(Model model, View view) {
		super(model, view);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			ImageIO.write(getView().createImage(), "png", new File("sequence.png"));

			FileWriter fstream = new FileWriter("activity.svg");
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(SVGFactory.AddHeader(SVGFactory.ActivityToSVG((ActivityView) getView().getComponent(0))));
			out.close();
			fstream = new FileWriter("sequence.svg");
			out = new BufferedWriter(fstream);
			out.write(SVGFactory.AddHeader(SVGFactory.SequenceToSVG((SubSequenceView) getView())));
			out.close();
			
			fstream = new FileWriter("sequence.tex");
            out = new BufferedWriter(fstream);
            out.write(TikzFactory.AddHeader(TikzFactory.SequenceToTikz((SubSequenceView) getView())));
            out.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
	}

	public void mouseClicked(MouseEvent e) {

		if(e.getButton() == LEFT_MOUSE_BUTTON) {
			ActivityRenderingModel currentActivityRenderingModel;
			for(int i=0 ; i<getView().getComponentCount() ; i++) {
				currentActivityRenderingModel = ((ActivityRenderingModel)((View)getView().getComponent(i)).getRenderingModel());
            currentActivityRenderingModel.setOpaque();
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
			((SubSequenceView)getView()).getPopup().show(e.getComponent(), e.getX(), e.getY());
		}
	}
}
