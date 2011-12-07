package sequence.ui.component.sequence.subSequence.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
    		boolean correctExt=false;
    		do
    		{
	    		int returnVal = fc.showSaveDialog(getView());
	    		if(returnVal == JFileChooser.CANCEL_OPTION) correctExt=true;
				if(returnVal == JFileChooser.APPROVE_OPTION)
				{
					File f = fc.getSelectedFile();
					int mid = f.getName().lastIndexOf('.') + 1;
					String ext = f.getName().substring(mid);
					
					for(int i=0; i<ImageIO.getWriterFormatNames().length; i++)
					{
						if(ImageIO.getWriterFormatNames()[i].equals(ext))
						{
							correctExt=true;
						}
					}
					if(correctExt)
					{
						ImageIO.write(getView().createImage(), ext, f);
						
						config.setLastOpenedDirectory(fc.getCurrentDirectory());
						try {
							Config.serialize(config);
						}
						catch (java.io.IOException ex) {
							ex.printStackTrace();
						}
					}
					else
					{
						StringBuilder sb=new StringBuilder();
						for(int i=0; i<ImageIO.getWriterFormatNames().length; i++)
						{
							sb.append(ImageIO.getWriterFormatNames()[i].toString());
							sb.append(" ");
						}
						ImageIO.getWriterFormatNames().toString();
						JOptionPane.showMessageDialog(getView(),
								"Supported file types are : "+sb,
								"File type not supported",
								JOptionPane.ERROR_MESSAGE);
					}
				}
    		}while(!correctExt);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
