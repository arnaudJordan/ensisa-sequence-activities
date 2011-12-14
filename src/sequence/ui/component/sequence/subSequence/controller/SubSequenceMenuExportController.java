package sequence.ui.component.sequence.subSequence.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
import sequence.utilities.SVGFactory;
import sequence.utilities.TikzFactory;

public class SubSequenceMenuExportController extends Controller implements
ActionListener {

	public SubSequenceMenuExportController(Model model, View view) {
		super(model, view);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		
		fc.addChoosableFileFilter(new FileNameExtensionFilter("Image File", ImageIO.getWriterFormatNames()));
		fc.addChoosableFileFilter(new FileNameExtensionFilter("Tex (Tikz)", "tex"));
		fc.addChoosableFileFilter(new FileNameExtensionFilter("Vectoriel", "svg"));

		Config config = ((MainWindow) getView().getTopLevelAncestor()).getConfig();
		if(config.getLastOpenedDirectory() !=null)
			fc.setCurrentDirectory(config.getLastOpenedDirectory());

		int returnVal = fc.showSaveDialog(getView());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File f = fc.getSelectedFile();
			config.setLastOpenedDirectory(fc.getCurrentDirectory());
			try {
				Config.serialize(config);
			}
			catch (java.io.IOException ex) {
				ex.printStackTrace();
			}
			String ext= "";
			if(f.getName().contains("."))
			{
				int mid = f.getName().lastIndexOf('.') + 1;
				ext = f.getName().substring(mid);
			}
			else
			{
				try
				{
					ext = ((FileNameExtensionFilter) fc.getFileFilter()).getExtensions()[0];
					String name = fc.getSelectedFile().getPath()+'.'+ext;
					f = new File(name);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			if(f.exists()) {
				int response = JOptionPane.showConfirmDialog(
						fc,
						"The file " + fc.getSelectedFile().getName() + " already exists.\n" +
						"Do you want to replace it?",
						"Overwrite file",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE
				);
				if (response == JOptionPane.NO_OPTION)
					return;
			}
			if(tikzExport(f, ext));
			else if(svgExport(f, ext));
			else if(imageExport(getView().createImage(), ext, f)); 				
			else
				JOptionPane.showMessageDialog(fc, "No correct file selected", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private boolean svgExport(File f, String ext) {
		if(!ext.equalsIgnoreCase("svg")) return false;
		FileWriter fstream;
		try {
			fstream = new FileWriter(f);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(SVGFactory.AddHeader(SVGFactory.SequenceToSVG(getView())));
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean tikzExport(File f, String ext) {
		if(!ext.equalsIgnoreCase("tex")) return false;
		try {
			FileWriter fstream = new FileWriter(f);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(TikzFactory.AddHeader(TikzFactory.SequenceToTikz(getView())));
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean imageExport(BufferedImage createImage, String ext, File f)
	{
		for(int i=0; i<ImageIO.getWriterFormatNames().length; i++)
		{
			if(ImageIO.getWriterFormatNames()[i].equals(ext))
			{
				try {
					ImageIO.write(getView().createImage(), ext, f);
					return true;
				} catch (IOException e) {
					e.printStackTrace();
				}
				return false;
			}
		}
		return false;
	}
}
