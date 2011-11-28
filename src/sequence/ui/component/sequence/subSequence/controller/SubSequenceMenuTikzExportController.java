package sequence.ui.component.sequence.subSequence.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

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
        	final JFileChooser fc = new JFileChooser();
        	FileNameExtensionFilter filter= new FileNameExtensionFilter("Tex file", "tex");
        	fc.setFileFilter(filter);
        	
        	int returnVal = fc.showSaveDialog(getView());
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				FileWriter fstream = new FileWriter(fc.getSelectedFile());
				BufferedWriter out = new BufferedWriter(fstream);
				out.write(TikzFactory.AddHeader(TikzFactory.SequenceToTikz((SubSequenceView) getView())));
				out.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
