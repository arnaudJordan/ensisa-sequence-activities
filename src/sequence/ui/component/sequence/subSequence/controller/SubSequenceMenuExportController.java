package sequence.ui.component.sequence.subSequence.controller;

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
import sequence.ui.component.sequence.subSequence.SubSequenceView;
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
}
