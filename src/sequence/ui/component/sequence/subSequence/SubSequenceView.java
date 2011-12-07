package sequence.ui.component.sequence.subSequence;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.ui.component.sequence.subSequence.controller.SubSequenceMenuExportController;
import sequence.ui.component.sequence.subSequence.controller.SubSequenceMenuImageExportController;
import sequence.ui.component.sequence.subSequence.controller.SubSequenceMenuInfoController;
import sequence.ui.component.sequence.subSequence.controller.SubSequenceMenuSVGExportController;
import sequence.ui.component.sequence.subSequence.controller.SubSequenceMenuTikzExportController;

public class SubSequenceView extends View {
	private static final long serialVersionUID = 1L;
	private Container parent;
	private JPopupMenu popup;

	public SubSequenceView(Model model, Container parent) {
		super(model);
		this.parent = parent;
		setRenderingModel(new SubSequenceRenderingModel());
		setRenderer(new SubSequenceRenderer(this));
		popup = new JPopupMenu();
		JMenu exportMenu = new JMenu("Export");
		JMenuItem imageExportMenu = new JMenuItem("to image");
		imageExportMenu.addActionListener(new SubSequenceMenuImageExportController(getModel(), this));
		exportMenu.add(imageExportMenu);
		
		JMenuItem svgExportMenu = new JMenuItem("to svg");
		svgExportMenu.addActionListener(new SubSequenceMenuSVGExportController(getModel(), this));
		exportMenu.add(svgExportMenu);
		
		JMenuItem tikzExportMenu = new JMenuItem("to tikz");
		tikzExportMenu.addActionListener(new SubSequenceMenuTikzExportController(getModel(), this));
		exportMenu.add(tikzExportMenu);
		
		popup.add(exportMenu);
		
	    JMenuItem infoItem = new JMenuItem("Info");
	    infoItem.addActionListener(new SubSequenceMenuInfoController(getModel(), this));
	    popup.add(infoItem);
	}
	
	public Container getParent() {
		return parent;
	}
	
	public JPopupMenu getPopup() {
		return popup;
	}
	
	public void modelChanged(Model m) {
		removeAll();
		((SubSequenceRenderer)getRenderer()).initialize();
	}
}
