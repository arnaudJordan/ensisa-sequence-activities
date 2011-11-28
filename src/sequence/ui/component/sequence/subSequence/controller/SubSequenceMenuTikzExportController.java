package sequence.ui.component.sequence.subSequence.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.ui.component.sequence.subSequence.SubSequenceView;
import sequence.utilities.TikzFactory;

public class SubSequenceMenuTikzExportController extends Controller implements
		ActionListener {

	public SubSequenceMenuTikzExportController(Model model, View view) {
		super(model, view);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        try {
        	FileWriter fstream = new FileWriter("sequence.tex");
        	BufferedWriter out = new BufferedWriter(fstream);
			out.write(TikzFactory.AddHeader(TikzFactory.SequenceToTikz((SubSequenceView) getView())));
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
