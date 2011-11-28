package sequence.ui.component.sequence.subSequence.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.ui.component.activity.ActivityView;
import sequence.utilities.SVGFactory;

public class SubSequenceMenuImageExportController extends Controller implements
		ActionListener {

	public SubSequenceMenuImageExportController(Model model, View view) {
		super(model, view);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			ImageIO.write(getView().createImage(), "png", new File("sequence.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
