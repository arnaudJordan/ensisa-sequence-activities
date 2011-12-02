package sequence.ui.component.sequence.subSequence.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import sequence.mvc.Controller;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.ui.window.MainWindow;
import sequence.utilities.Config;

public class SubSequenceMenuImageExportController extends Controller implements
		ActionListener {

	public SubSequenceMenuImageExportController(Model model, View view) {
		super(model, view);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			final JFileChooser fc = new JFileChooser();
        	FileNameExtensionFilter filter= new FileNameExtensionFilter("Image File", ImageIO.getWriterFormatNames());
        	fc.setFileFilter(filter);
        	
        	Config config = ((MainWindow) getView().getTopLevelAncestor()).getConfig();
    		if(config.getLastOpenedDirectory() !=null)
    			fc.setCurrentDirectory(config.getLastOpenedDirectory());
    		fc.setMultiSelectionEnabled(true);
    		int returnVal = fc.showSaveDialog(getView());
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				config.setLastOpenedDirectory(fc.getCurrentDirectory());
				try {
					Config.serialize(config);
				}
				catch (java.io.IOException ex) {
					ex.printStackTrace();
				}
				File f = fc.getSelectedFile();
				int mid = f.getName().lastIndexOf('.') + 1;
				String ext = f.getName().substring(mid);
				
				ImageIO.write(getView().createImage(), ext, f);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
