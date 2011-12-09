package sequence.ui.component.sequence.summarizedSequence;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import sequence.model.activity.Activity;
import sequence.mvc.Model;
import sequence.mvc.View;
import sequence.ui.component.sequence.subSequence.controller.SubSequenceMenuExportController;

public class SummarizedSequenceView extends View {
	private static final long serialVersionUID = 1L;
	private Container parent;
	private List<Activity> selectedActivities;
	private JPopupMenu popup;

	public SummarizedSequenceView(Model model, Container parent) {
		super(model);
		this.parent = parent;
		this.selectedActivities = new ArrayList<Activity>();		
		setRenderer(new SummarizedSequenceRenderer(this));
		popup = new JPopupMenu();
	    JMenuItem exportItem = new JMenuItem("Develop");
	    exportItem.addActionListener(new SummarizedSequenceController(getModel(), this));
	    
	    JMenuItem exportMenu = new JMenuItem("Export");
	    exportMenu.addActionListener(new SubSequenceMenuExportController(getModel(), this));
		/*JMenuItem imageExportMenu = new JMenuItem("to image");
		imageExportMenu.addActionListener(new SubSequenceMenuImageExportController(getModel(), this));
		exportMenu.add(imageExportMenu);
		
		JMenuItem svgExportMenu = new JMenuItem("to svg");
		svgExportMenu.addActionListener(new SubSequenceMenuSVGExportController(getModel(), this));
		exportMenu.add(svgExportMenu);
		
		JMenuItem tikzExportMenu = new JMenuItem("to tikz");
		tikzExportMenu.addActionListener(new SubSequenceMenuTikzExportController(getModel(), this));
		exportMenu.add(tikzExportMenu);*/
		
		popup.add(exportMenu);
	    
	    popup.add(exportItem);
	}
	
	public JPopupMenu getPopup() {
		return popup;
	}

	public Container getParent() {
		return parent;
	}

	public List<Activity> getSelectedActivities() {
		return selectedActivities;
	}
}
