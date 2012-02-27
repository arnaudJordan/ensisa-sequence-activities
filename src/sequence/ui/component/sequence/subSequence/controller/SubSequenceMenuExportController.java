package sequence.ui.component.sequence.subSequence.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

/**
 * The Class SubSequenceMenuExportController.
 */
public class SubSequenceMenuExportController extends Controller implements
		ActionListener {

	/**
	 * Instantiates a new sub sequence menu export controller.
	 *
	 * @param model the model
	 * @param view the view
	 */
	public SubSequenceMenuExportController(final Model model, final View view) {
		super(model, view);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(final ActionEvent e) {
		final JFileChooser fc = new JFileChooser();

		fc.addChoosableFileFilter(new FileNameExtensionFilter("Image File",
				ImageIO.getWriterFormatNames()));
		fc.addChoosableFileFilter(new FileNameExtensionFilter("Tex (Tikz)",
				"tex"));
		fc.addChoosableFileFilter(new FileNameExtensionFilter("Vectoriel",
				"svg"));

		final Config config = ((MainWindow) getView().getTopLevelAncestor())
				.getConfig();
		if (config.getLastOpenedDirectory() != null)
			fc.setCurrentDirectory(config.getLastOpenedDirectory());

		final int returnVal = fc.showSaveDialog(getView());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File f = fc.getSelectedFile();
			config.setLastOpenedDirectory(fc.getCurrentDirectory());
			try {
				Config.serialize(config);
			} catch (final java.io.IOException ex) {
				ex.printStackTrace();
			}
			String ext = "";
			if (f.getName().contains(".")) {
				final int mid = f.getName().lastIndexOf('.') + 1;
				ext = f.getName().substring(mid);
			} else {
				try {
					ext = ((FileNameExtensionFilter) fc.getFileFilter())
							.getExtensions()[0];
					final String name = fc.getSelectedFile().getPath() + '.'
							+ ext;
					f = new File(name);
				} catch (final Exception ex) {
					ex.printStackTrace();
				}
			}
			if (f.exists()) {
				final int response = JOptionPane.showConfirmDialog(fc,
						"The file " + fc.getSelectedFile().getName()
								+ " already exists.\n"
								+ "Do you want to replace it?",
						"Overwrite file", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);
				if (response == JOptionPane.NO_OPTION)
					return;
			}
			if (tikzExport(f, ext))
				;
			else if (svgExport(f, ext))
				;
			else if (imageExport(ext, f))
				;
			else
				JOptionPane.showMessageDialog(fc, "No correct file selected",
						"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Svg export.
	 *
	 * @param f the f
	 * @param ext the ext
	 * @return true, if successful
	 */
	private boolean svgExport(final File f, final String ext) {
		if (!ext.equalsIgnoreCase("svg"))
			return false;
		FileWriter fstream;
		try {
			fstream = new FileWriter(f);
			final BufferedWriter out = new BufferedWriter(fstream);
			out.write(SVGFactory.AddHeader(SVGFactory.SequenceToSVG(getView())));
			out.close();
			return true;
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Tikz export.
	 *
	 * @param f the f
	 * @param ext the ext
	 * @return true, if successful
	 */
	private boolean tikzExport(final File f, final String ext) {
		if (!ext.equalsIgnoreCase("tex"))
			return false;
		try {
			final FileWriter fstream = new FileWriter(f);
			final BufferedWriter out = new BufferedWriter(fstream);
			out.write(TikzFactory.AddHeader(TikzFactory
					.SequenceToTikz(getView())));
			out.close();
			return true;
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Image export.
	 *
	 * @param ext the ext
	 * @param f the f
	 * @return true, if successful
	 */
	private boolean imageExport(final String ext, final File f) {
		for (int i = 0; i < ImageIO.getWriterFormatNames().length; i++) {
			if (ImageIO.getWriterFormatNames()[i].equals(ext)) {
				try {
					ImageIO.write(getView().createImage(), ext, f);
					return true;
				} catch (final IOException e) {
					e.printStackTrace();
				}
				return false;
			}
		}
		return false;
	}
}
